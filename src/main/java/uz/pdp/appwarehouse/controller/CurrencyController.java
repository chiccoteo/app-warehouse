package uz.pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.entity.Currency;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.service.CurrencyService;

@RestController
@RequestMapping("/currency")
public class CurrencyController {

    @Autowired
    CurrencyService currencyService;


    @PostMapping
    public Result addCurrency(@RequestBody Currency currency) {
        return currencyService.addCurrency(currency);
    }

    @GetMapping
    public Result getCurrencies() {
        return currencyService.getCurrencies();
    }

    @GetMapping("/{id}")
    public Result getCurrencyById(@PathVariable Integer id) {
        return currencyService.getCurrencyById(id);
    }

    @PutMapping("/{id}")
    public Result editCurrencyById(@PathVariable Integer id, @RequestBody Currency currency) {
        return currencyService.editCurrencyById(id, currency);
    }

    @DeleteMapping("/{id}")
    public Result deleteCurrencyById(@PathVariable Integer id) {
        return currencyService.deleteCurrencyById(id);
    }
}
