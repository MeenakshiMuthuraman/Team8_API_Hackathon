package com.stepdefinitions;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;

import static io.restassured.RestAssured.given;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.opencsv.CSVWriter;
import com.utilities.*;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


public class userlogin {

    DieticianOperation DTO = new DieticianOperation();
    private JSONObject request = new JSONObject();
   
    public static Response response;
    public static String authToken;
    org.apache.log4j.Logger log = Loggerload.getLogger(Loggerload.class);
    @Given("the user makes a login request with correct {string} and {string}")
    public void the_user_makes_a_login_request_with_correct_and(String username, String password) {
    	DTO.ValidLogin(username, password);

    }
    @When("user performs post operation for login")
    public void user_performs_post_operation_for_login() {
    	
    	response = DTO.PerformLogin();
        
    }
    @Then("the API should respond with a status code OK")
    public void the_api_should_respond_with_a_status_code_ok() {
    	assertEquals(200,response.statusCode());
    	log.info(response.prettyPrint());
    	response.prettyPrint();

    }
    @And("the response should contain an authentication token")
    public void the_response_should_contain_an_authentication_token() {
    	 authToken = response.jsonPath().getString("token");
    	 DTO.StoreToken(authToken);
    	System.out.println(authToken);
    	String filePath = System.getProperty("user.dir")+"/src/test/resources/TestData/data.csv";
		System.out.println(filePath);
		try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {
            writer.writeNext(new String[]{"DieticianToken",authToken});

            System.out.println("Tokens have been written to " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    	assertNotNull(authToken);
    }
    

    
    @When("User performs logout")
    public void user_performs_logout() {
       response =  DTO.PerformLogout(authToken);
    }


    @Then("the response should contain logged out successfully")
    public void the_response_should_contain_logged_out_successfully() {
    	log.info("============Dietician Logged Out successfully==============");
        String message = response.prettyPrint();
    }
    @Given("the user makes a login request with credentials from JSON file {string}")
    public void the_user_makes_a_login_request_with_credentials_from_json_file(String filePath) {
        DTO.CredentialJsonReader(filePath);
    }

}
