package com.billing.app;

import com.billing.service.*;

import java.util.List;
import java.util.Scanner;
import com.billing.model.*;

public class MainApp {
	
	static Scanner sc=new Scanner(System.in);
	
	static Productservice ps=new Productservice();
	static InvoiceService is=new InvoiceService();
	
	private static String readNonEmptyString(Scanner sc, String prompt) {
	    String input;
	    while (true) {
	        System.out.print(prompt);
	        input = sc.nextLine().trim();

	        if (!input.isEmpty()) {
	            return input;
	        }

	        System.out.println("Input cannot be empty. Please try again.");
	    }
	}
	
	private static int readInt(Scanner sc, String prompt) {
	    while (true) {
	        System.out.print(prompt);
	        String input = sc.nextLine().trim();

	        try {
	            return Integer.parseInt(input);
	        } catch (NumberFormatException e) {
	            System.out.println("Invalid number! Please enter a valid integer.");
	        }
	    }
	}
	
	private static double readDouble(Scanner sc, String prompt) {
	    while (true) {
	        System.out.print(prompt);
	        String input = sc.nextLine().trim();

	        try {
	            return Double.parseDouble(input);
	        } catch (NumberFormatException e) {
	            System.out.println("Invalid number! Please enter a valid double.");
	        }
	    }
	}

	public static void main(String[] args) {
		
		boolean check=true;
		while(check) {
			printMenu();
			int choice = readInt(sc,"Enter choice: ");
            switch (choice) {
                case 1:
                    handleAddProduct();
                    break;
                case 2:
                    ps.viewAllProduct();
                    break;
                case 3:
                    handleSearchProduct();
                    break;
                case 4:
                    handleSortProducts();
                    break;
                case 5:
                    handleFilterProducts();
                    break;
                case 6:
                    handleCreateInvoice();
                    break;
                case 7:
                    handleAddProductToInvoice();
                    break;
                case 8:
                    handleViewInvoices();
                    break;
                case 0:
                    System.out.println("Goodbye!");
                    check = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
            System.out.println();
        }
		}
	
	

	
	
	 private static void handleSortProducts() {
		 System.out.println("Sort by: 1=Price , 2=Name A-Z");
	        int opt = readInt(sc,"Option: ");
	        if (opt == 1) ps.sortByPrice();
	        else if (opt == 3) ps.sortByName();
	        else System.out.println("Invalid option.");
	        System.out.println("Sorted. Current list:");
	        ps.viewAllProduct();
	}

	private static void handleFilterProducts() {
		 System.out.println("Filter by: 1=Category, 2=Price Range");
	        int opt = readInt(sc,"Option: ");
	        if (opt == 1) {
	            System.out.println("Enter category: electronics / grocery / fashion");
	            String cat = readNonEmptyString(sc,"Category: ");
	            List<Product> out = ps.filterByCategory(cat);
	            if (out.isEmpty()) System.out.println("No products in this category.");
	            else out.forEach(System.out::println);
	        } else if (opt == 2) {
	            double min = readDouble(sc,"Min price: ");
	            double max = readDouble(sc,"Max price: ");
	            List<Product> out = ps.filterByPriceRange(min, max);
	            if (out.isEmpty()) System.out.println("No products in this price range.");
	            else out.forEach(System.out::println);
	        } else {
	            System.out.println("Invalid option.");
	        }
	}

	private static void handleCreateInvoice() {
		    String name = readNonEmptyString(sc,"Customer name (optional, press enter to skip): ");
	        Invoice inv;
	        if (name.trim().isEmpty()) inv = is.createInvoice();
	        else inv = is.createInvoice(name);
	        System.out.println("Created Invoice ID: " + inv.getInvoiceId());
	}

	private static void handleAddProductToInvoice() {
		    int invoiceId = readInt(sc,"Invoice ID: ");
	        int productId = readInt(sc,"Product ID: ");
	        Product p = ps.getProductById(productId);
	        if(p == null) {
	            System.out.println("Product not found.");
	            return;
	        }
	        is.addProductToInvoice(invoiceId, p);
	        is.calculateInvoiceTotals(invoiceId);
	        System.out.println("Product added and totals recalculated.");
	}

	private static void handleViewInvoices() {
		 List<Invoice> list = is.getAllInvoices();
	        if (list.isEmpty()) {
	            System.out.println("No invoices created.");
	            return;
	        }
	        for (Invoice inv : list) {
	            System.out.println(inv.generateInvoiceText());
	        }
	}

	private static void printMenu() {
	        System.out.println("====== Billing & Invoice System ======");
	        System.out.println("1. Add Product");
	        System.out.println("2. View Products");
	        System.out.println("3. Search Product");
	        System.out.println("4. Sort Products");
	        System.out.println("5. Filter Products");
	        System.out.println("6. Create Invoice");
	        System.out.println("7. Add Product to Invoice");
	        System.out.println("8. View Invoices");
	        System.out.println("0. Exit");
	    }
	 
	 private static void handleAddProduct() {
			System.out.println("Choose product type: 1=Electronics, 2=Grocery, 3=Fashion");
	        int type = readInt(sc,"Type: ");
	        String name = readNonEmptyString(sc,"Name: ");
	        double price = readDouble(sc,"Price: ");
	        try {
	            ps.addProduct(name,price,type);
	            System.out.printf("Added product successfully");
	        } catch (IllegalArgumentException e) {
	            System.out.println("Failed to add product: " + e.getMessage());
	        }
		}
	 
	 private static void handleSearchProduct() {
			int ch=readInt(sc,"Enter 1 for search By Id & 2 for search By Name");
			switch(ch) {
			case 1:
				int id=readInt(sc,"Enter the id ");
				Product p=ps.searchById(id);
				if(p!=null) p.toString();
				break;
			case 2:
				String name=readNonEmptyString(sc,"Enter the name ");
				Product pr=ps.searchByName(name);
				if(pr!=null) System.out.println(pr);
				break;
			default:
				System.out.println("You have entered the wrong choice");
			}
		}

}
