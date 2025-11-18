package com.billing.model;
import com.billing.interfaces.*;

public abstract class Product implements Taxable,Discountable {
	private int productId;
	private String name,category;
	private double price;
	private static int counter=1;
	
	public Product() {
		
	}
	public Product(String name,double price,String category) {
		this.productId=counter++;
		this.name=name;
		this.price=price;
		this.category=category;
	}
	public int getProductId() {
		return productId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	@Override
	public double calculateTax() {
		
		return price;	
	}
	@Override
	public double calculateDiscount() {
		
		return price;
	}
	@Override
	public String toString() {
		return "Product [productId=" + productId + ", name=" + name + ", price=" + price + "]";
	}
	
	
	
}
