package com.demo.ecommerce.infrastructure.adapters.in.prices;

import com.demo.ecommerce.domain.exceptions.PriceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class PricesExceptionHandler {

    @ExceptionHandler(PriceNotFoundException.class)
    @ResponseBody
    public ResponseEntity<Map<String, String>> handlePriceNotFoundException(PriceNotFoundException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("status", "error");
        response.put("message", ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
