package com.tripmanagement.asdc.service;

public interface NotificationService {

    public boolean sendEmail(String message, String subject, String email);

}