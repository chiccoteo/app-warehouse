package uz.pdp.appwarehouse.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appwarehouse.entity.Currency;

public interface CurrencyRepo extends JpaRepository<Currency, Integer> {

    boolean existsByName(String name);
}
