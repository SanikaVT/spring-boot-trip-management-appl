package com.tripmanagement.asdc.dao;

import com.tripmanagement.asdc.model.VehicleOwner;
import com.tripmanagement.asdc.service.NotificationService;
import com.tripmanagement.asdc.stringsAndConstants.StringMessages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VehicleOwnerDAOImpl implements VehicleOwnerDAO {


    @Autowired
    JdbcTemplate jdbcTemplate;

	@Autowired
	NotificationService notificationService;

	@Override
	public void saveVehicleOwner(VehicleOwner vehicleOwner) {

        String sql = "insert into vehicleowner values("+vehicleOwner.getVehicleowner_fname()+","+vehicleOwner.getVehicleowner_lname()+","+vehicleOwner.getPhone()+","+vehicleOwner.getAddress()+","+vehicleOwner.getEmail()+","+vehicleOwner.getVehicle_id()+","+vehicleOwner.getPassword()+");";
        jdbcTemplate.update(sql);
		notificationService.sendEmail(vehicleOwner.getVehicleowner_fname()+StringMessages.USER_REGISTERED_SUCCESSFULLY,StringMessages.AUTH_SUCCESSFUL,vehicleOwner.getEmail());

		
	}

	@Override
	public VehicleOwner getVehicleOwner(int theId) {
	   VehicleOwner carOwner=jdbcTemplate.queryForObject("select * from vehicleowner where vehicleowner_id="+theId,
       BeanPropertyRowMapper.newInstance(VehicleOwner.class));
	  return carOwner;
	}

	@Override
	public List<VehicleOwner> getVehicleOwners() {
		List<VehicleOwner> vehicleOwners=jdbcTemplate.queryForList("select * from vehicleowner",
		VehicleOwner.class);
		return vehicleOwners;
	}
}










