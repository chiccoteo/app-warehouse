package uz.pdp.appwarehouse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Input {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Timestamp date = new Timestamp(System.currentTimeMillis());

    @ManyToOne
    private Warehouse warehouse;

    @ManyToOne
    private Supplier supplier;

    @ManyToOne
    private Currency currency;

    private String factureNumber;

    @Column(nullable = false, unique = true)
    private String code;
}
