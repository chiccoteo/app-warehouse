package uz.pdp.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.entity.Client;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.repo.ClientRepo;

import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    ClientRepo clientRepo;


    public Result addClient(Client client) {
        boolean existByPhoneNumber = false;
        if (!clientRepo.findAll().isEmpty())
            existByPhoneNumber = clientRepo.existsByPhoneNumber(client.getPhoneNumber());
        if (existByPhoneNumber)
            return new Result("This a phone number with client is already exists", false);
        clientRepo.save(client);
        return new Result("Successfully saved", true);
    }

    public Result getClients() {
        return new Result("Successfully", true, clientRepo.findAll());
    }

    public Result getClientById(Integer id) {
        Optional<Client> optionalClient = clientRepo.findById(id);
        if (optionalClient.isEmpty())
            return new Result("Such a client not found", false);
        return new Result("Successfully", true, optionalClient.get());
    }

    public Result editClientById(Integer id, Client client1) {
        Optional<Client> optionalClient = clientRepo.findById(id);
        if (optionalClient.isEmpty())
            return new Result("Such client not found", false);
        Client client = optionalClient.get();
        boolean existByPhoneNumber = clientRepo.existsByPhoneNumber(client1.getPhoneNumber());
        if (existByPhoneNumber)
            return new Result("This a phone number with client is already exists", false);
        client.setName(client1.getName());
        clientRepo.save(client);
        return new Result("Successfully edited", true);
    }

    public Result deleteClientById(Integer id) {
        Optional<Client> optionalClient = clientRepo.findById(id);
        if (optionalClient.isEmpty())
            return new Result("Such a client not found", false);
        clientRepo.deleteById(id);
        return new Result("Successfully deleted", true);
    }
}
