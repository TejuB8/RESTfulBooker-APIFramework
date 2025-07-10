package com.APIFramework.asserts;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.assertEquals;

import io.restassured.response.Response;

public class AssertActions {
	
	//common assertions which can be reused.
	
	
	public void verifyResponseBody(String actual, String expected, String description) {
		assertEquals(actual,expected,description);
	}
	
	public void verifyResponseBody(int actual,int expected,String description) {
		assertEquals(actual,expected,description);
	}
	
	public void verifyStatusCode(Response response,Integer expected) {
		assertEquals(response.getStatusCode(),expected);
	}
	
	public void verifyStringKey(String keyExpect,String keyActual) {
		assertThat(keyExpect).isNotNull();
		assertThat(keyExpect).isNotNull().isNotBlank();
		assertThat(keyExpect).isEqualTo(keyActual);
		
	}

}
