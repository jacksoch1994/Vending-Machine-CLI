package com.techelevator;

import java.math.BigDecimal;

public class VendingMachineCLI {

	public void run() {
		// entry point for the vending machine
	Controller vendingMachine = new Controller();
	vendingMachine.start();

	}

	public static void main(String[] args) {
		VendingMachineCLI cli = new VendingMachineCLI();
		cli.run();
	}
}
