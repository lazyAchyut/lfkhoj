package com.lftechnology.lfkhoj;

import com.tngtech.configbuilder.annotation.propertyloaderconfiguration.PropertiesFiles;
import com.tngtech.configbuilder.annotation.valueextractor.PropertyValue;

import lombok.Data;
/**
 * This class reads the value of each key from the config.properties file.
 */
@Data
@PropertiesFiles("config")
public class Config {
	
	@PropertyValue("hipchat.uri")
	private String hipchatUri;
	
	@PropertyValue("auth.token")
	private String authToken;
	
	@PropertyValue("hipchat.room.id")
	private String roomId;
}
