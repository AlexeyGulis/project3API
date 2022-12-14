package by.gulis.project3API.services;

import by.gulis.project3API.models.Sensor;
import by.gulis.project3API.repositories.SensorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class SensorsService {
    private SensorsRepository sensorsRepository;

    @Autowired
    public SensorsService(SensorsRepository sensorsRepository) {
        this.sensorsRepository = sensorsRepository;
    }

    public List<Sensor> findAll(){
        return sensorsRepository.findAll();
    }

    @Transactional
    public void save(Sensor sensor){
        sensorsRepository.save(sensor);
    }

    public Boolean findByName(String name){
        if(sensorsRepository.findByName(name).isEmpty())
            return false;
        else return true;
    }
    public Integer findByNameAndGetId(String name){
           return sensorsRepository.findByName(name).get(0).getId();
    }
}
