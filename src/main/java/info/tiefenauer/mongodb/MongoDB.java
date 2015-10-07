package info.tiefenauer.mongodb;

import static java.util.Arrays.asList;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.Document;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;

public class MongoDB {

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

	private static void find() {
		FindIterable<Document> iterable = db.getCollection("restaurants").find();
		iterable.forEach((Block<Document>) logger::debug);
	}

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
