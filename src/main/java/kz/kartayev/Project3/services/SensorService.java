package kz.kartayev.Project3.services;

import kz.kartayev.Project3.models.Sensor;
import kz.kartayev.Project3.repository.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SensorService {
    private final SensorRepository sensorRepository;
    @Autowired
    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    public Optional<Sensor> findByName(String s){
        return Optional.ofNullable(sensorRepository.findByName(s));
    }
    @Transactional
    public void save(Sensor sensor){
        sensorRepository.save(sensor);
    }
    public List<Sensor> findAll(){
        return sensorRepository.findAll();
    }
}
