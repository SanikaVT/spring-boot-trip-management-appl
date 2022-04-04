package com.tripmanagement.asdc.service;

import java.util.List;

import com.tripmanagement.asdc.model.Booking;


public interface BookingService {

	public boolean saveRide(Booking booking);
	public List<Booking> getUpcomingRidesForCustomer(int customer_id);
	public List<Booking> getPreviousRidesForCustomer(int customer_id);
	public String payforRide(Booking booking); 

}