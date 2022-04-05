package com.tripmanagement.asdc.dao;

import com.tripmanagement.asdc.model.Vehicle;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource("/application-test.properties")
class VehicleDAOImplTest {
    @Autowired
    VehicleDAO vehicleDAO;

    @Test
    void testaddVehicle_true() {
        Vehicle vehicle= new Vehicle();
        vehicle.setVehicle_id(11111111);
        vehicle.setVehicle_name("testcasePleaseignore");
        vehicle.setFuel_economy(2222);
        vehicle.setBrand("testing");
        vehicle.setVehicleowner_id(11111);
        vehicle.setFuel_economy_status("good");
        vehicle.setNumber_plate("TESTCASE1");
        vehicle.setType("Sedan");
        vehicle.setTrips(99);
        vehicle.setKms_driven(2020);
        vehicle.setAvailable_seats(4);
        vehicle.setFuel_consumed(123);
        assertTrue(vehicleDAO.addVehicle(vehicle));
    }
    @Test
    void testaddVehicleException() {
        Vehicle vehicle= new Vehicle();
        vehicle.setVehicle_id(11111111);
        vehicle.setVehicle_name("testcasePleaseignore\'");
        vehicle.setFuel_economy(2222);
        vehicle.setBrand("testing");
        vehicle.setVehicleowner_id(11111);
        vehicle.setFuel_economy_status("good");
        vehicle.setNumber_plate("TESTCASE1");
        vehicle.setType("Sedan");
        vehicle.setTrips(99);
        vehicle.setKms_driven(2020);
        vehicle.setAvailable_seats(4);
        vehicle.setFuel_consumed(123);
        assertFalse(vehicleDAO.addVehicle(vehicle));
    }
    @Test
    void testaddVehicle_false(){

        assertFalse(vehicleDAO.addVehicle(null));
    }

    @Test
    void testgetVehicleDetails_true() {
        assertTrue(vehicleDAO.getVehicleDetails(45) != null);
    }

    @Test
    void testgetVehicleDetails_false() {
        assertTrue(vehicleDAO.getVehicleDetails(00) == null);
    }

    @Test
    void testgetVehicleCorrect()
    {
        assertTrue(vehicleDAO.getVehicles(11111).size()>0);
    }
    @Test
    void testgetVehicleWrong()
    {
        assertTrue(vehicleDAO.getVehicles(-1).size()<=0);
    }

//    @Test
//    void testupdateFuelEconomy_true() {
//        int vehicle_id = 11111111;
//        float fuelEconomy = 550;
//        assertTrue(vehicleDAO.updateFuelEconomy(vehicle_id,fuelEconomy));
//    }
//
//    @Test
//    void testupdateFuelEconomy_false() {
//
//        assertFalse(vehicleDAO.updateFuelEconomy(-1,-1));
//    }

    @Test
    void deleteVehicle_true() {
      assertTrue(vehicleDAO.deleteVehicle(21));
    }

    @Test
    void testupdateVehicleFuelEconomyCorrect(){
        Vehicle vehicle= new Vehicle();
        vehicle.setVehicle_id(11111111);
        vehicle.setVehicle_name("testcasePleaseignore");
        vehicle.setFuel_economy(2222);
        vehicle.setBrand("testing");
        vehicle.setVehicleowner_id(11111);
        vehicle.setFuel_economy_status("good");
        vehicle.setNumber_plate("TESTCASE1");
        vehicle.setType("Sedan");
        vehicle.setTrips(99);
        vehicle.setKms_driven(2020);
        vehicle.setAvailable_seats(4);
        vehicle.setFuel_consumed(123);
        assertTrue(vehicleDAO.updateVehicleFuelEconomy(vehicle));
    }
    @Test
    void testupdateVehicleFuelEconomyException(){
        Vehicle vehicle= new Vehicle();
        vehicle.setVehicle_id(11111111);
        vehicle.setVehicle_name("testcasePleaseignore");
        vehicle.setFuel_economy(2222);
        vehicle.setBrand("testing");
        vehicle.setVehicleowner_id(11111);
        vehicle.setFuel_economy_status("good\'");
        vehicle.setNumber_plate("TESTCASE1");
        vehicle.setType("Sedan");
        vehicle.setTrips(99);
        vehicle.setKms_driven(2020);
        vehicle.setAvailable_seats(4);
        vehicle.setFuel_consumed(123);
        assertFalse(vehicleDAO.updateVehicleFuelEconomy(vehicle));
    }

    @Test
    void testupdateVehicleFuelEconomyNull(){
        Vehicle vehicle = null;
        assertFalse(vehicleDAO.updateVehicleFuelEconomy(vehicle));
    }

}