//Elizabeth Szentmiklossy UCID: 30165216
//Justine Mangaliman UCID: 30164741
//Enzo Mutiso UCID: 30182555
//Abdelrahman Mohamed UCID: 30162037
//Mohammad Mustafa Mehtab UCID: 30189394 
package com.thelocalmarketplace.software;

public class StartSession {
	private boolean sessionStarted = false;
	
	public boolean checkSessionStarted() {
		if (sessionStarted == false) {
			return false;
		} else {
			return true;
		}
	}
	
	// method to start session
	public void startSession() {
		boolean status = checkSessionStarted();
		
		if (status == false) {
			System.out.println("Touch Anywhere to Start");
		} else {
			System.out.println("System already started!");
		}
	}
	
	public void addItem() {
		
	}
}
