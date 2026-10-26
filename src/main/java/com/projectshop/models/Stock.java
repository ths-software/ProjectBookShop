package com.projectshop.models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Stock {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stockId;

    @OneToOne
    private Product product;

    private String seller;

    private int stockQuantity;

    public Stock()
    {}

    public Stock(Product product, int stockQuantity) {
        this.product = product;
        this.stockQuantity = stockQuantity;
    }

    public Stock(Product product, String seller, int stockQuantity) {
        this.product = product;
        this.seller = seller;
        this.stockQuantity = stockQuantity;
    }

    public Long getStockId() {
        return stockId;
    }

    public void setStockId(Long stockId) {
        this.stockId = stockId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Stock stock = (Stock) o;
        return stockQuantity == stock.stockQuantity && Objects.equals(stockId, stock.stockId) && Objects.equals(product, stock.product) && Objects.equals(seller, stock.seller);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stockId, product, seller, stockQuantity);
    }

    @Override
    public String toString() {
        return "Stock{" +
                "stockId=" + stockId +
                ", product=" + product +
                ", seller='" + seller + '\'' +
                ", stockQuantity=" + stockQuantity +
                '}';
    }
}
