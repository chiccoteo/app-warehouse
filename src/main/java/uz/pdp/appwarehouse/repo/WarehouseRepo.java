package uz.pdp.appwarehouse.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appwarehouse.entity.Warehouse;


public interface WarehouseRepo extends JpaRepository<Warehouse, Integer> {

    boolean existsByName(String name);

}
