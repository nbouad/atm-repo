package com.exciteholidays.atm.technical.properties;

/**
 * Enum containing the properties of the application
 * @author Najate Bouad
 *
 */
public enum AtmProperties {

	SELECT_OPTION("select.option"),
	INCORRECT_REQUEST("incorrect.request"),
	INCORRECT_REQUEST_TYPE("incorrect.request.type"),
	INPUT_ERROR("input.error"),
	END_TRANSACTION("end.transaction"),
	ERROR_FATAL("error.fatal"),
	MOVEMENT_HISTORICAL_LABEL_OK_KO("movement.historical.label.ok.ko"),
	MOVEMENT_HISTORICAL_LABEL_DATE("movement.historical.label.date"),
	MOVEMENT_HISTORICAL_LABEL_TYPE("movement.historical.label.type"),
	MOVEMENT_HISTORICAL_LABEL_DETAIL("movement.historical.label.details"),
	MOVEMENT_HISTORICAL_OK("movement.historical.ok"),
	MOVEMENT_HISTORICAL_KO("movement.historical.ko"),
	MOVEMENT_HISTORICAL_AMOUNT("movement.historical.amount"),
	MOVEMENT_HISTORICAL_NOTE("movement.historical.note"),
	MOVEMENT_HISTORICAL_NA("movement.historical."),
	TREASURY_DETAIL("treasury.detail"),
	WITHDRAWAL_REQUEST("withdrawal.request"),
	WITHDRAWAL_ERROR("withdrawal.error"),
	WITHDRAWAL_SUCCESS("withdrawal.success"),
	INITIALISATION_REQUEST_NOTE("initialisation.request.note");
			
	private String	label;
	
	private AtmProperties(String label) {
		this.label = label;
	}

	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}
	

}
