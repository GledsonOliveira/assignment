package com.gmo.test;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class responsible for read parameters of import 
 * @author Gledson Oliveira
 * 
 * Example
 * 	 java -jar localhost 27017 /opt/file/countries.csv
 *
 */
public class CsvImport {

	public static void main(String[] args) {
		
		//validating all parameters
		if (args.length != 3) {
			System.out.println("Invalid parameters list! You must enter the following parameters: server, port, file");
			return;
		}
		String server = args[0];
		String port   = args[1];
		String filePath  = args[2];
		
	    File file = new File(filePath);
	    
	    if (!file.exists()) {
	    	System.out.println("Invalid path/file!");
	    	return;
	    }
	    
	    Pattern fileExtnPtrn = Pattern.compile("([^\\s]+(\\.(?i)(csv))$)");
	    
	    Matcher mtch = fileExtnPtrn.matcher(filePath);
	    
        if(!mtch.matches()){
        	System.out.println("Invalid path/file!");
	    	return;
        }
        
		Mongo mongo = new Mongo();
		
		mongo.importFile(server, port, filePath);
			
	}

}
