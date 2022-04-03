package com.tripmanagement.asdc.dao;

import java.util.List;

import com.tripmanagement.asdc.model.Vehicle;

public interface VehicleDAO {

	public boolean addVehicle(Vehicle vehicle);
	public Vehicle getVehicleDetails(int vehicle_id);
	public List<Vehicle> getVehicles(int vehicleOwnerId);
	public boolean updateVehicleFuelEconomy(Vehicle vehicle);
	public boolean deleteVehicle(int vehicleId);
	
}