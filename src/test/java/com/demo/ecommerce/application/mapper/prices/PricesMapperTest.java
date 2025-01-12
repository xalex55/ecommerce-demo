package com.demo.ecommerce.application.mapper.prices;

import com.demo.ecommerce.application.dto.prices.FinalPriceDto;
import com.demo.ecommerce.domain.model.prices.Price;
import com.demo.ecommerce.domain.model.prices.PriceId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;

class PricesMapperTest {

    private PricesMapper pricesMapper;
    private final static LocalDateTime startDate = LocalDateTime.of(2025, 1, 12, 10, 0, 0, 0);
    private final static LocalDateTime endDateDate = LocalDateTime.of(2025, 1, 12, 16, 0, 0, 0);

    @BeforeEach
    void setUp() {
        pricesMapper = new PricesMapper();
    }

    @Test
    void testToFinalPriceDtoValidPrice() {
        PriceId priceId = PriceId.builder()
                .brandId(2)
                .productId(1)
                .priceList(3)
                .build();

        Price price = Price.builder()
                .id(priceId)
                .startDate(startDate)
                .endDate(endDateDate)
                .price(199.99f)
                .currency("EUR")
                .priority(1)
                .build();

        FinalPriceDto result = pricesMapper.toFinalPriceDto(price);

        assertNotNull(result);
        assertEquals(price.getId().getProductId(), result.getProductId());
        assertEquals(price.getId().getBrandId(), result.getBrandId());
        assertEquals(price.getId().getPriceList(), result.getPriceList());
        assertEquals(price.getStartDate(), result.getPriceStartDate());
        assertEquals(price.getEndDate(), result.getPriceEndDate());
        assertEquals(price.getPrice(), result.getFinalPrice());
        assertEquals("EUR", result.getCurrency());
    }

    @Test
    void testToFinalPriceDtoNullPrice() {
        FinalPriceDto result = pricesMapper.toFinalPriceDto(null);

        assertNull(result);
    }
}
