package com.stepdefinitions;


import io.cucumber.core.internal.com.fasterxml.jackson.databind.JsonNode;
import io.cucumber.java.it.Date;
import io.restassured.response.Response;
import jdk.internal.org.jline.utils.Log;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONObject;
import com.utilities.RestAssuredExtension;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.utilities.*;
import java.nio.file.Files;
import java.nio.file.Paths;
//import api.endpoints.Routes;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class DieticianOperation {

	public static String loginEndpoint = "/login";
	public static String logoutEndPoint = "/logoutdietician";
    
    public static Response response;
    public static JSONObject request = new JSONObject();
    public static String authToken;
    public static int patientId;
    public static int fileId;
    
    public static void SetfileId(int fileid)
	{
		request.put("fileId", fileid);
		fileId =fileid;	
	}
	public static int getfileId()
	{
		return fileId;
	}
    public static void SetPatientId(int patientid)
	{
		request.put("patientId", patientid);
		patientId =patientid;	
	}
	public static int getPatientId()
	{
		return patientId;
	}
	public static void StoreToken(String authtoken)
	{
		request.put("token", authtoken);
		authToken = authtoken;
	}
	public static String getToken()
	{
		return authToken;
	}
    public static JSONObject setupData() {
    	baseURI="https://dietician-dev-41d9a344a720.herokuapp.com/dietician";
    	String token = getToken();
		System.out.println("Token:"+token);
		request = (JSONObject) given()
				.baseUri(baseURI)
				.header("Authorization", "Bearer " + token)
				.contentType("application/json");
		return request;
    }
	public static void ValidLogin(String username,String password)
	{
		request.put("password", password);
		request.put("userLoginEmail", username);
//		System.out.println(request.toJSONString());
	}
	public static Response PerformLogin()
	{
		System.out.println(request.toJSONString());
		baseURI="https://dietician-dev-41d9a344a720.herokuapp.com/dietician";
		response = given()
			    .baseUri(baseURI)
			    .header("Content-Type", "application/json") 
			    .body(request.toJSONString()) 
			    .when()
			    .post(loginEndpoint);
				
				return response;
	}
	
	public static Response PerformLogout(String authtoken)
	{
		baseURI="https://dietician-dev-41d9a344a720.herokuapp.com/dietician";
		Response response = given()
				.baseUri(baseURI)
				.header("Authorization", "Bearer " + authtoken)
				.when()
				.get(logoutEndPoint);
		return response;
		
	}
	public static Response GetAllMorbidity(String token)
	{
		baseURI="https://dietician-dev-41d9a344a720.herokuapp.com/dietician";
		System.out.println("Token:"+token);
		Response response = given()
				.baseUri(baseURI)
				.header("Authorization", "Bearer " + token)
				.when()
				.get("/morbidity");
		return response;
	}
	public static Response ReadPatientDetails(String token)
	{
		baseURI = "https://dietician-dev-41d9a344a720.herokuapp.com/dietician";
		try {
		    String patientInfoPath = ".//src/test/resources/TestData/patientdetails.json";

		    String jsonData = new String(Files.readAllBytes(Paths.get(patientInfoPath)));

		    ObjectMapper objectMapper = new ObjectMapper();
		    com.fasterxml.jackson.databind.JsonNode jsonNode = objectMapper.readTree(jsonData);

		    String firstName = jsonNode.get("FirstName").asText();
		    String lastName = jsonNode.get("LastName").asText();
		    String contactNumber = jsonNode.get("ContactNumber").asText();
		    String email = jsonNode.get("Email").asText();
		    String allergy = jsonNode.get("Allergy").asText();
		    String foodCategory = jsonNode.get("FoodCategory").asText();

		    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
		    OffsetDateTime dateOfBirth = OffsetDateTime.parse(jsonNode.get("DateOfBirth").asText(), formatter);

		    JSONObject patientInfo = new JSONObject();
		        patientInfo.put("FirstName", firstName);
		        patientInfo.put("LastName", lastName);
		        patientInfo.put("ContactNumber", contactNumber);
		        patientInfo.put("Email", email);
		        patientInfo.put("Allergy", allergy);
		        patientInfo.put("FoodCategory", foodCategory);
		        patientInfo.put("DateOfBirth", formatter.format(dateOfBirth));

		   
		    String filepath = System.getProperty("user.dir") + "src/test/resources/TestData/Hypo Thyroid-Report.pdf.pdf";
		    response = given()
		            .baseUri(baseURI)
		            .header("Authorization", "Bearer " + token)
		            .multiPart("patientInfo", patientInfo.toString(), "application/json")
		            .multiPart("file", filepath)
		            .post("/patient");


		} catch (Exception e) {
		    e.printStackTrace();
		}
		finally {
		    System.out.println("########################Response of patient details###########");
		    if (response != null) {
		        response.prettyPrint();
		    }
		}
		return response;
	}
	public static Response GetMorbidityByTestName(String token,String testname)
	{
		baseURI="https://dietician-dev-41d9a344a720.herokuapp.com/dietician";
		Response response = given()
				.baseUri(baseURI)
				.header("Authorization", "Bearer " + token)
				.pathParam("morbidityName", testname)
				.when()
				.get("/morbidity/{morbidityName}");
		return response;
	}
	public static Response CreatePatient(String token)
	{
		System.out.println(token);
		response = ReadPatientDetails(token);
		return response;
				
		}		
	
	public static Response GetAllPatient(String token)
	{
		baseURI="https://dietician-dev-41d9a344a720.herokuapp.com/dietician";
		Response response = given()
				.baseUri(baseURI)
				.header("Authorization", "Bearer " + token)
				.when()
				.get("/patient");
		return response;
	}
	
	public Response DeletePatient(String token,int patientid) {
		baseURI="https://dietician-dev-41d9a344a720.herokuapp.com/dietician";
		Response response = given()
				.baseUri(baseURI)
				.header("Authorization", "Bearer " + token)
				.pathParam("patientId", patientid)
				.when()
				.delete("/patient/{patientId}");
		return response;
	}
	public Response UpdatePatient(String token,int patientid)
	{
		baseURI = "https://dietician-dev-41d9a344a720.herokuapp.com/dietician";
		System.out.println("******PATIENTID*****:"+patientid);
		 JSONObject resquest=new JSONObject();
	 		resquest.put("FirstName", "Ruhi");
	 		resquest.put("LastName", "Sam");
	 		resquest.put("ContactNumber", "9655555555");
	 		resquest.put("Email", "Ruhi@Sam.com");
	 		resquest.put("Allergy", "Hazelnut");
	 		resquest.put("FoodCategory", "Jain");
	 		resquest.put("DateOfBirth", "1989-08-25");
	 		
		baseURI="https://dietician-dev-41d9a344a720.herokuapp.com/dietician";
		  Response response = given()
		    .baseUri(baseURI)
		    .header("Authorization", "Bearer " + token)
		   
		    .pathParam("patientId", patientid)
		    .multiPart("patientInfo", resquest.toString(), "application/json")
		    //.multiPart(valueToUse, resquest.toString(), "application/json")
     	    .when().
		   body(resquest.toJSONString()).
		   
		    put("/patient/{patientId}");
		  System.out.println("Response Message:"+response);
		  return response;
       
}
	public Response GetTestReport(String token,int patientid) {
		baseURI="https://dietician-dev-41d9a344a720.herokuapp.com/dietician";
		Response response = given()
				.baseUri(baseURI)
				.header("Authorization", "Bearer " + token)
				.pathParam("patientId", patientid)
				.when()
				.get("/patient/testReports/{patientId}");
		return response;
	}
	public Response GetFileReport(String token,String fileid) {
		baseURI="https://dietician-dev-41d9a344a720.herokuapp.com/dietician";
		Response response = given()
				.baseUri(baseURI)
				.header("Authorization", "Bearer " + token)
				.pathParam("fileId", fileid)
				.when()
				.get("/patient/testReports/viewFile/{fileId}");
		return response;
	}
	
	public void CredentialJsonReader(String filePath) {
		
		try {
            
            String jsonData = new String(Files.readAllBytes(Paths.get(filePath)));

            ObjectMapper objectMapper = new ObjectMapper();
            com.fasterxml.jackson.databind.JsonNode jsonNode = objectMapper.readTree(jsonData);

//            String password = jsonNode.get("password").asText();
//            String userLoginEmail = jsonNode.get("userLoginEmail").asText();
//            ValidLogin(userLoginEmail,password);
//            System.out.println("Password: " + password);
//            System.out.println("User Login Email: " + userLoginEmail);
            com.fasterxml.jackson.databind.JsonNode loginArray = jsonNode.get("login");

            com.fasterxml.jackson.databind.JsonNode loginData = loginArray.get(0);

            String password = loginData.get("password").asText();
            String userLoginEmail = loginData.get("userLoginEmail").asText();

            ValidLogin(userLoginEmail,password);
                    System.out.println("Password: " + password);
                    System.out.println("User Login Email: " + userLoginEmail);
                    System.out.println("---"); 
                
            
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}