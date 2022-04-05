package com.tripmanagement.asdc.dao;

public interface RegistrationDAO {
    public boolean checkUserExistByEmail(String email);

    public String checkEmailPassword(String email, String password);
}
