# mongodb-java-examples
This projects contains a sample project for the code provided in the [Official MongoDB Documentation](http://docs.mongodb.org/getting-started/java/) for Java.

## How to run
1. Build with [Maven](http://maven.apache.org/)
2. run with `java MongoDB`

## Project structure
The code is structured in a way so that there is one Java class for each MongoDB operation:
- MongoFind: Query documents from MongoDB
- MongoInsert: Insert documents into MongoDB
- MongoUpdate: Update documents in MongoDB
- MongoReplace: Replace documents in MongoDB as a special form of updating
- MongoRemove: Delete documents from MongoDB or dropping whole collections
- MongoSort: Sorting result sets queried from MongoDB
- MongoGroup: Grouping result sets queried from MongoDB

## Good to know
- The project contains rudimentary logging to see the result of operations in STDOUT. 
- There is a simple class hierarchy to avoid code duplication. 
- The database is rebuilt before each run with the data contained in `src/main/resources/dataset.json`.

(c) 2015 by Daniel Tiefenauer
