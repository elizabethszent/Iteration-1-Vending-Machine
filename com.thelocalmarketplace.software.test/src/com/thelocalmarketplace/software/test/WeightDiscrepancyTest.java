//Elizabeth Szentmiklossy UCID: 30165216
//Justine Mangaliman UCID: 30164741
//Enzo Mutiso UCID: 30182555
//Abdelrahman Mohamed UCID: 30162037
//Mohammad Mustafa Mehtab UCID: 30189394
package com.thelocalmarketplace.software.test;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;

import org.junit.Test;

import com.jjjwelectronics.Item;
import com.jjjwelectronics.Mass;
import com.jjjwelectronics.Numeral;
import com.jjjwelectronics.scale.ElectronicScale;
import com.jjjwelectronics.scanner.Barcode;
import com.jjjwelectronics.scanner.BarcodedItem;
import com.tdc.coin.Coin;
import com.tdc.coin.CoinSlot;
import com.thelocalmarketplace.hardware.Product;
import com.thelocalmarketplace.software.PayviaCoin;
import com.thelocalmarketplace.software.WeightDiscrepancy;

import powerutility.PowerGrid;

/**
 * This class contains JUnit test cases for the WeightDiscrepancy class to verify its functionality.
 * @author Elizabeth Szentmiklossy (UCID: 30165216)
 */
public class WeightDiscrepancyTest {
	
		PowerGrid grid = PowerGrid.instance();
		Numeral[] numerals = new Numeral[]{Numeral.valueOf((byte) 2)};
		
		ElectronicScale listner = new ElectronicScale();
		// Create an instance of WeightDiscrepancy with an expected weight of one gram and the electronic scale listener.
		WeightDiscrepancy discrepancy = new WeightDiscrepancy(Mass.ONE_GRAM, listner);
		Barcode barcode = new Barcode(numerals);
		BarcodedItem item;
		

		/**
	     * Test case to check if the actual weight is equal to the expected weight.
	     */
	    @Test
	    public void EqualTO() {
	    	listner.plugIn(grid);
	    	listner.turnOn();
	    	// Create a BarcodedItem with the specified barcode and one gram of weight.
	    	item = new BarcodedItem(barcode, Mass.ONE_GRAM);
	    	listner.addAnItem(item);
	    	// Verify that the expected and actual weights are equal.
	        assertEquals(discrepancy.CompareWeight(),true);
	    }
	    
	    /**
	     * Test case to check if the actual weight is more than the expected weight.
	     */
	    @Test
	    public void MoreThan() {
	    	listner.plugIn(grid);
	    	listner.turnOn();
	    	Mass mass = new Mass(20);
	    	// Create a BarcodedItem with the specified barcode and a weight of 20 grams.
	    	item = new BarcodedItem(barcode, mass);
	    	listner.addAnItem(item);
	    	// Verify that the expected and actual weights are not equal.
	        assertEquals(discrepancy.CompareWeight(),false);   	
			
		
		}

}
