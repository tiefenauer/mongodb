package info.tiefenauer.mongodb;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class MongoDB {

	private static Logger logger = LogManager.getLogger(MongoDB.class);

	public static void main(String[] args) {
		MongoClient mongoClient = new MongoClient();
		MongoDatabase db = mongoClient.getDatabase("test");
		logger.info(db);
		mongoClient.close();
	}
	
}
