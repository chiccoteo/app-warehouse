package uz.pdp.appwarehouse.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DailyInputDto {
    private String productName;
    private Double amount;
    private Double price;
}
