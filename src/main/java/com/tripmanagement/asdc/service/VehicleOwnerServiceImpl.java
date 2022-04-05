package com.tripmanagement.asdc.service;

import com.tripmanagement.asdc.dao.VehicleOwnerDAO;
import com.tripmanagement.asdc.model.User;
import com.tripmanagement.asdc.model.VehicleOwner;
import com.tripmanagement.asdc.stringsAndConstants.Constants;
import com.tripmanagement.asdc.stringsAndConstants.ServiceStringMessages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/*Service class for VehicleOwner contains logic related to vehicleOwner. This class interacts with the vehicleowner DAO  for database operations*/
@Service
public class VehicleOwnerServiceImpl implements VehicleOwnerService {

	@Autowired
	private VehicleOwnerDAO vehicleOwnerDAO;
	
	@Autowired
	private NotificationService notificationService;
	
	//This method interacts with VehicleOwnerDAO to save vehicleOwner into the database
	@Override
	@Transactional
	public boolean saveVehicleOwner(User user) {
		if(user==null||user.getEmail()==null)
		return false;
		try{
		VehicleOwner vehicleOwner = new VehicleOwner();
		vehicleOwner.setVehicleowner_fname(user.getFirst_name());
		vehicleOwner.setVehicleowner_lname(user.getLast_name());
		vehicleOwner.setEmail(user.getEmail());
		vehicleOwner.setPassword(user.getPassword());
			vehicleOwner.setAvailable_credits(Constants.INITIAL_CREDITS);
		boolean isSuccess=vehicleOwnerDAO.saveVehicleOwner(vehicleOwner);
		if(isSuccess) {
			String message = vehicleOwner.getVehicleowner_fname() + ServiceStringMessages.USER_REGISTERED_SUCCESSFULLY;
			notificationService.sendEmail(message, ServiceStringMessages.AUTH_SUCCESSFUL,vehicleOwner.getEmail());
		}
		return isSuccess;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	//This method interacts with vehicleOwnerDAO to get vehicleOwner by his email during registration
	@Override
	@Transactional
	public VehicleOwner getVehicleOwnerByEmail(String email) {
		if (email == null)
			return null;
		try {
			return vehicleOwnerDAO.getVehicleOwnerByEmail(email);
		} catch (Exception e) {
			return null;
		}
	}

	//This method interacts with vehicleOwnerDAO to get vehicleOwner by his id
	@Override
	@Transactional
	public VehicleOwner getVehicleOwnerByOwnerId(int vehicleOwnerId) {
		try{
		return vehicleOwnerDAO.getVehicleOwnerById(vehicleOwnerId);
		}
		catch(Exception e)
		{
			return null;
		}
	}

	//This method interacts with vehicleDAO to update the vehicleOwner creidts when vehicleOwner hits the Buy creidts
	@Override
	@Transactional
	public boolean buyCredits(int vehicleOwnerId, int credits) {
		try{
			VehicleOwner vehicleOwner=vehicleOwnerDAO.getVehicleOwnerById(vehicleOwnerId);
			return vehicleOwnerDAO.updateAvaialableCredits(vehicleOwnerId, vehicleOwner.getAvailable_credits()+credits);
		}
		catch(Exception e)
		{
			return false;
		}
	}

}





