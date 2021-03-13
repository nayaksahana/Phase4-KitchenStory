package com.sahana.sportyshoes.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "products")  
public class Products {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "productId")
	private int productId;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "price")
	private float price;

		public Products() {
		super();
	}

	public Products(int productId, String name, float price, int categoryId) {
			super();
			this.productId = productId;
			this.name = name;
			this.price = price;
		}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
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

	@Override
	public String toString() {
		return "Products [productId=" + productId + ", name=" + name + ", price=" + price + ", "
				+ "]";
	}
	
}
