package com.tripmanagement.asdc.dao;

import com.tripmanagement.asdc.model.Customer;

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

/*Class contains methods specific to database operations on customer table*/
@Repository
public class CustomerDAOImpl implements CustomerDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

	Logger logger = LoggerFactory.getLogger(CustomerDAOImpl.class);

    //This method is used to insert customer object into the database
    @Override
    public boolean saveCustomer(Customer customer) {
        if(customer==null||customer.getCustomer_fname().isEmpty())
			return false;
        try{
            String name = customer.getCustomer_fname() + "','" + customer.getCustomer_lname();
            String innerSubQuery = ",'" + name + "','";
            String subQuery1 = innerSubQuery + customer.getMobile_no() + "','";
            String emailPassword = customer.getEmail() + "','" + customer.getPassword();
            String subQuery2 = emailPassword + "'," + customer.getAvailable_credits() + ");";
            String sql = "insert into customer values(" + null + subQuery1 + subQuery2;
        jdbcTemplate.update(sql);
        return true;
        }
        catch(Exception e)
        {
            logger.error("Error saving customers",e);
            return false;
        }
    }

    //This method is used to get a customer by his email from the customer table
    @Override
    public Customer getCustomerByEmail(String email) {
        if(email==null||email.isEmpty())
        return null;
        try{
            String s = "select * from customer where email='" + email + "'";
            Customer customer = jdbcTemplate.query(s, new ResultSetExtractor<Customer>() {
                @Override
                public Customer extractData(ResultSet rs) throws SQLException,
                        DataAccessException {
                    if (rs.next()) {
                        Customer cust = new Customer();
                        cust.setEmail(rs.getString("email"));
                        cust.setCustomer_fname(rs.getString("customer_fname"));
                        cust.setCustomer_lname(rs.getString("customer_lname"));
                        cust.setMobile_no(rs.getString("mobile_no"));
                        cust.setAvailable_credits(Integer.parseInt(rs.getString("available_credits")));
                        cust.setCustomer_id(Integer.parseInt(rs.getString("customer_id")));
                        return cust;
                    } else return null;
                }
            });
        return customer;
        }
        catch(Exception e)
        {
            logger.error("Error getting customer by email",e);
            return null;
        }
    }

    //This method is used to get a customer by his id from the customer table
    @Override
    public Customer getCustomerById(int id) {
        try{
            String query="select * from customer where customer_id="+id;
        Customer customer=jdbcTemplate.queryForObject(query,
        BeanPropertyRowMapper.newInstance(Customer.class));
        return customer;
        }
        catch(Exception e)
        {
            logger.error("Error getting customer by Id",e);
            return null;
        }
    }

    //This method is used to update available credits of the customer when the customer pays for his ride or buys credits
    @Override
    public boolean updateAvaialableCredits(int customer_id, int available_credits) {
        try{
			String sql = "update customer set available_credits="+available_credits+" where customer_id="+customer_id;
			jdbcTemplate.update(sql);
			return true;
			}
			catch(Exception e)
			{
				logger.error("Error updating available credits in Customer",e);
				return false;
	
			}
    }
    
}
