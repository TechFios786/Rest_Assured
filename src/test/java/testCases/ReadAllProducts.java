package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;


public class ReadAllProducts {
	@Test
	public void read_All_Product() {
		SoftAssert softAssert = new SoftAssert();
		
		Response response = 
	given()
//		.log().all()
		.baseUri("https://techfios.com/api-prod/api/product")
		.header("Content-Type","application/json; charset=UTF-8")
		.auth().preemptive().basic("demo@techfios.com", "abc123").
	when()
//		.log().all()
		.get("/read.php").
	then()
//		.log().all()
		.extract().response();
		
		
		int statuscode = response.getStatusCode();
		System.out.println("actual status code " + statuscode);
		Assert.assertEquals(statuscode, 200);
		
		String actualHeader = response.getHeader("Content-Type");
		System.out.println("actual header: " + actualHeader);
		Assert.assertEquals(actualHeader, "application/json; charset=UTF-8");
		
		String actualbody = response.getBody().asString();
		System.out.println("the actual Response Body: " + actualbody);
		
		JsonPath jp = new JsonPath(actualbody);
		
		String firstProductId = jp.get("records[0].id");
		System.out.println("First Product ID: " + firstProductId);
		
		
		String secondProductId = jp.get("records[1].id");
		System.out.println("Second Product ID: " + secondProductId);
		
		if(firstProductId != null) {
			System.out.println("Records are not null. ");
			
		}
		else {
			System.out.println("Records are null. ");
		}
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
