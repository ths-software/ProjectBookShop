package com.projectshop.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.projectshop.models.AppUser;
import com.projectshop.models.Buy;
import com.projectshop.models.Product;
import com.projectshop.repositories.ProductRepository;
import com.projectshop.storage.StorageService;

@Service
@Primary
public class ProductServiceDB implements ProductService {
	
	@Autowired
	ProductRepository repository;
	
	@Autowired
	StorageService storageService;

	@Override
	public Product insert(Product p) {
		
		return repository.save(p);
	}

	@Override
	public void delete(long id) {
		
		repository.deleteById(id);
		
	}

	@Override
	public void delete(Product p) {
		
		if(!p.getImage().isEmpty())
		{
			storageService.delete(p.getImage());
		}
		
		repository.delete(p);
		
	}

	@Override
	public Product edit(Product p) {
		
		return repository.save(p);
	}

	@Override
	public Product findById(long id) {
		
		return repository.findById(id).orElse(null);
	}


	@Override
	public List<Product> productOfABuy(Buy b) {
		
		return repository.findByBuy(b);
	}

	@Override
	public List<Product> findAll() {
		return repository.findAll();
	}

	/*
	@Override
	public List<Product> productWithOutSales() {
		
		return repository.findByBuyIsNull();
	}
	 */

	@Override
	public List<Product> search(String query) {
		
		return repository.findByNameContainsIgnoreCase(query);
	}


	@Override
	public List<Product> findAllById(List<Long> ids) {
		
		return repository.findAllById(ids);
	}

	@Override
	public List<Product> pagination(Long offset, Long limit) {
		return repository.findWithLimitOffset(offset,limit);
	}

}
