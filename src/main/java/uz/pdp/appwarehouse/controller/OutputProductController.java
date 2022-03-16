package uz.pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.payload.OutputProductDto;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.service.OutputProductService;

@RestController
@RequestMapping("/outputProduct")
public class OutputProductController {

    @Autowired
    OutputProductService outputProductService;


    @PostMapping
    public Result addOutputProduct(@RequestBody OutputProductDto outputProductDto) {
        return outputProductService.add(outputProductDto);
    }

    @GetMapping
    public Result getOutputProducts() {
        return outputProductService.getAll();
    }

    @GetMapping("/{id}")
    public Result getOutputProductById(@PathVariable Integer id) {
        return outputProductService.getById(id);
    }

    @PutMapping("/{id}")
    public Result editOutputProductById(@PathVariable Integer id, @RequestBody OutputProductDto outputProductDto) {
        return outputProductService.editById(id, outputProductDto);
    }

    @DeleteMapping("/{id}")
    public Result deleteOutputProductById(@PathVariable Integer id) {
        return outputProductService.deleteById(id);
    }
}
