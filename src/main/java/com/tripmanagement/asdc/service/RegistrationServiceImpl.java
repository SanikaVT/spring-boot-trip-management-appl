package com.tripmanagement.asdc.service;

import javax.transaction.Transactional;

import com.tripmanagement.asdc.dao.RegistrationDAO;
import com.tripmanagement.asdc.stringsAndConstants.ServiceStringMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/*Service class for Registration contains logic related to rregistration of a user. This class interacts with the registration DAO for database operations*/
@Service
public class RegistrationServiceImpl implements RegistrationService{


    @Autowired
    RegistrationDAO registrationDAO;

    //This method is used to validate the email and password using registraion DAO to interact with the database and returns the message accordingly
    @Override
    @Transactional
    public String checkEmailPassword(String email, String password) {
        try{
            if (password == null || email == null) {
                return ServiceStringMessages.INCORRECT_AUTH;
            } else if (password.isEmpty() || email.isEmpty()) {
                return ServiceStringMessages.INCORRECT_AUTH;
            } else {
                if (!registrationDAO.checkUserExistByEmail(email))
                    return ServiceStringMessages.NO_USER_FOUND;
                else
                    return registrationDAO.checkEmailPassword(email, password);
            }
        }
        catch(Exception e)
        {
            return null;
        }
    }

    //This method is used to check if the user exist in the database when the customer/vehicleOwner tries to register into the application
    @Override
    @Transactional
    public boolean checkUserExistByEmail(String email)
    {
        if(email==null||email.isEmpty())
        return false;
        else
        return registrationDAO.checkUserExistByEmail(email);
    }

}
