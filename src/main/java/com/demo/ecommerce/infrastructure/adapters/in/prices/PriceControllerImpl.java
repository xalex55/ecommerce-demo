package com.demo.ecommerce.infrastructure.adapters.in.prices;

import com.demo.ecommerce.application.dto.prices.FinalPriceDto;
import com.demo.ecommerce.application.ports.in.prices.PriceController;
import com.demo.ecommerce.application.services.prices.PricesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;


@RestController
@RequestMapping("/prices")
public class PriceControllerImpl implements PriceController {

    private final PricesService pricesService;

    public PriceControllerImpl(PricesService pricesService) {
        this.pricesService = pricesService;
    }

    @Override
    @GetMapping("/getProductPriceByDate")
    public ResponseEntity<FinalPriceDto> getProductPriceByDate(@RequestParam LocalDateTime applicationDate, @RequestParam Integer productId,
                                                                       @RequestParam Integer brandId) {
        return ResponseEntity.ok(pricesService.getProductPriceByDate(applicationDate, productId, brandId));
    }
}
