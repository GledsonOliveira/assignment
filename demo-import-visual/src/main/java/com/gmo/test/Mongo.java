package com.gmo.test;

import com.mongodb.MongoClient;

/**
 * Class for mongodb test connection and import 
 * @author Gledson Oliveira
 *
 */
public class Mongo {

	public String validateMongoConnection(String server, String port) {
		
		try {
			MongoClient mongoClient = new MongoClient(server, Integer.parseInt(port)); 

			mongoClient.getAddress();
			
			mongoClient.close();
		} catch (Exception e) {
		
			return "Connection MongoDB error. Invalid connection!";
		}
		return null;
	}
	
	public String importFile(String server, String port, String filePath) {
				
		Runtime r = Runtime.getRuntime();
		
		@SuppressWarnings("unused")
		Process p = null;
		
		String command = "mongoimport --host "+server+" --port "+port+" --db demoDB --collection countries --type csv --headerline --file " + filePath;
		try {
			p = r.exec(command);
		  
		  	return "Import csv into Database completed.";
		  
		} catch (Exception e){
			
			return "Error executing " + command + e.toString();
		}			
			
	}
}
