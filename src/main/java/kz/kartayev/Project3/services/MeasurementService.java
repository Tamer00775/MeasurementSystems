package kz.kartayev.Project3.services;

import kz.kartayev.Project3.models.Measurement;
import kz.kartayev.Project3.repository.MeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class MeasurementService {
    private final MeasurementRepository measurementRepository;
    private final SensorService sensorService;
    @Autowired
    public MeasurementService(MeasurementRepository measurementRepository, SensorService sensorService) {
        this.measurementRepository = measurementRepository;
        this.sensorService = sensorService;
    }
    @Transactional
    public void save(Measurement measurement){
        measurement.setLocalDate(LocalDate.now());
        measurementRepository.save(measurement);
    }

    public List<Measurement> findAll(){
        return measurementRepository.findAll();
    }
}
