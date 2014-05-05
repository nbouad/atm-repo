package com.exciteholidays.atm.model;

import java.util.List;



/**
 * Class representing the movement historical inquiry
 * @author Najate Bouad
 *
 */
public class MovementHistoricalInquiry extends AtmTransaction {

	/**
	 * @return list of Movement Historical
	 */
	public List<Movement> movementHistoricalRequest(){
		return getMovementsHistorical();
	}
}
