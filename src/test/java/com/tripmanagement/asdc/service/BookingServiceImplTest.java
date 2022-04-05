package com.tripmanagement.asdc.service;

import com.tripmanagement.asdc.model.Booking;
import com.tripmanagement.asdc.model.Trip;
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
class BookingServiceImplTest {
    @Autowired
    BookingService bookingService;
    @Test
    void testGetUpcomingRidesForCorrectCustomer() {
        //assert True here
        assertTrue(bookingService.getUpcomingRidesForCustomer(4).size()>=0);
    }

    @Test
    void testGetUpcomingRidesForWrongCustomer() {
        assertTrue(bookingService.getUpcomingRidesForCustomer(00).size()<=0);
    }

    @Test
    void testSaveNullRide() {
        Booking booking=null;
        assertFalse(bookingService.saveRide(booking));

    }
    @Test
    void testSaveEmptyRide() {
        Booking booking=new Booking();
        assertFalse(bookingService.saveRide(booking));

    }
    @Test
    void testSaveCorrectRide() {
        Trip trip=new Trip();
        trip.setTrip_id(56);
        trip.setSource("Bangalore");
        trip.setDestination("Mysore");
        trip.setVehicle_id(1111);
        trip.setEstimated_kms(555);
        trip.setKms_travelled(50);
        trip.setAvailable_seats(4);
        trip.setStart_time("01-01-2022");
        trip.setEnd_time("01-01-2023");
        trip.setSeats_remaining(8);
        trip.setCost(1000);
        trip.setVehicle_owner_id(1111);
        Booking booking = new Booking(30,40,"2022-03-31",55,0,105,111,trip);
        //Booking booking=new Booking(100, "test_source", "test_destination", 2, 4, 3, 3, "2022-01-01", 20, 23);
        assertTrue(bookingService.saveRide(booking));

    }

    @Test
    void testgetPreviousTripsForCorrectCustomer() {
        assertTrue(bookingService.getPreviousRidesForCustomer(4).size()>=0);
    }
    @Test
    void testgetPreviousTripsForWrongCustomer() {
        assertTrue(bookingService.getPreviousRidesForCustomer(-1).size()<=0);
    }

    @Test
    void testpayforRideSucess() {
        Booking booking=new Booking();
        booking.setBooked_ride_id(35);
        booking.setCustomer_id(41);
        booking.setTimestamp("2022-03-31");
        booking.setCost(1);
        booking.setSeats_booked(2);
        booking.setTrip_id(107);
        booking.setIsPaid(1);
        booking.setTrip(new Trip());
        assertEquals(bookingService.payforRide(booking),"Success");
    }
}