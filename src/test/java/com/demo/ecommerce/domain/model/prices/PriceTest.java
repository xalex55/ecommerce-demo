package com.demo.ecommerce.domain.model.prices;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

class PriceTest {

    private Price price;
    private Price anotherPrice;

    private Price createPrice(Float finalPrice) {
        PriceId priceId = PriceId.builder()
                .productId(35455)
                .brandId(1)
                .priceList(1)
                .build();

        return Price.builder()
                .id(priceId)
                .startDate(LocalDateTime.of(2025, 6, 14, 10, 0, 0, 0))
                .endDate(LocalDateTime.of(2025, 6, 14, 18, 0, 0, 0))
                .priority(1)
                .price(finalPrice)
                .currency("EUR")
                .build();
    }

    @BeforeEach
    void setUp() {
        price = createPrice(33F);
        anotherPrice = createPrice(12F);
    }

    @Test
    void testPriceConstructor() {
        assertNotNull(price);
        assertEquals(35455, price.getId().getProductId());
        assertEquals(33F, price.getPrice());
        assertEquals("EUR", price.getCurrency());
        assertEquals(LocalDateTime.of(2025, 6, 14, 10, 0, 0, 0), price.getStartDate());
    }

    @Test
    void testEqualsWithSameValues() {
        assertEquals(createPrice(33F), price);
    }

    @Test
    void testEqualsWithDifferentValues() {
        anotherPrice.setPrice(200.0f);
        assertNotEquals(anotherPrice, price);
    }

    @Test
    void testHashCode() {
        Price priceClone = createPrice(33F);
        assertEquals(priceClone.hashCode(), price.hashCode());
    }

    @Test
    void testToString() {
        String toStringValue = price.toString();
        assertTrue(toStringValue.contains("Price(id="));
        assertTrue(toStringValue.contains("price=" + price.getPrice()));
        assertTrue(toStringValue.contains("currency=" + price.getCurrency()));
    }
}

