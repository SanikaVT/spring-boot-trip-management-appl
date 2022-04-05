package com.tripmanagement.asdc.dao;

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
class VehicleOwnerDAOImplTest {
    @Autowired
    VehicleOwnerDAO vehicleOwnerDAO;
    @Test
    void testsaveVehicleOwner() {
        VehicleOwner vehicleOwner = new VehicleOwner();
        vehicleOwner.setVehicleowner_fname("test");
        vehicleOwner.setVehicleowner_lname("case");
        vehicleOwner.setEmail("test@email.test");
        vehicleOwner.setPassword("123456");
        vehicleOwner.setVehicleOwner_id(1111111);
        vehicleOwner.setPhone("1234567890");
        assertTrue(vehicleOwnerDAO.saveVehicleOwner(vehicleOwner));
    }
    @Test
    void testsaveVehicleOwnerIllegalname() {

        VehicleOwner vehicleOwner = new VehicleOwner();
        vehicleOwner.setVehicleowner_fname("test\'");
        vehicleOwner.setVehicleowner_lname("case");
        vehicleOwner.setEmail("test@email.test");
        vehicleOwner.setPassword("123456");
        vehicleOwner.setVehicleOwner_id(1111111);
        vehicleOwner.setPhone("1234567890");
        assertFalse(vehicleOwnerDAO.saveVehicleOwner(vehicleOwner));
    }

    @Test
    void testsaveVehicleOwner_null(){
        VehicleOwner vehicleOwner = new VehicleOwner();
        assertFalse(vehicleOwnerDAO.saveVehicleOwner(vehicleOwner));

    }

    @Test
    void testGetCorrectVehicleOwnerByEmail() {
        assertNotNull(vehicleOwnerDAO.getVehicleOwnerByEmail("test1@case.com"));
    }
    @Test
    void testGetWrongVehicleOwnerbyEmail() {
        assertNull(vehicleOwnerDAO.getVehicleOwnerByEmail("test1@test1.test1"));
    }

    @Test
    void testGetNullVehicleOwnerbyEmail() {
        assertNull(vehicleOwnerDAO.getVehicleOwnerByEmail(null));
    }

    @Test
    void testGetEmptyVehicleOwnerbyEmail() {
        assertNull(vehicleOwnerDAO.getVehicleOwnerByEmail(null));
    }

    @Test
    void testgetVehicleOwnerById_true() {
        assertTrue(vehicleOwnerDAO.getVehicleOwnerById(71)!=null);
    }

    @Test
    void testgetVehicleOwnerById_false() {
        assertNull(vehicleOwnerDAO.getVehicleOwnerById(00));
    }

    @Test
    void testupdateAvaialableCredits_correct(){
        assertTrue(vehicleOwnerDAO.updateAvaialableCredits(42,552));
    }

}