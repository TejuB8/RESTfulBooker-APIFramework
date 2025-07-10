package com.APIFramework.base;

import org.testng.annotations.BeforeClass;

import com.APIFramework.asserts.AssertActions;
import com.APIFramwork.endpoints.APIConstants;
import com.APIFramwork.modules.PayLoadManager;
import com.APIFramwork.pojos.AuthResponse;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.qameta.allure.*;

public class BaseTest {
//common to all testcases
//baseurl,content type
	
	public RequestSpecification requestSpecification;
	public Response response;
	public AssertActions assertActions;
	public PayLoadManager payloadManager;
	public JsonPath jsonpath;
	public ValidatableResponse validatableResponse;
	
	
	@BeforeClass(alwaysRun = true)
	public void setUp() {
		payloadManager=new PayLoadManager();
		assertActions=new AssertActions();
		
		requestSpecification=RestAssured
				.given()
				.baseUri(APIConstants.BASE_URL)
				.contentType(ContentType.JSON)
				.log().all();
		
		
	}
	
	public String getToken() {
		requestSpecification=RestAssured
				.given()
				.baseUri(APIConstants.BASE_URL)
				.basePath(APIConstants.AUTH_URL)
				.contentType(ContentType.JSON);
		
		//setting up the payload
		String payload=payloadManager.setAuthPayload();
		
		response=RestAssured.given(requestSpecification).body(payload)
				.when().post();
		
		AuthResponse token=payloadManager.authResponseString(response.asString());
		
		return token.getToken();
	
		}
}
