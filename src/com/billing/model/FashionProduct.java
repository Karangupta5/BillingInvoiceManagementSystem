package com.billing.model;
import com.billing.interfaces.*;

public class FashionProduct extends Product implements Taxable,Discountable{

	public FashionProduct(String name,double price,String category) {
		super(name,price,category);
	}

	@Override
	public double calculateTax() {
		return 0;
	}
	@Override
	public double calculateDiscount() {
		return 0;
	}
}
