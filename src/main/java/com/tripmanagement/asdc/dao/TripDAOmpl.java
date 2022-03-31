package com.tripmanagement.asdc.dao;

import com.tripmanagement.asdc.model.Trip;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TripDAOmpl implements TripDAO {


    @Autowired
    JdbcTemplate jdbcTemplate;

	Logger logger = LoggerFactory.getLogger(TripDAOmpl.class);


	@Override
	public boolean saveTrip(Trip trip) {
		if(trip==null)
		return false;
		try
		{
		String sql = "insert into trip values("+null+",'"+trip.getSource()+"','"+trip.getDestination()+"',"+trip.getEstimated_kms()+","+trip.getVehicle_id()+","+trip.getKms_travelled()+","+trip.getAvailable_seats()+",'"+trip.getStart_time()+"','"+trip.getEnd_time()+"',"+trip.getSeats_taken()+","+trip.getCost()+",'"+trip.getVehicle_owner_id()+"');";
        jdbcTemplate.update(sql);
		return true;
		}
		catch(Exception e)
		{
			logger.error("Error saving trip",e);
			return false;
		}
		
	}

	@Override
	public Trip getTripDetails(int trip_id) {
		try{

			return jdbcTemplate.queryForObject("select * from trip where trip_id="+trip_id,
					BeanPropertyRowMapper.newInstance(Trip.class));
		}
		catch(Exception e)
		{
			logger.error("Error getting trip details",e);
			return null;
		}
	}

	@Override
	public List<Trip> getUpcomingTripsForVehicleOwner(int vehicleOwnerId, String timestamp) {
		List<Trip> trips=new ArrayList<>();
		try{
		trips= jdbcTemplate.query("select * from trip where vehicle_owner_id="+vehicleOwnerId,
				BeanPropertyRowMapper.newInstance(Trip.class));
		return trips;
		}
		catch(Exception e)
		{
			logger.error("Error getting upcoming trips for VehicleOwner",e);
			return trips;
		}
	}


	@Override
	public boolean deleteTrip(int trip_id) {
		try{
		String sql = "delete from Trip where trip_id="+trip_id;
        jdbcTemplate.update(sql);
		return true;
		}
		catch(Exception e)
		{
			logger.error("Error deleting trip",e);
			return false;
		}
		
	}

	@Override
	public List<Trip> getAvailableTripsList(String source, String destination, String timestamp) {
		List<Trip> trips=new ArrayList<>();
		if(source==null||destination==null)
		return null;
		try{
		trips= jdbcTemplate.query("select * from trip where source='"+source+"' and destination='"+destination+"'",
				BeanPropertyRowMapper.newInstance(Trip.class));
		return trips;}
		catch(Exception e)
		{
			logger.error("Error getting available trips",e);
			return trips;
		}
	}


	@Override
	public List<Trip> getPreviousTripsForVehicleOwner(int vehicleOwnerId, String timestamp) {
		List<Trip> trips=new ArrayList<>();
		try{
		trips= jdbcTemplate.query("select * from trip where vehicle_owner_id="+vehicleOwnerId,
				BeanPropertyRowMapper.newInstance(Trip.class));
		return trips;
		}
		catch(Exception e)
		{
			logger.error("Error getting previous trips for VehicleOwner",e);
			return trips;
		}
	}

	@Override
	public List<String> getSources() {
		List<String> sources=new ArrayList<>();
		try{
		sources= jdbcTemplate.queryForList("select distinct source from trip",String.class);
		return sources;
		}
		catch(Exception e)
		{
			logger.error("Error getting sources",e);
			return sources;
		}
	}

	@Override
	public List<String> getDestinations() {
		List<String> destinations=new ArrayList<>();
		try{
		destinations= jdbcTemplate.queryForList("select distinct destination from trip",String.class);
		return destinations;
		}
		catch(Exception e)
		{
			logger.error("Error getting destinations",e);
			return destinations;
		}
	}

	@Override
	public boolean updateAvailableSeats(int trip_id, int available_seats) {
		try{
			String sql = "update trip set available_seats="+available_seats+" where trip_id="+trip_id;
			jdbcTemplate.update(sql);
			return true;
			}
			catch(Exception e)
			{
				logger.error("Error updating fuel economy",e);
				return false;
	
			}	
	}

	
}










