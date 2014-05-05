package com.exciteholidays.atm;

import static org.fest.assertions.api.Assertions.assertThat;

import java.util.EnumMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.exciteholidays.atm.constant.Note;
import com.exciteholidays.atm.constant.TransactionRequest;
import com.exciteholidays.atm.model.Treasury;
import com.exciteholidays.atm.model.WithdrawalInquiry;
import com.exciteholidays.atm.service.IAtmService;
import com.exciteholidays.atm.service.internal.AtmService;


/**
 * Class testing the AtmService
 * @author Najate Bouad
 *
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest( {AtmService.class })
public class AtmServiceTest {
	private IAtmService atmService = new AtmService();
	private int nbNoteInit50;
	private int nbNoteInit20;
	private Map<Note, Integer> notes;
	private WithdrawalInquiry withdrawalInquiryMocked;
	private Treasury treasuryMocked;
	
	@Before
	public void setUp() throws Exception{
		nbNoteInit50 = 20000;
		nbNoteInit20 = 30000;
		notes = new EnumMap<>(Note.class);
		notes.put(Note.FIFTY, nbNoteInit50);
		notes.put(Note.TWENTY, nbNoteInit20);
		
		withdrawalInquiryMocked = Mockito.mock(WithdrawalInquiry.class);
		treasuryMocked = Mockito.mock(Treasury.class);
		PowerMockito.doReturn(notes).when(treasuryMocked).getNbCurrentNotes();
		PowerMockito.doReturn(notes).when(treasuryMocked).getNbNotesInit();
		PowerMockito.whenNew(WithdrawalInquiry.class).withAnyArguments().thenReturn(withdrawalInquiryMocked);
		PowerMockito.doReturn(treasuryMocked).when(withdrawalInquiryMocked).getTreasury();
	}
	
	@Test
	public void no_changes_of_treasury() throws Exception {
		//Mock the withdraw and the treasury
		Map<Note, Integer> notesEmpty = new EnumMap<>(Note.class);
		notesEmpty.put(Note.FIFTY, 0);
		notesEmpty.put(Note.TWENTY, 0);
		PowerMockito.doReturn(notesEmpty).when(withdrawalInquiryMocked).withdrawRequest(Mockito.anyInt());
		
		//TEST
		atmService.withdrawAmount(0);
		//Assert
		for (Note note : Note.values()) {
			assertThat(treasuryMocked.getNbCurrentNotes().get(note)).isEqualTo(notes.get(note));
		}
		// We check that there is a WITHDRAWAL movement with $0 given and so no notes
		Mockito.verify(withdrawalInquiryMocked, Mockito.times(1)).logMovement(false, TransactionRequest.WITHDRAWAL, 0, notesEmpty);
	}
	
	@Test
	public void withdraw_of_all_treasury_money() throws Exception {
		//Mock the withdraw and the treasury
		Map<Note, Integer> notesTmp = new EnumMap<>(Note.class);
		notesTmp.put(Note.FIFTY, nbNoteInit50);
		notesTmp.put(Note.TWENTY, nbNoteInit20);
		PowerMockito.doReturn(notesTmp).when(withdrawalInquiryMocked).withdrawRequest(Mockito.anyInt());
		
		int amountToCashout = nbNoteInit20 * Note.TWENTY.getAmount() + nbNoteInit50 * Note.FIFTY.getAmount();
		
		//TEST
		atmService.withdrawAmount(amountToCashout);
		//Assert
		for (Note note : Note.values()) {
			assertThat(treasuryMocked.getNbCurrentNotes().get(note)).isEqualTo(0);
		}
		// We check that there is a WITHDRAWAL movement with $0 given and so no notes
		Mockito.verify(withdrawalInquiryMocked, Mockito.times(1)).logMovement(false, TransactionRequest.WITHDRAWAL, amountToCashout, notesTmp);
	}
	
	@Test
	public void withdraw_of_some_money_of_treasury() throws Exception {
		//Mock the withdraw and the treasury
		Map<Note, Integer> noteInit = new EnumMap<>(Note.class);
		noteInit.put(Note.FIFTY, nbNoteInit50);
		noteInit.put(Note.TWENTY, nbNoteInit20);
		Map<Note, Integer> notesTmp = new EnumMap<>(Note.class);
		notesTmp.put(Note.FIFTY, nbNoteInit50 - 16);
		notesTmp.put(Note.TWENTY, nbNoteInit20 - 100);
		PowerMockito.doReturn(notesTmp).when(withdrawalInquiryMocked).withdrawRequest(Mockito.anyInt());
		
		int amountToCashout = notesTmp.get(Note.TWENTY) * Note.TWENTY.getAmount() + notesTmp.get(Note.FIFTY) * Note.FIFTY.getAmount();
		
		//TEST
		atmService.withdrawAmount(amountToCashout);
		//Assert
		for (Note note : Note.values()) {
			assertThat(treasuryMocked.getNbCurrentNotes().get(note)).isEqualTo(noteInit.get(note) - notesTmp.get(note));
		}
		// We check that there is a WITHDRAWAL movement with $0 given and so no notes
		Mockito.verify(withdrawalInquiryMocked, Mockito.times(1)).logMovement(false, TransactionRequest.WITHDRAWAL, amountToCashout, notesTmp);
	}
}
