package com.projectshop.repositories;

import com.projectshop.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import com.projectshop.models.AppUser;

import java.util.List;

public interface UserRepository extends JpaRepository<AppUser, Long> {
	
	AppUser findFirstByEmail(String email);

	List<AppUser> findByNameContainsIgnoreCase(String name);

}
