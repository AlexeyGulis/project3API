package by.gulis.project3API.controllers;


import by.gulis.project3API.DTO.SensorDTO;
import by.gulis.project3API.models.Sensor;
import by.gulis.project3API.services.SensorsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/sensors")
public class SensorsController {
    private SensorsService sensorsService;

    private ModelMapper modelMapper;
    @Autowired
    public SensorsController(SensorsService sensorsService, ModelMapper modelMapper) {
        this.sensorsService = sensorsService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<SensorDTO> showSensors(){
        return sensorsService.findAll().stream().map(this::convertToSensorDTO).collect(Collectors.toList());
    }

    private SensorDTO convertToSensorDTO(Sensor sensor){
        SensorDTO sensorDTO = modelMapper.map(sensor, SensorDTO.class);
        return sensorDTO;
    }
}
