package uz.pdp.appwarehouse.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String firstname;
    private String lastname;
    private String phoneNumber;
    private String password;
    private Set<Integer> warehouseIds;
}
