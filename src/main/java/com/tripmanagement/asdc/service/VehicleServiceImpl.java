package com.tripmanagement.asdc.service;

import java.util.ArrayList;
import java.util.List;

import com.tripmanagement.asdc.dao.FuelEconomyDAO;
import com.tripmanagement.asdc.dao.VehicleDAO;
import com.tripmanagement.asdc.model.ChartData;
import com.tripmanagement.asdc.model.FuelEconomy;
import com.tripmanagement.asdc.model.Vehicle;

import com.tripmanagement.asdc.stringsAndConstants.ServiceConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/*Service class for Vehicle contains logic related to vehicle and fuel economy of teh vehicle. This class interacts with the Vehicle DAO and Fueleconomy DAO  for database operations*/
@Service
public class VehicleServiceImpl implements VehicleService {

	@Autowired
	VehicleDAO vehicleDAO;

	@Autowired
	FuelEconomyDAO fuelEconomyDAO;

	//This method interacts with VehicleDAO to save vehicleOwner into the database
	@Override
	@Transactional
	public boolean addVehicle(Vehicle vehicle) {
		if(vehicle==null||vehicle.getVehicle_name().isEmpty())
		return false;
		try {
			float economy = calculateFuelEconomy(vehicle.getKms_driven(), vehicle.getFuel_consumed());
			vehicle.setFuel_economy(economy);
			vehicle.setFuel_economy_status(getFuelEconomyStatus(economy));
			vehicleDAO.addVehicle(vehicle);
			FuelEconomy fuelEconomy = new FuelEconomy();
			fuelEconomy.setFuel_consumed(vehicle.getFuel_consumed());
			fuelEconomy.setKms_travelled(vehicle.getKms_driven());
			fuelEconomy.setFuel_economy(economy);
			fuelEconomy.setVehicle_id(vehicle.getVehicle_id());
			saveFuelEconomy(fuelEconomy);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	//This method interacts with VehicleDAO to get vehicle details by vehicle_id
	@Override
	@Transactional
	public Vehicle getVehicleDetails(int vehicle_id) {
		try{
		Vehicle vehicle=vehicleDAO.getVehicleDetails(vehicle_id);
		return vehicle;
		}
		catch(Exception e)
		{
			return null;
		}
	}

	//This method interacts with VehicleDAO to get list of vehicles by vehicleOwner_id
	@Override
	@Transactional
	public List<Vehicle> getVehicles(int vehicleOwnerId) {
		try{
		List<Vehicle> vehicles=vehicleDAO.getVehicles(vehicleOwnerId);
		return vehicles;
		}
		catch(Exception e)
		{
			return new ArrayList<Vehicle>();
		}
	}

	//This method interacts with vehicle DAO and fuel economy DAO to update fuel economy of a vehicle and add new entry in the fuelEconomy table
	@Override
	@Transactional
	public boolean updateFuelEconomy(FuelEconomy fuelEconomy) {
		if(fuelEconomy==null)
		return false;
		try{
			float tripKms = fuelEconomy.getKms_travelled();
			float tripFuel = fuelEconomy.getFuel_consumed();
		float fuelEco= calculateFuelEconomy(tripKms, tripFuel);
		fuelEconomy.setFuel_economy(fuelEco);
		Vehicle vehicle=vehicleDAO.getVehicleDetails(fuelEconomy.getVehicle_id());
		vehicle.setFuel_economy(calculateFuelEconomy(vehicle.getKms_driven()+tripKms,vehicle.getFuel_consumed()+tripFuel));
		vehicle.setKms_driven(vehicle.getKms_driven()+tripKms);
		vehicle.setFuel_consumed(vehicle.getFuel_consumed()+tripFuel);
		vehicle.setFuel_economy_status(getFuelEconomyStatus(vehicle.getFuel_economy()));
		vehicleDAO.updateVehicleFuelEconomy(vehicle);
		saveFuelEconomy(fuelEconomy);
		return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}

	//This method interacts with VehicleDAO to delete vehicle from the database
	@Override
	@Transactional
	public boolean deleteVehicle(int vehicleId) {
		try{
		return vehicleDAO.deleteVehicle(vehicleId);
		}
		catch(Exception e)
		{
			return false;
		}
	}

	//This method contains formula to calculate fuel economy of a vehicle
	@Override
	public float calculateFuelEconomy(float kms_driven, float fuel_consumed) {
		float fuelEconomy = 0.00f;
		if ((int) fuel_consumed != 0) {
			fuelEconomy = kms_driven / fuel_consumed;
		}
		return (float) Math.round(fuelEconomy * 100.0) / 100.0f; //Magic Number for rounding off Fuel Economy Value
	}

	//This method is used to get Fuel economy status based on the value of the fuel economy
	private String getFuelEconomyStatus(float fuelEconomy) {
		if (fuelEconomy > 13)
			return ServiceConstants.GOOD;
		else if (fuelEconomy < 8)
			return ServiceConstants.BAD;
		else return ServiceConstants.AVERAGE;
	}

	//This method interacts with Fuel economy DAO to save new fuel economy in fuelEconomy table
	@Override
	public boolean saveFuelEconomy(FuelEconomy fuelEconomy) {
		if(fuelEconomy==null)
		return false;
		try{
		fuelEconomyDAO.saveFuelEconomy(fuelEconomy);
		return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}


	@Override
	public ArrayList<ChartData> getVehicleUseChart(int vehicleOwnerId) {
		List<Vehicle> vehicles = vehicleDAO.getVehicles(vehicleOwnerId);
		ArrayList<ChartData> chartDataArrayList = new ArrayList<>();
		float[] kmsTravelled = new float[vehicles.size()];
		float[] fuelConsumed = new float[vehicles.size()];
		String[] labels = new String[vehicles.size()];
		String[] backgroundColor = new String[vehicles.size()];
		ChartData chartData1 = new ChartData();
		ChartData chartData2 =  new ChartData();
		for (int i = 0; i < vehicles.size(); i++) {
			labels[i] = vehicles.get(i).getVehicle_name();
			kmsTravelled[i] = vehicles.get(i).getKms_driven();
			fuelConsumed[i] = vehicles.get(i).getFuel_consumed();
			backgroundColor[i] = "rgb("+randomRGB()+","+randomRGB()+","+randomRGB()+")";
		}

		chartData1.setData(kmsTravelled);
		chartData1.setLabel(labels);
		chartData1.setBackgroundColor(backgroundColor);

		chartData2.setLabel(labels);
		chartData2.setData(fuelConsumed);
		chartData2.setBackgroundColor(backgroundColor);

		chartDataArrayList.add(chartData1);
		chartDataArrayList.add(chartData2);
		return chartDataArrayList;
	}

	@Override
	public ArrayList<ChartData> getFuelConsumedChart(int vehicleOwnerId) {
		List<Vehicle> vehicles = vehicleDAO.getVehicles(vehicleOwnerId);
		ArrayList<ChartData> chartDataArrayList = new ArrayList<>();
		float[] kmsTravelled = new float[vehicles.size()];
		float[] fuelConsumed = new float[vehicles.size()];
		String[] labels = new String[vehicles.size()];
		String[] backgroundColor = new String[vehicles.size()];
		ChartData chartData1 = new ChartData();
		ChartData chartData2 =  new ChartData();
		for (int i = 0; i < vehicles.size(); i++) {
			labels[i] = vehicles.get(i).getVehicle_name();
			kmsTravelled[i] = vehicles.get(i).getKms_driven();
			fuelConsumed[i] = vehicles.get(i).getFuel_consumed();
			backgroundColor[i] = "rgb("+randomRGB()+","+randomRGB()+","+randomRGB()+")";
		}

		chartData1.setData(kmsTravelled);
		chartData1.setLabel(labels);
		chartData1.setBackgroundColor(backgroundColor);

		chartData2.setLabel(labels);
		chartData2.setData(fuelConsumed);
		chartData2.setBackgroundColor(backgroundColor);

		chartDataArrayList.add(chartData1);
		chartDataArrayList.add(chartData2);
		return chartDataArrayList;
	}



	private int randomRGB(){
		return (int) Math.floor(Math.random() * (235 - 52 + 1) + 52);
	}


}





