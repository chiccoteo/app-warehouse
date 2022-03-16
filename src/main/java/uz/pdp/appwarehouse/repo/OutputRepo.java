package uz.pdp.appwarehouse.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appwarehouse.entity.Output;

public interface OutputRepo extends JpaRepository<Output, Integer> {
}
