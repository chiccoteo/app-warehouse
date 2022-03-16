package uz.pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.entity.Supplier;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.service.SupplierService;

@RestController
@RequestMapping("/supplier")
public class SupplierController {

    @Autowired
    SupplierService supplierService;


    @PostMapping
    public Result addSupplier(@RequestBody Supplier supplier) {
        return supplierService.addSupplier(supplier);
    }

    @GetMapping
    public Result getSuppliers() {
        return supplierService.getSuppliers();
    }

    @GetMapping("/{id}")
    public Result getSupplierById(@PathVariable Integer id) {
        return supplierService.getSupplierById(id);
    }

    @PutMapping("/{id}")
    public Result editSupplierById(@PathVariable Integer id, @RequestBody Supplier supplier) {
        return supplierService.editSupplierById(id, supplier);
    }

    @DeleteMapping("/{id}")
    public Result deleteSupplierById(@PathVariable Integer id) {
        return supplierService.deleteSupplierById(id);
    }
}
