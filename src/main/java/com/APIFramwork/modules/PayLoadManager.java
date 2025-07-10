package com.APIFramwork.modules;

import com.APIFramwork.pojos.Auth;
import com.APIFramwork.pojos.AuthResponse;
import com.APIFramwork.pojos.Booking;
import com.APIFramwork.pojos.BookingResponse;
import com.APIFramwork.pojos.Bookingdates;
import com.google.gson.Gson;

public class PayLoadManager {
	
	
	Gson gson;
	
	public String createPayloadBookingString()
	{
		
		Booking booking=new Booking();
		booking.setFirstname("Teju");
		booking.setLastname("B");
		booking.setDepositpaid(true);
		booking.setTotalprice(2400);
		
		Bookingdates bookingdates=new Bookingdates();
		bookingdates.setCheckin("2024-02-01");
		bookingdates.setCheckout("2024-02-01");
		
	    booking.setBookingdates(bookingdates);
	    booking.setAdditionalneeds("dinner");
	 
	    //converting java object to string
	    gson=new Gson();
		String jsonStringPayload=gson.toJson(booking);
		
		
		return jsonStringPayload;
		
		
	}
	
	//converting java object to string
	public BookingResponse bookingResponse(String responseString) {
		gson=new Gson();
		BookingResponse bookingResponse=gson.fromJson(responseString, BookingResponse.class);
		return bookingResponse;
	}
	
	public Booking jsonResponseBooking(String responseString) {
		gson=new Gson();
		Booking booking=gson.fromJson(responseString, Booking.class);
		return booking;
	}
	
	public String setAuthPayload() {
		Auth auth = new Auth();
		auth.setUsername("admin");
		auth.setPassword("password123");
		
		Gson gson=new Gson();
		String authJsonString=gson.toJson(auth);
		return authJsonString;
	}
	
	public AuthResponse authResponseString(String responseString) {
		AuthResponse auth = new AuthResponse();
		
		Gson gson= new Gson();
		AuthResponse authResponse=gson.fromJson(responseString,AuthResponse.class );
		return authResponse;
	}
	
	public String fullUpdatePayloadAsString() {
		Booking booking=new Booking();
		booking.setFirstname("Varun");
		booking.setLastname("Teju");
		booking.setDepositpaid(true);
		booking.setTotalprice(2400);
		
		Bookingdates bookingdates=new Bookingdates();
		bookingdates.setCheckin("2024-02-01");
		bookingdates.setCheckout("2024-02-01");
		
	    booking.setBookingdates(bookingdates);
	    booking.setAdditionalneeds("dinner");
	 
	    //converting java object to string
	    gson=new Gson();
		String jsonStringPayload=gson.toJson(booking);
		
		
		return jsonStringPayload;
		
	}

}
