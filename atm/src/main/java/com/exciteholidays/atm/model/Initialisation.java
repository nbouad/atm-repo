package com.exciteholidays.atm.model;

import java.util.Map;

import com.exciteholidays.atm.constant.Note;
import com.exciteholidays.atm.constant.TransactionRequest;

/**
 * Initialisation of the ATM
 * @author Najate Bouad
 *
 */
public class Initialisation extends AtmTransaction {

	/** method which initialize the treasury
	 * @param initNote
	 */
	public void initialisationData(Map<Note, Integer> initNote){
		Treasury treasury = new Treasury(initNote);
		setTreasury(treasury);
		logMovement(false, TransactionRequest.INIT, treasury.getCurrentAmount(), treasury.getNbNotesInit());
	}
}
