package com.tripmanagement.asdc.dao;

import com.tripmanagement.asdc.model.Customer;
import com.tripmanagement.asdc.model.VehicleOwner;
import com.tripmanagement.asdc.stringsAndConstants.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RegistrationDAOImpl implements RegistrationDAO{


    @Autowired
    JdbcTemplate jdbcTemplate;


    @Override
    public boolean checkEmailExists(String email) {
        
           VehicleOwner carOwner=jdbcTemplate.queryForObject("select * from vehicleowner where email="+email,
           BeanPropertyRowMapper.newInstance(VehicleOwner.class)); 
           if(carOwner!=null&&!carOwner.getEmail().isEmpty())
           return true;
           else
           return false;
    }


    @Override
    public boolean checkEmailPassword(String email, String password) {
        VehicleOwner carOwner=jdbcTemplate.queryForObject("select * from vehicleowner where email="+email+" and password="+password,
        BeanPropertyRowMapper.newInstance(VehicleOwner.class)); 
        if(carOwner!=null&&!carOwner.getEmail().isEmpty())
           return true;
           else
           return false;
    }


    @Override
    public boolean checkEmailExists(String email, String userType) {
        if(userType== Constants.uType_Customer)
        {
            Customer customer=jdbcTemplate.queryForObject("select * from customer where email="+email,
            BeanPropertyRowMapper.newInstance(Customer.class)); 
            if(customer!=null&&!customer.getEmail().isEmpty())
            return true;
            else
            return false;
        }
        else if(userType==Constants.uType_Customer){
        VehicleOwner carOwner=jdbcTemplate.queryForObject("select * from vehicleowner where email="+email,
           BeanPropertyRowMapper.newInstance(VehicleOwner.class)); 
           if(carOwner!=null&&!carOwner.getEmail().isEmpty())
           return true;
           else
           return false;
    }
    else return false;
    }
    
}
