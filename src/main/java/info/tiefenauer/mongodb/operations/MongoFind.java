package info.tiefenauer.mongodb.operations;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.gt;
import static com.mongodb.client.model.Filters.lt;
import static com.mongodb.client.model.Filters.or;
import static java.util.Arrays.asList;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.FindIterable;

import info.tiefenauer.mongodb.MongoDB;

/**
 * To specify a condition on a field within an embedded document, use the dot notation. Dot notation requires quotes around the whole dotted field name.
 * @author danieltiefenauer
 *
 */
public class MongoFind extends MongoOperation {
	
	public MongoFind() {
		super("find", "found");
	}

	/**
	 * Query database 
	 */
	@Override
	public void execute() {
		// query all
		FindIterable<Document> iterable = all();
		logSize(iterable);
		
		/**
		 * query matching
		 */
		find(new Document("borough", "Manhattan"));
		find(eq("borough", "Manhattan"));
		
		/**
		 * query matching embedded document
		 */
		find(new Document("address.zipcode", "10075"));
		find(eq("address.zipcode", "10075"));
		
		/**
		 * query embedded document
		 */
		find(new Document("grades.grade", "B"));
		find(eq("grades.grade", "B"));
		
		/**
		 * query greater than
		 */
		find(new Document("grades.score", new Document("$gt", 30)));
		find(eq("grades.score", new Document("$gt", 30)));
		find(gt("grades.score", 30));
		
		/**
		 * query less than
		 */
		find(new Document("grades.score", new Document("$lt", 10)));
		find(eq("grades.score", new Document("$lt", 10)));
		find(lt("grades.score", 10));
		
		/**
		 * combined conditions: AND
		 */
		find(new Document("cuisine", "Italian").append("address.zipcode", "10075"));
		find(and(eq("cuisine", "Italian"), eq("address.zipcode", "10075")));
		
		/**
		 * combined conditions: OR
		 */
		find(new Document("$or", asList(
					new Document("cuisine", "Italian"),
					new Document("address.zipcode", "10075")
					)
				)
		);
		find(or(eq("cuisine", "Italian"), eq("address.zipcode", "10075")));
	}
	
	public static FindIterable<Document> all() {
		FindIterable<Document> iterable = db.getCollection(MongoDB.COLLECTION_NAME).find();
		return iterable;
	}
	
	private FindIterable<Document> find(Bson bson) {
		FindIterable<Document> iterable = db.getCollection(MongoDB.COLLECTION_NAME).find(bson);
		logSize(iterable);
		return iterable;
	}

}
