package com.utilities;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Loggerload {

	private static boolean root=false;
	
	public static Logger getLogger(Class cls){
		if(root){
			return Logger.getLogger(cls);
		}
		PropertyConfigurator.configure("src/test/resources/schemas/log4j2.properties");
		root = true;
		return Logger.getLogger(cls);
	}
	
	
}



