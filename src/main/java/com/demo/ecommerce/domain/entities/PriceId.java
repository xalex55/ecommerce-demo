package com.demo.ecommerce.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PriceId implements Serializable {

    @Column(name = "brand_id")
    private Integer brandId;

    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "price_list")
    private Integer priceList;
}
