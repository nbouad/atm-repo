package com.exciteholidays.atm;

import static org.fest.assertions.api.Assertions.assertThat;

import java.util.EnumMap;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

import com.exciteholidays.atm.constant.Note;
import com.exciteholidays.atm.constant.TransactionRequest;
import com.exciteholidays.atm.model.Initialisation;


/**
 * Class testing the initialisation of ATM
 * @author Najate Bouad
 *
 */
public class InitialisationTest {

	private Initialisation initialisation = new Initialisation();
	private static int nbNoteInit50;
	private static int nbNoteInit20;
	
	@BeforeClass
	public static void init() {
		nbNoteInit50 = 20000;
		nbNoteInit20 = 30000;
	}

	@Test
	public void initialise_with_no_notes() {

		//Test
		initialisation.initialisationData(null);
		//Assert
		assertThat(initialisation.getTreasury()).isNotNull();
		assertThat(initialisation.getTreasury().getNbNotesInit()).isNotNull();
		assertThat(initialisation.getTreasury().getNbCurrentNotes()).isNotNull();
		assertThat(initialisation.getTreasury().getNbNotesInit()).isNotEmpty();
		assertThat(initialisation.getTreasury().getNbCurrentNotes()).isNotEmpty();
		assertThat(initialisation.getTreasury().getInitAmount()).isZero();
		assertThat(initialisation.getTreasury().getCurrentAmount()).isZero();
		assertThat(initialisation.getMovementsHistorical()).isNotNull();
		assertThat(initialisation.getMovementsHistorical()).isNotEmpty();
		assertThat(initialisation.getMovementsHistorical().get(initialisation.getMovementsHistorical().size() - 1)).isNotNull();
		assertThat(initialisation.getMovementsHistorical().get(initialisation.getMovementsHistorical().size() - 1).isError()).isFalse();
		assertThat(initialisation.getMovementsHistorical().get(initialisation.getMovementsHistorical().size() - 1).getType()).isNotNull();
		assertThat(initialisation.getMovementsHistorical().get(initialisation.getMovementsHistorical().size() - 1).getType()).isEqualTo(TransactionRequest.INIT);
		assertThat(initialisation.getMovementsHistorical().get(initialisation.getMovementsHistorical().size() - 1).getAmount()).isZero();
		assertThat(initialisation.getMovementsHistorical().get(initialisation.getMovementsHistorical().size() - 1).getNbNotes()).isNotNull();
		assertThat(initialisation.getMovementsHistorical().get(initialisation.getMovementsHistorical().size() - 1).getNbNotes()).isNotEmpty();
		for (Note note : Note.values()) {
			assertThat(initialisation.getTreasury().getNbNotesInit().get(note)).isNull();
			assertThat(initialisation.getTreasury().getNbCurrentNotes().get(note)).isNull();
			assertThat(initialisation.getMovementsHistorical().get(initialisation.getMovementsHistorical().size() - 1).getNbNotes().get(note)).isNull();
		}
	}
	
	@Test
	public void initialise_with_notes_empty() {
		//Test
		Map<Note, Integer> notes = new EnumMap<>(Note.class);
		notes.put(Note.FIFTY, null);
		notes.put(Note.TWENTY, null);
		initialisation.initialisationData(notes);
		//Assert
		assertThat(initialisation.getTreasury()).isNotNull();
		assertThat(initialisation.getTreasury().getNbNotesInit()).isNotNull();
		assertThat(initialisation.getTreasury().getNbCurrentNotes()).isNotNull();
		assertThat(initialisation.getTreasury().getNbNotesInit()).isNotEmpty();
		assertThat(initialisation.getTreasury().getNbCurrentNotes()).isNotEmpty();
		assertThat(initialisation.getTreasury().getInitAmount()).isZero();
		assertThat(initialisation.getTreasury().getCurrentAmount()).isZero();
		assertThat(initialisation.getMovementsHistorical()).isNotNull();
		assertThat(initialisation.getMovementsHistorical()).isNotEmpty();
		assertThat(initialisation.getMovementsHistorical().get(initialisation.getMovementsHistorical().size() - 1)).isNotNull();
		assertThat(initialisation.getMovementsHistorical().get(initialisation.getMovementsHistorical().size() - 1).isError()).isFalse();
		assertThat(initialisation.getMovementsHistorical().get(initialisation.getMovementsHistorical().size() - 1).getType()).isNotNull();
		assertThat(initialisation.getMovementsHistorical().get(initialisation.getMovementsHistorical().size() - 1).getType()).isEqualTo(TransactionRequest.INIT);
		assertThat(initialisation.getMovementsHistorical().get(initialisation.getMovementsHistorical().size() - 1).getAmount()).isZero();
		assertThat(initialisation.getMovementsHistorical().get(initialisation.getMovementsHistorical().size() - 1).getNbNotes()).isNotNull();
		assertThat(initialisation.getMovementsHistorical().get(initialisation.getMovementsHistorical().size() - 1).getNbNotes()).isNotEmpty();
		for (Note note : Note.values()) {
			assertThat(initialisation.getTreasury().getNbNotesInit().get(note)).isNull();
			assertThat(initialisation.getTreasury().getNbCurrentNotes().get(note)).isNull();
			assertThat(initialisation.getMovementsHistorical().get(initialisation.getMovementsHistorical().size() - 1).getNbNotes().get(note)).isNull();
		}
	}
	
