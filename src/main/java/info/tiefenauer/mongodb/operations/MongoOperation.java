package info.tiefenauer.mongodb.operations;

import com.mongodb.client.MongoDatabase;

import info.tiefenauer.mongodb.MongoDB;

public abstract class MongoOperation implements IMongoOperation {

	public static MongoDatabase db;
	
	protected String mongoOperationName;

	private String mongoOperationNamePast;
	
	/**
	 * Default constructor
	 * @param mongoOperationName
	 */
	public MongoOperation(String mongoOperationName) {
		db = MongoDB.db;
		this.mongoOperationName = mongoOperationName;
		this.mongoOperationNamePast = mongoOperationName + "d";
	}
	
	/**
	 * Default constructor
	 * @param mongoOperationName
	 * @param mongoOperationNamePast
	 */
	public MongoOperation(String mongoOperationName, String mongoOperationNamePast){
		this(mongoOperationName);
		this.mongoOperationNamePast = mongoOperationNamePast;
	}

	@Override
	public String getMongoOperationName() {
		return mongoOperationName;
	}
	
	@Override
	public String getMongoOperationNamePast(){
		return mongoOperationNamePast;
	}

}
