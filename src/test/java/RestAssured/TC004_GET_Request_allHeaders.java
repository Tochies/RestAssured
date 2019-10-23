package RestAssured;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class TC004_GET_Request_allHeaders {
  @Test
  public void GetWeatherDetails(){
    // Specify the URI
    RestAssured.baseURI = "https://maps.googleapis.com";

    //Request object
    RequestSpecification httpRequest = RestAssured.given();
    //Responnse object
    Response response = httpRequest.request(Method.GET, "/maps/api/place/nearbysearch/xml?location=-33.8670522,151.1957362&radius=1500&type=supermarket&key=AIzaSyBjGCE3VpLU4lgTqSTDmHmJ2HoELb4Jy1s");

    //print response in the console window
    String responseBody = response.getBody().asString();
    System.out.println("Response body is : " + responseBody);

    Headers allHeaders = response.headers(); // This will capture all headers
    for(Header header : allHeaders){ System.out.println(header.getName() + " --> " + header.getValue());}
  }
}
