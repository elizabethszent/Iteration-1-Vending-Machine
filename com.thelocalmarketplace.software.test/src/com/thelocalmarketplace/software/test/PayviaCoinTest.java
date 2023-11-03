//Elizabeth Szentmiklossy UCID:30165216
// Justine Mangaliman UCID: 30164741
// Abdelrahman Mohamed UCID: 30162037
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

public class PayviaCoinTest {
		ElectronicScale listner = new ElectronicScale();
		WeightDiscrepancy discrepancy = new WeightDiscrepancy(Mass.ZERO, listner);
		CoinSlot coin_slot = new CoinSlot();
		
		PowerGrid grid = PowerGrid.instance();
	

	    @Test
	    public void AddCoins() {
	    	coin_slot.connect(grid);
	    	coin_slot.activate();
	    	
	    	
	    	Coin.DEFAULT_CURRENCY = Currency.getInstance(Locale.CANADA); 
	    	PayviaCoin paymentHandeler = new PayviaCoin(BigDecimal.ZERO, null, discrepancy, coin_slot);
	    	PayviaCoin payment = new PayviaCoin(BigDecimal.valueOf(3), null, discrepancy, coin_slot);
	    	
	    	Coin tray = new Coin(BigDecimal.ONE);

	        assertEquals(paymentHandeler.MakePayment(tray),false);
	        assertEquals(payment.MakePayment(tray),true);
	        assertEquals(payment.MakePayment(tray),true);
	        assertEquals(payment.MakePayment(tray),false);
	        assertEquals(payment.MakePayment(tray),false);
	    }
	

}
