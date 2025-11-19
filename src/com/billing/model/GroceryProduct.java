package com.billing.model;

public class GroceryProduct extends Product {

	public GroceryProduct(String name,double price) {
		super(name,price);
	}

	@Override
	public double calculateTax() {
		return getPrice()*0.05;
	}
	@Override
	public double calculateDiscount() {
		return 0;
	}
	@Override
    public String toString() {
        return String.format("%s | Grocery | Tax: %.2f | Disc: %.2f | Final: %.2f",
                super.toString(),
                calculateTax(),
                calculateDiscount(),
                getPrice() + calculateTax() - calculateDiscount());
    }
}
