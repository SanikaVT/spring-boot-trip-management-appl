package com.tripmanagement.asdc.service;

import com.tripmanagement.asdc.model.Trip;
import com.tripmanagement.asdc.model.Vehicle;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource("/application-test.properties")
class TripServiceImplTest {


    @Autowired
    TripServiceImpl tripService;

    @Test
    void saveNullTrip() {
        Trip trip=null;
        assertFalse(tripService.saveTrip(trip));
    }

    @Test
    void saveEmptyTrip() {
        Trip trip=new Trip();
        assertFalse(tripService.saveTrip(trip));
    }

    @Test
    void saveCorrectTrip() {
        Trip trip=new Trip();
        trip.setVehicle_id(47);
        trip.setSource("test_source");
        trip.setDestination("test_destination");
        assertTrue(tripService.saveTrip(trip));
    }


    @Test
    void deleteCorrectTrip() {
        assertTrue(tripService.deleteTrip(9));
    }

    @Test
    void testgetTripDetails() {
        assertTrue(tripService.getTripDetails(105)!=null);
    }
    @Test
    void testWrongTripDetails() {
        assertNull(tripService.getTripDetails(00));
    }

    @Test
    void testGetCorrectUpcomingTripsForVehicleOwner() {
        assertTrue(tripService.getUpcomingTripsForVehicleOwner(14).size()>=0);
    }

    @Test
    void testGetWrongUpcomingTripsForVehicleOwner() {
        assertTrue(tripService.getUpcomingTripsForVehicleOwner(00).size()<=0);
    }

    @Test
    void testGetCorrectPreviousTripsForVehicleOwner() {
        assertTrue(tripService.getPreviousTripsForVehicleOwner(12).size()>=0);
    }

    @Test
    void testGetWrongPreviousTripsForVehicleOwner_false() {
        assertTrue(tripService.getPreviousTripsForVehicleOwner(00).size()<=0);
    }

    @Test
    void testGetAvailableTripsList() {
        assertTrue(tripService.getAvailableTripsList("Halifax","Lucknow").size()>=0);
    }

    @Test
    void testGetAvailableTripsListWithNullSource() {
        assertNull(tripService.getAvailableTripsList(null,"Lucknow"));
    }

    @Test
    void testGetAvailableTripsListWithNullDestination() {
        assertNull(tripService.getAvailableTripsList("Halifax",null));
    }

    @Test
    void testGetAvailableTripsListWithEmptySource() {
        assertNull(tripService.getAvailableTripsList("","Lucknow"));
    }

    @Test
    void testGetAvailableTripsListWithEmptyDestination() {
        assertNull(tripService.getAvailableTripsList("Halifax",""));
    }

    @Test
    void testgetSources(){
        assertTrue(tripService.getSources().size()>=0);
    }
    @Test
    void testgetDestinations(){
        assertTrue(tripService.getDestinations().size()>=0);
    }

    @Test
    void testcalculateCostCorrect(){
        Vehicle vehicle = new Vehicle();
        vehicle.setFuel_economy(30);
        Trip trip = new Trip();
        trip.setEstimated_kms(2000);
        vehicle.setAvailable_seats(4);
        trip.setAvailable_seats(4);
        assertEquals(tripService.calculateCost(vehicle,trip),32.2f);
    }

    @Test
    void testcalculateCostNull(){
        Vehicle vehicle = new Vehicle();
        Trip trip = new Trip();
        assertTrue(tripService.calculateCost(vehicle,trip)==0);
    }

}