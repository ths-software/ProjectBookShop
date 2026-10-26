package com.projectshop.models;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Product implements Comparable<Product> {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	private float price;
	private String image;

	private String description;

	private int buyQuantity;
	
	@ManyToOne
	private Buy buy;
	
	public Product()
	{
		
	}

	public Product(String name, float price, String image, int buyQuantity) {
		this.name = name;
		this.price = price;
		this.image = image;
		this.buyQuantity = buyQuantity;
	}

	public Product(String name, float price,String description, String image, int buyQuantity) {
		this.name = name;
		this.price = price;
		this.image = image;
		this.buyQuantity = buyQuantity;
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getBuyQuantity() {
		return buyQuantity;
	}

	public void setBuyQuantity(int buyQuantity) {
		this.buyQuantity = buyQuantity;
	}

	public Buy getBuy() {
		return buy;
	}

	public void setBuy(Buy buy) {
		this.buy = buy;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass()) return false;
		Product product = (Product) o;
		return Float.compare(price, product.price) == 0 && Objects.equals(id, product.id) && Objects.equals(name, product.name) && Objects.equals(image, product.image) && Objects.equals(description, product.description);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, price, image, description);
	}

	@Override
	public String toString() {
		return "Product{" +
				"id=" + id +
				", name='" + name + '\'' +
				", price=" + price +
				", image='" + image + '\'' +
				", description='" + description + '\'' +
				", buyQuantity=" + buyQuantity +
				", buy=" + buy +
				'}';
	}

	@Override
	public int compareTo(Product o) {
		Long pid1=o.getId();
		Long pid2=o.getId();

		return pid1.compareTo(pid2);
	}
}
