package com.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public class ConfigReader {

		Properties property;
		
		/**
		 * call loadConfig while instantiate 
		 * 
		 */
		public ConfigReader() {
			try {
				loadConfig();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		/**
		 * Load properties 
		 * @throws Exception
		 */
		public void loadConfig() throws Exception {
			
			try {
				property = new Properties();
				
				String filePath = System.getProperty("user.dir") + "./src/test/resources/config.properties";
				FileInputStream FP = new FileInputStream(filePath);
				property.load(FP);
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}  
			
		}
		
		/**
		 * get url from properties
		 * @return
		 */
		public String getBaseUrl() {	
			return property.getProperty("base_url");
		}
		
		/*API endpoints */
		
		public String getlogin_uri () {
			return property.getProperty("login_uri");
		}
		public String getlogout_uri () {
			return property.getProperty("logout_uri");
		}
		public String getpatient_uri () {
			return property.getProperty("patient_uri");
		}
		public String getupdate_uri() {
			return property.getProperty("update_uri");
		}
		public String getdeletebyid_uri(){
			return property.getProperty("deletebyid_uri");
		}
		public String gettestreports_uri(){
			return property.getProperty("testreports_uri");
		}
		public String gettestreports1_uri(){
			return property.getProperty("testreports1_uri");
		}
		public String getmorbidity_uri(){
			return property.getProperty("morbidity_uri");
		}
		public String getmorbidityname_uri(){
			return property.getProperty("morbidityname_uri");
		}	
				
		
	}

