package com.gmo.test.ws.mongo.connection;

import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoDatabase;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

/**
 * Class for connection with MongoDB
 * 
 * References: mongodb.github.io/mongo-java-driver
 * @author Gledson Olivera
 *
 */
public class MongoDBConnection {
	
	private static MongoClient mongoClient;
	
	private static MongoDatabase mongoDatabase;
    
	private static final String server = "localhost";
	private static final int port = 27017;
	private static final String database = "demoDB";
	//private static final String user = "";
	//private static final String password = "";
	
	private MongoDBConnection(){};
		
	public static MongoDatabase getDatabase(){
		if(mongoClient == null){
			try {
				CodecRegistry pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
		                fromProviders(PojoCodecProvider.builder().automatic(true).build()));
				
				mongoClient = new MongoClient(server, port);
				
				if(mongoDatabase == null)			
					mongoDatabase = mongoClient.getDatabase(database);
				
				mongoDatabase = mongoDatabase.withCodecRegistry(pojoCodecRegistry);
				
			} catch (Exception e) {
				return null;
			}
		}
		
		return mongoDatabase;
	}

	
}
