package com.exciteholidays.atm.factory;

import com.exciteholidays.atm.exception.TechnicalException;
import com.exciteholidays.atm.technical.ConfigurationManager;

/**
 * Factory permitting to create the manager configuration
 * 
 * @author Najate Bouad
 * 
 */
public class ConfigManagerFactory {

	private static ConfigurationManager configManager;
	
	/**
	 * Method creating the unique config manager
	 * @return the config manager
	 * @throws TechnicalException
	 */
	public static ConfigurationManager getConfigManager() {
		if (configManager == null) {
			try {
				configManager =  new ConfigurationManager();
			} catch (TechnicalException e) {
			}
		}
		return configManager;
	}

}
