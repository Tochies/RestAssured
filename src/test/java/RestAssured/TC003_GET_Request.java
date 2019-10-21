package RestAssured;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC003_GET_Request {
  @Test
  void googleMapTest(){
    //Specify base URI
    RestAssured.baseURI = "http://maps.googleapis.com";
    //Request object
    RequestSpecification httpRequest = RestAssured.given();
    //Response object
    Response response = httpRequest.request(Method.GET, "/maps/api/place/nearbysearch/xml?location=-33.8670522,151.1957362&radius=1500&type=supermarket&key=AIzaSyBjGCE3VpLU4lgTqSTDmHmJ2HoELb4Jy1s");
    // Print response in console window
    String responseBody = response.getBody().asString();
    System.out.println("Response Body of Request is : " + responseBody);

    //Validating headers
    String contentType = response.header("Content-Type");  // Capture details of content Type header
    System.out.println("Content Type is: " +contentType);
    Assert.assertEquals(contentType, "application/xml; charset=UTF-8");

    String contentEncoding = response.header("Content-Encoding"); // Capture details of content-encoding headers
    System.out.println("Content Encoding is : " +contentEncoding);
    Assert.assertEquals(contentEncoding, "gzip");
  }
}
