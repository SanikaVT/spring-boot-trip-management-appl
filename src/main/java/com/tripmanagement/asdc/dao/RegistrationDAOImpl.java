package com.tripmanagement.asdc.dao;

import com.tripmanagement.asdc.model.Customer;
import com.tripmanagement.asdc.model.VehicleOwner;
import com.tripmanagement.asdc.stringsAndConstants.Constants;
import com.tripmanagement.asdc.stringsAndConstants.DAOStringMessages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class RegistrationDAOImpl implements RegistrationDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    Logger logger = LoggerFactory.getLogger(RegistrationDAOImpl.class);


    @Override
    public boolean checkUserExistByEmail(String email) {
        if(email==null||email.isEmpty())
        return false;
        try{
            String selectQuery = "select count(*) from vehicleowner where email='" + email + "'";
            int emailCount = jdbcTemplate.queryForObject(selectQuery
                , Integer.class);
        System.out.println(emailCount);
        if (emailCount <= 0)
        {
            String selectQuery2 = "select count(*) from customer where email='" + email + "'";
            emailCount = jdbcTemplate.queryForObject(selectQuery2
                , Integer.class);
            if(emailCount<=0)
            {
                return false;
            }
            else 
                return true;
        }
        else
            return true;
    }
    catch(Exception e)
    {
        logger.error("Error getting user by email",e);
        return false;
    }
    }


    @Override
    public String checkEmailPassword(String email, String password) {
        if (password == null || email == null) {
            return DAOStringMessages.INCORRECT_AUTH;
        } else if ( password.isEmpty() || email.isEmpty()) {
            return DAOStringMessages.INCORRECT_AUTH;
        }
        try{
        String fetchCustomer = "select * from customer where email='" + email + "' and password='" + password + "'";
        String fetchVehicleOwner = "select * from vehicleowner where email='" + email + "' and password='" + password + "'";
        Customer customer = jdbcTemplate.query(fetchCustomer, new ResultSetExtractor<Customer>() {
            @Override
            public Customer extractData(ResultSet rs) throws SQLException,
                    DataAccessException {
                if (rs.next()) {
                    Customer cust = new Customer();
                    cust.setEmail(rs.getString("email"));
                    return cust;
                } else return null;
            }
        });

        VehicleOwner vehicleOwner = jdbcTemplate.query(fetchVehicleOwner, new ResultSetExtractor<VehicleOwner>() {
            @Override
            public VehicleOwner extractData(ResultSet rs) throws SQLException,
                    DataAccessException {
                if (rs.next()) {
                    VehicleOwner vo = new VehicleOwner();
                    vo.setEmail(rs.getString("email"));
                    return vo;
                } else return null;
            }
        });

        if (vehicleOwner != null && !vehicleOwner.getEmail().isEmpty())
            return Constants.USER_TYPE_VEHICLE_OWNER;
        else if (customer != null && !customer.getEmail().isEmpty())
            return Constants.USER_TYPE_CUSTOMER;
        else
            return DAOStringMessages.INCORRECT_AUTH;
    }
    catch(Exception e)
    {
        logger.error("Error checking email and password",e);
        return null;
    }
    }


    @Override
    public boolean checkEmailExists(String email, String userType) {
        if (email == null||userType == null) {
            return false;
        } else if (email.isEmpty() || userType.isEmpty()) {
            return false;
        }
        try{
        if (userType.equals(Constants.USER_TYPE_CUSTOMER)) {
            String selectCustomerQuery = "select * from customer where email='" + email + "'";
            Customer customer = jdbcTemplate.queryForObject(selectCustomerQuery,
                    BeanPropertyRowMapper.newInstance(Customer.class));
            if (customer != null && !customer.getEmail().isEmpty())
                return true;
            else
                return false;
        } else if (userType.equals(Constants.USER_TYPE_VEHICLE_OWNER)) {
            String selectCarOwnerQuery = "select * from vehicleowner where email='" + email + "'";
            BeanPropertyRowMapper<VehicleOwner> rowMapper = BeanPropertyRowMapper.newInstance(VehicleOwner.class);
            VehicleOwner carOwner = jdbcTemplate.queryForObject(selectCarOwnerQuery,
                    rowMapper);
            if (carOwner != null && !carOwner.getEmail().isEmpty())
                return true;
            else
                return false;
        } else return false;
    }
    catch(Exception e)
    {
        logger.error("Error checking user exist by email",e);
        return false;
    }
    }

}
