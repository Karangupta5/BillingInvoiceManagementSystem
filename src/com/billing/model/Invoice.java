package com.billing.model;
import java.time.LocalDateTime;
import java.util.*;

public class Invoice {
	private int invoiceId;
	private List<Product> items;
	private double totalTax,totalDiscount,finalAmount,subTotal;
	private static int invoiceCounter=1;
	private String customerName;
	private LocalDateTime createdAt;
	
	public Invoice() {
		this.items=new ArrayList<Product>();
		this.invoiceId=invoiceCounter++;
		this.subTotal=0;
		this.totalTax=0;
		this.totalDiscount=0;
		this.finalAmount=0;
		this.createdAt = LocalDateTime.now();
	}
	public Invoice(String customerName){
		this.customerName=customerName;
		this.items=new ArrayList<Product>();
		this.invoiceId=invoiceCounter++;
		this.subTotal=0;
		this.totalTax=0;
		this.totalDiscount=0;
		this.finalAmount=0;
		this.createdAt = LocalDateTime.now();
	}
	
	public int getInvoiceId() {
        return invoiceId;
    }

    public List<Product> getItems() {
        return items;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public double getTotalTax() {
        return totalTax;
    }

    public double getTotalDiscount() {
        return totalDiscount;
    }

    public double getFinalAmount() {
        return finalAmount;
    }

    public String getCustomerName() {
        return customerName;
    }

	
	public void addProduct(Product p) {
		if(p!=null){ 
			items.add(p);
			recalculateTotals();
			System.out.println("Product added successfully");
		}
	}
	public void removeProductById(int id) {
		 if (items == null || items.isEmpty()) return;
	        boolean removed = items.removeIf(p -> p.getProductId() == id);
	        if (removed) {
	            recalculateTotals();
	        }
	}
	public void recalculateTotals() {
		subTotal=0;
		totalTax=0;
		totalDiscount=0;
		for(Product p:items) {
			subTotal+=p.getPrice();
			totalTax+=p.calculateTax();
			totalDiscount+=p.calculateDiscount();
			finalAmount=subTotal+totalTax-totalDiscount;
		}
	}
	
	public String generateInvoiceText() {
		StringBuilder sb=new StringBuilder();
		
		sb.append("=========== INVOICE ===========\n");
	    sb.append("Invoice ID: ").append(invoiceId).append("\n");
	    sb.append("Customer: ").append(customerName != null ? customerName : "N/A").append("\n");
	    sb.append("--------------------------------\n");

	    if (items==null) sb.append("No items in this invoice.\n");
	    else{
	        sb.append(String.format("%-5s %-15s %-10s %-10s %-10s\n",
	                "ID", "Name", "Price", "Tax", "Discount"));

	        sb.append("----------------------------------------------\n");

	        for (Product p : items) {
	            sb.append(String.format("%-5d %-15s %-10.2f %-10.2f %-10.2f\n",
	                    p.getProductId(),
	                    p.getName(),
	                    p.getPrice(),
	                    p.calculateTax(),
	                    p.calculateDiscount()));
	        }
	    }

	    sb.append("--------------------------------\n");
	    sb.append(String.format("Subtotal:       %.2f\n", subTotal));
	    sb.append(String.format("Total Tax:      %.2f\n", totalTax));
	    sb.append(String.format("Total Discount: %.2f\n", totalDiscount));
	    sb.append(String.format("Final Amount:   %.2f\n", finalAmount));
	    sb.append("================================\n");

		return sb.toString();
	}
	@Override
    public String toString() {
        return String.format("Invoice[%d] items=%d final=%.2f", invoiceId, items.size(), finalAmount);
    }
}
