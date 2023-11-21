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
    org.apache.log4j.Logger log = Loggerload.getLogger(Loggerload.class);

	
	@When("the user requests to create patient record with valid credential")
	public void the_user_requests_to_create_patient_record_with_valid_credential() {
		String token=DTO.getToken();
	    response = DTO.CreatePatient(token);
	}

	@Then("the API should respond with a status code created")
	public void the_api_should_respond_with_a_status_code_created() {
		assertEquals(201,response.statusCode());
    	int patientId = response.jsonPath().getInt("patientId");
    	System.out.println("***************PatientID************:"+patientId);
    	log.info("*******************Patient Created*****************");
    	DTO.SetPatientId(patientId);
    	response.prettyPrint();
	}

	@Then("the response should contain successful message")
	public void the_response_should_contain_successful_creation() {
		response.prettyPrint();
	}
	@When("the user request to get all patient details")
	public void the_user_request_to_get_all_patient_details() {
		String token=DTO.getToken();
    	log.info("*******************Getting All Patients*****************");
    	response = DTO.GetAllPatient(token);
	}
	
	@When("the user request to delete patient with valid patient id")
	public void the_user_request_to_delete_patient_with_valid_patient_id() {
		String token = DTO.getToken();
		int patientid = DTO.getPatientId();
		response =DTO.DeletePatient(token,patientid);
	}	
	@When("the user requests to update patient record with valid credential")
	public void the_user_requests_to_update_patient_record_with_valid_credential() {
		String token=DTO.getToken();
		int patientid=DTO.getPatientId();
	    response = DTO.UpdatePatient(token,patientid);
	}

	@Then("the API shhould respond with a status code updated")
	public void the_api_shhould_respond_with_a_status_code_updated() {
		assertEquals(200,response.statusCode());
		response.prettyPrint();
	}

	@Then("the responde should contain successful message")
	public void the_responde_should_contain_successful_message() {
		response.prettyPrint();
	}
}
