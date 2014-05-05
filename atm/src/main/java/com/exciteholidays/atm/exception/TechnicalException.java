package com.exciteholidays.atm.exception;

/**
 * Technical Exception
 * 
 * @author Najate Bouad
 * 
 */
public class TechnicalException extends Exception {

	private static final long serialVersionUID = 1844137275274986890L;

	public TechnicalException(String message, Throwable cause) {
		super(message, cause);
	}
}
