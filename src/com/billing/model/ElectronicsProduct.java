package com.billing.model;
import com.billing.interfaces.*;
//import com.billing.model.*;

public class ElectronicsProduct extends Product implements Taxable,Discountable {
	
	public ElectronicsProduct(String name,double price,String category) {
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
