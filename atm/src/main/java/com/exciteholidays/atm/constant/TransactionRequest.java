package com.exciteholidays.atm.constant;


/**
 * Enum about the type of transaction request
 * @author Najate Bouad
 *
 */
public enum TransactionRequest {
	
	INIT(0, "Initialisation"),
	WITHDRAWAL(1, "Withdrawal"),
	TREASURY(2, "Treasury"),
	MOVEMENTHISTORICAL(3, "Movement Historical"),
	EXIT(4, "Exit");
	
	
	private int	code;
	private String	label;
	
	private TransactionRequest(int code, String label) {
		this.code = code;
		this.label = label;
	}
	
	public String getLabel() {
		return label;
	}

	public int getCode() {
		return code;
	}
	
	public static TransactionRequest find(int code) {
		for (TransactionRequest transactionRequest : TransactionRequest.values()) {
			if (transactionRequest.getCode() == code && code != 0) {
				return transactionRequest;
			}
		}
		return null;
	}
}
