package com.tripmanagement.asdc.controller;

import com.tripmanagement.asdc.model.Customer;
import com.tripmanagement.asdc.model.User;
import com.tripmanagement.asdc.model.Vehicle;
import com.tripmanagement.asdc.model.VehicleOwner;
import com.tripmanagement.asdc.service.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class TestController {



        private MockMvc mockMvc;
        @InjectMocks
        private  RegistrationController registrationController;

        @Mock
        private VehicleService vehicleService;

        @Mock
        private RegistrationService registrationService;

        @Mock
        private TripService tripService;

        @Mock
        private CustomerService customerService;

        @Mock
        private VehicleOwnerService vehicleOwnerService;

        @Mock
        private BookingService bookedRidesService;

        @Mock
        private User user;
    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        Object[] controllers;
        mockMvc = MockMvcBuilders.standaloneSetup(registrationController).build();
    }
        @Test
        public void testUserloginIncorrect() throws Exception {
            when(registrationService.checkUserExistByEmail(any())).thenReturn(true);
            when(registrationService.checkEmailPassword(any(),any())).thenReturn("Incorrect email or password");

            mockMvc.perform(post("/dashboard"))
                    .andExpect(status().isOk())
                    .andExpect(view().name("login"));

        }

    @Test
    public void testUserloginVehicleOwner() throws Exception {
        when(registrationService.checkUserExistByEmail(any())).thenReturn(true);
        when(registrationService.checkEmailPassword(any(),any())).thenReturn("VEHICLE_OWNER");
        when(vehicleOwnerService.getVehicleOwnerByEmail(any())).thenReturn(new VehicleOwner());

        List<Vehicle> vehicle1=new ArrayList<>();
        Vehicle vehicle = new Vehicle();
        Vehicle vehicle2 = new Vehicle();
        vehicle1.add(vehicle);
        vehicle1.add(vehicle2);

        when(vehicleService.getVehicles(anyInt())).thenReturn(vehicle1);
        when(tripService.getPreviousTripsForVehicleOwner(anyInt())).thenReturn(null);
        when(tripService.getUpcomingTripsForVehicleOwner(anyInt())).thenReturn(null);
        mockMvc.perform(post("/dashboard"))
                .andExpect(status().isOk())
                .andExpect(view().name("owner-dashboard"));

    }

    @Test
    public void testUserloginVehicleCustomer() throws Exception {
        when(registrationService.checkUserExistByEmail(any())).thenReturn(true);
        when(registrationService.checkEmailPassword(any(),any())).thenReturn("CUSTOMER");
        when(customerService.getCustomerByEmail(any())).thenReturn(new Customer());
        when(bookedRidesService.getPreviousRidesForCustomer(anyInt())).thenReturn(null);
        when(bookedRidesService.getUpcomingRidesForCustomer(anyInt())).thenReturn(null);
        when(tripService.getSources()).thenReturn(null);
        when(tripService.getDestinations()).thenReturn(null);

        mockMvc.perform(post("/dashboard"))
                .andExpect(status().isOk())
                .andExpect(view().name("customer-dashboard"));

    }

    @Test
    public void testregisterUserExists() throws Exception {
        when(registrationService.checkUserExistByEmail(any())).thenReturn(true);
        mockMvc.perform(post("/register-user"))
                .andExpect(status().isOk())
                .andExpect(view().name("register"));

    }

    @Test
    public void testregisterUserNewVehicleOwner() throws Exception {
        when(registrationService.checkUserExistByEmail(any())).thenReturn(false);
        mockMvc.perform(post("/register-user").param("userType","Vehicle Owner"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    @Test
    public void testRegisterUserNew() throws Exception {
        when(registrationService.checkUserExistByEmail(any())).thenReturn(false);
        mockMvc.perform(post("/register-user").param("userType","Vehicle Owner"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    @Test
    public void testforgotPassword() throws Exception {

        mockMvc.perform(get("/forgot-password"))
                .andExpect(status().isOk())
                .andExpect(view().name("forgot"));
    }

    @Test
    public void testRegister() throws Exception {
        mockMvc.perform(get("/register-user"))
                .andExpect(status().isOk())
                .andExpect(view().name("register"));
    }




//        @Test
//        public void testList() throws Exception{
//            String message1 = null;
//            mockMvc.perform(get("/"))
//                    .andExpect(status().isOk())
//                    .andExpect(view().name("login"))
//                    .andExpect(model().attribute("message",message1));
//
//        }





    }


