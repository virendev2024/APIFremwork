package stepDefinations;

import io.cucumber.java.Before;
import java.io.IOException;

public class Hooks
{
    @Before("@DeletePlace")
    public void beforeScenario() throws IOException {

        // execute the code only when place id is null
        //write the code that will give you place id

            StepDefination m = new StepDefination();
            if(StepDefination.place_id == null)
            {
            m.addPlacePayloadWith("viren", "hindi", "mohali");
            m.user_calls_with_http_request("AddPlaceAPI", "POST");
            m.verify_place_Id_created_maps_to_using("viren", "getPlaceAPI");
            }

    }
}
