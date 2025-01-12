package com.demo.ecommerce.application.services.prices.impl;

import com.demo.ecommerce.application.dto.prices.FinalPriceDto;
import com.demo.ecommerce.application.mapper.prices.PricesMapper;
import com.demo.ecommerce.application.services.prices.PricesService;
import com.demo.ecommerce.domain.exceptions.PriceNotFoundException;
import com.demo.ecommerce.domain.model.prices.Price;
import com.demo.ecommerce.infrastructure.adapters.out.persistence.prices.PriceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PricesServiceImpl implements PricesService {

    private final PriceRepository priceRepository;
    private final PricesMapper pricesMapper;

    public PricesServiceImpl(PriceRepository priceRepository, PricesMapper pricesMapper) {
        this.priceRepository = priceRepository;
        this.pricesMapper = pricesMapper;
    }

    public FinalPriceDto getProductPriceByDate(LocalDateTime applicationDate, Integer productId, Integer brandId) {

        Price price = priceRepository.findProductPriceByDate(applicationDate, productId, brandId).stream().findFirst()
                .orElseThrow(() -> new PriceNotFoundException("No se ha encontrado precio para el producto con los parámetros especificados"));

        return pricesMapper.toFinalPriceDto(price);
    }
}
