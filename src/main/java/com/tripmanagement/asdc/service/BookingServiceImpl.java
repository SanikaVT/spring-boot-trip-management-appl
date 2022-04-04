package com.tripmanagement.asdc.service;

import com.tripmanagement.asdc.dao.BookingDAO;
import com.tripmanagement.asdc.dao.CustomerDAO;
import com.tripmanagement.asdc.dao.TripDAO;
import com.tripmanagement.asdc.dao.VehicleOwnerDAO;
import com.tripmanagement.asdc.model.Booking;
import com.tripmanagement.asdc.model.Customer;
import com.tripmanagement.asdc.model.Trip;
import com.tripmanagement.asdc.model.VehicleOwner;
import com.tripmanagement.asdc.util.Utility;
import com.tripmanagement.asdc.stringsAndConstants.ServiceStringMessages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.transaction.Transactional;

@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
	BookingDAO bookedRidesDAO;

	@Autowired
	TripDAO tripDAO;

	@Autowired
	CustomerDAO customerDAO;

	@Autowired
	VehicleOwnerDAO vehicleOwnerDAO;

	@Autowired
	NotificationService notificationService;

	Logger logger = LoggerFactory.getLogger(BookingServiceImpl.class);

	@Override
	@Transactional
	public boolean saveRide(Booking booking) {
		if(booking==null)
			return false;
		try {
			logger.info("Inside saveRide method of BookingServiceImpl");
			Trip trip = tripDAO.getTripDetails(booking.getTrip_id());
			if ((trip.getSeats_remaining()) - booking.getSeats_booked() >= 0) {
				booking.setCost(trip.getCost()*booking.getSeats_booked());
				booking.setTimestamp(trip.getStart_time());
				booking.setIsPaid(0);
				logger.info("There are available seats in the vehicle, attempting to book " + booking.getSeats_booked()
						+ " seats now");
				boolean isSuccess = bookedRidesDAO.saveRide(booking);
				if (isSuccess) {
					logger.info("Successfully booked seats");
					notificationService.sendEmail(ServiceStringMessages.RIDE_BOOKED_SUCCESSFULLY+trip.getSource()+" to "+trip.getDestination(), ServiceStringMessages.RIDE_BOOKED,
							customerDAO.getCustomerById(booking.getCustomer_id()).getEmail());
					tripDAO.updateAvailableSeats(trip.getTrip_id(),
							trip.getSeats_remaining() - booking.getSeats_booked());
					logger.info("Successfully sent notification");
				}
				return isSuccess;
			}
			logger.info("Seats not available");
			return false;
		} catch (Exception e) {
			logger.info("Unable to book seats", e);
			return false;
		}
	}

	@Override
	@Transactional
	public List<Booking> getUpcomingRidesForCustomer(int customer_id) {
		try {
			List<Booking> bookingList = bookedRidesDAO.getAllRidesForCustomer(customer_id);
			List<Booking> upcomingBookings = new ArrayList<>();
			for (Booking booking : bookingList) {
				float cost=(float) Math.ceil(booking.getCost());
				booking.setCost(cost);
				String start_time = booking.getTimestamp().replace("T", " ");
				booking.setTimestamp(Utility.convertDate(booking.getTimestamp()));
				booking.setTrip(tripDAO.getTripDetails(booking.getTrip_id()));
				String current_time = Utility.getCurrentTime();
				Date start = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH).parse(start_time);
				Date current = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH).parse(current_time);
				if (current.compareTo(start) < 0) {
					upcomingBookings.add(booking);
				}
			}
			return upcomingBookings;
		} catch (Exception e) {
			return new ArrayList<Booking>();
		}
	}

	@Override
	@Transactional
	public List<Booking> getPreviousRidesForCustomer(int customer_id) {
		try {
			List<Booking> allRides = bookedRidesDAO.getAllRidesForCustomer(customer_id);
			List<Booking> previousRides = new ArrayList<>();
			for (Booking ride : allRides) {
				float cost=(float) Math.ceil(ride.getCost());
				ride.setCost(cost);
				String start_time = ride.getTimestamp().replace("T", " ");
				String current_time = Utility.getCurrentTime();
				ride.setTimestamp(Utility.convertDate(ride.getTimestamp()));
				ride.setTrip(tripDAO.getTripDetails(ride.getTrip_id()));
				Date end = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH).parse(start_time);
				Date current = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH).parse(current_time);
				if (current.compareTo(end) > 0) {
					previousRides.add(ride);
				}

			}
			return previousRides;
		} catch (Exception e) {
			return new ArrayList<Booking>();
		}

	}


	@Override
	@Transactional
	public String payforRide(Booking booking) {
		if(booking==null)
			return ServiceStringMessages.FAILURE;
		Customer customer=customerDAO.getCustomerById(booking.getCustomer_id());
		int cost_credits=(int) Math.ceil(booking.getCost());
		if(customer.getAvailable_credits()>= cost_credits)
		{
			bookedRidesDAO.updateIsPaid(customer.getCustomer_id(), booking.getBooked_ride_id());
			VehicleOwner vehicleOwner=vehicleOwnerDAO.getVehicleOwnerById(tripDAO.getTripDetails(booking.getTrip_id()).getVehicle_owner_id());
			int increment_credits=vehicleOwner.getAvailable_credits()+cost_credits;
			int decrease_credits=customer.getAvailable_credits()-cost_credits;
			vehicleOwnerDAO.updateAvaialableCredits(vehicleOwner.getVehicleOwner_id(), increment_credits);
			customerDAO.updateAvaialableCredits(customer.getCustomer_id(), decrease_credits);
			return ServiceStringMessages.SUCCESS;
		}
		else
		{
			return ServiceStringMessages.PLEASE_BUY_CREDITS;
		}
	}




}
