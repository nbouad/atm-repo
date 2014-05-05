package com.exciteholidays.atm.model;



/**
 * Class representing the ATM's treasury
 * @author Najate Bouad
 *
 */
public class TreasuryInquiry extends AtmTransaction {

	
	/**
	 * return the current treasury
	 * @return Treasury
	 */
	public Treasury treasuryRequest() {
		return getTreasury();
	}
}
