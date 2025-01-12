package com.demo.ecommerce.application.dto.prices;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;

class FinalPriceDtoTest {

    private final static LocalDateTime startDate = LocalDateTime.of(2025, 1, 12, 10, 0, 0, 0);
    private final static LocalDateTime endDateDate = LocalDateTime.of(2025, 1, 12, 16, 0, 0, 0);

    @Test
    void testNoArgsConstructor() {
        FinalPriceDto dto = new FinalPriceDto();

        assertNull(dto.getProductId());
        assertNull(dto.getBrandId());
        assertNull(dto.getPriceList());
        assertNull(dto.getPriceStartDate());
        assertNull(dto.getPriceEndDate());
        assertNull(dto.getFinalPrice());
        assertNull(dto.getCurrency());
    }

    @Test
    void testAllArgsConstructor() {
        FinalPriceDto dto = new FinalPriceDto(1, 2, 3, startDate, endDateDate, 99.99f, "EUR");

        assertEquals(1, dto.getProductId());
        assertEquals(2, dto.getBrandId());
        assertEquals(3, dto.getPriceList());
        assertEquals(startDate, dto.getPriceStartDate());
        assertEquals(endDateDate, dto.getPriceEndDate());
        assertEquals(99.99f, dto.getFinalPrice());
        assertEquals("EUR", dto.getCurrency());

    }

    @Test
    void testBuilder() {
        FinalPriceDto dto = FinalPriceDto.builder()
                .productId(1)
                .brandId(2)
                .priceList(3)
                .priceStartDate(startDate)
                .priceEndDate(endDateDate)
                .finalPrice(99.99f)
                .currency("EUR")
                .build();

        assertEquals(1, dto.getProductId());
        assertEquals(2, dto.getBrandId());
        assertEquals(3, dto.getPriceList());
        assertEquals(startDate, dto.getPriceStartDate());
        assertEquals(endDateDate, dto.getPriceEndDate());
        assertEquals(99.99f, dto.getFinalPrice());
        assertEquals("EUR", dto.getCurrency());
    }
}
