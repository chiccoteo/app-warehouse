package uz.pdp.appwarehouse.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.appwarehouse.entity.InputProduct;

import java.util.List;

public interface InputProductRepo extends JpaRepository<InputProduct, Integer> {

    @Query(value = "select p.name as productName, sum(ip.amount) as amount, sum(ip.price) as price\n" +
            "from input_product ip\n" +
            "         join product p on p.id = ip.product_id\n" +
            "         join input i on i.id = ip.input_id\n" +
            "where i.date > current_date\n" +
            "group by p.name", nativeQuery = true)
    List<InputProduct> getDailyInputs();
}
