package resources;

import Pojo.AddPlace;
import Pojo.Location;
import java.util.ArrayList;
import java.util.List;

public class TestDataBuild
{
    public AddPlace addPlacePayLoad(String name , String language , String address)
    {
        AddPlace p = new AddPlace();   // creating object of class AddPlace
        p.setAccuracy(50);
        p.setName(name);
        p.setPhone_number("(+91) 983 893 3937");
        p.setAddress(address);
        p.setWebsite("http://google.com");
        p.setLanguage(language);

        //types
        List<String> types = new ArrayList<>();
        types.add("shoe park");
        types.add("shop");
        p.setTypes(types);

        // location
        Location l = new Location();
        l.setLat(-38.383494);
        l.setLng(33.427362);
        p.setLocation(l);
        return p;

    }

    public String deletePlacePayload(String placeId)
    {
        return "{\r\n    \"place_id\":\""+placeId+"\"\r\n}";

    }
}
