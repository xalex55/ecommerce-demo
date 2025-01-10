package com.demo.ecommerce.infrastructure.adapters.in.prices;

import com.demo.ecommerce.application.dto.prices.FinalPriceDto;
import com.demo.ecommerce.application.ports.in.prices.PriceController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;


@RestController
@RequestMapping("/prices")
public class PriceControllerImpl implements PriceController {

    @Override
    @GetMapping("/getPriceDiscountedByDateRange")
    public ResponseEntity<FinalPriceDto> getPriceDiscountedByDateRange(@RequestParam LocalDateTime applicationDate, @RequestParam Integer productId,
                                                                       @RequestParam Integer brandId) {
        return ResponseEntity.ok(null);
    }
}
