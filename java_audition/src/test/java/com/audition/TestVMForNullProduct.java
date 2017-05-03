package com.audition;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestVMForNullProduct {

	@Before
	public void setup(){
		/*First Person buying chips. Chip Count is 2. */
		VendingMachine.initialize();
	}
	
	//Testing NULL product selected after inserting coin
		@Test
		public void testNullProdAfterCoinInsertion() {
			VendingMachine vm = new VendingMachine();
			 vm.insertCoin(10);
			 vm.selectProduct(null);
			 String dispMsg=vm.getDisplayMessage();
			 assertEquals("$0.10", dispMsg);

		}
		
		//Testing NULL product without inserting coin
		@Test
		public void testNullProdWithoutCoinInserted() {
			VendingMachine vm = new VendingMachine();
			 vm.selectProduct(null);
			 String dispMsg=vm.getDisplayMessage();
			 assertEquals("INSERT COIN", dispMsg);

		}

}
