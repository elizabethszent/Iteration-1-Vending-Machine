//Elizabeth Szentmiklossy UCID: 30165216
//Justine Mangaliman UCID: 30164741
//Enzo Mutiso UCID: 30182555
//Abdelrahman Mohamed UCID: 30162037
//Mohammad Mustafa Mehtab UCID: 30189394
package com.thelocalmarketplace.software.test;
import com.jjjwelectronics.Mass;
import com.jjjwelectronics.scale.AbstractElectronicScale;
import com.jjjwelectronics.scale.ElectronicScale;
import com.tdc.coin.Coin;
import com.tdc.coin.CoinSlot;
import com.thelocalmarketplace.hardware.CoinTray;
import com.thelocalmarketplace.software.*;

import powerutility.PowerGrid;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;

import org.junit.Test;
/**
 * This class contains JUnit test cases to verify the functionality of the PayviaCoin class.
 * 
 * @author Elizabeth Szentmiklossy (UCID: 30165216)
 
 */
public class PayviaCoinTest {
		ElectronicScale listner = new ElectronicScale();
		WeightDiscrepancy discrepancy = new WeightDiscrepancy(Mass.ZERO, listner);
		CoinSlot coin_slot = new CoinSlot();
		
		PowerGrid grid = PowerGrid.instance();
		
		/**
	     * Test case to verify adding coins and making payments with the PayviaCoin class.
	     */
	    @Test
	    public void AddCoins() {
	    	coin_slot.connect(grid);
	    	coin_slot.activate();
	    	
	    	// Set the default currency to Canadian dollars for testing.
	    	Coin.DEFAULT_CURRENCY = Currency.getInstance(Locale.CANADA); 
	    	
	    	// The starting amount paid
	    	PayviaCoin paymentHandeler = new PayviaCoin(BigDecimal.ZERO, null, discrepancy, coin_slot);
	    	//The amount owed
	    	PayviaCoin payment = new PayviaCoin(BigDecimal.valueOf(3), null, discrepancy, coin_slot);
	    	
	    	// The amount that is being inserted each time
	    	Coin tray = new Coin(BigDecimal.ONE);
	    	
	    	
	    	// Test the MakePayment method with various scenarios.
	        assertEquals(paymentHandeler.MakePayment(tray),false);
	        //One DOLLAR PAID
	        assertEquals(payment.MakePayment(tray),true);
	        //$2 PAID
	        assertEquals(payment.MakePayment(tray),true);
	        //$3 Paid. Total paid so makepayment should return false
	        assertEquals(payment.MakePayment(tray),false);
	        //check for validity
	        assertEquals(payment.MakePayment(tray),false);
	    }
	

}
