package com.billing.model;
import com.billing.interfaces.*;

public class FashionProduct extends Product implements Taxable,Discountable{

	public FashionProduct(String name,double price) {
		super(name,price);
	}

	@Override
	public double calculateTax() {
		return getPrice()*0.12;
	}
	@Override
	public double calculateDiscount() {
		return getPrice()*0.10;
	}
	 @Override
	    public String toString() {
	        return String.format("%s | Fashion | Tax: %.2f | Disc: %.2f | Final: %.2f",
	                super.toString(),
	                calculateTax(),
	                calculateDiscount(),
	                getPrice() + calculateTax() - calculateDiscount());
	    }
}
