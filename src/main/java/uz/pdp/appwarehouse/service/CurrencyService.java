package uz.pdp.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.entity.Currency;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.repo.CurrencyRepo;

import java.util.Optional;

@Service
public class CurrencyService {

    @Autowired
    CurrencyRepo currencyRepo;


    public Result addCurrency(Currency currency) {
        boolean existsByName = currencyRepo.existsByName(currency.getName());
        if (existsByName)
            return new Result("This currency is already exists", false);
        currencyRepo.save(currency);
        return new Result("Successfully saved", true);
    }

    public Result getCurrencies() {
        return new Result("Successfully", true, currencyRepo.findAll());
    }

    public Result getCurrencyById(Integer id) {
        Optional<Currency> optionalCurrency = currencyRepo.findById(id);
        if (optionalCurrency.isEmpty())
            return new Result("Such a currency not found", false);
        return new Result("Successfully", true, optionalCurrency.get());
    }

    public Result editCurrencyById(Integer id, Currency currency1) {
        Optional<Currency> optionalCurrency = currencyRepo.findById(id);
        if (optionalCurrency.isEmpty())
            return new Result("Such currency not found", false);
        Currency currency = optionalCurrency.get();
        boolean existsByName = currencyRepo.existsByName(currency1.getName());
        if (existsByName)
            return new Result("This currency is already exists", false);
        currency.setName(currency1.getName());
        currencyRepo.save(currency);
        return new Result("Successfully edited", true);
    }

    public Result deleteCurrencyById(Integer id) {
        Optional<Currency> optionalCurrency = currencyRepo.findById(id);
        if (optionalCurrency.isEmpty())
            return new Result("Such a currency not found", false);
        currencyRepo.deleteById(id);
        return new Result("Successfully deleted", true);
    }
}
