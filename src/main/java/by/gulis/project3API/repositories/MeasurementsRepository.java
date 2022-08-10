package by.gulis.project3API.repositories;

import by.gulis.project3API.models.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeasurementsRepository extends JpaRepository<Measurement,Integer> {
}
