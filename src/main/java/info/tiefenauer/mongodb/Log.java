package info.tiefenauer.mongodb;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import info.tiefenauer.mongodb.operations.IMongoOperation;

public class Log {
	
	private static final Map<String, Logger> loggers = new HashMap<>();
	
	public static <T> void log(Class<T> clazz, Object obj) {
		Logger logger =  getLogger(clazz);
		logger.debug(obj);
	}

	private static <T> Logger getLogger(Class<T> clazz) {
		if (!loggers.containsKey(clazz.getName()))
			loggers.put(clazz.getName(), LogManager.getLogger(clazz));
		return loggers.get(clazz.getName());
	}

	public static void logSize(IMongoOperation operation, String operationNamePast, long size) {
		Logger logger =  getLogger(operation.getClass());
		logger.debug(operationNamePast + " " + size + " elements");		
	}
}
