package com.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;

	

@io.cucumber.testng.CucumberOptions(
			plugin = {"pretty", "html:target/DieticianAPI_HtmlReport.html",
					"io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
					"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
					}, 
			monochrome=false,  
			tags = " ", 
			features = {
					"src/test/resources/features/01userlogin.feature",
					"src/test/resources/features/02morbidity_controller.feature",
					"src/test/resources/features/03patient_Controller.feature",
					"src/test/resources/features/04patient_testreports.feature",
					
			},
			glue= {"com.stepdefinitions"})


	public class TestRunner extends AbstractTestNGCucumberTests {

	}
	
	

