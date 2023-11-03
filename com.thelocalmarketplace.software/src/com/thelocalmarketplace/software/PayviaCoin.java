//Elizabeth Szentmiklossy UCID: 30165216
//Justine Mangaliman UCID: 30164741
//Abdelrahman Mohamed UCID: 30162037
package com.thelocalmarketplace.software;
import java.math.BigDecimal;

import com.tdc.IComponent;
import com.tdc.IComponentObserver;
import com.tdc.coin.Coin;
import com.tdc.coin.CoinSlot;
import com.tdc.coin.CoinSlotObserver;
import com.tdc.coin.CoinStorageUnit;
import com.tdc.coin.CoinStorageUnitObserver;
import com.thelocalmarketplace.hardware.CoinTray;



public class PayviaCoin implements CoinStorageUnitObserver {
	private BigDecimal amount_inserted = BigDecimal.ZERO; //
	private BigDecimal amount_owed; 
	private CoinSlot coinSlot;
	private WeightDiscrepancy discrepancy;
	private CoinTray dispenced;
	
	//inalize value of amount_owed and CoinTray
	public PayviaCoin(BigDecimal total, CoinTray tray,  WeightDiscrepancy new_discrepancy, CoinSlot new_coinSlot) {
		amount_owed = total;
		dispenced = tray;
		this.discrepancy = new_discrepancy;
		this.coinSlot = new_coinSlot;
		
//		observer.attach(this);
		
		
	}
	
	
	public boolean MakePayment(Coin money) {
		amount_inserted = amount_inserted.add(money.getValue());
		
		// Check weight discrepancy
		enabled(coinSlot);
		if(!discrepancy.CompareWeight()) {
			disabled(coinSlot);
		} else if (amount_inserted.compareTo(amount_owed)<0){
				return true;
		}
		
		return false;

}


	@Override
	public void enabled(IComponent<? extends IComponentObserver> component) {
		// TODO Auto-generated method stub
		component.enable();
	}


	@Override
	public void disabled(IComponent<? extends IComponentObserver> component) {
		// TODO Auto-generated method stub
		component.disable();
	}


	@Override
	public void turnedOn(IComponent<? extends IComponentObserver> component) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void turnedOff(IComponent<? extends IComponentObserver> component) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void coinsFull(CoinStorageUnit unit) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void coinAdded(CoinStorageUnit unit) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void coinsLoaded(CoinStorageUnit unit) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void coinsUnloaded(CoinStorageUnit unit) {
		// TODO Auto-generated method stub
		
	}
}

