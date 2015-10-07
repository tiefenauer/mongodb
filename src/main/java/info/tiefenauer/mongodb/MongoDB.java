package info.tiefenauer.mongodb;

import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class MongoDB {
	
	public static final String COLLECTION_NAME = "restaurants";

	private static Logger logger = LogManager.getLogger(MongoDB.class);

	private static MongoClient mongoClient;
	private static MongoDatabase db;

	public static void main(String[] args) {
		mongoClient = new MongoClient();
		MongoOperation.db = mongoClient.getDatabase("test");
		logger.debug(db);
		
		Arrays.asList(
				MongoInsert.class, 
				MongoFind.class, 
				MongoSort.class)
		.forEach(clazz -> {
			try {
				clazz.newInstance().execute();
			} catch (Exception e) {
				System.err.println("could not instantiate class " + clazz.getName());
				e.printStackTrace();
			}
		});
		
		mongoClient.close();
	}

}
