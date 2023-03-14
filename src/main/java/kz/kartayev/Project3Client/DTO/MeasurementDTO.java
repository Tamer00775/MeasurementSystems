package kz.kartayev.Project3Client.DTO;

import kz.kartayev.Project3.models.Sensor;

public class MeasurementDTO {

    private Double value;

    private Boolean isRaining;

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
