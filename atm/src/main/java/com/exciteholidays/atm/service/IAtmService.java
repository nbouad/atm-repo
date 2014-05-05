package com.exciteholidays.atm.service;

import java.util.List;
import java.util.Map;

import com.exciteholidays.atm.constant.Note;
import com.exciteholidays.atm.exception.BusinessException;
import com.exciteholidays.atm.model.Movement;
import com.exciteholidays.atm.model.Treasury;


/**
 * ATM Service
 * @author Najate Bouad
 * 
 */
public interface IAtmService {

	void initialiseNote(Map<Note, Integer> notes);
	
	void withdrawAmount(int amountToWithdraw) throws BusinessException;
	
	List<Movement> getMovementHistorical();
	
	Treasury getTreasury();
}
