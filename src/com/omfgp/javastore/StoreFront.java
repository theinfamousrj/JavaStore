package com.omfgp.javastore;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class StoreFront {

	private String name;
	private ArrayList<Item> inventory = new ArrayList<Item>();
	private ArrayList<Item> shoppingList = new ArrayList<Item>();
	private double bill = 0.0;
	DecimalFormat df = new DecimalFormat("0.00");
	
	public StoreFront() {
		
	}
	
	public StoreFront(String name) {
		this.name = name;
		this.inventory = null;
	}
	
	public StoreFront(String name, ArrayList<Item> inventory) {
		this.name = name;
		this.inventory = inventory;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setInventory(ArrayList<Item> inventory) {
		this.inventory = inventory;
	}
	
	public void printHelp() {
		System.out.println("Type /list to list all of the items we have available.");
		System.out.println("Type /buy q i to buy some quantity (q) of the item (i).");
		System.out.println("Type /cart to view the items you've got in your cart.");
		System.out.println("Type /order q p i to order some quantity (q) of the item (i) at a specific price (p).");
		System.out.println("Type /checkout to complete your purchase.");
		System.out.println("Type /help to list all of the commands again.");
	}
	
	public void sellItem(String itemName, int soldQuantity) {
		int index = this.findItemByName(itemName.toLowerCase(), this.inventory);
		if(index != -1) {
			int originalQuantity = this.inventory.get(index).getQuantity();
			int newQuantity = originalQuantity-soldQuantity;
			
			if(newQuantity == 0) {
				this.addToCart(this.inventory.get(index).getName(), this.inventory.get(index).getPrice(), soldQuantity);
				this.inventory.remove(index);
			} else if(newQuantity > 0) {
				this.addToCart(this.inventory.get(index).getName(), this.inventory.get(index).getPrice(), soldQuantity);
				this.inventory.get(index).setQuantity(newQuantity);
			}else {
				System.out.println("Sorry, but we don't have enough " + itemName + ".");
			}
		} else {
			System.out.println("Sorry, but we don't have any " + itemName + ".");
		}
	}
	
	public void addToCart(String name, double price, int quantity) {
		int index = this.findItemByName(name, this.shoppingList);
		if(index != -1) {
			int oldQuantity = this.shoppingList.get(index).getQuantity();
			this.shoppingList.get(index).setQuantity(oldQuantity + quantity);
		} else if(index == -1) {
			Item newItem = new Item(name, price, quantity);
			this.shoppingList.add(newItem);
		}
	}
	
	public void orderItem(String itemName, double itemPrice, int orderedQuantity) {
		int index = this.findItemByName(itemName.toLowerCase(), this.inventory);
		if(index != -1) {
			int originalQuantity = this.inventory.get(index).getQuantity();
			int newQuantity = originalQuantity+orderedQuantity;
			
			this.inventory.get(index).setPrice(itemPrice);
			this.inventory.get(index).setQuantity(newQuantity);
		} else {
			this.inventory.add(new Item(itemName, itemPrice, orderedQuantity));
		}
	}
	
	public void displayInventory() {
		System.out.println("Name\t\tPrice\t\tQuantity");
		for(Item item:this.inventory) {
			if(item.getName().length() > 10) {
				System.out.println(item.getName() + "\t$" + df.format(item.getPrice()) + "\t\t" + item.getQuantity());
			} else {
				System.out.println(item.getName() + "\t\t$" + df.format(item.getPrice()) + "\t\t" + item.getQuantity());
			}
		}
	}
	
	public void displayBill(int checkout) {
		this.bill = 0.0;
		System.out.println("Name\t\tPrice\t\tQuantity");
		for(Item item:this.shoppingList) {
			if(item.getName().length() > 10) {
				System.out.println(item.getName() + "\t$" + df.format(item.getPrice()) + "\t\t" + item.getQuantity());
			} else {
				System.out.println(item.getName() + "\t\t$" + df.format(item.getPrice()) + "\t\t" + item.getQuantity());
			}
			this.bill += item.getPrice()*item.getQuantity();
		}
		if(checkout == 0) {
			System.out.println("=========================================");
			System.out.println("Your total is: $" + df.format(this.bill));
		} else {
			System.out.println("=========================================");
			System.out.println("Subtotal:\t$" + df.format(this.bill));
		}
	}
	
	private int findItemByName(String name, ArrayList<Item> list) {
		for(int i=0; i<list.size(); i++) {
			if(list.get(i).getName().equals(name)) {
				return i;
			}
		}
		return -1;
	}
}
