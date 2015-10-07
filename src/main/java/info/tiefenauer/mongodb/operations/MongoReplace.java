package info.tiefenauer.mongodb.operations;

import static com.mongodb.client.model.Filters.eq;
import static java.util.Arrays.asList;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.result.UpdateResult;

import info.tiefenauer.mongodb.MongoDB;

/**
 * <p>
 * To replace the entire document except for the _id field, pass an entirely new document 
 * as the second argument to the replaceOne method. The replacement document can have different 
 * fields from the original document. In the replacement document, you can omit the _id field 
 * since the _id field is immutable. If you do include the _id field, it must be the same value as the existing value.
 * </p>
 * <strong>IMPORTANT</strong>
 * <p>After the update, the document only contains the field or fields in the replacement document.</p>
 * @author danieltiefenauer
 *
 */
public class MongoReplace extends MongoOperation {

	public MongoReplace() {
		super("replace");
	}

	@Override
	public void execute() {
		
		/**
		 * After the following update, the modified document will only contain the _id field, 
		 * name field, the address field. i.e. the document will not contain the restaurant_id, cuisine, grades, and the borough fields.
		 */
		replace(eq("restaurant_id", "41704620"),
		        new Document("address",
		                new Document()
		                        .append("street", "2 Avenue")
		                        .append("zipcode", "10075")
		                        .append("building", "1480")
		                        .append("coord", asList(-73.9557413, 40.7720266)))
		                .append("name", "Vella 2"));
	}
	
	private UpdateResult replace(Bson filter, Document append) {
		UpdateResult result = db.getCollection(MongoDB.COLLECTION_NAME).replaceOne(filter, append);
		logResultCount(result.getModifiedCount());
		return result;
	}

}
