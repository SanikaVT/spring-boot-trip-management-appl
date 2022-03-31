package com.tripmanagement.asdc.service;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.tripmanagement.asdc.dao.TripDAO;
import com.tripmanagement.asdc.dao.VehicleDAO;
import com.tripmanagement.asdc.dao.VehicleOwnerDAO;
import com.tripmanagement.asdc.model.Ride;
import com.tripmanagement.asdc.model.Trip;
import com.tripmanagement.asdc.model.Vehicle;
import com.tripmanagement.asdc.model.VehicleOwner;
import com.tripmanagement.asdc.stringsAndConstants.StringMessages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TripServiceImpl implements TripService {

	@Autowired
	TripDAO tripDAO;

	@Autowired
	VehicleDAO vehicleDAO;

	@Autowired
	VehicleOwnerDAO vehicleOwnerDAO;

	@Autowired
	NotificationService notificationService;
	
	@Override
	@Transactional
	public boolean saveTrip(Trip trip) {
		//SimpleDateFormat dateTime=new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		//trip.getTimestamp();
		try{
		boolean isSuccess= tripDAO.saveTrip(trip);
		if(isSuccess)
				notificationService.sendEmail(StringMessages.RIDE_CREATED_SUCCESSFULLY,StringMessages.RIDE_CREATED,vehicleOwnerDAO.getVehicleOwnerById(vehicleDAO.getVehicleDetails(trip.getVehicle_id()).getVehicleowner_id()).getEmail());
		return isSuccess;
		}
		catch(Exception e)
		{
			return false;
		}
	}

	@Override
	@Transactional
	public Trip getTripDetails(int trip_id) {
		try{
		Trip trip=tripDAO.getTripDetails(trip_id);
		return trip;
		}
		catch(Exception e)
		{
			return null;
		}
	}

	@Override
	@Transactional
	public List<Trip> getUpcomingTripsForVehicleOwner(int vehicleOwnerId) {
		try{
		return tripDAO.getUpcomingTripsForVehicleOwner(vehicleOwnerId, getCurrentTime().toString());
		}
		catch(Exception e)
		{
			return new ArrayList<Trip>();
		}
	}

	@Override
	@Transactional
	public boolean deleteTrip(int trip_id) {
		try{
		return tripDAO.deleteTrip(trip_id);
		}
		catch(Exception e)
		{
			return false;
		}
	}

	@Override
	@Transactional
	public List<Ride> getAvailableTripsList(String source, String destination) {
		if(source==null||destination==null)
		return null;
		try{
		List<Ride> rideList=new ArrayList<>();
		List<Trip> tripList=tripDAO.getAvailableTripsList(source, destination,getCurrentTime().toString());
		for(Trip trip:tripList)
		{
			Vehicle vehicle=vehicleDAO.getVehicleDetails(trip.getVehicle_id());
			VehicleOwner vehicleOwner=vehicleOwnerDAO.getVehicleOwnerById(vehicleDAO.getVehicleDetails(trip.getVehicle_id()).getVehicleowner_id());
			Ride ride=new Ride(trip.getTrip_id(), vehicle.getVehicle_id(), vehicle.getNumber_plate(),
					vehicle.getFuel_economy(), vehicleOwner.getVehicleowner_fname(), vehicle.getVehicleowner_id(),
					vehicleOwner.getPhone(), calculateCost(vehicle,trip), trip.getAvailable_seats());
			rideList.add(ride);
		}
		return rideList;
	}
	catch(Exception e)
	{
		return new ArrayList<Ride>();

	}
	}

	@Override
	@Transactional
	public float calculateCost(Vehicle vehicle, Trip trip) {
		if(trip==null||vehicle==null||vehicle.getAvailable_seats()==0)
		return 0;
		else{
		float cost=(float)(trip.getEstimated_kms()*vehicle.getFuel_economy()*(float)1.20)/vehicle.getAvailable_seats();
		return cost;
		}
	}



	@Override
	@Transactional
	public List<Trip> getPreviousTripsForVehicleOwner(int vehicleOwnerId) {
		try{
		return tripDAO.getPreviousTripsForVehicleOwner(vehicleOwnerId, getCurrentTime().toString());
		}
		catch(Exception e)
		{
			return new ArrayList<Trip>();
		}
	}

	@Override
	@Transactional
	public List<String> getSources() {
		return tripDAO.getSources();
	}

	@Override
	@Transactional
	public List<String> getDestinations() {
		return tripDAO.getDestinations();

}


	public Date getCurrentTime()
	{
		long millis = System.currentTimeMillis(); 
    	Date currentDateTime = new Date(millis);
		return currentDateTime;
	}

}





