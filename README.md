# mongodb-java-examples
This projects contains a sample project for the code provided in the [Official MongoDB Documentation](http://docs.mongodb.org/getting-started/java/) for Java.

## Prerequisites
1. Install [MongoDB](https://www.mongodb.org/) into a directory of your choice (does not need to be program folder, if you're using Windows). The installation folder will be referenced as `{mongo_home}` here.
2. You should add `{mongo_home}\bin` to your `%PATH%`
3. Run a MongoDB server. Consult the [Manual](http://docs.mongodb.org/manual/) if you're unsure how to do this

## How to run
1. Clone this repository into a folder of your choice. The project folder will be referenced as `{project_home}` here
2. Import the dataset included in this project by running the following command:

    `$ mongoimport.exe --db test --collection restaurants --drop --file {project_home}/dataset.json`

3. Build the project with [Maven](http://maven.apache.org/)
4. run the application with `java MongoDB`

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
