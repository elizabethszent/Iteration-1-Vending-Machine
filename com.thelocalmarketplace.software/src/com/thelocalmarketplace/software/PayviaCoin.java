//Elizabeth Szentmiklossy UCID: 30165216
//Justine Mangaliman UCID: 30164741
package com.thelocalmarketplace.software;
import java.math.BigDecimal;
import com.tdc.coin.Coin;
import com.thelocalmarketplace.hardware.CoinTray;


public class PayviaCoin {
	private BigDecimal amount_inserted = BigDecimal.ZERO; //
	private BigDecimal amount_owed; 
	private CoinTray dispenced;
	//inalize value of amount_owed and CoinTray
	public PayviaCoin(BigDecimal total, CoinTray tray ) {
		amount_owed= total;
		dispenced = tray;
	}
	
	
	public boolean MakePayment(Coin money) {
		amount_inserted = amount_inserted.add(money.getValue());
		if (amount_inserted.compareTo(amount_owed)<0){
			return true;
			
		}
		return false;
		 
		
	}
	

}
