package com.billing.model;
import com.billing.interfaces.*;

public class GroceryProduct extends Product implements Taxable,Discountable {

	@Override
	public double calculateTax() {
		return 0;
	}
	@Override
	public double calculateDiscount() {
		return 0;
	}
}
