package RestAssured;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;



public class TC001_GET_Request {

  @Test
  public void getWeatherDetails(){
    //Specify base URI
    RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city/";
    //Request object
    RequestSpecification httpRequest = RestAssured.given();
    //Response object
    Response response = httpRequest.request(Method.GET, "/Lagos");
    // Print response in cocnsole window
    String responseBody = response.getBody().asString();
    System.out.println("Response Body of Request is : " + responseBody);
    //Status code validation
    int StatusCode = response.getStatusCode();
    System.out.println("Status Code is : " + StatusCode);
    Assert.assertEquals(StatusCode, 200);

    //Status line validation
    String StatusLine = response.getStatusLine();
    System.out.println("Status Line is : " + StatusLine);
    Assert.assertEquals(StatusLine,"HTTP/1.1 200 OK");
  }

}
