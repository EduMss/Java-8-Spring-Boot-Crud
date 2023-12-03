package org.edumss.domain.product;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Table(name = "product")
@Entity(name = "product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Product {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    private String name;

    private Integer priceInCents;

    private Boolean active;

    public Product(RequestProduct requestProduct){
        this.name = requestProduct.getName();
        this.priceInCents = requestProduct.getPriceInCents();
        this.active = true;
    }
}
