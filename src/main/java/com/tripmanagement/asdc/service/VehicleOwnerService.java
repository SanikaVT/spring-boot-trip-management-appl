package com.tripmanagement.asdc.service;

//import java.util.List;

import com.tripmanagement.asdc.model.VehicleOwner;

;

public interface VehicleOwnerService {

	public void saveVehicleOwner(VehicleOwner carOwner);

	public VehicleOwner getVehicleOwner(int theId);

	//public void deleteCustomer(int theId);
	
}