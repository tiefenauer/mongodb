package info.tiefenauer.mongodb.operations;

import org.bson.Document;

import com.mongodb.client.result.DeleteResult;

import info.tiefenauer.mongodb.MongoDB;

public class MongoRemove extends MongoOperation {

	public MongoRemove() {
		super("remove");
	}

	@Override
	public void execute() {
		deleteAll();
		
		drop();
	}


	/**
	 * To remove all documents from a collection, pass an empty conditions document {} to the deleteMany method.
	 * The remove all operation only removes the documents from the collection. The collection itself, as well as any indexes for the collection, remain. 
	 * @return
	 */
	private DeleteResult deleteAll() {
		DeleteResult result = db.getCollection(MongoDB.COLLECTION_NAME).deleteMany(new Document());
		logResultCount(result.getDeletedCount());
		return result;
	}
	
	/**
	 * To remove all documents from a collection, it may be more efficient to drop the entire collection, 
	 * including the indexes, and then recreate the collection and rebuild the indexes. Use the drop method to drop a collection, including any indexes.
	 */
	private void drop() {
		
	}

}
