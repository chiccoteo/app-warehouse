package uz.pdp.appwarehouse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.appwarehouse.entity.template.AbsEntity;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product extends AbsEntity {

    @ManyToOne(optional = false)
    private Category category;

    @OneToOne
    private Attachment photo;

    @Column(nullable = false, unique = true)
    private String code;

    @ManyToOne(optional = false)
    private Measurement measurement;
}
