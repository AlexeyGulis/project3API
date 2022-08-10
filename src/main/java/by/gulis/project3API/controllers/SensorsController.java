package by.gulis.project3API.controllers;


import by.gulis.project3API.models.Sensor;
import by.gulis.project3API.services.SensorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sensors")
public class SensorsController {
    private SensorsService sensorsService;
    @Autowired
    public SensorsController(SensorsService sensorsService) {
        this.sensorsService = sensorsService;
    }

    @GetMapping
    public List<Sensor> showSensors(){
        return sensorsService.findAll();
    }
}
