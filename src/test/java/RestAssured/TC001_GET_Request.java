package RestAssured;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class TC001_GET_Request {

  @Test
  void getWeatherDetails(){
    //Specify base URI
    RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city/";
    //Request object
    RequestSpecification httpRequest = RestAssured.given();
    //Response object
    Response response = httpRequest.request(Method.GET, "/Lagos");
    // Print response in cocnsole window
    String responseBody = response.getBody().asString();
    System.out.println("Response Body of Request is : " + responseBody);
  }

}
