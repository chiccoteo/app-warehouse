package uz.pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.entity.Measurement;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.service.MeasurementService;

@RestController
@RequestMapping("/measurement")
public class MeasurementController {

    @Autowired
    MeasurementService measurementService;


    @PostMapping
    public Result addMeasurement(@RequestBody Measurement measurement) {
        return measurementService.addMeasurement(measurement);
    }

    @GetMapping
    public Result getMeasurements() {
        return measurementService.getMeasurements();
    }

    @GetMapping("/{id}")
    public Result getMeasurementById(@PathVariable Integer id) {
        return measurementService.getMeasurementById(id);
    }

    @PutMapping("/{id}")
    public Result editMeasurementById(@PathVariable Integer id, @RequestBody Measurement measurement) {
        return measurementService.editMeasurementById(id, measurement);
    }

    @DeleteMapping("/{id}")
    public Result deleteMeasurementById(@PathVariable Integer id) {
        return measurementService.deleteMeasurementById(id);
    }
}
