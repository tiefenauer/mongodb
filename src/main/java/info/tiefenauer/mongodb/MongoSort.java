package info.tiefenauer.mongodb;

import static com.mongodb.client.model.Sorts.ascending;

import org.bson.Document;
import org.bson.conversions.Bson;

public class MongoSort extends MongoOperation implements IMongoOperation {

	@Override
	public void execute() {
		sort(new Document("borough", +1).append("address.zipcode", +1));
		sort(ascending("borough", "address.zipcode"));
	}
	
	private void sort(Bson bson) {
		Document first = MongoFind.all().sort(bson).first();
		log(first);
	}

}
