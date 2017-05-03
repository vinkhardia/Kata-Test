package com.audition;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;

/**
 * 
 * This Vending Machine accepts only Nickel, Dime and Quarter as mentioned in the Kata Features.
 * 
 * The Vending Machine has following features:
 * 1) Insert Coin. Validates for 0 amount inserted and display the required message.
 * 2) Select Product and display appropriate message based on the amount in the machine or for sold out product.
 * 3) Returns Change in the return Tray when the inserted amount is more than product price and display appropriate message.
 * 4) Return Coin Feature to return the amount in the return Tray when Return Coin is pressed and display appropriate message. 
 * 5) Displays SOLD Out when any product is out of stock.
 * 
 * Tried to use the decimal format $1.75 to show money amount through out the application.
 */

public class VendingMachine {

	private Double totalAmount = 0.00;
	static Locale locale = new Locale("en", "US");
	static NumberFormat currformat = NumberFormat.getCurrencyInstance(locale);

	private static HashMap<String, Double> products = new HashMap<String, Double>();
	private String displayMessage = "INSERT COIN";
	private String returnTray = "$0.00";

	private int soldOutMsgCount = 0;
	private static boolean soldOutFlag = false;
	private int thankMsgCount = 0;

	final static int PENNIES_PER_NICKEL = 5;
	final static int PENNIES_PER_DIME = 10;
	final static int PENNIES_PER_QUARTER = 25;

	private String productSelected = "";

	private static HashMap<String, Integer> prodQuantity = new HashMap<String, Integer>();

	public static void initialize() {
		products.put("cola", 1.00);
		products.put("chips", 0.50);
		products.put("candy", 0.65);
		prodQuantity.put("cola", 3);
		prodQuantity.put("chips", 2);
		prodQuantity.put("candy", 4);
		soldOutFlag = false;
	}

	/*------------------------------ INSERT COIN -----------------------------*/
	public void insertCoin(int inCoin) {

		if (inCoin == 0) {
			setDisplayMessage("INSERT COIN");
			return;
		}

		if (!((inCoin == PENNIES_PER_NICKEL) || (inCoin == PENNIES_PER_DIME) || (inCoin == PENNIES_PER_QUARTER))) {
			double dbleInCoin = (new Double(inCoin)).doubleValue() / 100;
			setReturnTray(VendingMachine.currformat.format(dbleInCoin));
			return;
		}

		double dbleInCoin = inCoin;
		/*
		 * Converted inserted coin cent amount in decimal format to show the
		 * total money amount in format $1.10 for display Message and Showing
		 * the amount in return Tray.
		 */
		this.totalAmount = this.totalAmount + dbleInCoin / 100;

		/*
		 * In case the product is selected(after an amount is inserted) and if
		 * the amount < price of item and user inserts more coins.
		 */
		String finalResult = "";
		if (!"".equals(getProductSelected()))
			processChange();
		else {
			setDisplayMessage(VendingMachine.currformat.format(this.totalAmount));
		}

	}

	/*---------------------SELECT PRODUCT -----------------------------*/
	public void selectProduct(String inputProduct) {

		if (this.totalAmount == 0) {
			setDisplayMessage("INSERT COIN");
			return;
		}

		if (inputProduct == null)
			return;

		if ("".equals(inputProduct))
			return;

		inputProduct = inputProduct.replaceAll(" ", "");

		inputProduct = inputProduct.toLowerCase();

		if (products.containsKey(inputProduct))
			setProductSelected(inputProduct);

		Integer prodQuantity = VendingMachine.prodQuantity.get(inputProduct);

		// When the selected product is out of stock
		if (prodQuantity <= 0) {
			VendingMachine.soldOutFlag = true;
			setDisplayMessage("SOLD OUT");
			setProductSelected("");
			return;
		}

		Double price = products.get(inputProduct);

		if (price != null)
			processChange();

	}

	/*---------------RETURN COIN BUTTON -----------------------------*/
	public String returnCoins() {

		String returnedAmnt = VendingMachine.currformat.format(this.totalAmount);
		this.setReturnTray(returnedAmnt);
		setDisplayMessage("INSERT COIN");
		this.totalAmount = 0.00;
		setProductSelected("");

		return returnedAmnt;
	}
	
	
	/**
	 * FOR RETURNING CHANGES and when Return Button Pressed, the coins will be returned in this return Tray
	 */
	public String getReturnTray() {
		return returnTray;
	}
	

	/** **
	 * Calculate Change
	 * */ 
	private void processChange() {

		Double price = VendingMachine.products.get(productSelected);

		if (price == null)
			return;

		/*
		 * When amount inserted is less than price of product selected then
		 * display PRICE and the price cost.
		 */
		if (this.totalAmount.doubleValue() < price.doubleValue()) {
			setReturnTray("$0.00");
			setDisplayMessage("PRICE " + VendingMachine.currformat.format(price.doubleValue()));
		}
		/*
		 * When amount inserted is more than price of product selected then
		 * display THANK YOU and return the remaining Amt in the return
		 * tray.Update the product quantity
		 */
		else if (this.totalAmount.doubleValue() > price.doubleValue()) {
			Double change = (totalAmount * 100) - (price * 100);
			change = change / 100;

			updateProdQuantity();

			setDisplayMessage("THANK YOU");
			setReturnTray(VendingMachine.currformat.format(change.doubleValue()));
			setProductSelected("");
			this.totalAmount = 0.00;
		} /*
			 * When amount inserted is same as the price of product selected then
			 * display THANK YOU and update the product quantity.
			 */
		else if (this.totalAmount.doubleValue() == price.doubleValue()) {

			updateProdQuantity();

			setDisplayMessage("THANK YOU");
			setProductSelected("");
			this.totalAmount = 0.00;
			setReturnTray("$0.00");
		}

	}

	private void updateProdQuantity() {

		String prodSlctd = getProductSelected();
		int selProdQnty = VendingMachine.prodQuantity.get(prodSlctd);
		selProdQnty = selProdQnty - 1;
		VendingMachine.prodQuantity.replace(prodSlctd, selProdQnty);

	}

	private String getProductSelected() {
		return productSelected;
	}

	private void setProductSelected(String productSelected) {
		this.productSelected = productSelected;
	}

	private void setDisplayMessage(String displayMessage) {
		this.displayMessage = displayMessage;

	}

	public String getDisplayMessage() {

		checkDisplMsgAfterSoldOutOrThankYouMsg();
		return this.displayMessage;

	}

	public void checkDisplMsgAfterSoldOutOrThankYouMsg() {

		if (soldOutFlag && this.soldOutMsgCount >= 1) {
			if (this.totalAmount > 0)
				setDisplayMessage(VendingMachine.currformat.format(this.totalAmount));
			else {
				setDisplayMessage("INSERT COIN");
				setProductSelected("");
			}

			this.soldOutMsgCount = 0;

		} else if (soldOutFlag && this.soldOutMsgCount == 0)
			this.soldOutMsgCount += 1;

		// This when the user checks the message again without inserting coin or
		// 0 amount in Machine.
		if ("THANK YOU" == this.displayMessage && this.thankMsgCount == 0)
			this.thankMsgCount += 1;
		else if (this.thankMsgCount >= 1 && this.totalAmount == 0) {
			setDisplayMessage("INSERT COIN");
			this.thankMsgCount = 0;

		} else if (this.thankMsgCount >= 1)
			this.thankMsgCount = 0;

	}

	

	/**
	 * @param returnTray
	 *            the returnTray to set
	 */
	public void setReturnTray(String returnTray) {

		this.returnTray = returnTray;
	}

}
