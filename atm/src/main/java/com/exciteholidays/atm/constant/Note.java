package com.exciteholidays.atm.constant;

/**
 * Enum about the different type of note
 * @author Najate Bouad
 *
 */
public enum Note {

	TWENTY(20),
	FIFTY(50);
	
	private int	amount;
	
	private Note(int amount) {
		this.amount = amount;
	}
	
	public int getAmount() {
		return amount;
	}
}
