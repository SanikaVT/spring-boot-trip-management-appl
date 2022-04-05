package com.tripmanagement.asdc.service;

import com.tripmanagement.asdc.dao.RegistrationDAO;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource("/application-test.properties")
class RegistrationServiceImplTest {

    @Autowired
    RegistrationService registrationService;

    @InjectMocks
    RegistrationServiceImpl registrationServiceImpl;

    @Mock
    RegistrationDAO registrationDAO;

    @Test
    void testCheckUserExistByEmailnull() {

        assertFalse(registrationService.checkUserExistByEmail(null));
    }

    @Test
    void testCheckUserExistByWrongEmail() {
        String email="test1@test1.test1";
        when(registrationDAO.checkUserExistByEmail(email)).thenReturn(true);

        assertTrue(registrationServiceImpl.checkUserExistByEmail("test1@test1.test1"));
    }

    @Test
    void testcheckUserExistByCorrectEmail() {
        assertTrue(registrationService.checkUserExistByEmail("test@case.com"));
    }

    @Test
    void testCheckWrongEmailPassword() {
        assertEquals(registrationService.checkEmailPassword("test1@test1.test", "test"),"User does not exist");
    }

    @Test
    void testCheckNullEmailPassword() {
        assertEquals(registrationService.checkEmailPassword(null, "test"),"Incorrect email or password");
    }

    @Test
    void testCheckEmailNullPassword() {
        assertEquals(registrationService.checkEmailPassword("svt@gmail.com", null),"Incorrect email or password");
    }

    @Test
    void testCheckEmptyEmailPassword() {
        assertEquals(registrationService.checkEmailPassword("", "qwerty123"),"Incorrect email or password");
    }

    @Test
    void testCheckEmailEmptyPassword() {
        assertEquals(registrationService.checkEmailPassword("svt@gmailcom", ""),"Incorrect email or password");
    }

    @Test
    void testCheckCorrectEmailPassword() {
        assertTrue(registrationService.checkEmailPassword("test@email.test", "123456") != null);
    }
 @Test
    void testCheckIllegalEmailPassword() {
        assertTrue(registrationService.checkEmailPassword("test@email.test\'", "123456") != null);
    }

    @Test
    void testCheckCorrectEmailExist() {
        assertTrue(registrationService.checkUserExistByEmail("test@case.com"));
        assertTrue(registrationService.checkUserExistByEmail("test1@case.com"));
    }

    @Test
    void testcheckWrongEmailExists() {
        assertFalse(registrationService.checkUserExistByEmail("xyz1@qwe.com"));
        assertFalse(registrationService.checkUserExistByEmail("test1@test.test"));
    }

    @Test
    void testCheckNullEmailExists() {
        assertFalse(registrationService.checkUserExistByEmail(null));
    }

    @Test
    void testCheckEmptyEmailExists() {
        assertFalse(registrationService.checkUserExistByEmail(""));
    }

}