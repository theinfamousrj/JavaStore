/**
 * 
 */
package com.omfgp.javastore;

import java.io.Console;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author theinfamousrj
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintStream out = System.out;
		ArrayList<Item> itemList = new ArrayList<Item>();
		
		itemList.add(new Item("eggs", 1.50, 10));
		itemList.add(new Item("pancake mix", 2.75, 15));
		itemList.add(new Item("milk", 3.50, 20));
		itemList.add(new Item("bacon", 2.00, 20));
		itemList.add(new Item("cheese", 1.00, 5));
		
		StoreFront store = new StoreFront("WolfMart", itemList);
		
		out.println("Welcome to " + store.getName() + "!");
		store.printHelp();
		
		while(true) {
			out.print("input: ");
			String input = in.nextLine();
		
			out.println("\n");
			
			if(input.toLowerCase().contains("/list")) {
				store.displayInventory();
			} else if(input.toLowerCase().contains("/buy")) {
				String[] splitInput = input.split(" ");
				String itemName = splitInput[2];
				if(splitInput.length > 3) {
					for(int i=3; i<splitInput.length; i++) {
						itemName += " " + splitInput[i];
					}
				}
				store.sellItem(itemName, Integer.valueOf(splitInput[1]));
			} else if(input.toLowerCase().contains("/order")) {
				String[] splitInput = input.split(" ");
				String itemName = splitInput[3];
				if(splitInput.length > 4) {
					for(int i=4; i<splitInput.length; i++) {
						itemName += " " + splitInput[i];
					}
				}
				store.orderItem(itemName, Double.valueOf(splitInput[2]), Integer.valueOf(splitInput[1]));
			} else if(input.toLowerCase().contains("/cart")) {
				store.displayBill(1);
			} else if(input.toLowerCase().contains("/checkout")) {
				store.displayBill(0);
			} else if(input.toLowerCase().contains("/help")) {
				store.printHelp();
			}
		}

	}

}
