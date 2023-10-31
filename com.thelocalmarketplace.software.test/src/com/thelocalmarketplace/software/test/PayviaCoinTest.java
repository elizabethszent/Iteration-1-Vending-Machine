//Elizabeth Szentmiklossy UCID:30165216
// Justine Mangaliman UCID: 30164741
package com.thelocalmarketplace.software.test;
import com.tdc.coin.Coin;
import com.thelocalmarketplace.hardware.CoinTray;
import com.thelocalmarketplace.software.*;
import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;

import org.junit.Test;

public class PayviaCoinTest {
	
	

	    @Test
	    public void AddCoins() {
	    	Coin.DEFAULT_CURRENCY = Currency.getInstance(Locale.CANADA); 
	    	PayviaCoin paymentHandeler = new PayviaCoin(BigDecimal.ZERO, null);
	    
	    	Coin tray = new Coin(BigDecimal.ONE);

	        assertEquals(paymentHandeler.MakePayment(tray),false);
	    }
	

}
