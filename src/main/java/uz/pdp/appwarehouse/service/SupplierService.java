package uz.pdp.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.entity.Supplier;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.repo.SupplierRepo;


import java.util.Optional;

@Service
public class SupplierService {

    @Autowired
    SupplierRepo supplierRepo;


    public Result addSupplier(Supplier supplier) {
        boolean existByPhoneNumber = supplierRepo.existsByPhoneNumber(supplier.getPhoneNumber());
        if (existByPhoneNumber)
            return new Result("This a phone number with supplier is already exists", false);
        supplierRepo.save(supplier);
        return new Result("Successfully saved", true);
    }

    public Result getSuppliers() {
        return new Result("Successfully", true, supplierRepo.findAll());
    }

    public Result getSupplierById(Integer id) {
        Optional<Supplier> optionalSupplier = supplierRepo.findById(id);
        if (optionalSupplier.isEmpty())
            return new Result("Such a supplier not found", false);
        return new Result("Successfully", true, optionalSupplier.get());
    }

    public Result editSupplierById(Integer id, Supplier supplier1) {
        Optional<Supplier> optionalSupplier = supplierRepo.findById(id);
        if (optionalSupplier.isEmpty())
            return new Result("Such supplier not found", false);
        Supplier supplier = optionalSupplier.get();
        boolean existByPhoneNumber = supplierRepo.existsByPhoneNumber(supplier1.getPhoneNumber());
        if (existByPhoneNumber)
            return new Result("This a phone number with supplier is already exists", false);
        supplier.setName(supplier1.getName());
        supplierRepo.save(supplier);
        return new Result("Successfully edited", true);
    }

    public Result deleteSupplierById(Integer id) {
        Optional<Supplier> optionalSupplier = supplierRepo.findById(id);
        if (optionalSupplier.isEmpty())
            return new Result("Such a supplier not found", false);
        supplierRepo.deleteById(id);
        return new Result("Successfully deleted", true);
    }
}
