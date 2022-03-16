package uz.pdp.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.entity.Currency;
import uz.pdp.appwarehouse.entity.Output;
import uz.pdp.appwarehouse.entity.Client;
import uz.pdp.appwarehouse.entity.Warehouse;
import uz.pdp.appwarehouse.payload.OutputDto;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.repo.CurrencyRepo;
import uz.pdp.appwarehouse.repo.OutputRepo;
import uz.pdp.appwarehouse.repo.ClientRepo;
import uz.pdp.appwarehouse.repo.WarehouseRepo;

import java.util.List;
import java.util.Optional;

@Service
public class OutputService {

    @Autowired
    OutputRepo outputRepo;

    @Autowired
    WarehouseRepo warehouseRepo;

    @Autowired
    ClientRepo clientRepo;

    @Autowired
    CurrencyRepo currencyRepo;


    public Result addOutput(OutputDto outputDto) {
        Optional<Warehouse> optionalWarehouse = warehouseRepo.findById(outputDto.getWarehouseId());
        Optional<Client> optionalClient = clientRepo.findById(outputDto.getClientId());
        Optional<Currency> optionalCurrency = currencyRepo.findById(outputDto.getCurrencyId());
        Result checking = checking(optionalWarehouse, optionalClient, optionalCurrency);
        if (checking.isSuccess()) {
            Output output = new Output();
            output.setFactureNumber(outputDto.getFactureNumber());
            output.setCode(getSpecialCode());
            output.setWarehouse(optionalWarehouse.orElse(null));
            output.setClient(optionalClient.orElse(null));
            output.setCurrency(optionalCurrency.orElse(null));
            outputRepo.save(output);
            return new Result("Successfully saved", true);
        }
        return checking;
    }

    private String getSpecialCode() {
        List<Output> outputs = outputRepo.findAll();
        if (outputs.isEmpty())
            return "1";
        return String.valueOf(Integer.parseInt(outputs.get(outputs.size() - 1).getCode()) + 1);
    }

    private Result checking(Optional<Warehouse> optionalWarehouse,
                            Optional<Client> optionalClient,
                            Optional<Currency> optionalCurrency) {
        if (optionalWarehouse.isEmpty())
            return new Result("Such a warehouse doesn't exist", false);
        if (optionalClient.isEmpty())
            return new Result("Such a client doesn't exist", false);
        if (optionalCurrency.isEmpty())
            return new Result("Such a currency doesn't exist", false);
        return new Result("Success", true);
    }

    public Result getOutputs() {
        return new Result("Success", true, outputRepo.findAll());
    }

    public Result getOutputById(Integer id) {
        Optional<Output> optionalOutput = outputRepo.findById(id);
        if (optionalOutput.isEmpty())
            return new Result("Such a output doesn't exist", false);
        return new Result("Successfully", true, optionalOutput.get());
    }

    public Result editOutputById(Integer id, OutputDto outputDto) {
        Optional<Output> optionalOutput = outputRepo.findById(id);
        if (optionalOutput.isEmpty())
            return new Result("Such a output doesn't exist", false);
        Optional<Warehouse> optionalWarehouse = warehouseRepo.findById(outputDto.getWarehouseId());
        Optional<Client> optionalClient = clientRepo.findById(outputDto.getClientId());
        Optional<Currency> optionalCurrency = currencyRepo.findById(outputDto.getCurrencyId());
        Result checking = checking(optionalWarehouse, optionalClient, optionalCurrency);
        if (checking.isSuccess()) {
            Output output = optionalOutput.get();
            output.setFactureNumber(outputDto.getFactureNumber());
//            output.setCode(getSpecialCode()); Code isn't changed
            output.setWarehouse(optionalWarehouse.orElse(null));
            output.setClient(optionalClient.orElse(null));
            output.setCurrency(optionalCurrency.orElse(null));
            outputRepo.save(output);
            return new Result("Successfully edited", true);
        }
        return checking;
    }

    public Result deleteOutputById(Integer id) {
        Optional<Output> optionalOutput = outputRepo.findById(id);
        if (optionalOutput.isEmpty())
            return new Result("Such a output doesn't exist", false);
        outputRepo.deleteById(id);
        return new Result("Successfully deleted", true);
    }
}
