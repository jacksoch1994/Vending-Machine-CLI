package com.techelevator;

public class VendingMachineCLI {

	public void run() {
		// entry point for the vending machine
		Inventory inventory = new Inventory();

		for (String key : inventory.getInventory().keySet()) {
			System.out.println(inventory.getInventory().get(key));


		}
	}

	public static void main(String[] args) {
		VendingMachineCLI cli = new VendingMachineCLI();
		cli.run();
	}
}
