package kz.kartayev.Project3.controllers;

import kz.kartayev.Project3.DTO.MeasurementDTO;
import kz.kartayev.Project3.DTO.SensorDTO;
import kz.kartayev.Project3.models.Measurement;
import kz.kartayev.Project3.models.Sensor;
import kz.kartayev.Project3.services.SensorService;
import kz.kartayev.Project3.util.ErrorUtil;
import kz.kartayev.Project3.util.SensorErrorResponse;
import kz.kartayev.Project3.util.SensorNotCreatedException;
import kz.kartayev.Project3.util.SensorValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static kz.kartayev.Project3.util.ErrorUtil.getFieldErrors;
@RestController
@RequestMapping("/sensors")
public class SensorController {
    private final ModelMapper modelMapper;
    private final SensorService sensorService;
    private final SensorValidator validator;
    @Autowired
    public SensorController(ModelMapper modelMapper, SensorService sensorService, SensorValidator validator) {
        this.modelMapper = modelMapper;
        this.sensorService = sensorService;
        this.validator = validator;
    }

    @GetMapping()
    public List<Sensor> sensorList(){
        return sensorService.findAll();
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> registration(@RequestBody @Valid SensorDTO sensorDTO, BindingResult bindingResult)  {
        Sensor sensorToadd = convertToSensor(sensorDTO);
        validator.validate(sensorToadd, bindingResult);
        if(bindingResult.hasErrors()){
            getFieldErrors(bindingResult);
        }
        sensorService.save(sensorToadd);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    public Sensor convertToSensor(SensorDTO sensorDTO){
        return modelMapper.map(sensorDTO, Sensor.class);
    }

    @ExceptionHandler
    public ResponseEntity<SensorErrorResponse> handleException(SensorNotCreatedException sensorNotCreatedException){
        SensorErrorResponse response = new SensorErrorResponse(sensorNotCreatedException.getMessage() , System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
