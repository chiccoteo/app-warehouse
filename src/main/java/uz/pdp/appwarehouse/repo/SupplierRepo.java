package uz.pdp.appwarehouse.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appwarehouse.entity.Supplier;

public interface SupplierRepo extends JpaRepository<Supplier, Integer> {

    boolean existsByPhoneNumber(String phoneNumber);
}
