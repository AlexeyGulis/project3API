package by.gulis.project3API.controllers;


import by.gulis.project3API.DTO.MeasurementDTO;
import by.gulis.project3API.DTO.SensorDTO;
import by.gulis.project3API.models.Measurement;
import by.gulis.project3API.models.Sensor;
import by.gulis.project3API.services.MeasurementsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/measurements")
public class MeasurementsController {
    private MeasurementsService measurementsService;

    private ModelMapper modelMapper;

    @Autowired
    public MeasurementsController(MeasurementsService measurementsService, ModelMapper modelMapper) {
        this.measurementsService = measurementsService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<MeasurementDTO> showMeasurements(){
        return measurementsService.findAll().stream().map(this::convertToMeasurementDTO).collect(Collectors.toList());
    }

    private MeasurementDTO convertToMeasurementDTO(Measurement measurement){
        MeasurementDTO measurementDTO = modelMapper.map(measurement, MeasurementDTO.class);
        if(measurement.getOwner() != null) measurementDTO.setSensorDTO(convertToSensorDTO(measurement.getOwner()));
        return measurementDTO;
    }

    private SensorDTO convertToSensorDTO(Sensor sensor){
        return modelMapper.map(sensor, SensorDTO.class);
    }
}
