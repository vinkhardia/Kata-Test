package com.audition;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.audition.VendingMachine;

/*
 * Only Nickels(5), Dimes (10) and Quarters (25) are allowed in this Vending Macine. Use any input except 5,10 and 25 for this test case.
 * */
public class TestVMWithInvalidCoins {

	@Before
	public void setup(){
		/*First Person buying chips. Chip Count is 2. */
		VendingMachine.initialize();
	}
	
	@Test
	public void testInsertCoin() {
		VendingMachine vm = new VendingMachine();
		vm.insertCoin(1);
		String returnTray=vm.getReturnTray();
		assertEquals("$0.01",returnTray);
	

	}

}
