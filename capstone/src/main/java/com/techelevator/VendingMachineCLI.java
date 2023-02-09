package com.techelevator;

import java.math.BigDecimal;

public class VendingMachineCLI {

	public void run() {
		// entry point for the vending machine
	Logger logger = new Logger();
	logger.logTransaction("FEED MONEY", new BigDecimal("5.00"), new BigDecimal("2.00"));
	logger.logTransaction("FEED MONEY", new BigDecimal("5.00"), new BigDecimal("2.00"));


	}

	public static void main(String[] args) {
		VendingMachineCLI cli = new VendingMachineCLI();
		cli.run();
	}
}
