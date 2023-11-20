package com.stepdefinitions;
import io.cucumber.core.logging.Logger;
import io.cucumber.java.en.*;
import com.utilities.*;
import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import com.utilities.ConfigReader;
import com.utilities.Excelreader;

import org.testng.Assert;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.CoreMatchers.equalTo;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Random;

import org.hamcrest.Matchers;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class patient_controller {

	

	/*
	@Given("user creates post request from  {string} and {int}")
	
		

@When("the user makes a login request with correct username and password")
public void the_user_makes_a_login_request_with_correct_username_and_password() {
	

@Then("the API should respond with a status code OK")
public void the_api_should_respond_with_a_status_code_ok() {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}
*/
	
	
	
@Given("user creates post request from  {string} and <RowNumber>")
public void user_creates_post_request_from_and(String string, Integer int1) throws IOException {

	request1=given().
			header("Content-Type","application/Json").
			contentType(ContentType.JSON).
			accept(ContentType.JSON);
	Excelreader reader = new Excelreader();	
	testdata = reader.readexcelsheet(excelpath,string,int1);
	rowval=int1;
	
	//save role id and role status in another array
	String FirstName = testdata.get("FirstName");
	String LastName = testdata.get("Last Name");
String ContactNumber = testdata.get("ContactNumber");
String Email = testdata.get("Email");
String Allergy = testdata.get("Allergy");
String FoodCategory = testdata.get("FoodCategory");
String DateOfBirth = testdata.get("DateOfBirth");

	JSONArray userRoleMapsArray = new JSONArray();
	JSONObject userRoleMap = new JSONObject();
	userRoleMap.put("FirstName", FirstName);
	userRoleMap.put("Last Name", Last Name);
	userRoleMap.put("ContactNumber", ContactNumber);
	userRoleMap.put("Email", Email);
	userRoleMap.put("Allergy", Allergy);
	userRoleMap.put("FoodCategory", FoodCategory);
	userRoleMap.put("DateOfBirth", DateOfBirth);
	
	
	userRoleMapsArray.add(userRoleMap);
	
	
	//System.out.println("userrolearray "+userRoleMapsArray);
	//userRoleMapsArray.put(userRoleMap);

	requestbody.put("userRoleMaps",userRoleMapsArray);
	System.out.println(requestbody.toJSONString());

}

@When("user send post request with valid requestbody and valid endpoint")
public void user_send_post_request_with_valid_requestbody_and_valid_endpoint() {
	response = request1.when().body(requestbody.toJSONString()).
			post(baseURL+config.getpatient_uri());
			//then().
			//statusCode(201).log().all().extract().response();
}

@Then("user will receive {int} created with response for user module")
public void user_will_receive_created_with_response_for_user_module(Integer int1) {
    
}
}

