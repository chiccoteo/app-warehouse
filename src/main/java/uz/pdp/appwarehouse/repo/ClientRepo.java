package uz.pdp.appwarehouse.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appwarehouse.entity.Client;

public interface ClientRepo extends JpaRepository<Client, Integer> {

    boolean existsByPhoneNumber(String phoneNumber);
}
