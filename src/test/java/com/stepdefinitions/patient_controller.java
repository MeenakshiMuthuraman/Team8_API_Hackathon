package com.stepdefinitions;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;

import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;

import static io.restassured.RestAssured.given;

import org.json.simple.JSONObject;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.opencsv.CSVWriter;
import com.utilities.*;

public class patient_controller {
	
    DieticianOperation DTO = new DieticianOperation();
    public static Response response;

	
	@When("the user requests to create patient record with valid credential")
	public void the_user_requests_to_create_patient_record_with_valid_credential() {
		String token=DTO.getToken();
	    response = DTO.CreatePatient(token);
	}

	@Then("the API should respond with a status code created")
	public void the_api_should_respond_with_a_status_code_created() {
		assertEquals(201,response.statusCode());
    	response.prettyPrint();
	}

	@Then("the response should contain successful creation")
	public void the_response_should_contain_successful_creation() {
		response.prettyPrint();
	}
}
