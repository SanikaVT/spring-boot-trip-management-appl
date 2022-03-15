package com.tripmanagement.asdc.dao;

import com.tripmanagement.asdc.model.Customer;
import com.tripmanagement.asdc.service.NotificationService;
import com.tripmanagement.asdc.stringsAndConstants.StringMessages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    NotificationService notificationService;



    @Override
    public List<Customer> getCustomers() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void saveCustomer(Customer customer) {
        String sql = "insert into customer values("+customer.getCustomer_id()+","+customer.getCustomer_fname()+","+customer.getCustomer_lname()+","+customer.getMobile_no()+","+customer.getAddress()+","+customer.getEmail()+");";
        jdbcTemplate.update(sql);
        notificationService.sendEmail(customer.getCustomer_fname()+ StringMessages.USER_REGISTERED_SUCCESSFULLY,StringMessages.AUTH_SUCCESSFUL,customer.getEmail());


    }

    @Override
    public Customer getCustomer(int theId) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
