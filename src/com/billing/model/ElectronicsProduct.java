package com.billing.model;

public class ElectronicsProduct extends Product {
	
	public ElectronicsProduct(String name,double price) {
		super(name,price);
	}

	@Override
	public double calculateTax() {
		return getPrice()*0.18;
	}
	
	@Override
	public double calculateDiscount() {
		return (getPrice()>1000)? 500:0;
	}
	@Override
    public String toString() {
        return String.format("%s | Electronics | Tax: %.2f | Disc: %.2f | Final: %.2f",
                super.toString(),
                calculateTax(),
                calculateDiscount(),
                getPrice() + calculateTax() - calculateDiscount());
    }
}
