package kz.kartayev.Project3.repository;

import kz.kartayev.Project3.models.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Integer> {

    public Sensor findByName(String s);

}
