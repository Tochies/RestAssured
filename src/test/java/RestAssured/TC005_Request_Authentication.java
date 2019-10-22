package RestAssured;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.authentication.PreemptiveOAuth2HeaderScheme;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC005_Request_Authentication {
  @Test
  public void AuthorizationTest(){
    RestAssured.baseURI = "http://restapi.demoqa.com/authentication/CheckForAuthentication";

    // Basic authentication
    PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme();
    authScheme.setUserName("ToolsQA");
    authScheme.setPassword("TestPassword");

    RestAssured.authentication = authScheme;
    //Request object
    RequestSpecification httpRequest = RestAssured.given();
    //Response object
    Response response = httpRequest.request(Method.GET, "/");
    // Print response in console window
    String responseBody = response.getBody().asString();
    System.out.println("Response Body of Request is : " + responseBody);

    //status code valaidation
    int statusCode = response.getStatusCode();
    System.out.println("Status code is : " + statusCode);
    Assert.assertEquals(statusCode, 200);
  }
}
