package com.projectshop.services;

import com.projectshop.models.Product;
import com.projectshop.models.Stock;

public interface StockService {
    public Stock findByProduct(Product product);
    public Stock insert(Stock stock);
    public Stock delete(Stock stock);
}
