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

public class morbidity {
	DieticianOperation DTO = new DieticianOperation();
    private JSONObject request = new JSONObject();
    Response response;

	
	@When("the user requests to get all morbidity details with bearer token")
	public void the_user_requests_to_get_all_morbidity_details_with_bearer_token() throws CsvValidationException {
		String token = DTO.getToken();
		
    	response = DTO.GetAllMorbidity(token);
//            }
//        catch (IOException e) {
//            e.printStackTrace();
//        }
	}

	

	@Then("the response should contain all morbidity details")
	public void the_response_should_contain_all_morbidity_details() {
        String message = response.prettyPrint();

	}



}
