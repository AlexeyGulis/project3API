package by.gulis.project3API.controllers;


import by.gulis.project3API.DTO.SensorDTO;
import by.gulis.project3API.models.Sensor;
import by.gulis.project3API.services.SensorsService;
import by.gulis.project3API.util.ErrorResponse;
import by.gulis.project3API.util.exceptions.SensorNotCreatedException;
import by.gulis.project3API.util.validators.SensorDTOValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/sensors")
public class SensorsController {
    private SensorsService sensorsService;
    private SensorDTOValidator sensorDTOValidator;
    private ModelMapper modelMapper;
    @Autowired
    public SensorsController(SensorsService sensorsService, ModelMapper modelMapper, SensorDTOValidator sensorDTOValidator) {
        this.sensorsService = sensorsService;
        this.modelMapper = modelMapper;
        this.sensorDTOValidator = sensorDTOValidator;
    }

    @GetMapping
    public List<SensorDTO> showSensors(){
        return sensorsService.findAll().stream().map(this::convertToSensorDTO).collect(Collectors.toList());
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> registrationSensor(@RequestBody @Valid SensorDTO sensorDTO, BindingResult bindingResult){
        sensorDTOValidator.validate(sensorDTO,bindingResult);
        StringBuilder stringBuilder = new StringBuilder();
        if(bindingResult.hasErrors()){
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError e : errors
                 ) {
                stringBuilder.append(e.getField()).append(" - ").append(e.getDefaultMessage()).append(";");
            }
            throw new SensorNotCreatedException(stringBuilder.toString());
        }
        sensorsService.save(convertToSensor(sensorDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException(SensorNotCreatedException e){
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage(),System.currentTimeMillis());
        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }

    private SensorDTO convertToSensorDTO(Sensor sensor){
        return modelMapper.map(sensor, SensorDTO.class);
    }
    private Sensor convertToSensor(SensorDTO sensorDTO){
        return modelMapper.map(sensorDTO, Sensor.class);
    }
}
