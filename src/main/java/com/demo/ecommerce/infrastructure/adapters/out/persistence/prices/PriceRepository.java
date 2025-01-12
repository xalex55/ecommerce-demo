package com.demo.ecommerce.infrastructure.adapters.out.persistence.prices;

import com.demo.ecommerce.domain.model.prices.Price;
import com.demo.ecommerce.domain.model.prices.PriceId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface PriceRepository extends JpaRepository<Price, PriceId> {

    @Query("SELECT p FROM Price p WHERE p.id.productId = :productId " +
            "AND p.id.brandId = :brandId " +
            "AND p.startDate <= :applicationDate " +
            "AND p.endDate >= :applicationDate " +
            "ORDER BY p.priority DESC")
    List<Price> findProductPriceByDate(@Param("applicationDate") LocalDateTime applicationDate,
                                       @Param("productId") Integer productId,
                                       @Param("brandId") Integer brandId);
    }