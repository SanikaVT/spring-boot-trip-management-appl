package com.tripmanagement.asdc.controller;

import com.tripmanagement.asdc.model.*;
import com.tripmanagement.asdc.service.*;
import com.tripmanagement.asdc.stringsAndConstants.ControllerConstants;
import com.tripmanagement.asdc.stringsAndConstants.ControllerStringMessages;
import com.tripmanagement.asdc.stringsAndConstants.ServiceStringMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class RegistrationController {

    @Autowired
    RegistrationService registrationService;

    @Autowired
    VehicleOwnerService vehicleOwnerService;

    @Autowired
    CustomerService customerService;

    @Autowired
    VehicleService vehicleService;

    @Autowired
    TripService tripService;

    @Autowired
    BookingService bookedRidesService;

    @RequestMapping("/")
    public String basePage() {
        return "login";
    }

    @PostMapping("/dashboard")
    public String userLogin(User user, HttpSession httpSession, Model model) {
        if(registrationService.checkUserExistByEmail(user.getEmail())) {
            String message = registrationService.checkEmailPassword(user.getEmail(), user.getPassword());
            if (message.equalsIgnoreCase(ControllerStringMessages.INCORRECT_AUTH)) {
                model.addAttribute("messageStatus","FAILURE");
                model.addAttribute("message",message);
                return "login";
            } else {
                if (message.equalsIgnoreCase(ControllerConstants.USER_TYPE_VEHICLE_OWNER)) {
                    VehicleOwner vehicleOwner = vehicleOwnerService.getVehicleOwnerByEmail(user.getEmail());
                    httpSession.setAttribute("vehicleOwner", vehicleOwner);
                    httpSession.setAttribute("listOfVehicle", vehicleService.getVehicles(vehicleOwner.getVehicleOwner_id()));
                    List<Trip> previousTripsForVehicleOwner = tripService.getPreviousTripsForVehicleOwner(vehicleOwner.getVehicleOwner_id());
                    httpSession.setAttribute("previousRides", previousTripsForVehicleOwner);
                    List<Trip> upcomingTripsForVehicleOwner = tripService.getUpcomingTripsForVehicleOwner(vehicleOwner.getVehicleOwner_id());
                    httpSession.setAttribute("upcomingRides", upcomingTripsForVehicleOwner);
                    return "owner-dashboard";
                } else {
                    Customer customer = customerService.getCustomerByEmail(user.getEmail());
                    httpSession.setAttribute("source", "");
                    httpSession.setAttribute("destination", "");
                    httpSession.setAttribute("customer", customer);

                    model.addAttribute("listOfRides", new ArrayList<Ride>());
                    List<Booking> previousRidesForCustomer = bookedRidesService.getPreviousRidesForCustomer(customer.getCustomer_id());
                    httpSession.setAttribute("previousRides", previousRidesForCustomer);

                    List<Booking> upcomingRidesForCustomer = bookedRidesService.getUpcomingRidesForCustomer(customer.getCustomer_id());
                    httpSession.setAttribute("upcomingRides", upcomingRidesForCustomer);
                    httpSession.setAttribute("sourceList", tripService.getSources());
                    httpSession.setAttribute("destinationList", tripService.getDestinations());
                    return "customer-dashboard";
                }
            }
        }
        else {
            model.addAttribute("messageStatus","FAILURE");
            model.addAttribute("message","User does not exists, please register.");
            return "login";
        }
    }

    /*
     * Will return Forget Password Page
     * */
    @RequestMapping("/forgot-password")
    public String forgotPassword() {
        return "forgot";
    }

    /*
     * Will return Registration Page
     * */
    @RequestMapping("/register-user")
    public String registerUser() {
        return "register";
    }


    @PostMapping("/register-user")
    public String registerUser(User user, BindingResult result, Model model) {
        if(!registrationService.checkUserExistByEmail(user.getEmail())) {
            if (user.getUserType().equalsIgnoreCase(ControllerConstants.USER_TYPE_VEHICLE_OWNER))
                vehicleOwnerService.saveVehicleOwner(user);
            else
                customerService.saveCustomer(user);
            model.addAttribute("messageStatus", ControllerStringMessages.SUCCESS_STATUS);
            model.addAttribute("message",ControllerStringMessages.ACCOUNT_CREATED);
            return "login";
        }
        else {
            model.addAttribute("messageStatus", ControllerStringMessages.FAILURE_STATUS);
            model.addAttribute("message", ServiceStringMessages.USER_ALREADY_EXIST);
            return "register";
        }
    }

}
