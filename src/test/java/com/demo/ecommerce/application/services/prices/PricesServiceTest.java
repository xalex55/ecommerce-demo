package com.demo.ecommerce.application.services.prices;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.demo.ecommerce.application.dto.prices.FinalPriceDto;
import com.demo.ecommerce.application.mapper.prices.PricesMapper;
import com.demo.ecommerce.application.services.prices.impl.PricesServiceImpl;
import com.demo.ecommerce.domain.exceptions.PriceNotFoundException;
import com.demo.ecommerce.domain.model.prices.Price;
import com.demo.ecommerce.domain.model.prices.PriceId;
import com.demo.ecommerce.infrastructure.adapters.out.persistence.prices.PriceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

@ExtendWith(MockitoExtension.class)
class PricesServiceTest {

    @Mock
    private PriceRepository priceRepository;

    @Mock
    private PricesMapper pricesMapper;

    @InjectMocks
    private PricesServiceImpl pricesService;

    private static Stream<Arguments> providedPriceUseCases() {
        return Stream.of(
                Arguments.of(LocalDateTime.of(2025, 6, 14, 10, 0, 0, 0), 35455, 1, 100.0f),
                Arguments.of(LocalDateTime.of(2025, 6, 14, 16, 0, 0, 0), 35455, 1, 150.0f),
                Arguments.of(LocalDateTime.of(2025, 6, 14, 21, 0, 0, 0), 35455, 1, 200.0f),
                Arguments.of(LocalDateTime.of(2025, 6, 15, 10, 0, 0, 0), 35455, 1, 120.0f),
                Arguments.of(LocalDateTime.of(2025, 6, 16, 21, 0, 0, 0), 35455, 1, 180.0f)
        );
    }

    @ParameterizedTest
    @MethodSource("providedPriceUseCases")
    void testGetProductPriceByDate(LocalDateTime applicationDate, Integer productId, Integer brandId, Float expectedPrice) {
        PriceId priceId1 = PriceId.builder()
                .productId(productId)
                .brandId(brandId)
                .priceList(1)
                .build();

        Price price1 = Price.builder()
                .id(priceId1)
                .startDate(applicationDate.minusHours(1))
                .endDate(applicationDate.plusHours(1))
                .price(expectedPrice)
                .currency("EUR")
                .priority(0)
                .build();

        PriceId priceId2 = PriceId.builder()
                .productId(productId)
                .brandId(brandId)
                .priceList(2)
                .build();

        Price price2 = Price.builder()
                .id(priceId2)
                .startDate(applicationDate.minusHours(1))
                .endDate(applicationDate.plusHours(1))
                .price(expectedPrice)
                .currency("EUR")
                .priority(1)
                .build();

        when(priceRepository.findProductPriceByDate(any(), any(), any()))
                .thenReturn(List.of(price2, price1));

        FinalPriceDto finalPriceDto = FinalPriceDto.builder()
                .productId(productId)
                .brandId(brandId)
                .priceList(1)
                .priceStartDate(price2.getStartDate())
                .priceEndDate(price2.getEndDate())
                .finalPrice(price2.getPrice())
                .build();

        when(pricesMapper.toFinalPriceDto(any())).thenReturn(finalPriceDto);

        FinalPriceDto result = pricesService.getProductPriceByDate(applicationDate, productId, brandId);

        verify(priceRepository).findProductPriceByDate(applicationDate, productId, brandId);
        verify(pricesMapper).toFinalPriceDto(price2);

        assertNotNull(result);
        assertEquals(expectedPrice, result.getFinalPrice());
    }

    @Test
    void testGetProductPriceByDatePriceNotFound() {
        when(priceRepository.findProductPriceByDate(any(), any(), any()))
                .thenReturn(List.of());

        assertThrows(PriceNotFoundException.class, () -> pricesService.getProductPriceByDate(any(), any(), any()));

        verify(priceRepository).findProductPriceByDate(any(), any(), any());
    }
}
