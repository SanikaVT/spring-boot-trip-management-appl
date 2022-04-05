package com.tripmanagement.asdc.controller;

import com.tripmanagement.asdc.model.*;
import com.tripmanagement.asdc.service.BookingService;
import com.tripmanagement.asdc.service.CustomerService;
import com.tripmanagement.asdc.service.TripService;
import com.tripmanagement.asdc.service.VehicleService;
import com.tripmanagement.asdc.stringsAndConstants.ControllerStringMessages;
import com.tripmanagement.asdc.stringsAndConstants.ServiceStringMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CustomerController {

    @Autowired
    TripService tripService;

    @Autowired
    VehicleService vehicleService;

    @Autowired
    CustomerService customerService;

    @Autowired
    BookingService bookingService;

    @PostMapping("/search-rides")
    public String searchRides(Trip trip, Model model) 
    {
        String source = trip.getSource();
        String destination = trip.getDestination();
        List<Ride> availableTripsList = tripService.getAvailableTripsList(source, destination);
        if(availableTripsList.isEmpty()){
            model.addAttribute("messageStatus", ControllerStringMessages.FAILURE_STATUS);
            model.addAttribute("message",ControllerStringMessages.NO_RIDES);
        }
        model.addAttribute("listOfRides", availableTripsList);
        return "customer-dashboard";
    }

    @PostMapping("/book-ride")
    public String bookRide(Booking booking, HttpSession session, Model model) {
        if(bookingService.saveRide(booking)){
            model.addAttribute("messageStatus", ControllerStringMessages.SUCCESS_STATUS);
            model.addAttribute("message",ControllerStringMessages.RIDE_BOOKED);
        }
        else {
            model.addAttribute("messageStatus", ControllerStringMessages.FAILURE_STATUS);
            model.addAttribute("message",ControllerStringMessages.ERROR_OCCURRED);
        }
        session.setAttribute("upcomingRides", bookingService.getUpcomingRidesForCustomer(booking.getCustomer_id()));
        System.out.println("up: "+bookingService.getUpcomingRidesForCustomer(booking.getCustomer_id()).size());
        return "customer-dashboard";
    }

    @RequestMapping(value = "/open-credit-customer")
    public String openCredit( Model model) {
        return "customer-credits";
    }

    @PostMapping("/pay-ride")
    public String payRide(Booking booking, Model model, HttpSession session){
        String paymentStatusMessage = bookingService.payforRide(booking);
        if(paymentStatusMessage.equals(ServiceStringMessages.PAYMENT_COMPLETE)){
            model.addAttribute("messageStatus", ControllerStringMessages.SUCCESS_STATUS);
        }
        else {
            model.addAttribute("messageStatus", ControllerStringMessages.FAILURE_STATUS);
        }
        model.addAttribute("message",paymentStatusMessage);
        Customer customer = customerService.getCustomerById(booking.getCustomer_id());
        session.setAttribute("customer", customer);
        session.setAttribute("upcomingRides", bookingService.getUpcomingRidesForCustomer(booking.getCustomer_id()));
        session.setAttribute("previousRides", bookingService.getPreviousRidesForCustomer(booking.getCustomer_id()));
        return "booking-history";
    }

    @PostMapping("/pay-booking")
    public String payBooking(Booking booking, Model model, HttpSession session){
        String paymentStatusMessage = bookingService.payforRide(booking);
        if(paymentStatusMessage.equals(ServiceStringMessages.PAYMENT_COMPLETE)){
            model.addAttribute("messageStatus", ControllerStringMessages.SUCCESS_STATUS);
        }
        else {
            model.addAttribute("messageStatus", ControllerStringMessages.FAILURE_STATUS);
        }
        model.addAttribute("message",paymentStatusMessage);
        Customer customer = customerService.getCustomerById(booking.getCustomer_id());
        session.setAttribute("customer", customer);
        session.setAttribute("upcomingRides", bookingService.getUpcomingRidesForCustomer(booking.getCustomer_id()));
        session.setAttribute("previousRides", bookingService.getPreviousRidesForCustomer(booking.getCustomer_id()));
        return "customer-dashboard";
    }

    @RequestMapping("/booking-history")
    public String showBookingHistory(HttpSession session, Model model){
        return "booking-history";
    }

    @PostMapping("/add-credits-customer")
    public String addCreditsOwner(Credits credits, HttpSession session, Model model) {
        if(customerService.buyCredits(credits.getUserId(), credits.getCredits())){
            model.addAttribute("messageStatus", ControllerStringMessages.SUCCESS_STATUS);
            model.addAttribute("message",ControllerStringMessages.CREDITS_ADDED);
        }
        else {
            model.addAttribute("messageStatus", ControllerStringMessages.FAILURE_STATUS);
            model.addAttribute("message",ControllerStringMessages.ERROR_OCCURRED);
        }
        Customer customer = customerService.getCustomerById(credits.getUserId());
        session.setAttribute("customer", customer);
        return "customer-credits";
    }

    @RequestMapping("/customer-dashboard")
    public String showOwnerDashboard(HttpSession session, Model model){
        return "customer-dashboard";
    }
}
