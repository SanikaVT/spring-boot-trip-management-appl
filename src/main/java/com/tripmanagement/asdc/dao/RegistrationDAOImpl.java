package com.tripmanagement.asdc.dao;

import com.tripmanagement.asdc.model.Customer;
import com.tripmanagement.asdc.model.VehicleOwner;
import com.tripmanagement.asdc.stringsAndConstants.DAOConstants;
import com.tripmanagement.asdc.stringsAndConstants.DAOStringMessages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

/*Class contains methods specific to database operations on registraion/login of vehicleowner and customer*/
@Repository
public class RegistrationDAOImpl implements RegistrationDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    Logger logger = LoggerFactory.getLogger(RegistrationDAOImpl.class);


    //This method is used to valid if the customer/vehicleowner exits by his email
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


    //This method is used to valid the email and password of the customer/vehicleowner while authentication
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
            return DAOConstants.USER_TYPE_VEHICLE_OWNER;
        else if (customer != null && !customer.getEmail().isEmpty())
            return DAOConstants.USER_TYPE_CUSTOMER;
        else
            return DAOStringMessages.INCORRECT_AUTH;
    }
    catch(Exception e)
    {
        logger.error("Error checking email and password",e);
        return null;
    }
    }

}
