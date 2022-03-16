package uz.pdp.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.entity.Warehouse;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.repo.WarehouseRepo;

import java.util.Optional;

@Service
public class WarehouseService {

    @Autowired
    WarehouseRepo warehouseRepo;


    public Result addWarehouse(Warehouse warehouse) {
        boolean existsByName = warehouseRepo.existsByName(warehouse.getName());
        if (existsByName)
            return new Result("This warehouse is already exists", false);
        warehouseRepo.save(warehouse);
        return new Result("Successfully saved", true);
    }

    public Result getWarehouses() {
        return new Result("Successfully", true, warehouseRepo.findAll());
    }

    public Result getWarehouseById(Integer id) {
        Optional<Warehouse> optionalWarehouse = warehouseRepo.findById(id);
        if (optionalWarehouse.isEmpty())
            return new Result("Such a warehouse not found", false);
        return new Result("Successfully", true, optionalWarehouse.get());
    }

    public Result editWarehouseById(Integer id, Warehouse warehouse1) {
        Optional<Warehouse> optionalWarehouse = warehouseRepo.findById(id);
        if (optionalWarehouse.isEmpty())
            return new Result("Such warehouse not found", false);
        Warehouse warehouse = optionalWarehouse.get();
        boolean existsByName = warehouseRepo.existsByName(warehouse1.getName());
        if (existsByName)
            return new Result("This warehouse is already exists", false);
        warehouse.setName(warehouse1.getName());
        warehouseRepo.save(warehouse);
        return new Result("Successfully edited", true);
    }

    public Result deleteWarehouseById(Integer id) {
        Optional<Warehouse> optionalWarehouse = warehouseRepo.findById(id);
        if (optionalWarehouse.isEmpty())
            return new Result("Such a warehouse not found", false);
        warehouseRepo.deleteById(id);
        return new Result("Successfully deleted", true);
    }
}
