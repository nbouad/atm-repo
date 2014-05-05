package com.exciteholidays.atm.model;

import java.util.EnumMap;
import java.util.Map;

import com.exciteholidays.atm.constant.Note;
import com.exciteholidays.atm.constant.TransactionRequest;
import com.exciteholidays.atm.exception.BusinessException;


/**
 * Class representing the withdrawal inquiry
 * @author Najate Bouad
 *
 */
public class WithdrawalInquiry extends AtmTransaction {

	/**
	 * method which withdraw some cash, reduce the amount of treasury and log the movement                 
	 * @param amountToWithdraw
	 * @throws BusinessException
	 * @return notes
	 */
	public Map<Note, Integer> withdrawRequest(int amountToWithdraw) throws BusinessException{
		int nb50 = 0;
		int nb20 = 0;
		int amountRequested = amountToWithdraw;
		Map<Note, Integer> result = new EnumMap<>(Note.class);
		// First we check if we have $50 enough to dispense the requested cash
		while (amountToWithdraw >= Note.FIFTY.getAmount()) {
			if ( (nb50 + 1) > getTreasury().getNbCurrentNotes().get(Note.FIFTY)) {
				break;
			}
			amountToWithdraw -= Note.FIFTY.getAmount();
			nb50 ++;
		}
		// Case which we have too much $50 for completing the amount with $20 notes
		while (amountToWithdraw % Note.TWENTY.getAmount() != 0 && nb50 > 0) {
			nb50 --;
			amountToWithdraw += Note.FIFTY.getAmount();
		}
		// Finally, we check if we have  $20 enough for completing the amount
		while (amountToWithdraw >= Note.TWENTY.getAmount()) {
			if ( (nb20 + 1) > getTreasury().getNbCurrentNotes().get(Note.TWENTY)) {
				break;
			}
			amountToWithdraw -= Note.TWENTY.getAmount();
			nb20 ++;
		}
		// Case where it is impossible to dispense the cash
		if (amountToWithdraw != 0) {
			logMovement(true, TransactionRequest.WITHDRAWAL, amountRequested, null);
			throw new BusinessException("Sorry, we can not dispenser cash.");
		}
		result.put(Note.FIFTY, nb50);
		result.put(Note.TWENTY, nb20);
		return result ;
	}
}