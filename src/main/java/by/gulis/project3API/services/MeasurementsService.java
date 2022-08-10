package by.gulis.project3API.services;

import by.gulis.project3API.models.Measurement;
import by.gulis.project3API.repositories.MeasurementsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class MeasurementsService {
    private MeasurementsRepository measurementsRepository;

    @Autowired
    public MeasurementsService(MeasurementsRepository measurementsRepository) {
        this.measurementsRepository = measurementsRepository;
    }

    public List<Measurement> findAll(){
        return measurementsRepository.findAll();
    }

    public void save(Measurement measurement){
        measurementsRepository.save(measurement);
    }
}
