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

public class WeightDiscrepancyTest {
	
		PowerGrid grid = PowerGrid.instance();
		Numeral[] numerals = new Numeral[]{Numeral.valueOf((byte) 2)};
		
		ElectronicScale listner = new ElectronicScale();
		
		WeightDiscrepancy discrepancy = new WeightDiscrepancy(Mass.ONE_GRAM, listner);
		Barcode barcode = new Barcode(numerals);
		BarcodedItem item;
		


	    @Test
	    public void EqualTO() {
	    	listner.plugIn(grid);
	    	listner.turnOn();
	    	
	    	item = new BarcodedItem(barcode, Mass.ONE_GRAM);
	    	listner.addAnItem(item);
	    	
	        assertEquals(discrepancy.CompareWeight(),true);
	    }
	    @Test
	    public void MoreThan() {
	    	listner.plugIn(grid);
	    	listner.turnOn();
	    	
	    	Mass mass = new Mass(20);
	    	
	    	item = new BarcodedItem(barcode, mass);
	    	listner.addAnItem(item);
	    	
	        assertEquals(discrepancy.CompareWeight(),false);   	
			
		
		}

}
