package com.demo.ecommerce.infrastructure.adapters.out.persistence.prices;

import com.demo.ecommerce.domain.model.prices.Price;
import com.demo.ecommerce.domain.model.prices.PriceId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PriceRepository extends JpaRepository<Price, PriceId> {
}
