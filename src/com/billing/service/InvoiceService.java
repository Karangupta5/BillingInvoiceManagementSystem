package com.billing.service;
import java.util.*;
import com.billing.model.*;

public class InvoiceService {
	List<Invoice> invoices;
	
	public InvoiceService() {
		invoices=new ArrayList<Invoice>();
	}
	
	public Invoice createInvoice() {
		Invoice invoice=new Invoice();
		invoices.add(invoice);
		return invoice;
	}

    public Invoice createInvoice(String customerName) {
        Invoice invoice = new Invoice(customerName);
        invoices.add(invoice);
        return invoice;
    }
    public Invoice getInvoiceById(int id) {
    	for(Invoice inv:invoices) {
    		if(inv.getInvoiceId()==id) return inv;
    	}
    	return null;
    }
	public void addProductToInvoice(int invoiceId,Product product) {
		Invoice invoice=getInvoiceById(invoiceId);
		if (invoice == null) {
            System.out.println("Invoice not found: " + invoiceId);
            return;
        }
        if (product == null) {
            System.out.println("Invalid product.");
            return;
        }
        invoice.addProduct(product);
	}
	
	public void calculateInvoiceTotals(int invoiceId) {
        Invoice invoice = getInvoiceById(invoiceId);
        if (invoice == null) {
            System.out.println("Invoice not found: " + invoiceId);
            return;
        }
        invoice.recalculateTotals();
    }

    public List<Invoice> getAllInvoices() {
        return invoices;
    }
}
