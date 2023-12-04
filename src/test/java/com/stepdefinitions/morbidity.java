package com.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;

import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

import org.json.simple.JSONObject;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.FileReader;
import java.io.IOException;
import com.utilities.Loggerload;

public class morbidity {
	DieticianOperation DTO = new DieticianOperation();
//    private JSONObject request = new JSONObject();
    Response response;
    org.apache.log4j.Logger log = Loggerload.getLogger(Loggerload.class);

	
	@When("the user requests to get all morbidity details with bearer token")
	public void the_user_requests_to_get_all_morbidity_details_with_bearer_token() throws CsvValidationException {
		String token = DTO.getToken();
		
    	response = DTO.GetAllMorbidity(token);

	}

	@Then("the response should contain all morbidity details")
	public void the_response_should_contain_all_morbidity_details() {
        String message = response.prettyPrint();
        log.info(message);

	}

	@When("the user request to get morbidity condition by valid {string}")
	public void the_user_request_to_get_morbidity_condition_by_valid(String testname) {
		String token = DTO.getToken();
	    DTO.GetMorbidityByTestName(token, testname);
	}
	
	@Then("the response should contain morbidity details of requested {string}")
	public void the_response_should_contain_morbidity_details_of_requested_test_name(String testname) {
		String token = DTO.getToken();
	    response = DTO.GetMorbidityByTestName(token, testname);
	    response.prettyPrint();
	}
}
