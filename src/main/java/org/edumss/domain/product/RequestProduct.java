package org.edumss.domain.product;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestProduct {
    String id;

    @NotBlank
    private	String	name;

    @NotNull
    private	Integer priceInCents;
}
