package com.tripmanagement.asdc.service;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
class NotificationServiceImplTest {
    @Autowired
NotificationService notificationService;
    @Test
    void testsendEmail_Correct() {
        assertTrue(notificationService.sendEmail("test case","test case test","dharmaysureja@gmail.com"));
    }
    @Test
    void testsendEmailnullmessage(){
        assertFalse(notificationService.sendEmail(null,"hi","dharmaysureja@gmail.com"));
    }
    @Test
    void testsendEmailnullsubject(){
        assertFalse(notificationService.sendEmail("test case",null,"dharmaysureja@gmail.com"));
    }
    @Test
    void testsendEmailnullemail(){
        assertFalse(notificationService.sendEmail("test case","test case test",null));
    }
}