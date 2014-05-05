package com.exciteholidays.atm;

import java.text.MessageFormat;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import com.exciteholidays.atm.constant.Note;
import com.exciteholidays.atm.constant.TransactionRequest;
import com.exciteholidays.atm.exception.BusinessException;
import com.exciteholidays.atm.factory.ConfigManagerFactory;
import com.exciteholidays.atm.model.Movement;
import com.exciteholidays.atm.model.Treasury;
import com.exciteholidays.atm.service.IAtmService;
import com.exciteholidays.atm.service.internal.AtmService;
import com.exciteholidays.atm.technical.ConfigurationManager;
import com.exciteholidays.atm.technical.properties.AtmProperties;
import com.exciteholidays.atm.utils.PromptTool;

import dnl.utils.text.table.TextTable;

public class Main {

	private static boolean exitRequest = false;
	private static IAtmService atmService = new AtmService();
	private static ConfigurationManager cfm;
	
	public static void main(String[] args){
		cfm = configProperties();
		// First thing to do --> Initialize notes in the ATM
		initialiseNotes();
	    int swValue;
	    // Display menu graphics
	    PromptTool.mainMenu();
	    while(!exitRequest) {
	    	 swValue = PromptTool.inInt(cfm.getProperties().getProperty(AtmProperties.SELECT_OPTION.getLabel()), true);
	    	 TransactionRequest inquiryType = TransactionRequest.find(swValue);
	    	 if (inquiryType == null) {
	 			System.out.println(cfm.getProperties().getProperty(AtmProperties.INCORRECT_REQUEST.getLabel()));
	    	 }
		    switch (inquiryType) {
		    case WITHDRAWAL:
		    	withdrawCash();
		      break;
		    case TREASURY:
		    	treasuryInquiry();
		      break;
		    case MOVEMENTHISTORICAL:
		    	movementInquiry();
		      break;
		    case EXIT:
			      System.out.println();
			      exitRequest = true;
			      break;
		    default:
		    	System.out.println(cfm.getProperties().getProperty(AtmProperties.INCORRECT_REQUEST.getLabel()));
		    }
	    }
	}

	/**
	 * We configure the properties files of the application
	 */
	private static ConfigurationManager configProperties() {
		ConfigurationManager configMan =  ConfigManagerFactory.getConfigManager();
		if (configMan == null) {
			System.out.println("Error fatal");
			System.exit(0);
		}
		return configMan;
	}
	
	/**
	 * print the movement historical
	 */
	private static void movementInquiry() {
		List<Movement> movements = atmService.getMovementHistorical();
		String[] columnNames  = new String [] {cfm.getProperties().getProperty(AtmProperties.MOVEMENT_HISTORICAL_LABEL_DATE.getLabel()),
				cfm.getProperties().getProperty(AtmProperties.MOVEMENT_HISTORICAL_LABEL_TYPE.getLabel()),
				cfm.getProperties().getProperty(AtmProperties.MOVEMENT_HISTORICAL_LABEL_DETAIL.getLabel()), 
				cfm.getProperties().getProperty(AtmProperties.MOVEMENT_HISTORICAL_LABEL_OK_KO.getLabel())} ;
		String[][] data  = new String[movements.size()][4];
		Movement m = null;
		for(int i=0; i<data.length; i++){
			m = movements.get(i);
			if (m != null){
				StringBuffer sb = new StringBuffer();
				data[i][0] = m.getTimestamp().toString();
				data[i][1] = m.getType().getLabel();
				if (m.isError()) {
					buildTabTransactionKO(data, m, i, sb);
				}
				else {
					buildTabTransactionOK(data, m, i, sb);
				}
			}
		}
		TextTable tt = new TextTable(columnNames, data);                                                         
		tt.printTable();   
	}

