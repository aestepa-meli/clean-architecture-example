package com.slalom.example.db.mongo;


import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

public class MongoDocker {

	private static MongoClientURI uri;
	private static MongoClient mongoClient;

	public MongoDocker() {

	}

	public static MongoDatabase getDb(){
		if(mongoClient == null){
			uri = new MongoClientURI("mongodb://andres:test@localhost:27017/?authSource=admin");
			mongoClient = new MongoClient(uri);
		}

		return mongoClient.getDatabase("clean-arch");
	}
}
