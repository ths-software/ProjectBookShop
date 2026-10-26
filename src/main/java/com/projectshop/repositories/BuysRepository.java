package com.projectshop.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectshop.models.AppUser;
import com.projectshop.models.Buy;

public interface BuysRepository extends JpaRepository<Buy, Long> {
	
	List<Buy> findByPropietary(AppUser propietary);

}
