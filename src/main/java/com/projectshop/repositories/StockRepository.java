package com.projectshop.repositories;

import com.projectshop.models.Product;
import com.projectshop.models.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock,Long> {
    Stock findByProduct(Product product);
}
