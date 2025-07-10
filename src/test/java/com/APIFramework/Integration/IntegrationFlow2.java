package com.APIFramework.Integration;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.APIFramework.base.BaseTest;
import com.APIFramwork.endpoints.APIConstants;
import com.APIFramwork.pojos.BookingResponse;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class IntegrationFlow2 extends BaseTest{
	@Test(groups="qa", priority=1)
	@Owner("Tejaswini")
	@Description("TC#INT1- Step 1.Verify that booking is created")
	public void testCreateBooking(ITestContext iTestContext) {
		requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);
		
		response=RestAssured.given(requestSpecification).body(payloadManager.createPayloadBookingString())
				.when().post();
		
		validatableResponse=response.then().log().all().statusCode(200);
		
		BookingResponse bookingresponse=payloadManager.bookingResponse(response.asString());
		
		int bookingid=bookingresponse.getBookingid();
		
		iTestContext.setAttribute("bookingid",bookingid);
		System.out.println(bookingid);
		         
	}
	
	@Test(groups="qa", priority=2)
	@Owner("Tejaswini")
	@Description("TC#INT1- Step 1.Verify that booking is deleted by using bookingId")
	public void testDeleteBooking(ITestContext iTestContext) {
		int bookingid=(int) iTestContext.getAttribute("bookingid");
		
		String token=getToken();
		
		requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL+"/"+bookingid);
		
		response=RestAssured.given(requestSpecification).cookie("token",token).when().delete();
		
		validatableResponse=response.then().statusCode(201);
		System.out.println(bookingid);
		System.out.println("Token: "+ token);
	    System.out.println("booking Id: " + bookingid);
	    System.out.println("Response Code: "+ response.getStatusCode());
	    System.out.println("Response Body: " + response.getBody().asString());
		
	}
	@Test(groups="qa", priority=3)
	@Owner("Tejaswini")
	@Description("TC#INT1- Step 1.Verify that booking By ID")
	public void testVerifyBooking(ITestContext iTestContext) {
		int bookingid=(int) iTestContext.getAttribute("bookingid");
		
		requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL+"/"+bookingid);
		response=RestAssured.given(requestSpecification).when().get();
		validatableResponse=response.then().statusCode(404);
		
	    System.out.println("booking Id: " + bookingid);
	    System.out.println("Response Code: "+ response.getStatusCode());
	    System.out.println("Response Body: " + response.getBody().asString());
		
		
		
		
	}

}