	@Test
	public void initialise_with_notes() {
		//Test
		Map<Note, Integer> notes = new EnumMap<>(Note.class);
		notes.put(Note.FIFTY, nbNoteInit50);
		notes.put(Note.TWENTY, nbNoteInit20);
		initialisation.initialisationData(notes);
		//Assert
		assertThat(initialisation.getTreasury()).isNotNull();
		assertThat(initialisation.getTreasury().getNbNotesInit()).isNotNull();
		assertThat(initialisation.getTreasury().getNbCurrentNotes()).isNotNull();
		assertThat(initialisation.getTreasury().getNbNotesInit()).isNotEmpty();
		assertThat(initialisation.getTreasury().getNbCurrentNotes()).isNotEmpty();
		assertThat(initialisation.getTreasury().getInitAmount()).isEqualTo(nbNoteInit50*Note.FIFTY.getAmount() + nbNoteInit20*Note.TWENTY.getAmount());
		assertThat(initialisation.getTreasury().getCurrentAmount()).isEqualTo(nbNoteInit50*Note.FIFTY.getAmount() + nbNoteInit20*Note.TWENTY.getAmount());
		assertThat(initialisation.getMovementsHistorical()).isNotNull();
		assertThat(initialisation.getMovementsHistorical()).isNotEmpty();
		assertThat(initialisation.getMovementsHistorical().get(initialisation.getMovementsHistorical().size() - 1)).isNotNull();
		assertThat(initialisation.getMovementsHistorical().get(initialisation.getMovementsHistorical().size() - 1).isError()).isFalse();
		assertThat(initialisation.getMovementsHistorical().get(initialisation.getMovementsHistorical().size() - 1).getType()).isNotNull();
		assertThat(initialisation.getMovementsHistorical().get(initialisation.getMovementsHistorical().size() - 1).getType()).isEqualTo(TransactionRequest.INIT);
		assertThat(initialisation.getMovementsHistorical().get(initialisation.getMovementsHistorical().size() - 1).getAmount()).isEqualTo(nbNoteInit50*Note.FIFTY.getAmount() + nbNoteInit20*Note.TWENTY.getAmount());
		assertThat(initialisation.getMovementsHistorical().get(initialisation.getMovementsHistorical().size() - 1).getNbNotes()).isNotNull();
		assertThat(initialisation.getMovementsHistorical().get(initialisation.getMovementsHistorical().size() - 1).getNbNotes()).isNotEmpty();
		for (Note note : Note.values()) {
			assertThat(initialisation.getTreasury().getNbNotesInit().get(note)).isEqualTo(notes.get(note));
			assertThat(initialisation.getTreasury().getNbCurrentNotes().get(note)).isEqualTo(notes.get(note));
			assertThat(initialisation.getMovementsHistorical().get(initialisation.getMovementsHistorical().size() - 1).getNbNotes().get(note)).isNotNull();
			assertThat(initialisation.getMovementsHistorical().get(initialisation.getMovementsHistorical().size() - 1).getNbNotes().get(note)).isEqualTo(notes.get(note));
		}
	}

}
