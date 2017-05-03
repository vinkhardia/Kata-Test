package com.audition;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestInsertCoinWithNOCoins.class, TestVMForEmptyProductSelect.class, TestVMForNullProduct.class,
		TestVMForReturnChange.class, TestVMForReturnCoins.class, TestVMForSoldOutItem.class,
		TestVMWithInvalidCoins.class, TestVMWithInsertedAmtLessThanProdPrice.class,
		TestVMWithValidProductAndValidCoin.class })
public class AllTests {

}
