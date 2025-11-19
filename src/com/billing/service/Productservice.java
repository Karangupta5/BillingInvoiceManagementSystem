package com.billing.service;
import java.util.*;
import com.billing.model.*;
public class Productservice {
	private List<Product> products;
	public Productservice() {
		products=new ArrayList<Product>();
	}
	
	public void addProduct(String name,double price,int type) {
		if(type==1) products.add(new ElectronicsProduct(name,price));
		else if(type==2) products.add(new FashionProduct(name,price));
		else if(type==3) products.add(new GroceryProduct(name,price));
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
	public Product searchById(int id){
		for(Product p:products) {
			if(p.getProductId()==id)  return p;
		}
		System.out.println("Product is not found \n");
		return null;
	}
	public Product searchByName(String name){
		
		for(Product p:products){
			if(p.getName().equalsIgnoreCase(name))  return p;
		}
		System.out.println("Product is not found \n");
		return null;
	}
	public void sortByPrice() {
		Collections.sort(products,Comparator.comparing(Product::getPrice));
		for (Product p:products) System.out.println(p);
	}
	public void sortByName() {
		Collections.sort(products,Comparator.comparing(Product::getName));
		for (Product p:products) System.out.println(p);
	}
	public List<Product> filterByCategory(String category) {
		List<Product> temp = new ArrayList<>();
        if (category == null) return temp;
        String c = category.toLowerCase();
        for (Product p : products) {
            switch (c) {
                case "electronics":
                    if (p instanceof ElectronicsProduct) temp.add(p);
                    break;
                case "grocery":
                    if (p instanceof GroceryProduct) temp.add(p);
                    break;
                case "fashion":
                    if (p instanceof FashionProduct) temp.add(p);
                    break;
            }
        }
        return temp;
	}
	public List<Product> filterByPriceRange(double min,double max) {
		List<Product> temp=new ArrayList<Product>();
		for(Product p:products) if(p.getPrice()>=min && p.getPrice()<=max) temp.add(p);
		return temp;
	}

	public Product getProductById(int id) {
		for(Product p:products) {
			if(p.getProductId()==id)  return p;
		}
		return null;
	}
}
