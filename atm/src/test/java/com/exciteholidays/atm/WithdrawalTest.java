package com.exciteholidays.atm;

import static org.fest.assertions.api.Assertions.assertThat;

import java.util.EnumMap;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

import com.exciteholidays.atm.constant.Note;
import com.exciteholidays.atm.exception.BusinessException;
import com.exciteholidays.atm.model.Treasury;
import com.exciteholidays.atm.model.WithdrawalInquiry;


/**
 * Class testing the cashout
 * @author Najate Bouad
 *
 */
public class WithdrawalTest {

	private static WithdrawalInquiry withdrawalInquiry = new WithdrawalInquiry();
	private static Treasury treasury;
	private static int nbNoteInit50;
	private static int nbNoteInit20;
	private static Map<Note, Integer> notes;
	
	@BeforeClass
	public static void init() {
		nbNoteInit50 = 20000;
		nbNoteInit20 = 30000;
		notes = new EnumMap<>(Note.class);
		notes.put(Note.FIFTY, nbNoteInit50);
		notes.put(Note.TWENTY, nbNoteInit20);
		treasury = new Treasury(notes);
		withdrawalInquiry.setTreasury(treasury);
	}

	@Test
	public void withdraw_empty() throws BusinessException {
		//Test
		Map<Note, Integer> notesReceived = withdrawalInquiry.withdrawRequest(0);
		//Assert
		assertThat(notesReceived).isNotNull();
		assertThat(notesReceived).isNotEmpty();
		for (Note note : Note.values()) {
			assertThat(notesReceived.get(note)).isNotNull();
			assertThat(notesReceived.get(note)).isEqualTo(0);
		}
	}
	
	@Test(expected = BusinessException.class)
	public void withdraw_too_much_money() throws BusinessException {
		int amountToWithdraw = nbNoteInit50 * Note.FIFTY.getAmount() + nbNoteInit20 * Note.TWENTY.getAmount() + 1;
		//Test
		withdrawalInquiry.withdrawRequest(amountToWithdraw);
	}
	
	@Test
	public void check_log_mvt_withdrawal_error() {
		int amountToWithdraw = nbNoteInit50 * Note.FIFTY.getAmount() + nbNoteInit20 * Note.TWENTY.getAmount() + 1;
		//Test
		try {
			withdrawalInquiry.withdrawRequest(amountToWithdraw);
		} catch (BusinessException e) {
			assertThat(withdrawalInquiry.getMovementsHistorical()).isNotNull();
			assertThat(withdrawalInquiry.getMovementsHistorical()).isNotEmpty();
			assertThat(withdrawalInquiry.getMovementsHistorical().get(withdrawalInquiry.getMovementsHistorical().size() - 1)).isNotNull();
			assertThat(withdrawalInquiry.getMovementsHistorical().get(withdrawalInquiry.getMovementsHistorical().size() - 1).isError()).isTrue();
			assertThat(withdrawalInquiry.getMovementsHistorical().get(withdrawalInquiry.getMovementsHistorical().size() - 1).getAmount()).isEqualTo(amountToWithdraw);
			assertThat(withdrawalInquiry.getMovementsHistorical().get(withdrawalInquiry.getMovementsHistorical().size() - 1).getNbNotes()).isNull();
		}
	}
	
	@Test
	public void withdraw_all_treasury() throws BusinessException {
		int amountToWithdraw = nbNoteInit50 * Note.FIFTY.getAmount() + nbNoteInit20 * Note.TWENTY.getAmount();
		//Test
		Map<Note, Integer> notesReceived = withdrawalInquiry.withdrawRequest(amountToWithdraw);
		//Assert
		assertThat(notesReceived).isNotNull();
		assertThat(notesReceived).isNotEmpty();
		int amountTotal = 0;
		for (Note note : Note.values()) {
			assertThat(notesReceived.get(note)).isNotNull();
			assertThat(notesReceived.get(note)).isEqualTo(notes.get(note));
			amountTotal += notesReceived.get(note) * note.getAmount();
		}
		assertThat(amountTotal).isEqualTo(amountToWithdraw);
	}
	
	@Test
	public void withdraw_combination_note() throws BusinessException {
		int amountToWithdraw = nbNoteInit50 * Note.FIFTY.getAmount() + nbNoteInit20 * 2;
		//Test
		Map<Note, Integer> notesReceived = withdrawalInquiry.withdrawRequest(amountToWithdraw);
		//Assert
		assertThat(notesReceived).isNotNull();
		assertThat(notesReceived).isNotEmpty();
		int amountTotal = 0;
		for (Note note : notesReceived.keySet()) {
			assertThat(notesReceived.get(note)).isNotNull();
			amountTotal += notesReceived.get(note) * note.getAmount();
		}
		assertThat(amountTotal).isEqualTo(amountToWithdraw);
	}
	
	@Test
	public void withdraw_combination_note_and_delete_too_much_fifty_box() throws BusinessException {
		int amountToWithdraw = (nbNoteInit50 - 1) * Note.FIFTY.getAmount()  + nbNoteInit20 * Note.TWENTY.getAmount();
		//Test
		Map<Note, Integer> notesReceived = withdrawalInquiry.withdrawRequest(amountToWithdraw);
		//Assert
		assertThat(notesReceived).isNotNull();
		assertThat(notesReceived).isNotEmpty();
		int amountTotal = 0;
		for (Note note : notesReceived.keySet()) {
			assertThat(notesReceived.get(note)).isNotNull();
			amountTotal += notesReceived.get(note) * note.getAmount();
		}
		assertThat(amountTotal).isEqualTo(amountToWithdraw);
	}
	
	@Test(expected = BusinessException.class)
	public void withdraw_with_combinaison_impossible() throws BusinessException{
		int amountToWithdraw = nbNoteInit50 * Note.FIFTY.getAmount() + nbNoteInit20 * Note.TWENTY.getAmount() - 6;
		//Test
		withdrawalInquiry.withdrawRequest(amountToWithdraw);
	}
	

}
