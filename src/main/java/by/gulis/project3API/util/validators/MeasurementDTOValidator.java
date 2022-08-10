package by.gulis.project3API.util.validators;

import by.gulis.project3API.DTO.SensorDTO;
import by.gulis.project3API.services.SensorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class MeasurementDTOValidator implements Validator {
    private final SensorsService sensorsService;

    @Autowired
    public MeasurementDTOValidator(SensorsService sensorsService) {
        this.sensorsService = sensorsService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return SensorDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SensorDTO sensorDTO = (SensorDTO) target;
        if(!sensorsService.findByName(sensorDTO.getName())){
            errors.rejectValue("name","","Сенсора с таким названием не существует");
        }
    }
}
