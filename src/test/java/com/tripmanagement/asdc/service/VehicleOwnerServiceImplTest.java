package com.tripmanagement.asdc.service;

import com.tripmanagement.asdc.model.User;
import com.tripmanagement.asdc.model.VehicleOwner;
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
class VehicleOwnerServiceImplTest {

    @Autowired
    VehicleOwnerService vehicleOwnerService;
    @Test
    void testsaveVehicleOwner() {

        User user = new User();
        user.setFirst_name("test");
        user.setLast_name("case");
        user.setEmail("test@case.com");
        user.setPassword("123456");
        user.setUserType("VEHICLE_OWNER");
        assertTrue(vehicleOwnerService.saveVehicleOwner(user));
    }

    @Test
    void testsaveVehicleOwner_null(){
        User user = new User();
        assertFalse(vehicleOwnerService.saveVehicleOwner(user));

    }

    @Test
    void testGetCorrectVehicleOwnerByEmail() {
        assertNotNull(vehicleOwnerService.getVehicleOwnerByEmail("test@case.com"));
    }
    @Test
    void testGetWrongVehicleOwnerbyEmail() {
        assertNull(vehicleOwnerService.getVehicleOwnerByEmail("test1@test1.test1"));
    }

    @Test
    void testGetNullVehicleOwnerbyEmail() {
        assertNull(vehicleOwnerService.getVehicleOwnerByEmail(null));
    }

    @Test
    void testGetEmptyVehicleOwnerbyEmail() {
        assertNull(vehicleOwnerService.getVehicleOwnerByEmail(null));
    }

    @Test
    void testgetVehicleOwnerById_true() {
        assertTrue(vehicleOwnerService.getVehicleOwnerByOwnerId(69)!=null);
    }

    @Test
    void testgetVehicleOwnerById_false() {
        assertNull(vehicleOwnerService.getVehicleOwnerByOwnerId(00));
    }

    @Test
    void testbuycredits()
    {
        assertTrue(vehicleOwnerService.buyCredits(69,1));
    }
    @Test
    void testbuycredits_false() {
        assertFalse(vehicleOwnerService.buyCredits(-1,-1));
    }
}