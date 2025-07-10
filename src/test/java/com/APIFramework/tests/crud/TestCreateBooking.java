package com.APIFramework.tests.crud;

import org.testng.annotations.Test;

import com.APIFramework.base.BaseTest;
import com.APIFramwork.endpoints.APIConstants;
import com.APIFramwork.pojos.BookingResponse;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;

public class TestCreateBooking extends BaseTest {
	
	@Owner("Tejaswini")
	@Description("Verify that post request is working fine")
	@Test(groups="qa")
	public void testVerifyCreatBookingPOST01() {
		requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);
		
		response=RestAssured.given(requestSpecification)
				.when().body(payloadManager.createPayloadBookingString()).post();
		
		
		validatableResponse=response.then().log().all();
		validatableResponse.statusCode(200);
		
		BookingResponse bookingResponse=payloadManager.bookingResponse(response.asString());
		assertActions.verifyStringKey(bookingResponse.getBooking().getFirstname(), "Teju");
		
		
	}
	

}
