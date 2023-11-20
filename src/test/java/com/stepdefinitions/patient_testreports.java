package com.stepdefinitions;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

//import utilities.ConfigReader;
//import utilities.Excelreader;
//import utilities.Excelwriter;
import com.utilities.ConfigReader;
import com.utilities.Excelreader;
import com.utilities.Excelwriter;
import com.utilities.Loggerload;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Random;
import java.util.UUID;

import org.hamcrest.Matchers;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.Assert;
//import com.utilities.LoggerLoad;
//import com.utilities.LoggerLoad;


public class patient_testreports {
	ConfigReader config = new ConfigReader();		
	String baseURL=config.getBaseUrl();
	String userpostendpoint=config.getpatient_uri();
	String excelpath =".\\src/test/resources/data/data.xlsx";
	String writeexcelpath =".\\src/test/resources/data/writerid.xlsx";
	Excelreader reader = new Excelreader();	
	LinkedHashMap<String, String> testdata;
	LinkedHashMap<String, String> iddata;
	
	RequestSpecification request;
	Response response;
	JSONObject requestbody = new JSONObject();
	
	public static String token;
	public static String userID;

	public int rowval;
	
	
	JSONArray jsonArray = new JSONArray();
	
	String msgvalidation;

	@SuppressWarnings("unchecked")
	
	@Given("The POST endpoint and the reqeust payload from {string} and {int} for login")
	public void the_post_endpoint_and_the_reqeust_payload_from_and_for_login(String string, Integer int1) throws IOException {
	
	Loggerload.info("**** Create Patient Login ***");
	
	request =  given().contentType(ContentType.JSON);
	
	/*	//request = given().baseUri("https://dietician-dev-41d9a344a720.herokuapp.com/dietician/");
		   
			Excelreader reader = new Excelreader();	
			
			testdata1 = reader.readexcelsheet(excelpath,"Sheet1",int1);
			rowval=int1;
			
					
			requestbody.put("userLoginEmail",testdata1.get("userLoginEmail"));
			requestbody.put("password",testdata1.get("password"));
			
						
			System.out.println(requestbody.toString());
	*/
	/*====== manual===*/
	RestAssured.baseURI="https://dietician-dev-41d9a344a720.herokuapp.com/dietician";
	RequestSpecification request=RestAssured.given();
	String payload= "{\n"
			+ "  \"password\": \"Truth79\",\n"
			+ "  \"userLoginEmail\": \"sadhna.upy@gmail.com\"\n"
			+ "}";
	request.header("Content-Type", "application/json");
	Response responsegeneratetoken=	request.body(payload).post("/login");
	
	System.out.println("token"+responsegeneratetoken.prettyPrint());
	
	responsegeneratetoken.prettyPrint();
	String jsonstring=responsegeneratetoken.getBody().asString();
	
	String authToken = responsegeneratetoken.jsonPath().getString("token"); */
		
		
	}
	@SuppressWarnings("unchecked")
	@When("I send a POST reqeust for creating an token")
	public void i_send_a_post_reqeust_for_creating_an_token() throws IOException {
		
		/*
		response = request.header("","").contentType("application/json").accept("application/json").body(requestbody.toJSONString()).when().post("/login");
		System.out.println("Response from  = " + response.asPrettyString());
		*/
	/*	
		request = given().baseUri(baseURL);
	
	int int1 = 0;
	testdata = reader.readexcelsheet(excelpath,"Sheet1",int1);
		
	rowval=int1;
		
			String userLoginEmail = testdata.get("userLoginEmail");
			String password = testdata.get("password");
			requestbody.put("password",testdata.get("password"));
		requestbody.put("userLoginEmail",testdata.get("userLoginEmail"));
		
			
			userLoginEmail = testdata.get("userLoginEmail");
			password = testdata.get("password");
			
				
				
		response = request.when().body(requestbody.toJSONString()).post(baseURL+config.getpatient_uri());
		//	response = request.when().body(requestbody.toJSONString()).post("https://dietician-dev-41d9a344a720.herokuapp.com/dietician/login");
System.out.println("Response = " + response.asPrettyString());
*/
	}

	@Then("The program is successfully created {int}")
	public void the_program_is_successfully_created(Integer int1) throws IOException {
		  if (response.getStatusCode()==201) {
				System.out.println("Login created successfully");
			}
			System.out.println("rowval "+rowval);
			JsonPath js = new JsonPath(response.asString());
			Excelwriter writer = new Excelwriter();
	
			if (rowval==0) {
				
				
				token = response.body().jsonPath().getString("token");
				writer.WriteExcel(writeexcelpath, "Sheet1", token, 0);
			//	System.out.println("token from excel sheet "+iddata1.get("token"));
				
				
				
			}
	}

	@Given("patient creates GET request for dietience portal")
	public void patient_creates_get_request_for_dietience_portal() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("I send the GET request with the MorbilityID")
	public void i_send_the_get_request_with_the_morbility_id() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("the status could be {int}")
	public void the_status_could_be(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

}
