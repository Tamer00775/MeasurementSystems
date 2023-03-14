package kz.kartayev.Project3.DTO;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class SensorDTO {
    @Column(name="name")
    @NotEmpty(message = "name should be not empty!")
    @Size(min = 2, max = 30,  message = "Name characters should be greater or equal than 2")
    private String name;

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
}
