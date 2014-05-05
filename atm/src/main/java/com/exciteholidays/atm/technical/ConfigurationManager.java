package com.exciteholidays.atm.technical;

import java.io.IOException;
import java.util.Properties;

import com.exciteholidays.atm.exception.TechnicalException;

/**
 * Configuration Manager. 
 * @author Najate Bouad
 *
 */
public class ConfigurationManager {

	private String[] propertiesFiles = new String[] {"atm.properties"};
	private static Properties p = new Properties();

	public ConfigurationManager() throws TechnicalException {
		for (String file : propertiesFiles) {
            try {
				p.load(this.getClass().getClassLoader().getResourceAsStream(file));
			} 
            catch (IOException e) {
            	throw new TechnicalException(e.getMessage(), e);
			}
		}
	}
	
	/**
	 * @return Properties files
	 */
	public Properties getProperties() {
		return p;
	}
}
