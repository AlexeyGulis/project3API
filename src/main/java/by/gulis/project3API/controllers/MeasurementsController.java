package by.gulis.project3API.controllers;


import by.gulis.project3API.models.Measurement;
import by.gulis.project3API.services.MeasurementsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/measurements")
public class MeasurementsController {
    private MeasurementsService measurementsService;

    @Autowired
    public MeasurementsController(MeasurementsService measurementsService) {
        this.measurementsService = measurementsService;
    }

    @GetMapping
    public List<Measurement> showMeasurements(){
        return measurementsService.findAll();
    }
}
