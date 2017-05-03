package com.audition;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.audition.VendingMachine;

/*This test case is to check the return Change feature of Vending Machine when the selected product cost is less than the amount inserted.*/
public class TestVMForReturnChange {

	@Before
	public void setup(){
		/*First Person buying chips. Chip Count is 2. */
		VendingMachine.initialize();
	}
	
	
	@Test
	public void testReturnChange() {
		VendingMachine vm = new VendingMachine();

	
		vm.insertCoin(25);
		vm.insertCoin(25);
		vm.insertCoin(25);
		vm.insertCoin(25);
		vm.insertCoin(25);
		vm.insertCoin(25);
		vm.insertCoin(25);
		vm.selectProduct("Candy");
		
		String change = vm.getReturnTray();
		String dispMsg=vm.getDisplayMessage();

		assertEquals("$1.10", change);
		assertEquals("THANK YOU", dispMsg);
	}

}
