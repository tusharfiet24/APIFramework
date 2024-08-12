package stepDefinations;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import resources.TestDataBuild;
import resources.Utils;

public class stepDefination extends Utils {
	RequestSpecification req;
	Response response;
	TestDataBuild data = new TestDataBuild();

	@Given("Add Place Payload")
	public void add_place_payload() throws FileNotFoundException {
		req = given().spec(requestSpecification()).body(data.addPlacePayload());
	}

	@When("user calls {string} with post http request")
	public void user_calls_with_post_http_request(String string) {
		response = req.when().post("maps/api/place/add/json")
				.then().spec(responseSpecification()).extract().response();
	}

	@Then("the API call is success with status code {int}")
	public void the_api_call_is_success_with_status_code(int statusCode) {
		assertEquals(statusCode, response.getStatusCode());
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String key, String value) {
		String resp = response.asString();
		JsonPath js = new JsonPath(resp);
		assertEquals(value, js.getString(key));
	}
}
