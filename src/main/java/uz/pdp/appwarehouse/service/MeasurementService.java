package uz.pdp.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.entity.Measurement;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.repo.MeasurementRepo;

import java.util.Optional;

@Service
public class MeasurementService {

    @Autowired
    MeasurementRepo measurementRepo;


    public Result addMeasurement(Measurement measurement) {
        boolean existsByName = measurementRepo.existsByName(measurement.getName());
        if (existsByName)
            return new Result("This measurement is already exists", false);
        measurementRepo.save(measurement);
        return new Result("Successfully saved", true);
    }

    public Result getMeasurements() {
        return new Result("Successfully", true, measurementRepo.findAll());
    }

    public Result getMeasurementById(Integer id) {
        Optional<Measurement> optionalMeasurement = measurementRepo.findById(id);
        if (optionalMeasurement.isEmpty())
            return new Result("Such a measurement not found", false);
        return new Result("Successfully", true, optionalMeasurement.get());
    }

    public Result editMeasurementById(Integer id, Measurement measurement1) {
        Optional<Measurement> optionalMeasurement = measurementRepo.findById(id);
        if (optionalMeasurement.isEmpty())
            return new Result("Such measurement not found", false);
        Measurement measurement = optionalMeasurement.get();
        boolean existsByName = measurementRepo.existsByName(measurement1.getName());
        if (existsByName)
            return new Result("This measurement is already exists", false);
        measurement.setName(measurement1.getName());
        measurementRepo.save(measurement);
        return new Result("Successfully edited", true);
    }

    public Result deleteMeasurementById(Integer id) {
        Optional<Measurement> optionalMeasurement = measurementRepo.findById(id);
        if (optionalMeasurement.isEmpty())
            return new Result("Such a measurement not found", false);
        measurementRepo.deleteById(id);
        return new Result("Successfully deleted", true);
    }
}
