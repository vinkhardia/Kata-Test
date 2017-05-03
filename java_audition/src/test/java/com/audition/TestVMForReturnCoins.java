package com.audition;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/*This test case is to check the return Tray and Display Message when Return Coins is Pressed.*/
public class TestVMForReturnCoins {
	
	
	@Before
	public void setup(){
		/*First Person buying chips. Chip Count is 2. */
		VendingMachine.initialize();
	}
	
	
	/*Test the Return Coin Feature when User presses the Return coin Button without inserting coin*/
	@Test
	public void testReturnCoinsWithoutInsertingCoins() {
		VendingMachine vm = new VendingMachine();
		
		vm.returnCoins();
		String returnTray=vm.getReturnTray();
		assertEquals("$0.00", returnTray);
		
		String dispMsg = vm.getDisplayMessage();
		assertEquals("INSERT COIN", dispMsg);
		
	}
	
	
	/*Test the Return Coin Feature when User presses the Return coin Button without inserting coin and Only Pressing Select Prod Button*/
	@Test
	public void testReturnCoinsWithoutInsertingCoinsAndJustSelectingProd() {
		VendingMachine vm = new VendingMachine();
		
		vm.selectProduct("cola");
		String returnTray=vm.getReturnTray();
		assertEquals("$0.00", returnTray);
		
		String dispMsg = vm.getDisplayMessage();
		assertEquals("INSERT COIN", dispMsg);
		
	}
	
	
	/*Test the Return Coin Feature when User presses the Return coin Button after selecting the product and inserted amount is < product price*/
	@Test
	public void testReturnCoinsAfterInsertingCoin() {
		VendingMachine vm = new VendingMachine();
		
		vm.insertCoin(25);
		vm.insertCoin(25);
		vm.insertCoin(25);
		vm.selectProduct("cola");
		vm.returnCoins();
		String returnTray=vm.getReturnTray();
		assertEquals("$0.75", returnTray);
		
		String dispMsg = vm.getDisplayMessage();
		assertEquals("INSERT COIN", dispMsg);
		
	}
	
	/*Test the Return Coin Feature when User checks the display after product is selected and Return Coin is NOT PRESSED. 
	 * The Return Coin is pressed when the amount is still less than the prodcut amount.*/
	@Test
	public void TestReturnCoinsAfterPRICEMsgDisplay(){
		

		VendingMachine vm = new VendingMachine();
		//1)50 cents is less than 65 cents. So should display PRICE 0.65. Return Tray has zero amount.
		vm.insertCoin(25);
		vm.insertCoin(25);
		vm.selectProduct("CAndy");
		String dispMsg=vm.getDisplayMessage();
		String returnTray=vm.getReturnTray();
		assertEquals("PRICE $0.65", dispMsg);
		assertEquals("$0.00",returnTray);
		
		//2)Inserted 10 cents. Still 55 cents less than $0.65. So should display PRICE 0.65.Return Tray has zero amount.
		 vm.insertCoin(5);
		 dispMsg=vm.getDisplayMessage();
		 returnTray=vm.getReturnTray();
		 assertEquals("PRICE $0.65", dispMsg);
		 assertEquals("$0.00",returnTray);
		 
		
		//3)If buyer now changes his mind want to get all inserted money.
		 returnTray=vm.returnCoins();
		 assertEquals("$0.55",returnTray);
		 dispMsg=vm.getDisplayMessage();
		 assertEquals("INSERT COIN", dispMsg);
		
		
	}
	
	/*Test the Return Coin Feature when User presses the Return coin just after inserting coin and not selecting product*/
	@Test
	public void testReturnCoinsAfterCoinInsertAndNotSelectingProduct() {
		VendingMachine vm = new VendingMachine();
		
		vm.insertCoin(25);
		vm.insertCoin(25);
		vm.insertCoin(25);
		
		vm.returnCoins();
		String returnTray=vm.getReturnTray();
		assertEquals("$0.75", returnTray);
		
		String dispMsg = vm.getDisplayMessage();
		assertEquals("INSERT COIN", dispMsg);
		
	}
	
	/*Test the Return Coin Feature when User presses the Return coin just after dispensing the product.So the machine first displays Thank You and
	 *  when the message is checked again after pressing return coin(or not pressing return coin) it displays INSERT COIN
	 *  */
	@Test
	public void testReturnCoinsAfterThankYouMsg() {
		VendingMachine vm = new VendingMachine();
		//Chip price is $0.50
		vm.insertCoin(25);
		vm.insertCoin(25);
		vm.selectProduct("chips");
		
		String dispMsg = vm.getDisplayMessage();
		String returnTray=vm.getReturnTray();
		assertEquals("THANK YOU", dispMsg);
		assertEquals("$0.00", returnTray);
		
		//check display message again without pressing the returnCoin Button
		dispMsg = vm.getDisplayMessage();
		assertEquals("INSERT COIN", dispMsg);
		
		//check display message after pressing the returnCoin Button
		vm.returnCoins();
		returnTray=vm.getReturnTray();
		assertEquals("$0.00", returnTray);
		dispMsg = vm.getDisplayMessage();
		assertEquals("INSERT COIN", dispMsg);
		
	}
	

}
