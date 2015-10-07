package info.tiefenauer.mongodb;

import org.bson.Document;

import com.mongodb.client.FindIterable;

public interface IMongoOperation {

	void execute();
	
	default void log(Object obj){
		Log.log(getClass(), obj);
	}
	
	default void logSize(FindIterable<Document> iterable){
		Log.logSize(getClass(), iterable);
	}
	
	
}
