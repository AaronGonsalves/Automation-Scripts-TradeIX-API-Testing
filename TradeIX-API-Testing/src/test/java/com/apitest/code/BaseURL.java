package com.apitest.code;

import org.testng.annotations.BeforeClass;

import io.restassured.RestAssured;

public class BaseURL {

	@BeforeClass
	public void setup() {

		RestAssured.baseURI = "https://petstore.swagger.io/v2/pet";

	}
}
