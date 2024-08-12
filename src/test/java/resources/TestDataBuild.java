package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {
	public AddPlace addPlacePayload() {
		AddPlace p = new AddPlace();
		p.setAccuracy(50);
		p.setAddress("29, side layout, cohen 09");
		p.setLanguage("French-IN");
		p.setName("Tushar Rathore Academy");
		p.setPhone_number("(+91) 983 893 3937");
		p.setWebsite("http://google.com");

		List<String> types = new ArrayList<String>();
		types.add("shoe park");
		types.add("shop");
		p.setTypes(types);

		Location lc = new Location();
		lc.setLat(-38.383494);
		lc.setLng(33.427362);
		p.setLocation(lc);
		return p;
	}
}
