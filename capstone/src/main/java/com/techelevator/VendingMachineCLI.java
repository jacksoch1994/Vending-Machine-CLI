package com.techelevator;

public class VendingMachineCLI {

	public void run() {
		// entry point for the vending machine
		Inventory inventory = new Inventory();
		inventory.purchase("A1");
		inventory.purchase("A1");
		inventory.purchase("A1");
		inventory.purchase("A1");
		inventory.purchase("A1");
		inventory.purchase("A1");
		inventory.purchase("A1");
		inventory.purchase("A1");
		inventory.purchase("A1");
		inventory.purchase("A1");

		System.out.println(inventory);
	}

	public static void main(String[] args) {
		VendingMachineCLI cli = new VendingMachineCLI();
		cli.run();
	}
}
