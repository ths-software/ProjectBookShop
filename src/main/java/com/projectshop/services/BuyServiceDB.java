package com.projectshop.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.projectshop.models.AppUser;
import com.projectshop.models.Buy;
import com.projectshop.models.Product;
import com.projectshop.repositories.BuysRepository;
import com.projectshop.repositories.ProductRepository;

@Service
@Primary
public class BuyServiceDB implements BuyService {
	
	@Autowired
	BuysRepository buysRepository;
	
	@Autowired
	ProductRepository productRepository;

	@Override
	public Buy insert(Buy b) {
		
		return buysRepository.save(b);
	}

	@Override
	public Buy insert(AppUser u, Buy b) {
		
		b.setPropietary(u);
		
		return buysRepository.save(b);
	}

	@Override
	public Product addBuyToProduct(Product p, Buy b) {
		
		p.setBuy(b);
		return productRepository.save(p);
	}

	@Override
	public Buy findById(long id) {
		
		return buysRepository.findById(id).orElse(null);
	}

	@Override
	public List<Buy> findAll() {
		
		return buysRepository.findAll();
	}

	@Override
	public List<Buy> findByPropietary(AppUser u) {
		
		return buysRepository.findByPropietary(u);
	}

}
