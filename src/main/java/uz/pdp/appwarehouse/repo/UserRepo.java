package uz.pdp.appwarehouse.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appwarehouse.entity.User;

public interface UserRepo extends JpaRepository<User, Integer> {

    boolean existsByPhoneNumber(String phoneNumber);

}
