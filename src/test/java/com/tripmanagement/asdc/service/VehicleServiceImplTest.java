package com.tripmanagement.asdc.service;

import com.tripmanagement.asdc.model.FuelEconomy;
import com.tripmanagement.asdc.model.Vehicle;
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
class VehicleServiceImplTest {
    @Autowired
    VehicleService vehicleService;

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
        assertTrue(vehicleService.addVehicle(vehicle));
    }
    @Test
    void testaddVehicle_false(){

        assertFalse(vehicleService.addVehicle(null));
    }

    @Test
    void testgetVehicleDetails_true() {
        assertTrue(vehicleService.getVehicleDetails(47) != null);
    }

    @Test
    void testgetVehicleDetails_false() {
        assertTrue(vehicleService.getVehicleDetails(00) == null);
    }

    @Test
    void testupdateFuelEconomy_true() {
        FuelEconomy fuelEconomy = new FuelEconomy();
        fuelEconomy.setVehicle_id(47);
        fuelEconomy.setFuel_economy(12);
        fuelEconomy.setKms_travelled(202);
        fuelEconomy.setFuel_consumed(12);
        assertTrue(vehicleService.updateFuelEconomy(fuelEconomy));

    }

    @Test
    void testupdateFuelEconomy_false() {

        assertFalse(vehicleService.updateFuelEconomy(null));
    }

    @Test
    void deleteVehicle_true() {
        int vehicleId = 11111111;
        assertTrue(vehicleService.deleteVehicle(vehicleId));
    }


    @Test
    void testgetVehicles(){
        assertTrue(vehicleService.getVehicles(71).size()>0);
    }

    @Test
    void testgetVehicleUseChart()
    {
        assertTrue(vehicleService.getVehicleUseChart(47).size()>0);
    }

    @Test
    void testgetFuelConsumedChart()
    {
        assertTrue(vehicleService.getFuelConsumedChart(47).size()>0);
    }
}