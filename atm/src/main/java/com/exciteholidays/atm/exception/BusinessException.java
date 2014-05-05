package com.exciteholidays.atm.exception;

/**
 * Functional exception
 * 
 * @author Najate Bouad
 * 
 */
public class BusinessException extends Exception {

	private static final long serialVersionUID = -5457244077341344480L;

	public BusinessException(String message) {
		super(message);
	}
}
