package Day2;

import static io.restassured.RestAssured.*;

import java.util.HashMap;
import static org.hamcrest.Matchers.equalTo;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/*
 Different ways to create post request body
 1) Post request body using HashMap
 2) Post request body creation using org.json
 3) Post request body creation using POJO class
 4) Post request using external json file data
 */
public class DiffWaysToCreatePostRequestBody {
	
	int policyId;
	@BeforeClass
	void setup() {
		baseURI = "http://localhost";
		port = 8090;
		basePath = "/api/travel-insurance";
	}
	
	// 1) Post request body using HashMap

		@Test(priority = 1)
		void testPostUsingHashMap() {
			
			HashMap data = new HashMap();
				data.put("policyHolderName","Sonali Nikam");
				data.put("destinationCountry","New Zealand");
				data.put("coverageAmount","20000");
				data.put("durationInDays","15");
			
				given().contentType("application/json").body(data)
				.when().post("http://localhost:8090/api/travel-insurance")
				.then()
				.statusCode(201)
				.body("policyHolderName", equalTo("Sonali Nikam"))
				.body("destinationCountry", equalTo("New Zealand"))
				.body("coverageAmount", equalTo(20000))
				.body("durationInDays", equalTo(15))
				.log().all();
		
		}
	@Test(priority = 2)
	void deleteUser() {
		given().when().delete("http://localhost:8090/api/travel-insurance/1").then()
		.statusCode(200).log().all();
	}

}
