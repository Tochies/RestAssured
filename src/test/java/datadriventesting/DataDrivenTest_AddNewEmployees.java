package datadriventesting;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class DataDrivenTest_AddNewEmployees {
  @Test(dataProvider = "empdataprovider")
  void postNewEmployees(String ename, String eage, String esal){
    RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";

    RequestSpecification httpRequest = RestAssured.given();
    // Here we created data to send along the post request
    JSONObject requestParams = new JSONObject();

    requestParams.put("name", ename);
    requestParams.put("age", eage);
    requestParams.put("salary", esal);

    // Add a header for the json request body
    httpRequest.header("Content-Type", "application/json");

    // Add the json to the body of the request
    httpRequest.body(requestParams.toJSONString());

    //Post Request
    Response response = httpRequest.request(Method.POST, "/create");

    // capture the response body
    String responseBody = response.getBody().asString();
    System.out.println("Response body is : " + responseBody);

    Assert.assertEquals(responseBody.contains(ename), true);
    Assert.assertEquals(responseBody.contains(esal), true);
    Assert.assertEquals(responseBody.contains(eage), true);
  }

  @DataProvider(name = "empdataprovider")
  public String[][] getEmpdata() throws IOException {
    // Read data from an excel file
    String path = System.getProperty("user.dir") + "/files/Book1.xlsx";

    int rownum = utilities.getRowCount(path, "Sheet1");
    int colnum = utilities.getCellCount(path, "Sheet1", 1);

    String empdata [][] = new String[rownum][colnum];

    for (int i =1; i < rownum; i++){
      for (int j = 0; j < colnum; j++){
        empdata[i - 1][j] = utilities.getCellData(path, "Sheet1", i , j);
      }
    }



    // String empdata[][] = { {"toc123", "300", "40000"}, {"123toc", "900", "4555"} , {"you123", "555", "6474"}};
    return (empdata);
  }


}
