package kz.kartayev.Project3Client.DTO;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class SensorDTO {
    private String name;

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
}

