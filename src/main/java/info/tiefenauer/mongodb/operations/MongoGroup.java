package info.tiefenauer.mongodb.operations;

import static java.util.Arrays.asList;

import java.util.Arrays;

import org.bson.Document;

import com.mongodb.Block;
import com.mongodb.client.AggregateIterable;

import info.tiefenauer.mongodb.MongoDB;

/**
 * MongoDB can perform aggregation operations, such as grouping by a specified key and evaluating a total or a count for each distinct group.
 * Use the aggregate method to perform a stage-based aggregation. The aggregate method accepts as its argument an array of stages, where each 
 * stage, processed sequentially, describes a data processing step.
 * @author danieltiefenauer
 *
 */
public class MongoGroup extends MongoOperation {

	public MongoGroup() {
		super("group");
	}

	@Override
	public void execute() {
		/*
		 * The following example groups the documents in the restaurants collection by the borough field and 
		 * uses the $sum accumulator to count the documents for each group.
		 */
		aggregate(new Document("$group", new Document("_id", "$borough")
				.append("count", new Document("$sum", 1))));
		
		/*
		 * The following pipeline uses $match to query the restaurants collection for documents with 
		 * borough equal to "Queens" and cuisine equal to Brazilian. Then the $group stage groups the 
		 * matching documents by the address.zipcode field and uses the $sum accumulator to calculate the count. 
		 * $group accesses fields by the field path, which is the field name prefixed by a dollar sign $.
		 */
		match(new Document("$match", new Document("borough", "Queens")
					.append("cuisine", "Brazilian")),
		      new Document("$group", new Document("_id", "$address.zipcode")
		    		  .append("count", new Document("$sum", 1))));
	}

	/**
	 * Use the $group stage to group by a specified key. In the $group stage, specify the group by key 
	 * in the _id field. $group accesses fields by the field path, which is the field name prefixed by a dollar sign $. 
	 * The $group stage can use accumulators to perform calculations for each group. 
	 * @param list
	 * @return
	 */
	private AggregateIterable<Document> aggregate(Document... list) {
		AggregateIterable<Document> result = db.getCollection(MongoDB.COLLECTION_NAME).aggregate(Arrays.asList(list));
		logResultCount(result);
		result.forEach(new Block<Document>() {
		    @Override
		    public void apply(final Document document) {
		        System.out.println(document.toJson());
		    }
		});
		return result;
	}
	
	/**
	 * Use the $match stage to filter documents. $match uses the MongoDB query syntax. 
	 * @param list
	 */
	private void match(Document... list) {
		AggregateIterable<Document> result = db.getCollection(MongoDB.COLLECTION_NAME).aggregate(asList(list));
		logResultCount(result);
		result.forEach(new Block<Document>() {
		    @Override
		    public void apply(final Document document) {
		        System.out.println(document.toJson());
		    }
		});
	}	

}
