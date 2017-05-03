package com.audition;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestVMForEmptyProductSelect {
    
	@Before
	public void setup(){
		/*First Person buying chips. Chip Count is 2. */
		VendingMachine.initialize();
	}
	
	//Testing Empty product selected after inserting coin
	@Test
	public void testEmptyProdAfterCoinInsertion() {
		VendingMachine vm = new VendingMachine();
		 vm.insertCoin(10);
		 vm.selectProduct("");
		 String dispMsg=vm.getDisplayMessage();
		 assertEquals("$0.10", dispMsg);

	}
	
	//Testing Empty product without inserting coin
	@Test
	public void testEmptyProdWithoutCoinInserted() {
		VendingMachine vm = new VendingMachine();
		 vm.selectProduct("");
		 String dispMsg=vm.getDisplayMessage();
		 assertEquals("INSERT COIN", dispMsg);

	}

}
