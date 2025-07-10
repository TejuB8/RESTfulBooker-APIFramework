package com.APIFramework.tests.sample;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;

public class IntegrationSample {
	//create a booking, create a token
	//Get booking
	//Update the booking
	//Delete the booking
	
	@Test(groups="qa", priority=1)
	@Owner("Tejaswini")
	@Description("TC#INT1- Step 1.Verify that booking is created")
	public void testCreateBooking() {
		Assert.assertTrue(true);
	}
	
	@Test(groups="qa", priority=2)
	@Owner("Tejaswini")
	@Description("TC#INT1- Step 1.Verify that booking By ID")
	public void testVerifyBooking() {
		Assert.assertTrue(true);
	}
	
	@Test(groups="qa", priority=3)
	@Owner("Tejaswini")
	@Description("TC#INT1- Step 1.Verify that booking is Updated")
	public void testUpdateBooking() {
		Assert.assertTrue(true);
	}
	
	@Test(groups="qa", priority=4)
	@Owner("Tejaswini")
	@Description("TC#INT1- Step 1.Verify that booking is deleted by using bookingId")
	public void testDeleteBooking() {
		Assert.assertTrue(true);
	}

}
