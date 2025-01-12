package com.demo.ecommerce.application.services.prices;

import com.demo.ecommerce.application.dto.prices.FinalPriceDto;

import java.time.LocalDateTime;

public interface PricesService {

    FinalPriceDto getProductPriceByDate(LocalDateTime applicationDate, Integer productId, Integer brandId);
}
