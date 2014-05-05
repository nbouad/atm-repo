package com.exciteholidays.atm.service.internal;

import java.util.List;
import java.util.Map;

import com.exciteholidays.atm.constant.Note;
import com.exciteholidays.atm.constant.TransactionRequest;
import com.exciteholidays.atm.exception.BusinessException;
import com.exciteholidays.atm.model.Initialisation;
import com.exciteholidays.atm.model.Movement;
import com.exciteholidays.atm.model.MovementHistoricalInquiry;
import com.exciteholidays.atm.model.Treasury;
import com.exciteholidays.atm.model.TreasuryInquiry;
import com.exciteholidays.atm.model.WithdrawalInquiry;
import com.exciteholidays.atm.service.IAtmService;

/**
 * Service ATM containing all the functionalities of an ATM 
 * @author Najate Bouad
 * 
 */
public class AtmService implements IAtmService {

	@Override
	public void initialiseNote(Map<Note, Integer> notes) {
		Initialisation init = new Initialisation();
		init.initialisationData(notes);
		
	}
	@Override
	public void withdrawAmount(int amountToWithdraw) throws BusinessException {
		WithdrawalInquiry wdi = new WithdrawalInquiry();
		Map<Note, Integer> notesToRemove = wdi.withdrawRequest(amountToWithdraw);
		// Update the treasury
		if (notesToRemove != null) {
			for (Note note : notesToRemove.keySet()){
				wdi.getTreasury().getNbCurrentNotes().put(note, wdi.getTreasury().getNbCurrentNotes().get(note) - notesToRemove.get(note));		
			}
			// Log the movement
			wdi.logMovement(false, TransactionRequest.WITHDRAWAL, amountToWithdraw, notesToRemove);
		}
		
	}

	@Override
	public List<Movement> getMovementHistorical() {
		MovementHistoricalInquiry mhi = new MovementHistoricalInquiry();
		return mhi.movementHistoricalRequest();
	}

	@Override
	public Treasury getTreasury() {
		TreasuryInquiry treasury = new TreasuryInquiry();
		return treasury.treasuryRequest();
	}

}
