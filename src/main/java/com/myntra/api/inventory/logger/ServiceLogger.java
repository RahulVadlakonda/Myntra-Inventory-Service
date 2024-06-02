package com.myntra.api.inventory.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServiceLogger {

	public ServiceLogger() {
		// no argument constructor
	}
	
	/**
	 *  method to get the logger instance from logback factory
	 * @param clazz
	 * @return
	 */
	public static Logger getLogger(Class<? extends Object> clazz){	
		return LoggerFactory.getLogger(clazz);
	}


	/*  ************************ for non persistent logging ***************** */
	public static void info(Class<? extends Object> clazz,String message){
		getLogger(clazz).info(message);
	}

	public static void debug(Class<? extends Object> clazz,String message){
		getLogger(clazz).debug(message);
	}

	public static void error(Class<? extends Object> clazz,String message){
		getLogger(clazz).error(message);
	}
}
