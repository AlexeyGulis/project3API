package by.gulis.project3API.repositories;

import by.gulis.project3API.models.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SensorsRepository extends JpaRepository<Sensor,Integer> {
    public List<Sensor> findByName(String name);
}
