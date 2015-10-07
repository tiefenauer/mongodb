package info.tiefenauer.mongodb;

import static com.mongodb.client.model.Filters.eq;
import static java.util.Arrays.asList;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;

public class MongoDB {
	
	private static final String COLLECTION_NAME = "restaurants";

	private static Logger logger = LogManager.getLogger(MongoDB.class);

	private static MongoClient mongoClient;
	private static MongoDatabase db;

	public static void main(String[] args) throws ParseException {
		mongoClient = new MongoClient();
		db = mongoClient.getDatabase("test");
		logger.debug(db);
		insert();
		find();
		mongoClient.close();
	}

	/**
	 * Query database 
	 */
	private static void find() {
		// query all
		query();
		
		// query matching
		query("borough", "Manhattan");
		
		// query matching (alternative)
		query(eq("borough", "Manhattan"));
		
		// query matching (embedded document)
		query("address.zipcode", "10075");
		
		// query matching (embedded document)
		query(eq("address.zipcode", "10075"));
	}

	private static void query(String key, String value) {
		query(new Document(key, value));
		query(eq(key, value));
	}

	private static void query(Bson bson) {
		query(COLLECTION_NAME, bson);
	}

	private static void query(Document document) {
		query(COLLECTION_NAME, document);
	}

	private static void query() {
		query(COLLECTION_NAME);
	}

	private static void query(String collectionName) {
		FindIterable<Document> iterable = db.getCollection(collectionName).find();
		logSize(iterable);
	}
	
	private static void query(String collectionName, Bson bson) {
		FindIterable<Document> iterable = db.getCollection(collectionName).find(bson);
		logSize(iterable);
	}
	

	private static void logSize(FindIterable<Document> iterable) {
		int size = 0;
		for (Document document : iterable) {
			size++;
		}
		logger.debug("Found " + size + " elements");
	}

	/**
	 * insert
	 * @throws ParseException
	 */
	private static void insert() throws ParseException {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH);
		db.getCollection("restaurants").insertOne(
		        new Document("address",
		                new Document()
		                        .append("street", "2 Avenue")
		                        .append("zipcode", "10075")
		                        .append("building", "1480")
		                        .append("coord", asList(-73.9557413, 40.7720266)))
		                .append("borough", "Manhattan")
		                .append("cuisine", "Italian")
		                .append("grades", asList(
		                        new Document()
		                                .append("date", format.parse("2014-10-01T00:00:00Z"))
		                                .append("grade", "A")
		                                .append("score", 11),
		                        new Document()
		                                .append("date", format.parse("2014-01-16T00:00:00Z"))
		                                .append("grade", "B")
		                                .append("score", 17)))
		                .append("name", "Vella")
		                .append("restaurant_id", "41704620"));
	}

}
