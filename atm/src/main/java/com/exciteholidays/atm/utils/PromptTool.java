package com.exciteholidays.atm.utils;

import com.exciteholidays.atm.constant.TransactionRequest;
import com.exciteholidays.atm.factory.ConfigManagerFactory;
import com.exciteholidays.atm.technical.properties.AtmProperties;

/**
 * Class representing the "front-end part" of the application and managing the interaction with the user
 * @author Najate Bouad
 *
 */
public class PromptTool {
	
	private PromptTool(){
	}
	
	/**
	 * the printed menu
	 */
	public static void mainMenu() {
		System.out.println("====================================================");
	    System.out.println("|                ATM MENU 			   |");
	    System.out.println("====================================================");
	    System.out.println("|         Options:           		           |");
	    System.out.println("|        		1. WithDrawal 		   |");
	    System.out.println("|        		2. Treasury       	   |");
	    System.out.println("|        		3. Movement Historical     |");
	    System.out.println("|        		4. Exit           	   |");
	    System.out.println("====================================================");
	}
	
	public static void startInitialisation() {
		System.out.println("====================================================");
	    System.out.println("|                INITIALISATION              	   |");
	    System.out.println("====================================================");
	}
	
	public static void endInitialisation() {
		System.out.println("====================================================");
	    System.out.println("|                END OF INITIALISATION       	   |");
	    System.out.println("====================================================");
	}
	
	public static void logInfoInPrompt(StringBuffer sb){
		if (sb != null) {
			System.out.println(sb.toString());
		}
	}
	
	/**
	 * Method to display the user's prompt string
	 * @param prompt
	 */
	private static void printPrompt(String prompt) {
 		System.out.print(prompt);
 		System.out.flush();
 	}

 	/**
 	 * Method to make sure no data is available in the input stream
 	 */
 	private static void inputFlush() {
 		int dummy;
 		try {
	      while ((System.in.available()) != 0)
	        dummy = System.in.read();
	    } catch (java.io.IOException e) {
	      System.out.println(ConfigManagerFactory.getConfigManager().getProperties().getProperty(AtmProperties.INPUT_ERROR.getLabel()));
	    }
 	}

 	public static int inInt(String prompt, boolean isSelectionMenu) {
 		while (true) {
 			inputFlush();
 			printPrompt(prompt);
 			try {
 				int result = Integer.valueOf(inString().trim()).intValue();
 				if (isSelectionMenu && TransactionRequest.find(result) == null) {
 	 				System.out.println(ConfigManagerFactory.getConfigManager().getProperties().getProperty(AtmProperties.INCORRECT_REQUEST.getLabel()));
 				}
 				else {
 					return result;
 				}
 			}
 			catch (NumberFormatException e) {
				System.out.println(ConfigManagerFactory.getConfigManager().getProperties().getProperty(AtmProperties.INCORRECT_REQUEST_TYPE.getLabel()));

 			}
 		}
 	}
  
	private static String inString() {
		int aChar;
		String s = "";
	    boolean finished = false;

	    while (!finished) {
	      try {
	        aChar = System.in.read();
	        if (aChar < 0 || (char) aChar == '\n')
	          finished = true;
	        else if ((char) aChar != '\r')
	          s = s + (char) aChar; // Enter into string
	      }

	      catch (java.io.IOException e) {
	    	  System.out.println(ConfigManagerFactory.getConfigManager().getProperties().getProperty(AtmProperties.INCORRECT_REQUEST_TYPE.getLabel()));
	        finished = true;
	      }
	    }
	    return s;
	} 
}
