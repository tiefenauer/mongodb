package info.tiefenauer.mongodb;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.Document;

import com.mongodb.client.FindIterable;

public class Log {
	
	private static final Map<String, Logger> loggers = new HashMap<>();
	
	public static <T> void log(Class<T> clazz, Object obj) {
		Logger logger =  getLogger(clazz);
		logger.debug(obj);
	}

	public static <T> void logSize(Class<T> clazz, FindIterable<Document> iterable) {
		Logger logger =  getLogger(clazz);
		int size = 0;
		for (Document document : iterable) {
			size++;
		}
		logger.debug("Found " + size + " elements");
	}

	private static <T> Logger getLogger(Class<T> clazz) {
		if (!loggers.containsKey(clazz.getName()))
			loggers.put(clazz.getName(), LogManager.getLogger(clazz));
		return loggers.get(clazz.getName());
	}
}
