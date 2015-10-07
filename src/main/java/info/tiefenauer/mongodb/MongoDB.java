package info.tiefenauer.mongodb;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.util.JSON;

import info.tiefenauer.mongodb.operations.IMongoOperation;
import info.tiefenauer.mongodb.operations.MongoFind;
import info.tiefenauer.mongodb.operations.MongoInsert;
import info.tiefenauer.mongodb.operations.MongoReplace;
import info.tiefenauer.mongodb.operations.MongoSort;
import info.tiefenauer.mongodb.operations.MongoUpdate;

/**
 * @author danieltiefenauer
 */
public class MongoDB {
	
	public static final String COLLECTION_NAME = "restaurants";

	private static Logger logger = LogManager.getLogger(MongoDB.class);

	private static MongoClient mongoClient;
	public static MongoDatabase db;

	public static void main(String[] args) throws IOException {
		mongoClient = new MongoClient();
		db = mongoClient.getDatabase("test");
		logger.debug(db);

		recreateDB();
		Arrays.asList(
				MongoInsert.class, 
				MongoFind.class, 
				MongoSort.class,
				MongoUpdate.class,
				MongoReplace.class)
		.forEach(clazz -> {
			try {
				IMongoOperation operation = clazz.newInstance();
				logger.debug("executing MongoDB operation " + operation.getMongoOperationName());
				operation.execute();
			} catch (Exception e) {
				System.err.println("could not instantiate class " + clazz.getName());
				e.printStackTrace();
			}
		});
		
		mongoClient.close();
	}

	private static void recreateDB() throws IOException {
		logger.info("resetting DB");
		/**
		 * TODO: Find a way to read JSON and insert into DB without deprecated methods
		 */
		DBCollection collection = mongoClient.getDB("test").getCollection(COLLECTION_NAME);
		collection.drop();
		List<DBObject> dbos = new ArrayList<>();
		Files.lines(Paths.get("src/main/resources", "dataset.json"))
			.forEach(line -> {
				dbos.add((DBObject) JSON.parse(line));
			});
		collection.insert(dbos);
	}

}
