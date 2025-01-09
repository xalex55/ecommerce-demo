package com.demo.ecommerce.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "prices")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Price {

    @EmbeddedId
    private PriceId id;

    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDateTime endDate;

    @Column(name = "priority", nullable = false)
    private Integer priority;

    @Column(name = "price", nullable = false)
    private Float price;

    @Column(name = "curr", nullable = false)
    private String currency;
}

