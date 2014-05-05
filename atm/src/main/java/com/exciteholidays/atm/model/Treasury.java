package com.exciteholidays.atm.model;

import java.util.EnumMap;
import java.util.Map;

import com.exciteholidays.atm.constant.Note;

/**
 * Treasury of the ATM system
 * @author Najate Bouad
 *
 */
public class Treasury {

	private Map<Note, Integer> nbNotesInit;
	private Map<Note, Integer> nbCurrentNotes;
	
	
	public Treasury() {
		super();
	}
	
	/**
	 * Specific constructor for the initialisation of notes
	 * @param nbNotesInit
	 */
	public Treasury(Map<Note, Integer> nbNotesInit) {
		if (nbNotesInit == null) {
			this.nbNotesInit = new EnumMap<>(Note.class);
			for (Note note : Note.values()){
				this.nbNotesInit.put(note, null);
			}
		}
		else {
			this.nbNotesInit = nbNotesInit;			
		}
		this.nbCurrentNotes = new EnumMap<>(this.nbNotesInit);
	}


	/**
	 * @return the currentAmount
	 */
	public int getCurrentAmount() {
		int currentAmount = 0;
		if (nbCurrentNotes != null) {
			for (Map.Entry<Note,Integer> mapAmount : nbCurrentNotes.entrySet()) {
				if (mapAmount.getValue() != null){
					currentAmount = currentAmount + mapAmount.getValue() * mapAmount.getKey().getAmount() ;
				}
			}			
		}
		return currentAmount;
	}

	/**
	 * @return the initAmount
	 */
	public int getInitAmount() {
		int initAmount = 0;
		if (nbNotesInit != null){
			for (Map.Entry<Note,Integer> mapAmount : nbNotesInit.entrySet()) {
				if (mapAmount.getValue() != null){
					initAmount = initAmount + mapAmount.getValue() * mapAmount.getKey().getAmount() ;
				}
			}			
		}
		return initAmount;
	}
	
	/**
	 * @return the nbNotes
	 */
	public Map<Note, Integer> getNbCurrentNotes() {
		return nbCurrentNotes;
	}

	/**
	 * @param nbNotes the nbNotes to set
	 */
	public void setNbCurrentNotes(Map<Note, Integer> nbNotes) {
		this.nbCurrentNotes = nbNotes;
	}

	/**
	 * @return the nbNotesInit
	 */
	public Map<Note, Integer> getNbNotesInit() {
		return nbNotesInit;
	}

	/**
	 * @param nbNotesInit the nbNotesInit to set
	 */
	public void setNbNotesInit(Map<Note, Integer> nbNotesInit) {
		this.nbNotesInit = nbNotesInit;
	}

}
