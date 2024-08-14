package stepDefinations;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;
import java.io.IOException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefination extends Utils {
	RequestSpecification req;
	Response response;
	TestDataBuild data = new TestDataBuild();
	static String placeId;
	
	@Given("Add Place Payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException {
		req = given().spec(requestSpecification())
				.body(data.addPlacePayload(name, language, address));
	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String method) {
		// constructor will be called with value of resource which you pass
		APIResources resourceAPI = APIResources.valueOf(resource);

		if (method.equalsIgnoreCase("POST"))
			response = req.when().post(resourceAPI.getResource());
		else if (method.equalsIgnoreCase("GET"))
			response = req.when().get(resourceAPI.getResource());
	}

	@Then("the API call got success with status code {int}")
	public void the_api_call_got_success_with_status_code(Integer statusCode) {
		assertEquals(statusCode.intValue(), response.getStatusCode());
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String key, String expectedValue) {
		assertEquals(expectedValue, getJsonPath(response, key));
	}

	@Then("verify place_Id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {
		placeId = getJsonPath(response, "place_id");
		req = given().spec(requestSpecification()).queryParam("place_id", placeId);
		user_calls_with_http_request(resource, "Get");
		assertEquals(expectedName, getJsonPath(response, "name"));
	}

	@Given("DeletePlace Payload")
	public void delete_place_payload() throws IOException {
		req = given().spec(requestSpecification())
				.body(data.deletePlacePayload(placeId));
	}
}
