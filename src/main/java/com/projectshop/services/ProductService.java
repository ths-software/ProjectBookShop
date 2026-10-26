package com.projectshop.services;

import java.util.List;

import com.projectshop.models.AppUser;
import com.projectshop.models.Buy;
import com.projectshop.models.Product;

public interface ProductService {
	
	public Product insert(Product p);
	public void delete(long id);
	public void delete(Product p);
	public Product edit(Product p);
	
	public Product findById(long id);
	
	public List<Product> productOfABuy(Buy b);
	// public List<Product> productWithOutSales(); // productos sin ventas realizadas

	public List<Product> findAll();
	
	public List<Product> search(String query);
	
	public List<Product> findAllById(List<Long> ids);

	public List<Product> pagination(Long offset, Long limit);


}
