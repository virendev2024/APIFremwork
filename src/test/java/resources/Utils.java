package resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.*;
import java.util.Properties;
import static io.restassured.RestAssured.useRelaxedHTTPSValidation;

// All the reusable methods are written here

public class Utils
{
    RequestSpecification req;
    public RequestSpecification requestSpecification() throws IOException
    {
        // for logs or logging
        PrintStream log = new PrintStream(new FileOutputStream("logging.txt")); // we are writing into file thats why FileOutputStream
        useRelaxedHTTPSValidation();
        req = new RequestSpecBuilder().setBaseUri("baseUrl=https://rahulshettyacademy.com")
                .setBaseUri(getGlobalValue("baseUrl"))
                .addQueryParam("key","qaclick123")
                .addFilter(RequestLoggingFilter.logRequestTo(log))  // Request
                .addFilter(ResponseLoggingFilter.logResponseTo(log))  //Response
                .setContentType(ContentType.JSON).build();
        return req;
    }

    public static String getGlobalValue(String key) throws IOException {

        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream("src/test/java/resources/global.properties"); // we are reading from a file thats why FileInputStream
        prop.load(fis);
        return prop.getProperty(key);
    }

    public String getJsonPath(Response response , String key)
    {
        String res = response.asString();   // getting the repsonse
        JsonPath js = new JsonPath(res);    // getting the response into json path
        return js.get(key).toString();      //
    }
}
