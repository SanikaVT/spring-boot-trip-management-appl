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
        trip.setStart_time("2022-03-31T16:42");
        trip.setEnd_time("2022-04-02T16:42");
        trip.setVehicle_owner_id(2);
        trip.setCost(55);
        trip.setEstimated_kms(55);
        trip.setKms_travelled(55);
        trip.setAvailable_seats(4);

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
        assertTrue(tripService.getUpcomingTripsForVehicleOwner(72).size()>=0);
    }

    @Test
    void testGetWrongUpcomingTripsForVehicleOwner() {
        assertTrue(tripService.getUpcomingTripsForVehicleOwner(00).size()<=0);
    }

    @Test
    void testGetCorrectPreviousTripsForVehicleOwner() {
        assertTrue(tripService.getPreviousTripsForVehicleOwner(72).size()>=0);
    }

    @Test
    void testGetWrongPreviousTripsForVehicleOwner_false() {
        assertTrue(tripService.getPreviousTripsForVehicleOwner(-1).size()<=0);
    }

    @Test
    void testGetAvailableTripsList() {
        assertTrue(tripService.getAvailableTripsList("source1","destination1").size()>=0);
    }

    @Test
    void testGetAvailableTripsListWithNullSource() {
        assertNull(tripService.getAvailableTripsList(null,"test_destination"));
    }

    @Test
    void testGetAvailableTripsListWithNullDestination() {
        assertNull(tripService.getAvailableTripsList("test_source",null));
    }

    @Test
    void testGetAvailableTripsListWithEmptySource() {
        assertNull(tripService.getAvailableTripsList("","test_destination"));
    }

    @Test
    void testGetAvailableTripsListWithEmptyDestination() {
        assertNull(tripService.getAvailableTripsList("test_source",""));
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