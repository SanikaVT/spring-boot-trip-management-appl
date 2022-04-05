package com.tripmanagement.asdc.dao;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource("/application-test.properties")
class RegistrationDAOTest {

    @Autowired
    RegistrationDAO registrationDAO;

    @Test
    void testcheckUserExistByCorrectEmail() {
        assertTrue(registrationDAO.checkUserExistByEmail("test@case.com"));
    }
   @Test
    void testcheckUserExistByIllegalEmail() {
        assertFalse(registrationDAO.checkUserExistByEmail("vatsal@abc.com\'"));
    }

    @Test
    void testcheckUservehicleOwnerExistByCorrectEmail() {
        assertTrue(registrationDAO.checkUserExistByEmail("test@case.com"));
    }

    @Test
    void testCheckUserExistByEmailnull() {
        assertFalse(registrationDAO.checkUserExistByEmail(null));
    }

    @Test
    void testCheckUserExistByWrongEmail() {
        assertFalse(registrationDAO.checkUserExistByEmail("test1@test1.test1"));
    }

    @Test
    void testCheckWrongEmailPassword() {
        assertEquals(registrationDAO.checkEmailPassword("test@test.test", "test"),"Incorrect email or password");
    }

    @Test
    void testCheckNullEmailPassword() {
        assertEquals(registrationDAO.checkEmailPassword(null, "test"),"Incorrect email or password");
    }

    @Test
    void testCheckEmailNullPassword() {
        assertEquals(registrationDAO.checkEmailPassword("svt@gmail.com", null),"Incorrect email or password");
    }

    @Test
    void testWrongEmailPassword(){
        assertEquals(registrationDAO.checkEmailPassword("test@test.test","123"),"Incorrect email or password");
        assertEquals(registrationDAO.checkEmailPassword("vatsal@abc.com","123"),"Incorrect email or password");

    }
    @Test
    void testillegalemailPassword(){
        assertNull(registrationDAO.checkEmailPassword("abc@test.com\'","123"));
    }

    @Test
    void testCheckEmptyEmailPassword() {
        assertEquals(registrationDAO.checkEmailPassword("", "qwerty123"),"Incorrect email or password");
    }

    @Test
    void testCheckEmailEmptyPassword() {
        assertEquals(registrationDAO.checkEmailPassword("svt@gmailcom",""),"Incorrect email or password");
    }

    @Test
    void testCheckCorrectEmailPassword() {
        assertTrue(registrationDAO.checkEmailPassword("svt@gmail.com", "qwerty123") != null);
    }
}