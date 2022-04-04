package com.tripmanagement.asdc.dao;

import com.tripmanagement.asdc.model.Vehicle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class VehicleDAOImpl implements VehicleDAO {

	Logger logger = LoggerFactory.getLogger(VehicleDAOImpl.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

	@Override
	public boolean addVehicle(Vehicle vehicle) {
		if(vehicle==null||vehicle.getVehicle_name()==null)
		return false;
		try {
			int vehicleowner_id = vehicle.getVehicleowner_id();
			String number_plate = vehicle.getNumber_plate();
			String vehicle_name = vehicle.getVehicle_name();
			String type = vehicle.getType();
			int trips = vehicle.getTrips();
			float kms_driven = vehicle.getKms_driven();
			int available_seats = vehicle.getAvailable_seats();
			float fuel_economy = vehicle.getFuel_economy();
			String fuel_economy_status = vehicle.getFuel_economy_status();
			String brand = vehicle.getBrand();
			float fuel_consumed = vehicle.getFuel_consumed();
			String innerSubQuery1 = vehicleowner_id + ",'" + number_plate + "','" + vehicle_name + "','" + type + "'," + trips;
			String subQuery1 = "insert into vehicle values(" + null + "," + innerSubQuery1;
			String subQuery = fuel_economy + "," + fuel_consumed + ",'" + brand + "','" + fuel_economy_status;
			String innerSubQuery2 = kms_driven + "," + available_seats + "," + subQuery + "');";
			String sql = subQuery1 + "," + innerSubQuery2;
			jdbcTemplate.update(sql);
			logger.info("Vehicle successfully added.");
			return true;
		} catch (Exception exception){
			logger.error("Error while adding Vehicle", exception);
			return false;
			
		}

	}

	@Override
	public Vehicle getVehicleDetails(int vehicle_id) {
		try{
			BeanPropertyRowMapper<Vehicle> rowMapper = BeanPropertyRowMapper.newInstance(Vehicle.class);
			String sql = "select * from vehicle where vehicle_id=" + vehicle_id;
			Vehicle vehicle=jdbcTemplate.queryForObject(sql,rowMapper);
	   return vehicle;
		}
		catch(Exception e)
		{
			logger.error("Error getting vehicle details",e);
			return null;

		}
	}

	@Override
	public List<Vehicle> getVehicles(int vehicleOwnerId) {
		List<Vehicle> vehicles=new ArrayList<>();
		try{
			String selectVehicleQuery = "select * from vehicle where vehicleOwner_id=" + vehicleOwnerId;
			vehicles= jdbcTemplate.query(selectVehicleQuery,
				BeanPropertyRowMapper.newInstance(Vehicle.class));
		return vehicles;
		}
		catch(Exception e)
		{
			logger.error("Error getting list of vehicles",e);
			return vehicles;

		}
	}

	@Override
	public boolean updateVehicleFuelEconomy(Vehicle vehicle) {
		if(vehicle==null||vehicle.getVehicle_name()==null)
		return false;
		try{
			String query1 = "update vehicle set fuel_economy=" + vehicle.getFuel_economy() + ",kms_driven=";
			String s = ",fuel_economy_status='" + vehicle.getFuel_economy_status();
			String query2 = s + "' where vehicle_id=" + vehicle.getVehicle_id();
			String query3 = vehicle.getKms_driven() + ",fuel_consumed=" + vehicle.getFuel_consumed() + query2;
			String sql = query1 + query3;
        jdbcTemplate.update(sql);
		return true;
		}
		catch(Exception e)
		{
			logger.error("Error updating fuel economy",e);
			return false;

		}

	}

	@Override
	public boolean deleteVehicle(int vehicleId) {
		try {
			String sql = "delete from Vehicle where vehicle_id=" + vehicleId;
			jdbcTemplate.update(sql);
			logger.info("Vehicle successfully deleted.");
			return true;
		} catch (Exception exception){
			logger.error("Error while deleting Vehicle", exception);
			return false;
		}
	}

	
}










