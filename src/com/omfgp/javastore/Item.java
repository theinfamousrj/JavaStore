/**
 * 
 */
package com.omfgp.javastore;

/**
 * @author theinfamousrj
 *
 */
public class Item {

	private String name;
	private double price;
	private int quantity;
	
	public Item() {
		
	}
	
	public Item(String name, double price, int quantity) {
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		if(name == null) {
			this.catcher("Name");
		} else {
			this.name = name;
		}
	}
	
	public double getPrice() {
		return this.price;
	}
	
	public void setPrice(double price) {
		if(price < 0) {
			this.catcher("Price");
		} else {
			this.price = price;
		}
	}
	
	public int getQuantity() {
		return this.quantity;
	}
	
	public void setQuantity(int quantity) {
		if(quantity < 0) {
			this.catcher("Quantity");
		} else {
			this.quantity = quantity;
		}
	}
	
	public void catcher(String funcName) {
		try {
			throw new Exception();
		} catch (Exception e) {
			System.out.println(funcName + " cannot be less than zero or null.");
		}
	}
	
}
