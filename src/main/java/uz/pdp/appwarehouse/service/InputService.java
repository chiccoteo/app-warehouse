package uz.pdp.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.entity.*;
import uz.pdp.appwarehouse.payload.InputDto;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.repo.CurrencyRepo;
import uz.pdp.appwarehouse.repo.InputRepo;
import uz.pdp.appwarehouse.repo.SupplierRepo;
import uz.pdp.appwarehouse.repo.WarehouseRepo;

import java.util.List;
import java.util.Optional;

@Service
public class InputService {

    @Autowired
    InputRepo inputRepo;

    @Autowired
    WarehouseRepo warehouseRepo;

    @Autowired
    SupplierRepo supplierRepo;

    @Autowired
    CurrencyRepo currencyRepo;


    public Result addInput(InputDto inputDto) {
        Optional<Warehouse> optionalWarehouse = warehouseRepo.findById(inputDto.getWarehouseId());
        Optional<Supplier> optionalSupplier = supplierRepo.findById(inputDto.getSupplierId());
        Optional<Currency> optionalCurrency = currencyRepo.findById(inputDto.getCurrencyId());
        Result checking = checking(optionalWarehouse, optionalSupplier, optionalCurrency);
        if (checking.isSuccess()) {
            Input input = new Input();
            input.setFactureNumber(inputDto.getFactureNumber());
            input.setCode(getSpecialCode());
            input.setWarehouse(optionalWarehouse.orElse(null));
            input.setSupplier(optionalSupplier.orElse(null));
            input.setCurrency(optionalCurrency.orElse(null));
            inputRepo.save(input);
            return new Result("Successfully saved", true);
        }
        return checking;
    }

    private String getSpecialCode() {
        List<Input> inputs = inputRepo.findAll();
        if (inputs.isEmpty())
            return "1";
        return String.valueOf(Integer.parseInt(inputs.get(inputs.size() - 1).getCode()) + 1);
    }

    private Result checking(Optional<Warehouse> optionalWarehouse,
                            Optional<Supplier> optionalSupplier,
                            Optional<Currency> optionalCurrency) {
        if (optionalWarehouse.isEmpty())
            return new Result("Such a warehouse doesn't exist", false);
        if (optionalSupplier.isEmpty())
            return new Result("Such a supplier doesn't exist", false);
        if (optionalCurrency.isEmpty())
            return new Result("Such a currency doesn't exist", false);
        return new Result("Success", true);
    }

    public Result getInputs() {
        return new Result("Success", true, inputRepo.findAll());
    }

    public Result getInputById(Integer id) {
        Optional<Input> optionalInput = inputRepo.findById(id);
        if (optionalInput.isEmpty())
            return new Result("Such a input doesn't exist", false);
        return new Result("Successfully", true, optionalInput.get());
    }

    public Result editInputById(Integer id, InputDto inputDto) {
        Optional<Input> optionalInput = inputRepo.findById(id);
        if (optionalInput.isEmpty())
            return new Result("Such a input doesn't exist", false);
        Optional<Warehouse> optionalWarehouse = warehouseRepo.findById(inputDto.getWarehouseId());
        Optional<Supplier> optionalSupplier = supplierRepo.findById(inputDto.getSupplierId());
        Optional<Currency> optionalCurrency = currencyRepo.findById(inputDto.getCurrencyId());
        Result checking = checking(optionalWarehouse, optionalSupplier, optionalCurrency);
        if (checking.isSuccess()) {
            Input input = optionalInput.get();
            input.setFactureNumber(inputDto.getFactureNumber());
//            input.setCode(getSpecialCode()); Code isn't changed
            input.setWarehouse(optionalWarehouse.orElse(null));
            input.setSupplier(optionalSupplier.orElse(null));
            input.setCurrency(optionalCurrency.orElse(null));
            inputRepo.save(input);
            return new Result("Successfully edited", true);
        }
        return checking;
    }

    public Result deleteInputById(Integer id) {
        Optional<Input> optionalInput = inputRepo.findById(id);
        if (optionalInput.isEmpty())
            return new Result("Such a input doesn't exist", false);
        inputRepo.deleteById(id);
        return new Result("Successfully deleted", true);
    }
}
