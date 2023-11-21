package com.stepdefinitions;


import io.restassured.response.Response;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

import org.json.simple.JSONObject;
import com.utilities.RestAssuredExtension;

//import api.endpoints.Routes;


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
		System.out.println(request.toJSONString());
	}
	public static Response PerformLogin()
	{
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
		baseURI = "https://dietician-dev-41d9a344a720.herokuapp.com/dietician";
		JSONObject patientInfo = new JSONObject()
				{{	
			put("FirstName", "Ruhi");
            put("LastName", "Sam");
            put("ContactNumber", "6843557851");
            put("Email", "Ruhi@Sam.com");
            put("Allergy", "Hazelnut");
            put("FoodCategory", "Jain");
            put("DateOfBirth", "1989-08-25");
				}};
				String filepath = System.getProperty("user.dir")+"src/test/resources/TestData/Hypo Thyroid-Report.pdf.pdf";
				Response response = given()
		                .baseUri(baseURI)
		                .header("Authorization", "Bearer " + token).when()
		                .multiPart("patientInfo", patientInfo.toString(), "application/json")
		                .multiPart("file", filepath)  
		                .post("/patient");
				System.out.println("Response Message:"+response+"payload:"+patientInfo);
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
}
