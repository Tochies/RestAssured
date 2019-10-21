package RestAssured;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC001_POST_Request {

  @Test
  public void registrationSuccessful(){
    //Specify base URI
    RestAssured.baseURI = "http://restapi.demoqa.com/customer";
    //Request object
    RequestSpecification httpRequest = RestAssured.given();

    JSONObject requestParams = new JSONObject();
    requestParams.put("FirstName", "JohnXYZ11");
    requestParams.put("LastName", "JohnXYZ11");
    requestParams.put("UserName", "TOCHIEXYZ11");
    requestParams.put("Email", "tochie111@gmail.com");
    requestParams.put("Password", "testing123");

    httpRequest.header("Content-Type", "application/json");
    httpRequest.body(requestParams.toJSONString());     // This attaches the above data to the request


    //Response object
    Response response = httpRequest.request(Method.POST, "/register");

    // Print response in console window
    String responseBody = response.getBody().asString();
    System.out.println("Response Body of Request is : " + responseBody);
    //Status code validation
    int StatusCode = response.getStatusCode();
    System.out.println("Status Code is : " + StatusCode);
    Assert.assertEquals(StatusCode, 201);

    // Success code validationn
    String successCode = response.jsonPath().get("SuccessCode");
    Assert.assertEquals(successCode, "OPERATION_SUCCESS");
  }
}
