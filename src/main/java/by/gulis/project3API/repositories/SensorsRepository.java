package by.gulis.project3API.repositories;

import by.gulis.project3API.models.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SensorsRepository extends JpaRepository<Sensor,Integer> {
}
