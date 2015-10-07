package info.tiefenauer.mongodb.operations;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.result.UpdateResult;

import info.tiefenauer.mongodb.MongoDB;

/**
 * To change a field value, MongoDB provides update operators, 
 * such as $set to modify values. Some update operators, such as $set, will create the field if the 
 * field does not exist. See the individual update operators reference. 
 * @author danieltiefenauer
 *
 */
public class MongoUpdate extends MongoOperation {

	public MongoUpdate() {
		super("update");
	}

	@Override
	public void execute() {
		/**
		 * update the cuisine field and the $currentDate operator to update the lastModified field with the current date.
		 */
		updateOne(eq("name", "Juni"), 
				new Document("$set", new Document("cuisine", "American (New)"))
				.append("$currentDate", new Document("lastModified", true))
		);
		updateOne(eq("restaurant_id", "41156888"),
				new Document("$set", new Document("address.street", "East 31st Street"))
				);
		
		/**
		 * updates all documents that have address.zipcode field equal to "10016" and cuisine field 
		 * equal to "Other", setting the cuisine field to "Category To Be Determined" and the lastModified field to the current date
		 */
		updateMany(and(eq("address.zipcode", "10016"), eq("cuisine", "Other")),
				new Document("$set", new Document("cuisine", "Category To Be Determined"))
                .append("$currentDate", new Document("lastModified", true)));
		
	}

	/**
	 * Update the first found element
	 * @param filter
	 * @param update
	 * @return
	 */
	private UpdateResult updateOne(Bson filter, Bson update) {
		UpdateResult result = db.getCollection(MongoDB.COLLECTION_NAME).updateOne(filter, update);
		logResultCount(result.getModifiedCount());
		return result;
	}
	
	/**
	 * Update all found elements
	 * @param filter
	 * @param update
	 * @return
	 */
	private UpdateResult updateMany(Bson filter, Bson update) {
		UpdateResult result = db.getCollection(MongoDB.COLLECTION_NAME).updateMany(filter, update);
		logResultCount(result.getModifiedCount());
		return result;
	}
	
}
