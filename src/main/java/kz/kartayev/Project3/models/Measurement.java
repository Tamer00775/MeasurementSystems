package kz.kartayev.Project3.models;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name="measurements")
public class Measurement {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="value")
    @NotNull(message = "Should be not null")
    @Min(value = -100, message = "value should be in range -100 and 100")
    @Max(value =  100, message = "value should be in range -100 and 100")
    private double value;

    @Column(name="raining")
    @NotNull(message = "raining should be not empty")
    private Boolean raining;
    @Column(name="measurement_time")
    @NotNull(message = "Should be not null")
    private LocalDate localDate;

    @NotNull(message = "Should be not null")
    @ManyToOne
    @JoinColumn(name="sensor", referencedColumnName = "name")
    private Sensor sensor;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public boolean isRaining() {
        return raining;
    }

    public void setRaining(boolean raining) {
        this.raining = raining;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }
}
