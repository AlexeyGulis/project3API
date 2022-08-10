package by.gulis.project3API.util.validators;

import by.gulis.project3API.DTO.SensorDTO;
import by.gulis.project3API.models.Sensor;
import by.gulis.project3API.services.SensorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
@Component
public class SensorDTOValidator implements Validator {
    private final SensorsService sensorsService;

    @Autowired
    public SensorDTOValidator(SensorsService sensorsService) {
        this.sensorsService = sensorsService;
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return SensorDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SensorDTO sensorDTO = (SensorDTO) target;
        if(sensorsService.findByName(sensorDTO.getName())){
            errors.rejectValue("name","","Сенсор с таким названием уже существует");
        }
    }
}
