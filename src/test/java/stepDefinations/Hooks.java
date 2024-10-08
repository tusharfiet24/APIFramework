package stepDefinations;

import java.io.IOException;
import io.cucumber.java.Before;

public class Hooks {
	@Before
	public void beforeScenario() throws IOException {
		// execute this code only when place id is null
		// write a code that will give you place id
		StepDefination sd = new StepDefination();
		if(StepDefination.placeId == null) {
			sd.add_place_payload_with("Modi", "Hindi", "Delhi");
			sd.user_calls_with_http_request("addPlaceAPI", "POST");
			sd.verify_place_id_created_maps_to_using("Modi", "getPlaceAPI");
		}
	}
}