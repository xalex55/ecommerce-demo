package com.demo.ecommerce.application.dto.prices;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FinalPriceDto {

    Integer productId;
    Integer brandId;
    Integer priceList;
    LocalDateTime priceStartDate;
    LocalDateTime priceEndDate;
    Float finalPrice;
    String currency;

}
