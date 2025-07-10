package com.APIFramework.Integration;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.APIFramework.base.BaseTest;
import com.APIFramwork.endpoints.APIConstants;
import com.APIFramwork.pojos.BookingResponse;
import com.APIFramwork.pojos.Booking;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class IntegrationFlow4 extends BaseTest{
	
	//create a booking -->update it-->Try to delete
	
	
	@Test(groups="qa", priority=1)
	@Owner("Tejaswini")
	@Description("TC#INT1- Step 1.Verify that booking is created")
	public void testCreateBooking(ITestContext iTestContext) {
		requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);
		response=RestAssured.given(requestSpecification).body(payloadManager.createPayloadBookingString()).when().post();
		validatableResponse=response.then().statusCode(200);
		JsonPath jsonPath=response.jsonPath();
		int bookingId=jsonPath.getInt("bookingid");
		iTestContext.setAttribute("bookingid", bookingId);
		//System.out.println(bookingId);
		
	}
	
	@Test(groups="qa", priority=2)
	@Owner("Tejaswini")
	@Description("TC#INT1- Step 1.Verify that booking is Updated and fields reflect correct values")
	public void testUpdateBooking(ITestContext iTestContext) {
		int bookingId=(int) iTestContext.getAttribute("bookingid");
		String token=getToken();
		
		//System.out.println(token);
		
		requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL+"/"+bookingId);
		
		response=RestAssured.given(requestSpecification).body(payloadManager.fullUpdatePayloadAsString())
				.cookie("token",token).when().patch();
		
		validatableResponse=response.then().log().all().statusCode(200);
		
		iTestContext.setAttribute("token", token);
		
		//assertions
		
		Booking booking=payloadManager.jsonResponseBooking(response.asString());
		
		assertActions.verifyStringKey(booking.getFirstname(),"Varun");
		
		assertActions.verifyStringKey(booking.getLastname(),"Teju");
		
		System.out.println("Token: "+ token);
	    System.out.println("booking Id: " + bookingId);
	    System.out.println("Response Code: "+ response.getStatusCode());
	    System.out.println("Response Body: " + response.getBody().asString());
		
	}
	
	@Test(groups="qa", priority=3)
	@Owner("Tejaswini")
	@Description("TC#INT1- Step 1.Verify that booking is deleted by using bookingId")
	public void testDeleteBooking(ITestContext iTestContext) {
		String token=(String) iTestContext.getAttribute("token");
		
		int bookingid=(int) iTestContext.getAttribute("bookingid");
		
		
		requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL+"/"+bookingid);
		
		validatableResponse=RestAssured.given(requestSpecification).cookie("token",token).when().delete().then().statusCode(201);
		
		System.out.println("Token: "+ token);
	    System.out.println("booking Id: " + bookingid);
	    System.out.println("Response Code: "+ response.getStatusCode());
	    System.out.println("Response Body: " + response.getBody().asString());
		
	
	}
	

}
