package uz.pdp.appwarehouse.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appwarehouse.entity.OutputProduct;

public interface OutputProductRepo extends JpaRepository<OutputProduct, Integer> {
}
