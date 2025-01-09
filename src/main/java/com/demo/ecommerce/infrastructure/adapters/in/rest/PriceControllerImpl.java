package com.demo.ecommerce.infrastructure.adapters.in.rest;

import com.demo.ecommerce.application.ports.in.PriceController;
import com.demo.ecommerce.domain.entities.Price;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prices")
public class PriceControllerImpl implements PriceController {

    @Override
    @GetMapping
    public List<Price> getAllPrices() {
        return List.of();
    }
}
