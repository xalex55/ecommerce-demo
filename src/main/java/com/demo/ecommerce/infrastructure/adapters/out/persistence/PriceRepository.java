package com.demo.ecommerce.infrastructure.adapters.out.persistence;

import com.demo.ecommerce.domain.entities.Price;
import com.demo.ecommerce.domain.entities.PriceId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PriceRepository extends JpaRepository<Price, PriceId> {
}
