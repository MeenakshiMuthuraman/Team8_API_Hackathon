package com.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;

import static io.restassured.RestAssured.baseURI;
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

public class patient_testreports {

	 DieticianOperation DTO = new DieticianOperation();
	 public static Response response;
	
	@Given("the patient makes a request to view testreports")
	public void the_patient_makes_a_request_to_view_testreports() {
		String token = DTO.getToken();
		int patientid = 5095;
		response =DTO.GetTestReport(token,patientid);
	}

	@Then("the response should contain test reports")
	public void the_response_should_contain_test_reports() {
		response.prettyPrint();
	}

	@Given("the patient makes a request to view testreports with file")
	public void the_patient_makes_a_request_to_view_testreports_with_file() {
		String token = DTO.getToken();
		String fileid = "655c0b76fa57dd184cbea0fe";
		response =DTO.GetFileReport(token,fileid);
	}
	@Given("the patient makes a request to view testreports with valid fileid")
	public void the_patient_makes_a_request_to_view_testreports_with_validfileid() {
		String token = DTO.getToken();
		String fileid = "655c1741fa57dd184cbea1b0";
		response =DTO.GetFileReport(token,fileid);
	}
	@Then("the response should contain error message")
	public void the_response_should_contain_file_reports() {
		response.prettyPrint();
	}

}
