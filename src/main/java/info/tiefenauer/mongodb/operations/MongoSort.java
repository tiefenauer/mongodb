package info.tiefenauer.mongodb.operations;

import static com.mongodb.client.model.Sorts.ascending;
import static com.mongodb.client.model.Sorts.descending;

import org.bson.Document;
import org.bson.conversions.Bson;

public class MongoSort extends MongoOperation implements IMongoOperation {

	public MongoSort() {
		super("sort");
	}

	@Override
	public void execute() {
		/**
		 * sort ascending
		 */
		sort(new Document("borough", +1).append("address.zipcode", +1));
		sort(ascending("borough", "address.zipcode"));
		
		/**
		 * sort descending
		 */
		sort(new Document("borough", -1).append("address.building", -1));
		sort(descending("borough", "address.building"));
		
		/**
		 * sort mixed
		 */
		sort(new Document("borough", +1).append("address.street", -1));
	}
	
	private void sort(Bson bson) {
		Document first = MongoFind.all().sort(bson).first();
		log(first);
	}

}
