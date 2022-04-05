package com.tripmanagement.asdc.service;

import javax.transaction.Transactional;

import com.tripmanagement.asdc.dao.CustomerDAO;
import com.tripmanagement.asdc.model.Customer;
import com.tripmanagement.asdc.model.User;
import com.tripmanagement.asdc.stringsAndConstants.Constants;
import com.tripmanagement.asdc.stringsAndConstants.ServiceStringMessages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*Service class for customer contains logic related to customer. This class interacts with the customer DAO class for database operations*/
@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDAO customerDAO;

	@Autowired
	private NotificationService notificationService;

	//This method contains logic for saving customer before saving it to the database using booking DAO
	@Override
	@Transactional
	public boolean saveCustomer(User user) {
		if(user==null||user.getFirst_name()==null)
		return false;
		try{
		Customer customer = new Customer();
		customer.setCustomer_fname(user.getFirst_name());
		customer.setCustomer_lname(user.getLast_name());
		customer.setEmail(user.getEmail());
		customer.setPassword(user.getPassword());
		customer.setAvailable_credits(Constants.INITIAL_CREDITS);
		boolean isSuccess= customerDAO.saveCustomer(customer);
		if(isSuccess)
		{
			String greeting = "Hey " + customer.getCustomer_fname();
			String message = greeting + ServiceStringMessages.USER_REGISTERED_SUCCESSFULLY;
			notificationService.sendEmail(message, ServiceStringMessages.AUTH_SUCCESSFUL, customer.getEmail());
		}
		return isSuccess;
		}
		catch(Exception e)
		{
			System.out.println("exception occurred");
			return false;
		}
	}

	//This method gets Customer by his email using customer DAO 
	@Override
	@Transactional
	public Customer getCustomerByEmail(String email) {
		if(email==null||email.isEmpty())
		return null;
		try{
		Customer customer= customerDAO.getCustomerByEmail(email);
		return customer;
		}
		catch(Exception e)
		{
			return null;
		}
	}

	//This method gets Customer by his id using customer DAO
	@Override
	@Transactional
	public Customer getCustomerById(int id) {
		try{
			Customer customer= customerDAO.getCustomerById(id);
			return customer;
		}
		catch(Exception e)
		{
			return null;
		}
	}

	//This method is used to update credits using customer DAO when customer hits buy credits
	@Override
	@Transactional
	public boolean buyCredits(int customer_id, int credits) {
		try{
			Customer customer= customerDAO.getCustomerById(customer_id);
			return customerDAO.updateAvaialableCredits(customer_id, credits+customer.getAvailable_credits());
		}
		catch(Exception e)
		{
			return false;
		}
	}

}





