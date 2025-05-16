package stepDefinations;

import io.cucumber.java.en.*;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;
import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class StepDefination extends Utils {
    RequestSpecification res;
    ResponseSpecification resspec;
    Response response;
    static String place_id;
    TestDataBuild data = new TestDataBuild();

    @Given("Add Place Payload with {string} {string} {string}")
    public void addPlacePayloadWith(String name, String language, String address) throws IOException {
        res = given().spec(requestSpecification())
                .body(data.addPlacePayLoad(name,language,address));
    }
    @When("user calls {string} with {string} http request")
    public void user_calls_with_http_request(String resource, String method)
    {
        //constructor will be called and executed with the valueOf method
        APIResources resourceApi = APIResources.valueOf(resource);  // url will be passed here for eg "/maps/api/place/add/json"
        System.out.println(resourceApi.getResource());    // url will be printed

        resspec = new ResponseSpecBuilder().
                expectStatusCode(200).expectContentType(ContentType.JSON).build();

        if(method.equalsIgnoreCase("POST"))   // http method
            response = res.when().post(resourceApi.getResource());
        else if(method.equalsIgnoreCase("GET"))
            response = res.when().get(resourceApi.getResource());
    }
    @Then("the API call got success with status code {int}")
    public void the_api_call_got_success_with_status_code(Integer int1) {
        assertEquals(response.getStatusCode(),200);
    }
    @Then("{string} in response body is {string}")
    public void in_response_body_is(String keyValue, String expectedValue)
    {
        assertEquals(getJsonPath(response,keyValue),expectedValue); // keyvalue can be anything from the json eg. place_id

    }

    @Then("verify place_Id created maps to {string} using {string}")
    public void verify_place_Id_created_maps_to_using(String expectedName, String resource) throws IOException {

        // create requestSpec
        place_id=getJsonPath(response,"place_id");
        res=given().spec(requestSpecification()).queryParam("place_id",place_id);
        // hit getPlace API and get the reponse
        user_calls_with_http_request(resource,"GET");
        String actualName=getJsonPath(response,"name");
        assertEquals(actualName,expectedName);
    }

    @Given("DeletePlace Payload")
    public void delete_place_payload() throws IOException {
        // build request spec
        // No need to use POJO ,as it is a simple string not a complex json/arrays
        // so just convert the json into string
        // convert json into java accepted string
        res = given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));  // it gives logging , base url and query parameter
    }
}
