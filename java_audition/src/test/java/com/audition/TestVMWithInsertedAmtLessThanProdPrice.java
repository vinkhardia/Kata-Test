package com.audition;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.audition.VendingMachine;

public class TestVMWithInsertedAmtLessThanProdPrice {
	
	VendingMachine vm = new VendingMachine();
	
	
	@Before
	public void setup(){
		/*First Person buying chips. Chip Count is 2. */
		VendingMachine.initialize();
	}
	
	
	 
	@Test
	public void testEnteredPriceLessThanProdPriceScenario() {
		
		/**Testing for Cola. Price of Cola is $1.00 */
		
		//1)75 cents is less than $1.00. So should display PRICE 1.0
		vm.insertCoin(25);
		vm.insertCoin(25);
		vm.insertCoin(25);
		vm.selectProduct("cola");
		String dispMsg=vm.getDisplayMessage();
		assertEquals("PRICE $1.00", dispMsg);
		
		//2)Inserted 10 cents. Still 85 cents less than $1.00. So should display PRICE 1.0
		 vm.insertCoin(10);
		 dispMsg=vm.getDisplayMessage();
		 assertEquals("PRICE $1.00", dispMsg);
		 
		//3)Inserted 10 cents. Still 95 cents less than $1.00. So should display PRICE 1.0
		 vm.insertCoin(10);
		 dispMsg=vm.getDisplayMessage();
		 assertEquals("PRICE $1.00", dispMsg);
		 
		//4)Inserted 10 cents. 100 cents now equals  to  $1.00. So should display THANK YOU
		 vm.insertCoin(5);
		 dispMsg=vm.getDisplayMessage();
		 assertEquals("THANK YOU", dispMsg);

	
	}

}
