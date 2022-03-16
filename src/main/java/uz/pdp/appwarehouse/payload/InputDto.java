package uz.pdp.appwarehouse.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InputDto {
    private Integer warehouseId;
    private Integer supplierId;
    private Integer currencyId;
    private String factureNumber;
}
