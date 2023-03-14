package kz.kartayev.Project3.DTO;

import kz.kartayev.Project3.models.Sensor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class MeasurementDTO {

    @Column(name="value")
    @NotNull
    @Min(value = -100, message = "value should be in range -100 and 100")
    @Max(value =  100, message = "value should be in range -100 and 100")
    private double value;

    @Column(name="raining")
    @NotNull(message = "raining should be not empty")
    private Boolean isRaining;

    @ManyToOne

    @NotNull(message = "SENSOR SHOULD BE NOT NULL")
    @JoinColumn(name="sensor", referencedColumnName = "sensor_name")
    private Sensor sensor;

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Boolean getRaining() {
        return isRaining;
    }

    public void setRaining(boolean raining) {
        isRaining = raining;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }
}
