package Day1;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.port;

import java.util.HashMap;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class HTTPRequests {

	int policyId;

	@BeforeClass
	void setup() {
		baseURI = "http://localhost";
		port = 8090;
		basePath = "/api/travel-insurance";
	}

	@Test(priority = 3)
	void getUsersById() {

	given().when().get("/"+policyId).then().statusCode(200).log().all();
	}

	@Test(priority = 2)
	void getUsers() {

		given().when().get().then().statusCode(200).log().all();
	}

	@Test(priority = 1)
	void createUser() {

		HashMap data = prepareDataSet1();
		policyId = given().contentType("application/json").body(data).when()
				.post("http://localhost:8090/api/travel-insurance").jsonPath().getInt("policyId");
		// .then().statusCode(201)
		// .log().all();
		
		
		HashMap data1 = prepareDataSet2();
		 given().contentType("");
		policyId = given().contentType("application/json").body(data1).when().post("http://localhost:8090/api/travel-insurance")
				.jsonPath().getInt("policyId");
				//.then().statusCode(201).log().all();

	}

	@Test(priority = 4, dependsOnMethods = {"createUser"})
	void updateUser() {

		HashMap data = prepareDataSet3();
		given().contentType("application/json").body(data).when()
				.put("http://localhost:8090/api/travel-insurance/" + policyId).then().statusCode(200).log().all();

	}
	
	@Test (priority = 5)
	void deleteUser() {
		
		given()
		.when().delete("http://localhost:8090/api/travel-insurance/"+policyId)
		.then().statusCode(200).log().all();
	}

	private HashMap<String, String> prepareDataSet3() {
		HashMap<String, String> data3 = new HashMap<String, String>();
		data3.put("policyHolderName", "Shaarvi");
		data3.put("destinationCountry", "Australia");
		data3.put("coverageAmount", "400000");
		data3.put("durationInDays", "56");
		return data3;
	}

	private HashMap<String, String> prepareDataSet2() {
		HashMap<String, String> data1 = new HashMap<String, String>();
		data1.put("policyHolderName", "Sonali Nikam");
		data1.put("destinationCountry", "New Zealand");
		data1.put("coverageAmount", "20000");
		data1.put("durationInDays", "15");
		return data1;
	}

	private HashMap<String, String> prepareDataSet1() {
		HashMap<String, String> data = new HashMap<String, String>();
		data.put("policyHolderName", "John Smith");
		data.put("destinationCountry", "New Zealand");
		data.put("coverageAmount", "600000");
		data.put("durationInDays", "45");
		return data;
	}

}
