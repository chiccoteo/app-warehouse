package uz.pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.payload.InputProductDto;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.service.InputProductService;

@RestController
@RequestMapping("/inputProduct")
public class InputProductController {

    @Autowired
    InputProductService inputProductService;


    @PostMapping
    public Result addInputProduct(@RequestBody InputProductDto inputProductDto) {
        return inputProductService.add(inputProductDto);
    }

    @GetMapping
    public Result getInputProducts() {
        return inputProductService.getAll();
    }

    @GetMapping("/{id}")
    public Result getInputProductById(@PathVariable Integer id) {
        return inputProductService.getById(id);
    }

    @PutMapping("/{id}")
    public Result editInputProductById(@PathVariable Integer id, @RequestBody InputProductDto inputProductDto) {
        return inputProductService.editById(id, inputProductDto);
    }

    @DeleteMapping("/{id}")
    public Result deleteInputProductById(@PathVariable Integer id) {
        return inputProductService.deleteById(id);
    }
}
