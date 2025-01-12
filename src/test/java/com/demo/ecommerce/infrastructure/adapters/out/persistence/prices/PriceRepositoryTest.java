package com.demo.ecommerce.infrastructure.adapters.out.persistence.prices;

import com.demo.ecommerce.domain.model.prices.Price;
import com.demo.ecommerce.domain.model.prices.PriceId;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest(properties = {
        "spring.datasource.url=jdbc:h2:mem:testdb",
        "spring.jpa.hibernate.ddl-auto=create-drop",
}, showSql = false)
public class PriceRepositoryTest {

    @Autowired
    private PriceRepository priceRepository;

    private static final LocalDateTime applicationDate = LocalDateTime.of(2025, 6, 14, 10, 0, 0, 0);
    private static final Integer productId = 35455;
    private static final Integer brandId = 1;

    private Price price1;
    private Price price2;

    @BeforeEach
    void setUp() {
        PriceId priceId1 = PriceId.builder()
                .productId(productId)
                .brandId(brandId)
                .priceList(1)
                .build();

        PriceId priceId2 = PriceId.builder()
                .productId(productId)
                .brandId(brandId)
                .priceList(2)
                .build();

        price1 = Price.builder()
                .id(priceId1)
                .startDate(LocalDateTime.of(2025, 6, 14, 5, 0, 0, 0))
                .endDate(LocalDateTime.of(2025, 6, 14, 18, 0, 0, 0))
                .priority(0)
                .price(100.0f)
                .currency("EUR")
                .build();

        price2 = Price.builder()
                .id(priceId2)
                .startDate(LocalDateTime.of(2025, 6, 14, 8, 0, 0, 0))
                .endDate(LocalDateTime.of(2025, 6, 14, 12, 0, 0, 0))
                .priority(1)
                .price(90.0f)
                .currency("EUR")
                .build();

        priceRepository.save(price1);
        priceRepository.save(price2);
    }

    @AfterEach
    public void tearDown() {
        priceRepository.deleteAll();
    }

    @Test
    void testFindProductPriceByDate() {
        List<Price> prices = priceRepository.findProductPriceByDate(applicationDate, productId, brandId);

        assertFalse(prices.isEmpty());
        assertEquals(90F, prices.get(0).getPrice());
        assertEquals(100F, prices.get(1).getPrice());
        assertTrue(prices.containsAll(List.of(price2, price1)));
    }

    @Test
    void testFindProductPriceByDateNoResults() {
        List<Price> prices = priceRepository.findProductPriceByDate(applicationDate.plusDays(10), productId, brandId);

        assertTrue(prices.isEmpty());
    }
}

