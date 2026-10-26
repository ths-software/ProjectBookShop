package com.projectshop.repositories;

import java.util.List;

import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;

import com.projectshop.models.AppUser;
import com.projectshop.models.Buy;
import com.projectshop.models.Product;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
	List<Product> findByBuy(Buy buy);
	
	List<Product> findByBuyIsNull();
	List<Product> findByNameContainsIgnoreCaseAndBuyIsNull(String name);
	List<Product> findByNameContainsIgnoreCase(String name);
	//List<Product> findByNameContainsIgnoreCaseAndPropietary(String name, AppUser propietary);



	@Query(value = "SELECT * FROM Product ORDER BY id LIMIT :limit OFFSET :offset", nativeQuery = true)
	List<Product> findWithLimitOffset(@Param("offset") Long offset, @Param("limit") Long limit);
	

}
