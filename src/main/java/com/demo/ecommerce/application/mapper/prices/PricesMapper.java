package com.demo.ecommerce.application.mapper.prices;

import com.demo.ecommerce.application.dto.prices.FinalPriceDto;
import com.demo.ecommerce.domain.model.prices.Price;
import org.springframework.stereotype.Component;

@Component
public class PricesMapper {

    public FinalPriceDto toFinalPriceDto(Price price) {
        if (price == null) {
            return null;
        }
        return FinalPriceDto.builder()
                .productId(price.getId().getProductId())
                .brandId(price.getId().getBrandId())
                .priceList(price.getId().getPriceList())
                .priceStartDate(price.getStartDate())
                .priceEndDate(price.getEndDate())
                .finalPrice(price.getPrice())
                .build();
    }
}
