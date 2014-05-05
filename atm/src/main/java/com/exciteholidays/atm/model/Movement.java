package com.exciteholidays.atm.model;

import java.sql.Timestamp;
import java.util.Map;

import com.exciteholidays.atm.constant.Note;
import com.exciteholidays.atm.constant.TransactionRequest;


/**
 * Movement when something happen in the ATM
 * @author Najate Bouad
 *
 */
public class Movement {

	private Timestamp timestamp;

	private Map<Note, Integer> nbNotes;
	
	private int amount;
	
	private TransactionRequest type;
	
	private boolean error;
	
	public Movement() {
		super();
	}
	
	public Movement(Timestamp timestamp, Map<Note, Integer> nbNotes, int amount, TransactionRequest type, boolean error) {
		this.timestamp = timestamp;
		this.nbNotes = nbNotes;
		this.type = type;
		this.error = error;
		this.amount = amount;
	}
	
	/**
	 * @return the timeStamp
	 */
	public Timestamp getTimestamp() {
		return timestamp;
	}
	/**
	 * @param timestamp the timeStamp to set
	 */
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	/**
	 * @return the nbNotes
	 */
	public Map<Note, Integer> getNbNotes() {
		return nbNotes;
	}
	/**
	 * @param nbNotes the nbNotes to set
	 */
	public void setNbNotes(Map<Note, Integer> nbNotes) {
		this.nbNotes = nbNotes;
	}

	/**
	 * @return the amount
	 */
	public int getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(int amount) {
		this.amount = amount;
	}

	/**
	 * @return the type
	 */
	public TransactionRequest getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(TransactionRequest type) {
		this.type = type;
	}

	/**
	 * @return the error
	 */
	public boolean isError() {
		return error;
	}

	/**
	 * @param error the error to set
	 */
	public void setError(boolean error) {
		this.error = error;
	}
}