	/**
	 * Build of data for a transaction in success
	 * @param data : tab containing all data for one movement
	 * @param m
	 * @param i
	 * @param sb
	 */
	private static void buildTabTransactionOK(String[][] data, Movement m,
			int i, StringBuffer sb) {
		data[i][3] = cfm.getProperties().getProperty(AtmProperties.MOVEMENT_HISTORICAL_OK.getLabel());
		if (m.getType().getCode() == TransactionRequest.WITHDRAWAL.getCode()
					|| m.getType().getCode() == TransactionRequest.INIT.getCode()) {
			sb.append(MessageFormat.format(cfm.getProperties().getProperty(AtmProperties.MOVEMENT_HISTORICAL_AMOUNT.getLabel()), m.getAmount()));
			if (m.getNbNotes() != null) {
				sb.append(" (");
				for (Note n : m.getNbNotes().keySet()){
					sb.append(MessageFormat.format(cfm.getProperties().getProperty(AtmProperties.MOVEMENT_HISTORICAL_NOTE.getLabel()), m.getNbNotes().get(n), n.getAmount()));
				}
				sb.append(")");
			}
			data[i][2] = sb.toString();
		} else {
			data[i][2] = cfm.getProperties().getProperty(AtmProperties.MOVEMENT_HISTORICAL_NA.getLabel());
		}
	}

	/**
	 *  Build of data for a transaction in fail
	 * @param data : tab containing all data for one movement
	 * @param m
	 * @param i
	 * @param sb
	 */
	private static void buildTabTransactionKO(String[][] data, Movement m,
			int i, StringBuffer sb) {
		data[i][3] = cfm.getProperties().getProperty(AtmProperties.MOVEMENT_HISTORICAL_KO.getLabel());
		if (m.getType().getCode() == TransactionRequest.WITHDRAWAL.getCode()) {
			sb.append(MessageFormat.format(cfm.getProperties().getProperty(AtmProperties.MOVEMENT_HISTORICAL_AMOUNT.getLabel()), m.getAmount()));
			data[i][2] = sb.toString();
		} else {
			data[i][2] = cfm.getProperties().getProperty(AtmProperties.MOVEMENT_HISTORICAL_NA.getLabel());
		}
	}

	/**
	 * Request of Balance Inquiry
	 */
	private static void treasuryInquiry() {
		Treasury tr = atmService.getTreasury();
		int amountTotal = tr.getCurrentAmount(); 
		int nb50 = tr.getNbCurrentNotes().get(Note.FIFTY);
		int nb20 = tr.getNbCurrentNotes().get(Note.TWENTY);
		StringBuffer sb = new StringBuffer();
		sb.append(MessageFormat.format(cfm.getProperties().getProperty(AtmProperties.TREASURY_DETAIL.getLabel()), amountTotal, nb50, Note.FIFTY.getAmount(),
				nb20, Note.TWENTY.getAmount()));
		PromptTool.logInfoInPrompt(sb);
	}

	/**
	 * Method asking for the withdrawal inquiry
	 */
	private static void withdrawCash() {
	    int cash = PromptTool.inInt(cfm.getProperties().getProperty(AtmProperties.WITHDRAWAL_REQUEST.getLabel()), false);
		try {
			atmService.withdrawAmount(cash);
			System.out.println(cfm.getProperties().getProperty(AtmProperties.WITHDRAWAL_SUCCESS.getLabel()));
		} catch (BusinessException e) {
			System.out.println(cfm.getProperties().getProperty(AtmProperties.WITHDRAWAL_ERROR.getLabel()));
		}
	}
	
	/**
	 * Method which initialises notes in the ATM
	 */
	private static void initialiseNotes() {
		PromptTool.startInitialisation();
	    int nbFiftyNotes = PromptTool.inInt(
	    		MessageFormat.format(cfm.getProperties().getProperty(AtmProperties.INITIALISATION_REQUEST_NOTE.getLabel()), Note.FIFTY.getAmount())
	    		, false);
	    int nbTwentyNotes = PromptTool.inInt(
	    		MessageFormat.format(cfm.getProperties().getProperty(AtmProperties.INITIALISATION_REQUEST_NOTE.getLabel()), Note.TWENTY.getAmount())
	    		, false);
	    Map<Note, Integer> initNotes = new EnumMap<>(Note.class);
	    initNotes.put(Note.FIFTY, nbFiftyNotes);
	    initNotes.put(Note.TWENTY, nbTwentyNotes);
		atmService.initialiseNote(initNotes);
		PromptTool.endInitialisation();
	}
}
