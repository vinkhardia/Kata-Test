package com.audition;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestVMForSoldOutItem {

	@Before
	public void setup(){
		
		VendingMachine.initialize();
	}
	
		
	/*First Person buying chips. Chip Count is 2. */	
	@Test
	public void testingFirstPersonBuyingChips() {
		VendingMachine vm = new VendingMachine();
		vm.insertCoin(25);
		vm.insertCoin(25);
		vm.selectProduct("chips");
		String dispMsg=vm.getDisplayMessage();
		String returnTray=vm.getReturnTray();
		assertEquals("THANK YOU", dispMsg);
		assertEquals("$0.00",returnTray);
	}
	
	/*Second Person buying chips again. Chip Count is 1 */
	@Test
	public void testingSecondPersonBuyingChips() {
		testingFirstPersonBuyingChips();
		String dispMsg;
		String returnTray;
		/*Second Person buying chips. Chip Count is 1 */
		VendingMachine vm1 = new VendingMachine();
		vm1.insertCoin(25);
		vm1.insertCoin(25);
		vm1.selectProduct("chips");
		dispMsg=vm1.getDisplayMessage();
		returnTray=vm1.getReturnTray();
		assertEquals("THANK YOU", dispMsg);
		assertEquals("$0.00",returnTray);
	}
	
	/*Third Person trying to buy chips after inserting some amount when chips is out of stock.The chip count is 0. 
	 * Also testing the display messages after displaying SOLD OUT. */
	@Test
	public void testingThirdPersonBuyingChips() {
		testingSecondPersonBuyingChips();
		VendingMachine vm1 = new VendingMachine();
		String returnTray;
		vm1.insertCoin(25);
		vm1.insertCoin(25);
		vm1.selectProduct("chips");
		String dispMsg1=vm1.getDisplayMessage();
		//First the machine shows sold out message.
		assertEquals("SOLD OUT", dispMsg1);
		//When display message is checked again, it displays the amount in Vending Machine.
		String dispMsg2=vm1.getDisplayMessage();
		assertEquals("$0.50", dispMsg2);
		
		//Now if he wants to get back his entered amount by pressing returnCoin
		String coinsRtnd = vm1.returnCoins();
		String dispMsg3 = vm1.getDisplayMessage();
		assertEquals("INSERT COIN", dispMsg3);
		assertEquals("$0.50",coinsRtnd);
		
		
	}

	
	/*Third Person trying to buy chips without inserting any amount when chips is out of stock.
	 * The chip count is 0. Also testing the display messages after displaying SOLD OUT. */
	@Test
	public void testingThirdPersonBuyingChipsWithoutInsertingAmount() {
		testingSecondPersonBuyingChips();
		VendingMachine vm1 = new VendingMachine();
		String returnTray;
		vm1.selectProduct("chips");
		String dispMsg1=vm1.getDisplayMessage();
		//First the machine shows INSERT COIN.
		assertEquals("INSERT COIN", dispMsg1);
		
		
		//When display message is checked again, it displays the amount in Vending Machine.
		vm1.insertCoin(25);
		String dispMsg2=vm1.getDisplayMessage();
		assertEquals("$0.25", dispMsg2);
		
		//When display message is checked again after selecting the out of stock product.
		vm1.selectProduct("chips");
		String dispMsg3=vm1.getDisplayMessage();
		assertEquals("SOLD OUT", dispMsg3);
		
	   //Checking the message again after display of SOLD OUT
		String dispMsg4=vm1.getDisplayMessage();
		assertEquals("$0.25", dispMsg4);
		
		
	}
	
	
	

}
