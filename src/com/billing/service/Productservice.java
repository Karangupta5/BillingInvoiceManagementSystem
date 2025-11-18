package com.billing.service;
import java.util.*;
import com.billing.model.*;
public class Productservice {
	List<Product> products;
	public Productservice() {
		products=new ArrayList<Product>();
	}
	
	public void addProduct(String name,double price,int type) {
		if(type==1) products.add(new ElectronicsProduct(name,price,"Electronics"));
		else if(type==2) products.add(new FashionProduct(name,price,"Fashion"));
		else if(type==3) products.add(new GroceryProduct(name,price,"Grocery"));
		else {System.out.println("Invalid Product Type");
		return;}
		System.out.println("Product added succesfully");
		}
	public void viewAllProduct() {
		if(products.isEmpty()) {
            System.out.println("No products found.");
            return;
        }
        for (Product p:products) System.out.println(p);
	}
	public void searchById(int id){
		for(Product p:products) {
			if(p.getProductId()==id) {
				System.out.println(p);
				return;
			}
		}
		System.out.println("ProductId is not found \n");
	}
	public void searchByName(String name){
		for(Product p:products) {
			if(p.getName()==name) {
				System.out.println(p);
				return;
			}
		}
		System.out.println("ProductName is not found \n");
	}
	public void sortByPrice() {
		Collections.sort(products,Comparator.comparing(Product::getPrice));
		for (Product p:products) System.out.println(p);
	}
	public void sortByName() {
		Collections.sort(products,Comparator.comparing(Product::getName));
		for (Product p:products) System.out.println(p);
	}
	public void filterByCategory(String category) {
		List<Product> temp=new ArrayList<Product>();
		for(Product p:products)  if(p.getCategory()==category) temp.add(p);
		for(Product p:temp) {
			System.out.println(p);
		}
	}
	public void filterByPriceRange(double min,double max) {
		List<Product> temp=new ArrayList<Product>();
		for(Product p:products) if(p.getPrice()>=min && p.getPrice()<=max) temp.add(p);
		for(Product p:temp) {
			System.out.println(p);
		}
	}
}
