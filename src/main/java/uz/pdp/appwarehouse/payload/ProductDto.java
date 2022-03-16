package uz.pdp.appwarehouse.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private String name;
    private Integer categoryId;
    private Integer photoId;
    private Integer measurementId;
}
