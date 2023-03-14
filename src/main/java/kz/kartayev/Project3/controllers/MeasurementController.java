package kz.kartayev.Project3.controllers;

import kz.kartayev.Project3.DTO.MeasurementDTO;
import kz.kartayev.Project3.DTO.MeasurementsResponse;
import kz.kartayev.Project3.models.Measurement;
import kz.kartayev.Project3.models.Sensor;
import kz.kartayev.Project3.services.MeasurementService;
import kz.kartayev.Project3.services.SensorService;
import kz.kartayev.Project3.util.MeasurementError;
import kz.kartayev.Project3.util.MeasurementErrorResponse;
import kz.kartayev.Project3.util.MeasurementValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static kz.kartayev.Project3.util.ErrorUtil.getFieldErrors;

@RestController
@RequestMapping("/measurements")
public class MeasurementController {
    private final ModelMapper modelMapper;
    private final MeasurementService measurementService;
    private final MeasurementValidator validator;
    private final SensorService sensorService;
    @Autowired
    public MeasurementController(ModelMapper modelMapper, MeasurementService measurementService, MeasurementValidator validator, SensorService sensorService) {
        this.modelMapper = modelMapper;
        this.measurementService = measurementService;
        this.validator = validator;
        this.sensorService = sensorService;
    }
    @GetMapping
    public List<MeasurementDTO> index(){
        List<Measurement> measurements = measurementService.findAll();
        List<MeasurementDTO> dto = new ArrayList<>();
        for(int i = 0; i < measurements.size(); i++){
            dto.add(convertToDTO(measurements.get(i)));
        }
        return dto;
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> add(@RequestBody @Valid MeasurementDTO measurementDTO, BindingResult bindingResult){
        Measurement measurement = convertToMeasurement(measurementDTO);
        validator.validate(measurement, bindingResult);
        if(bindingResult.hasErrors()) {
            getFieldErrors(bindingResult);
        }
        measurementService.save(measurement);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @GetMapping("/rainyDaysCount")
    public Long counter(){
        return measurementService.findAll().stream().filter(Measurement::isRaining).count();
    }
    public Measurement convertToMeasurement(MeasurementDTO measurementDTO){
        return modelMapper.map(measurementDTO, Measurement.class);
    }

    public MeasurementDTO convertToDTO(Measurement measurement){
        return modelMapper.map(measurement, MeasurementDTO.class);
    }

    @ExceptionHandler
    public ResponseEntity<MeasurementErrorResponse> handleException(MeasurementError error){
            MeasurementErrorResponse response = new MeasurementErrorResponse(error.getMessage(), System.currentTimeMillis());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
