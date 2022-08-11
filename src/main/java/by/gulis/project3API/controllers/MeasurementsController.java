package by.gulis.project3API.controllers;


import by.gulis.project3API.DTO.MeasurementDTO;
import by.gulis.project3API.DTO.SensorDTO;
import by.gulis.project3API.models.Measurement;
import by.gulis.project3API.models.Sensor;
import by.gulis.project3API.services.MeasurementsService;
import by.gulis.project3API.services.SensorsService;
import by.gulis.project3API.util.ErrorResponse;
import by.gulis.project3API.util.exceptions.MeasurementNotCreatedException;
import by.gulis.project3API.util.validators.MeasurementDTOValidator;
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
@RequestMapping("/measurements")
public class MeasurementsController {

    private MeasurementDTOValidator validator;
    private MeasurementsService measurementsService;
    private SensorsService sensorsService;
    private ModelMapper modelMapper;

    @Autowired
    public MeasurementsController(MeasurementsService measurementsService,
                                  SensorsService sensorsService,
                                  ModelMapper modelMapper,
                                  MeasurementDTOValidator validator) {
        this.measurementsService = measurementsService;
        this.modelMapper = modelMapper;
        this.sensorsService = sensorsService;
        this.validator = validator;
    }

    @GetMapping
    public List<MeasurementDTO> showMeasurements(){
        return measurementsService.findAll().stream().map(this::convertToMeasurementDTO).collect(Collectors.toList());
    }

    @GetMapping("/rainyDaysCount")
    public Integer showRainyDaysCount(){
        return measurementsService.countRainingDays();
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addMeasurement(@RequestBody @Valid MeasurementDTO measurementDTO, BindingResult bindingResult){
        validator.validate(measurementDTO.getSensor(),bindingResult);
        if(bindingResult.hasErrors()){
            List<FieldError> errors = bindingResult.getFieldErrors();
            StringBuilder str = new StringBuilder();
            for (FieldError e : errors
                 ) {
                str.append(e.getField())
                        .append(" - ")
                        .append(e.getDefaultMessage())
                        .append(";");
            }
            throw new MeasurementNotCreatedException(str.toString());
        }
        measurementsService.save(convertToMeasurement(measurementDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException(MeasurementNotCreatedException e){
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage(),System.currentTimeMillis());
        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }
    private MeasurementDTO convertToMeasurementDTO(Measurement measurement){
        MeasurementDTO measurementDTO = modelMapper.map(measurement, MeasurementDTO.class);
        if(measurement.getOwner() != null) measurementDTO.setSensor(convertToSensorDTO(measurement.getOwner()));
        return measurementDTO;
    }

    private Measurement convertToMeasurement(MeasurementDTO measurementDTO){
        Measurement measurement = modelMapper.map(measurementDTO, Measurement.class);
        measurement.setOwner(convertToSensor(measurementDTO.getSensor()));
        measurement.getOwner().setId(sensorsService.findByNameAndGetId(measurementDTO.getSensor().getName()));
        return measurement;
    }
    private SensorDTO convertToSensorDTO(Sensor sensor){
        return modelMapper.map(sensor, SensorDTO.class);
    }
    private Sensor convertToSensor(SensorDTO sensorDTO){
        return modelMapper.map(sensorDTO, Sensor.class);
    }
}
