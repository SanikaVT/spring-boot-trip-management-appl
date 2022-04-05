package com.tripmanagement.asdc.dao;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;

import com.tripmanagement.asdc.model.Trip;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource("/application-test.properties")
class TripDAOmplTest {

@Autowired
TripDAO tripDAO;

    @Test
    void saveNullTrip() {
        Trip trip=null;
        assertFalse(tripDAO.saveTrip(trip));
    }

    @Test
    void saveEmptyTrip() {
        Trip trip=new Trip();
        assertFalse(tripDAO.saveTrip(trip));
    }

    @Test
    void saveCorrectTrip() {
        Trip trip=new Trip();
        trip.setVehicle_id(5);
        trip.setSource("test_source");
        trip.setDestination("test_destination");
        trip.setEstimated_kms(555);
        trip.setKms_travelled(55);
        trip.setAvailable_seats(4);
        trip.setStart_time("01-01-2022");
        trip.setEnd_time("02-02-2022");
        trip.setSeats_remaining(3);
        trip.setCost(65);
        trip.setVehicle_owner_id(2);
        assertTrue(tripDAO.saveTrip(trip));
    }
    @Test
    void saveCorrectTripIllegaldestination() {

        Trip trip=new Trip();
        trip.setVehicle_id(5);
        trip.setSource("test_source");
        trip.setDestination("test_destination\'");
        trip.setEstimated_kms(555);
        trip.setKms_travelled(55);
        trip.setAvailable_seats(4);
        trip.setStart_time("01-01-2022");
        trip.setEnd_time("02-02-2022");
        trip.setSeats_remaining(3);
        trip.setCost(65);
        trip.setVehicle_owner_id(2);
        assertFalse(tripDAO.saveTrip(trip));
    }


    @Test
    void getAllTripsForCorrectVehicleOwner(){
        assertTrue(tripDAO.getAllTripsForVehicleOwner(2).size()>0);
    }
    @Test
    void getAllTripsForWrongVehicleOwner(){
        assertTrue(tripDAO.getAllTripsForVehicleOwner(-1).size()<=0);
    }

    @Test
    void deleteCorrectTrip() {
        assertTrue(tripDAO.deleteTrip(9));
    }

    @Test
    void testgetTripDetails() {
        assertTrue(tripDAO.getTripDetails(105)!=null);
    }
    @Test
    void testWrongTripDetails() {
        assertNull(tripDAO.getTripDetails(00));
    }

    @Test
    void testGetAvailableTripsList() {
        assertTrue(tripDAO.getAvailableTripsList("Halifax","Lucknow",Date.valueOf("2022-01-01").toString()).size()>=0);
    }

    @Test
    void testGetAvailableTripsListWithNullSource() {
        assertNull(tripDAO.getAvailableTripsList(null,"Lucknow",Date.valueOf("2022-01-01").toString()));
    }

    @Test
    void testGetAvailableTripsListWithNullDestination() {
        assertNull(tripDAO.getAvailableTripsList("Halifax",null,Date.valueOf("2022-01-01").toString()));
    }

    @Test
    void testGetAvailableTripsListWithEmptySource() {
        assertTrue(tripDAO.getAvailableTripsList("","Lucknow",Date.valueOf("2022-01-01").toString()).size()<=0);
    }
   @Test
    void testGetAvailableTripsListWithIllegalsource() {
        assertTrue(tripDAO.getAvailableTripsList("\'","Lucknow",Date.valueOf("2022-01-01").toString()).size()<=0);
    }

    @Test
    void testGetAvailableTripsListWithEmptyDestination() {
        assertTrue(tripDAO.getAvailableTripsList("Halifax","",Date.valueOf("2022-01-01").toString()).size()<=0);
    }
    @Test
    void testGetgetSourcesExist(){
        assertTrue(tripDAO.getSources().size()>0);
    }
   @Test
    void testGetgetSourcesnotExist(){
        assertFalse(tripDAO.getSources().size()<=0);
    }
    @Test
    void testgetDestinationsExist(){
        assertTrue(tripDAO.getDestinations().size()>0);
    }
   @Test
    void testgetDestinationsnotExist(){
        assertFalse(tripDAO.getDestinations().size()<=0);
    }
    @Test
    void testupdateAvailableSeatsCorrect(){
        assertTrue(tripDAO.updateAvailableSeats(1,2));
    }





}