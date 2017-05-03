package com.audition;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.audition.VendingMachine;

public class TestVMWithValidProductAndValidCoin {

	@Before
	public void setup(){
		/*First Person buying chips. Chip Count is 2. */
		VendingMachine.initialize();
	}
	
	

	@Test
	public void testingVMWithValidProductAndCoin25Cola() {

		VendingMachine vm = new VendingMachine();
		vm.insertCoin(25);
		vm.insertCoin(25);
		vm.insertCoin(25);
		vm.insertCoin(25);
		vm.selectProduct("cola");
		String dispMsg=vm.getDisplayMessage();
		assertEquals("THANK YOU", dispMsg);
		

	}
	
	@Test
	public void testingVMWithValidProductAndCoin25N10N5Candy() {

		VendingMachine vm = new VendingMachine();
		vm.insertCoin(25);
		vm.insertCoin(25);
		vm.insertCoin(10);
		vm.insertCoin(5);
		vm.selectProduct("candy");
		String dispMsg=vm.getDisplayMessage();
		assertEquals("THANK YOU", dispMsg);
		

	}
	
	@Test
	public void testingVMWithValidProductAndCoin25Chips() {

		VendingMachine vm = new VendingMachine();
		vm.insertCoin(25);
		vm.insertCoin(25);
		vm.selectProduct("chips");
		String dispMsg=vm.getDisplayMessage();
		assertEquals("THANK YOU", dispMsg);
		
	}
}
