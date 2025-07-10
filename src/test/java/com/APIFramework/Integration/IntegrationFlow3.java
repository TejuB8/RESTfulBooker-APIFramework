package com.APIFramework.Integration;

import java.util.List;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.APIFramework.base.BaseTest;
import com.APIFramwork.endpoints.APIConstants;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;

public class IntegrationFlow3 extends BaseTest {
	// Get a booking from get all--> Try to delete the Booking
	
	
	@Test(groups="qa", priority=1)
	@Owner("Tejaswini")
	@Description("TC#INT1- Step 1.Get a Booking from Get all")
	public void getBookingByGetAll(ITestContext iTestContext) {
		requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);
		response=RestAssured.given(requestSpecification).when().get();
		
		validatableResponse=response.then().statusCode(200);
		
		List<Integer> bookingIdList=response.jsonPath().getList("bookingid");
		
		if(bookingIdList==null || bookingIdList.isEmpty()) {
			throw new SkipException("No bookings available to test deletion");
		}
		
		iTestContext.setAttribute("bookingid", bookingIdList.get(0));
		System.out.println(bookingIdList.get(0));
	
	}
	
	
	
	@Test(groups="qa", priority=2)
	@Owner("Tejaswini")
	@Description("TC#INT1- Step 1.Verify that booking is deleted by using bookingId")
	public void testDeleteBooking(ITestContext iTestContext) {
		int bookingIdToDelete=(int) iTestContext.getAttribute("bookingid");
		System.out.println(bookingIdToDelete);
		
		String token=getToken();
	
		
		
		
		requestSpecification.basePath("/booking/"+bookingIdToDelete);
		validatableResponse=RestAssured.given(requestSpecification).cookie("token",token)
				.when().delete().then().log().all().statusCode(201);
		
		
		System.out.println("Token: "+ token);
	    System.out.println("booking Id: " + bookingIdToDelete);
	    System.out.println("Response Code: "+ response.getStatusCode());
	    System.out.println("Response Body: " + response.getBody().asString());
		
	
	}
	@Test(groups="qa", priority=3)
	@Owner("Tejaswini")
	@Description("TC#INT1- Step 1.Verify that booking Id is deleted")
	public void testVerifyDeletedBooking(ITestContext iTestContext) {
		int bookingIdToDelete=(int) iTestContext.getAttribute("bookingid");
		requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL+"/"+bookingIdToDelete);
		validatableResponse=RestAssured.given(requestSpecification).when().get().then().statusCode(404);
		
		System.out.println("deleted bookingId is: "+ bookingIdToDelete);
		response.prettyPrint();
		
	
	    System.out.println("booking Id: " + bookingIdToDelete);
	    System.out.println("Response Code: "+ response.getStatusCode());
	    System.out.println("Response Body: " + response.getBody().asString());
		
	}
	
	
	

}
