//Elizabeth Szentmiklossy UCID: 30165216
//Justine Mangaliman UCID: 30164741
package com.thelocalmarketplace.software;
import java.math.BigDecimal;

import com.jjjwelectronics.IDevice;
import com.jjjwelectronics.IDeviceListener;
import com.jjjwelectronics.Mass;
import com.jjjwelectronics.scale.AbstractElectronicScale;
import com.jjjwelectronics.scale.ElectronicScaleListener;
import com.jjjwelectronics.scale.IElectronicScale;

public class WeightDiscrepancy implements ElectronicScaleListener{
	BigDecimal expectedWeight;
	BigDecimal actualWeight;
	
	public WeightDiscrepancy(BigDecimal eWeight,BigDecimal aWeight, AbstractElectronicScale listner ){
		expectedWeight = eWeight;
		actualWeight =	aWeight;
		listner.register(this);
		
		
	}
	public boolean CompareWeight() {
	if (expectedWeight.compareTo(actualWeight)== 0){
		return true;
	}
		return false;
	}
		
	
	@Override
	public void theMassOnTheScaleHasChanged(IElectronicScale scale, Mass mass) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void theMassOnTheScaleHasExceededItsLimit(IElectronicScale scale) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void theMassOnTheScaleNoLongerExceedsItsLimit(IElectronicScale scale) {
		// TODO Auto-generated method stub
		
		
	}
	@Override
	public void aDeviceHasBeenEnabled(IDevice<? extends IDeviceListener> device) {
	}
	@Override
	public void aDeviceHasBeenDisabled(IDevice<? extends IDeviceListener> device) {
	}
	@Override
	public void aDeviceHasBeenTurnedOn(IDevice<? extends IDeviceListener> device) {
	}
	@Override
	public void aDeviceHasBeenTurnedOff(IDevice<? extends IDeviceListener> device) {
	}

}
