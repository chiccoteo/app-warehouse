package uz.pdp.appwarehouse.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OutputDto {
    private Integer warehouseId;
    private Integer clientId;
    private Integer currencyId;
    private String factureNumber;
}
