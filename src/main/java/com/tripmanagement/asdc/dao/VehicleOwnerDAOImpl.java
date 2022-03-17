package com.tripmanagement.asdc.dao;

import com.tripmanagement.asdc.model.VehicleOwner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class VehicleOwnerDAOImpl implements VehicleOwnerDAO {


    @Autowired
    JdbcTemplate jdbcTemplate;

	@Override
	public void saveVehicleOwner(VehicleOwner vehicleOwner) {

        String sql = "insert into vehicleowner values("+null+",'"+vehicleOwner.getVehicleowner_fname()+"','"+vehicleOwner.getVehicleowner_lname()+"','"+vehicleOwner.getPhone()+"','"+vehicleOwner.getAddress()+"','"+vehicleOwner.getEmail()+"',"+vehicleOwner.getVehicle_id()+",'"+vehicleOwner.getPassword()+"');";
        jdbcTemplate.update(sql);
//		notificationService.sendEmail(vehicleOwner.getVehicleowner_fname()+StringMessages.USER_REGISTERED_SUCCESSFULLY,StringMessages.AUTH_SUCCESSFUL,vehicleOwner.getEmail());
		
	}

	@Override
	public VehicleOwner getVehicleOwner(int theId) {
	   VehicleOwner carOwner=jdbcTemplate.queryForObject("select * from vehicleowner where email="+theId,
       BeanPropertyRowMapper.newInstance(VehicleOwner.class));
	  return carOwner;
	}

	
}










