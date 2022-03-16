package uz.pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.entity.Warehouse;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.service.WarehouseService;

@RestController
@RequestMapping("/warehouse")
public class WarehouseController {

    @Autowired
    WarehouseService warehouseService;


    @PostMapping
    public Result addWarehouse(@RequestBody Warehouse warehouse) {
        return warehouseService.addWarehouse(warehouse);
    }

    @GetMapping
    public Result getWarehouses() {
        return warehouseService.getWarehouses();
    }

    @GetMapping("/{id}")
    public Result getWarehouseById(@PathVariable Integer id) {
        return warehouseService.getWarehouseById(id);
    }

    @PutMapping("/{id}")
    public Result editWarehouseById(@PathVariable Integer id, @RequestBody Warehouse warehouse) {
        return warehouseService.editWarehouseById(id, warehouse);
    }

    @DeleteMapping("/{id}")
    public Result deleteWarehouseById(@PathVariable Integer id) {
        return warehouseService.deleteWarehouseById(id);
    }
}
