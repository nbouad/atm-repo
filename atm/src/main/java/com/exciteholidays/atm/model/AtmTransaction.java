package com.exciteholidays.atm.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.exciteholidays.atm.constant.Note;
import com.exciteholidays.atm.constant.TransactionRequest;


/**
 * Abstract class representing the all possibles transactions in a ATM. 
 * @author Najate Bouad
 *
 */
public abstract class AtmTransaction {
	
	private static Treasury treasury;
	private static List<Movement> movementsHistorical = new ArrayList<Movement>();
	
	/** Method which log information
	 * @param error
	 */
	public void logMovement(boolean error, TransactionRequest type, int amount, Map<Note, Integer> soldNotes) {
		Date date = new Date();
		Movement mouvement = new Movement(new Timestamp(date.getTime()), soldNotes, amount, type, error);
		movementsHistorical.add(mouvement);
	}
	
	/**
	 * @return the treasury
	 */
	public Treasury getTreasury() {
		return treasury;
	}

	/**
	 * @param treasury the treasury to set
	 */
	public void setTreasury(Treasury treasury) {
		AtmTransaction.treasury = treasury;
	}

	public List<Movement> getMovementsHistorical() {
		return movementsHistorical;
	}

	public void setMovementsHistorical(List<Movement> movementsHistorical) {
		AtmTransaction.movementsHistorical = movementsHistorical;
	}
}
