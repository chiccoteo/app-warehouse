package uz.pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.payload.InputDto;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.service.InputService;

@RestController
@RequestMapping("/input")
public class InputController {

    @Autowired
    InputService inputService;


    @PostMapping
    public Result addInput(@RequestBody InputDto inputDto) {
        return inputService.addInput(inputDto);
    }

    @GetMapping
    public Result getInputs() {
        return inputService.getInputs();
    }

    @GetMapping("/{id}")
    public Result getInputById(@PathVariable Integer id) {
        return inputService.getInputById(id);
    }

    @PutMapping("/{id}")
    public Result editInputById(@PathVariable Integer id, @RequestBody InputDto inputDto) {
        return inputService.editInputById(id, inputDto);
    }

    @DeleteMapping("/{id}")
    public Result deleteInputById(@PathVariable Integer id) {
        return inputService.deleteInputById(id);
    }
}
