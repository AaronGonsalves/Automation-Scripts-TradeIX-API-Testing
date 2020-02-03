package com.apitest.code;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class API_Test extends BaseURL {

	public static int randomID = 100 + (int) (Math.random() * 1000);
	public static String petName = "Dog";

	@Test(priority = 1, enabled = true, description = "This API helps to create pet with ID, "
			+ "Category Name, Pet Name, Status, Tag Name, Photo URL ")
	public void testCreatePet() {

		// Creating JSON Objects
		JSONObject jsonMainBody = new JSONObject();
		JSONObject bodyCategory = new JSONObject();
		JSONObject bodyTags = new JSONObject();

		// Adding ID and name to Tags
		bodyTags.put("id", randomID);
		bodyTags.put("name", "Pet Dog");

		// Creating JSON Array Objects
		JSONArray jsonTagArray = new JSONArray();
		JSONArray jsonURLArrayList = new JSONArray();

		// Adding URL to the Photourls
		jsonURLArrayList.add("https://www.google.com/");
		// Combing data for the tags body
		jsonTagArray.add(bodyTags);

		// Adding ID and name to the Category body
		bodyCategory.put("id", randomID);
		bodyCategory.put("name", "mammal");

		// Adding ID, name and status to the main body
		jsonMainBody.put("id", randomID);
		jsonMainBody.put("name", petName);
		jsonMainBody.put("status", "available");

		// Combining all the data to the JSON body
		jsonMainBody.put("category", bodyCategory);
		jsonMainBody.put("tags", jsonTagArray);
		jsonMainBody.put("photoUrls", jsonURLArrayList);

		// Header Type for the API
		Response response = RestAssured.given().headers("Content-Type", "application/json").body(jsonMainBody).post();

		Assert.assertEquals(response.getStatusCode(), 200);
	}

	@Test(priority = 2, enabled = true, description = "This API helps to verify the data created for the pet")
	public void testVerifyCreatePet() {

		// Header Type for the API
		Response response = RestAssured.given().headers("Content-Type", "application/json").get("/" + randomID);

		// Retrieving data from the JSON body
		String accessName = response.jsonPath().get("name");
		int accessID = response.jsonPath().get("id");
		String accessStatus = response.jsonPath().get("status");

		Assert.assertEquals(accessID, randomID);
		Assert.assertEquals(accessName, accessName);
		Assert.assertEquals(accessStatus, "available");
		Assert.assertEquals(response.getStatusCode(), 200);
	}

	@Test(priority = 3, enabled = true, description = "This API updates the name of the pet")
	public void testUpdateCreatedPet() {

		// Creating JSON Objects
		JSONObject jsonMainBody = new JSONObject();
		JSONObject bodyCategory = new JSONObject();
		JSONObject bodyTags = new JSONObject();

		// Adding ID and name to Tags
		bodyTags.put("id", randomID);
		bodyTags.put("name", "Pet Dog");

		// Creating JSON Array Objects
		JSONArray jsonTagArray = new JSONArray();
		JSONArray jsonURLArrayList = new JSONArray();

		// Adding URL to the Photourls
		jsonURLArrayList.add("https://www.google.com/");
		// Combing data for the tags body
		jsonTagArray.add(bodyTags);

		// Adding ID and name to the Category body
		bodyCategory.put("id", randomID);
		bodyCategory.put("name", "mammal");

		// Adding ID, status to the main body and updating name of the pet
		jsonMainBody.put("id", randomID);
		jsonMainBody.put("name", "Cute Dog");
		jsonMainBody.put("status", "available");

		// Combining all the data to the JSON body
		jsonMainBody.put("category", bodyCategory);
		jsonMainBody.put("tags", jsonTagArray);
		jsonMainBody.put("photoUrls", jsonURLArrayList);

		// Header Type for the API
		Response response = RestAssured.given().headers("Content-Type", "application/json").body(jsonMainBody).put();

		Assert.assertEquals(response.getStatusCode(), 200);
	}

	@Test(priority = 4, enabled = true, description = "This API deletes the pet which was created")
	public void testDeletePet() {

		// Header Type for the API
		Response response = RestAssured.given().headers("Content-Type", "application/json").delete("/" + randomID);

		Assert.assertEquals(response.getStatusCode(), 200);
	}
}