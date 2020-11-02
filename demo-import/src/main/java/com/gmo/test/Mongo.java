package com.gmo.test;

import com.mongodb.MongoClient;

/**
 * Class for mongodb test connection and import 
 * @author Gledson Oliveira
 *
 */
public class Mongo {

	private int validateMongoConnection(String server, String port) {
		
		try {
			System.out.println("**************************");
			System.out.println("Testing connection.......");
			System.out.println("**************************");
			MongoClient mongoClient = new MongoClient(server, Integer.parseInt(port)); 

			mongoClient.getAddress();
			
			mongoClient.close();
		} catch (Exception e) {
			e.printStackTrace();			
		}
		
		return 0;
	}
	
	public void importFile(String server, String port, String filePath) {
		
		if (validateMongoConnection(server, port) == 0 ) {
			Runtime r = Runtime.getRuntime();
			
			System.out.println("**********************************");
			System.out.println("Importing file into mongodb.......");
			System.out.println("**********************************");
			
			@SuppressWarnings("unused")
			Process p = null;
			
			String command = "mongoimport --host "+server+" --port "+port+" --db demoDB --collection countries --type csv --headerline --file " + filePath;
			try {
				p = r.exec(command);
				
				System.out.println("Database: demoDB");
				System.out.println("Collection: countries");
				System.out.println("**********************************");
			  	System.out.println("Import csv into Database completed!!!");
			  
			} catch (Exception e){
				e.printStackTrace();
			}			
		}
	}
}
