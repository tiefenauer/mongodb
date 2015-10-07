package info.tiefenauer.mongodb;

import static java.util.Arrays.asList;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import org.bson.Document;

public class MongoInsert extends MongoOperation {

	@Override
	public void execute() {
		try {
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
		catch(ParseException parseEx){
			log("Parsing failed");
		}
	}

}
