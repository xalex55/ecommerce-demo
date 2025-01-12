package com.demo.ecommerce.application.ports.in.prices;

import com.demo.ecommerce.application.dto.prices.FinalPriceDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;


public interface PriceController {

    @Operation(summary = "Get the price for a specified product in the selected dates")
    @ApiResponses(value={
            @ApiResponse(responseCode = "200", description = "Found price",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(allOf = FinalPriceDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Price not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    ResponseEntity<FinalPriceDto> getProductPriceByDate(@Parameter(example = "2025-01-12T15:00:00.000") LocalDateTime applicationDate, @Parameter Integer productId,
                                                                @Parameter Integer brandId);

}
