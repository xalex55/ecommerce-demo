package com.demo.ecommerce.infrastructure.adapters.in.prices;

import com.demo.ecommerce.application.dto.prices.FinalPriceDto;
import com.demo.ecommerce.application.services.prices.PricesService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.provider.Arguments;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class PriceControllerImplTest {

    @Mock
    private PricesService pricesService;

    @InjectMocks
    private PriceControllerImpl priceController;

    @Autowired
    private MockMvc mockMvc;

    static Stream<Arguments> providePriceTestData() {
        return Stream.of(
                Arguments.of(LocalDateTime.of(2020, 6, 14, 10, 0, 0), 35.50F, 35455, 1, "EUR", 1,
                        LocalDateTime.of(2020, 6, 14, 0, 0, 0), LocalDateTime.of(2020, 12, 31, 23, 59, 59)),
                Arguments.of(LocalDateTime.of(2020, 6, 14, 16, 0, 0), 25.45F, 35455, 1, "EUR", 2,
                        LocalDateTime.of(2020, 6, 14, 15, 0, 0), LocalDateTime.of(2020, 6, 14, 18, 30, 0)),
                Arguments.of(LocalDateTime.of(2020, 6, 15, 10, 0, 0), 30.50F, 35455, 1, "EUR", 3,
                        LocalDateTime.of(2020, 6, 15, 0, 0, 0), LocalDateTime.of(2020, 6, 15, 11, 0, 0)),
                Arguments.of(LocalDateTime.of(2020, 6, 16, 21, 0, 0), 38.95F, 35455, 1, "EUR", 4,
                        LocalDateTime.of(2020, 6, 15, 16, 0, 0), LocalDateTime.of(2020, 12, 31, 23, 59, 59))
        );
    }

    @ParameterizedTest
    @MethodSource("providePriceTestData")
    void testGetProductPriceByDate(LocalDateTime applicationDate, Float expectedPrice, Integer productId, Integer brandId, String currency,
                                   Integer priceList, LocalDateTime priceStartDate, LocalDateTime priceEndDate) throws Exception {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        String formattedStartDate = priceStartDate.format(formatter);
        String formattedEndDate = priceEndDate.format(formatter);

        FinalPriceDto finalPriceDto = new FinalPriceDto(productId, brandId, priceList, priceStartDate, priceEndDate, expectedPrice, currency);

        when(pricesService.getProductPriceByDate(applicationDate, productId, brandId)).thenReturn(finalPriceDto);

        mockMvc.perform(get("/prices/getProductPriceByDate")
                        .param("applicationDate", applicationDate.toString())
                        .param("productId", productId.toString())
                        .param("brandId", brandId.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(productId))
                .andExpect(jsonPath("$.brandId").value(brandId))
                .andExpect(jsonPath("$.priceList").value(priceList))
                .andExpect(jsonPath("$.priceStartDate").value(formattedStartDate))
                .andExpect(jsonPath("$.priceEndDate").value(formattedEndDate))
                .andExpect(jsonPath("$.finalPrice").value(expectedPrice))
                .andExpect(jsonPath("$.currency").value(currency));
    }
}
