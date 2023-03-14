package kz.kartayev.Project3.util;

import kz.kartayev.Project3.models.Measurement;
import kz.kartayev.Project3.models.Sensor;
import kz.kartayev.Project3.services.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;
@Component
public class MeasurementValidator implements Validator {
    private final SensorService sensorService;
    @Autowired
    public MeasurementValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Measurement.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Measurement measurement = (Measurement) target;
        if(measurement.getSensor() == null){
            errors.rejectValue("sensor", "", "This sensor does not exist!");
        }
        if (sensorService.findByName(measurement.getSensor().getName()).isEmpty())
            errors.rejectValue("sensor", "", "This sensor does not exist");
    }
}
