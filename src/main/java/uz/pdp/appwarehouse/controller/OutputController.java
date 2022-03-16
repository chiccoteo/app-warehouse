package uz.pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.payload.OutputDto;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.service.OutputService;

@RestController
@RequestMapping("/output")
public class OutputController {

    @Autowired
    OutputService outputService;


    @PostMapping
    public Result addOutput(@RequestBody OutputDto outputDto) {
        return outputService.addOutput(outputDto);
    }

    @GetMapping
    public Result getOutputs() {
        return outputService.getOutputs();
    }

    @GetMapping("/{id}")
    public Result getOutputById(@PathVariable Integer id) {
        return outputService.getOutputById(id);
    }

    @PutMapping("/{id}")
    public Result editOutputById(@PathVariable Integer id, @RequestBody OutputDto outputDto) {
        return outputService.editOutputById(id, outputDto);
    }

    @DeleteMapping("/{id}")
    public Result deleteOutputById(@PathVariable Integer id) {
        return outputService.deleteOutputById(id);
    }
}
