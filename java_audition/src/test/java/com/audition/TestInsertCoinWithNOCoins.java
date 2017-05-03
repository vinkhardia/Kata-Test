package com.audition;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.audition.VendingMachine;

public class TestInsertCoinWithNOCoins {

	@Before
	public void setup(){
		/*First Person buying chips. Chip Count is 2. */
		VendingMachine.initialize();
	}
	
	@Test
	public void testInsertCoin() {

		VendingMachine vm = new VendingMachine();
		 vm.insertCoin(0);
		 String dispMsg=vm.getDisplayMessage();
		assertEquals("INSERT COIN", dispMsg);

	}

}
