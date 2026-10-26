package com.projectshop.services;

import com.projectshop.models.Product;
import com.projectshop.models.Stock;
import com.projectshop.repositories.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockServiceDB implements StockService{

    @Autowired
    StockRepository stockRepository;


    @Override
    public Stock findByProduct(Product product) {
        return stockRepository.findByProduct(product);
    }

    @Override
    public Stock insert(Stock stock) {
        return stockRepository.save(stock);
    }

    @Override
    public Stock delete(Stock stock) {
        stockRepository.delete(stock);
        return stock;
    }
}
