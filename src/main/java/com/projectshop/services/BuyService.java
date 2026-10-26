package com.projectshop.services;

import java.util.List;

import com.projectshop.models.AppUser;
import com.projectshop.models.Buy;
import com.projectshop.models.Product;

public interface BuyService {
	
	public Buy insert(Buy b);
	public Buy insert(AppUser u, Buy b);
	
	public Product addBuyToProduct(Product p, Buy b);
	public Buy findById(long id);
	
	public List<Buy> findAll();
	public List<Buy> findByPropietary(AppUser u);

}
