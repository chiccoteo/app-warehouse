package uz.pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.entity.Client;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.service.ClientService;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    ClientService clientService;


    @PostMapping
    public Result addClient(@RequestBody Client client) {
        return clientService.addClient(client);
    }

    @GetMapping
    public Result getClients() {
        return clientService.getClients();
    }

    @GetMapping("/{id}")
    public Result getClientById(@PathVariable Integer id) {
        return clientService.getClientById(id);
    }

    @PutMapping("/{id}")
    public Result editClientById(@PathVariable Integer id, @RequestBody Client client) {
        return clientService.editClientById(id, client);
    }

    @DeleteMapping("/{id}")
    public Result deleteClientById(@PathVariable Integer id) {
        return clientService.deleteClientById(id);
    }
}
