package kz.kartayev.Project3.DTO;

import kz.kartayev.Project3.models.Sensor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class MeasurementDTO {

    @NotNull
    @Min(value = -100, message = "value should be in range -100 and 100")
    @Max(value =  100, message = "value should be in range -100 and 100")
    private Double value;

    @NotNull(message = "raining should be not empty")
    private Boolean isRaining;


    @NotNull(message = "SENSOR SHOULD BE NOT NULL")
    private Sensor sensor;

    public double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Boolean getRaining() {
        return isRaining;
    }

    public void setRaining(Boolean raining) {
        isRaining = raining;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }
}
