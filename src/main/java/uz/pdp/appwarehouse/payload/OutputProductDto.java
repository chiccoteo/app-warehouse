package uz.pdp.appwarehouse.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class OutputProductDto {
    private Integer productId;
    private Double amount;
    private Double price;
    private Integer outputId;
}
