package com.demo.ecommerce.application.ports.in;

import com.demo.ecommerce.domain.entities.Price;

import java.util.List;


public interface PriceController {

    List<Price> getAllPrices();

}
