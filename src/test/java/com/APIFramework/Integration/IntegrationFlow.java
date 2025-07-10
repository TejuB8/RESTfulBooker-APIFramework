package com.APIFramework.Integration;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.APIFramework.asserts.AssertActions;
import com.APIFramework.base.BaseTest;
import com.APIFramwork.endpoints.APIConstants;
import com.APIFramwork.modules.PayLoadManager;
import com.APIFramwork.pojos.Booking;
import com.APIFramwork.pojos.BookingResponse;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
//TEST INTEGRATION SCENARIO 1	

//1. Create the  Booking -> bookingID
	
//2. Create a token -> token
	
//3. verify that the create booking is working - GET request to bookingID
	
//4. Update the booking (bookingID, Token) - Need to get the token, bookingID from the above request
	
//5.  Delete the booking- Need to get the token, bookingID from the above request

public class IntegrationFlow extends BaseTest {
	

	
	@Test(groups="qa", priority=1)
	@Owner("Tejaswini")
	@Description("TC#INT1- Step 1.Verify that booking is created")
	public void testCreateBooking(ITestContext iTestContext) {
		requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);
		requestSpecification.body(payloadManager.createPayloadBookingString());
		response=RestAssured.given(requestSpecification).when().post();
		
		validatableResponse=response.then().log().all().statusCode(200);
		BookingResponse bookingResponse=payloadManager.bookingResponse(response.asString());
		
//		
//		String actual=bookingResponse.getBooking().getFirstname();
//		System.out.println("Booking Firstname:  " +actual );
//        assertActions.verifyStringKey(actual,"Teju");
        
       iTestContext.setAttribute("bookingid", bookingResponse.getBookingid());
		
			
		
	}
	
	@Test(groups="qa", priority=2)
	@Owner("Tejaswini")
	@Description("TC#INT1- Step 1.Verify that booking By ID")
	public void testVerifyBooking(ITestContext iTestContext) {
		Integer bookingid=(Integer) iTestContext.getAttribute("bookingid");
		
		requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL+"/"+bookingid);
		response=RestAssured.given(requestSpecification).body(payloadManager.createPayloadBookingString()).when().get();
		
		validatableResponse=response.then().statusCode(200);
		Booking booking=payloadManager.jsonResponseBooking(response.asString());
		assertActions.verifyStringKey(booking.getFirstname(), "Teju");
		
		
	}
	
	@Test(groups="qa", priority=3)
	@Owner("Tejaswini")
	@Description("TC#INT1- Step 1.Verify that booking is Updated")
	public void testUpdateBooking(ITestContext iTestContext) {
		Integer bookingid=(Integer) iTestContext.getAttribute("bookingid");
		
		String token=getToken();
		
		iTestContext.setAttribute("token", token);
		
		requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL+"/"+bookingid);
		response=RestAssured.given(requestSpecification).cookie("token",token).body(payloadManager.fullUpdatePayloadAsString())
				.when().put();
		
		
		
		validatableResponse=response.then().statusCode(200);
		
		Booking booking=payloadManager.jsonResponseBooking(response.asString());
		
		assertActions.verifyStringKey(booking.getFirstname(), "Varun");
		assertActions.verifyStringKey(booking.getLastname(), "Teju");
		
		System.out.println("Token: "+ token);
	    System.out.println("booking Id: " + bookingid);
	    System.out.println("Response Code: "+ response.getStatusCode());
	    System.out.println("Response Body: " + response.getBody().asString());

		
	}
	
	@Test(groups="qa", priority=4)
	@Owner("Tejaswini")
	@Description("TC#INT1- Step 1.Verify that booking is deleted by using bookingId")
	public void testDeleteBooking(ITestContext iTestContext) {
		
	Integer bookingid=(Integer) iTestContext.getAttribute("bookingid");
	String token=(String) iTestContext.getAttribute("token");
	
	requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL+"/"+bookingid);
	validatableResponse=RestAssured.given(requestSpecification).cookie("token", token).when().delete().then().log().all().statusCode(201);
	System.out.println("Token: "+ token);
    System.out.println("booking Id: " + bookingid);
    System.out.println("Response Code: "+ response.getStatusCode());
    System.out.println("Response Body: " + response.getBody().asString());
	
	
	
}
}
