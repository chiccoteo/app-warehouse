package uz.pdp.appwarehouse.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appwarehouse.entity.Product;

public interface ProductRepo extends JpaRepository<Product, Integer> {

    boolean existsByNameAndCategoryId(String name, Integer category_id);
}
