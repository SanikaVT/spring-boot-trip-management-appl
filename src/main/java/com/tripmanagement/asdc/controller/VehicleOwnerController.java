package com.tripmanagement.asdc.controller;

import com.tripmanagement.asdc.model.*;
import com.tripmanagement.asdc.service.TripService;
import com.tripmanagement.asdc.service.VehicleOwnerService;
import com.tripmanagement.asdc.service.VehicleService;
import com.tripmanagement.asdc.stringsAndConstants.ControllerStringMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
public class VehicleOwnerController {

    @Autowired
    VehicleService vehicleService;

    @Autowired
    VehicleOwnerService vehicleOwnerService;

    @Autowired
    TripService tripService;

    @PostMapping("/create-ride")
    public String createRide(Trip tripData, BindingResult result, Model model, HttpSession httpSession) {
        if(tripService.saveTrip(tripData)){
            model.addAttribute("messageStatus", ControllerStringMessages.SUCCESS_STATUS);
            model.addAttribute("message",ControllerStringMessages.RIDE_CREATED);
        }
        else {
            model.addAttribute("messageStatus", ControllerStringMessages.FAILURE_STATUS);
            model.addAttribute("message",ControllerStringMessages.ERROR_OCCURRED);
        }
        int vehicleowner_id = vehicleService.getVehicleDetails(tripData.getVehicle_id()).getVehicleowner_id();
        VehicleOwner vehicleOwner = vehicleOwnerService.getVehicleOwnerByOwnerId(vehicleowner_id);
        model.addAttribute("vehicleOwner", vehicleOwner);
        model.addAttribute("listOfVehicle", vehicleService.getVehicles(vehicleOwner.getVehicleOwner_id()));
        List<Trip> previousTripsForVehicleOwner = tripService.getPreviousTripsForVehicleOwner(vehicleOwner.getVehicleOwner_id());
        httpSession.setAttribute("previousRides", previousTripsForVehicleOwner);
        List<Trip> upcomingTripsForVehicleOwner = tripService.getUpcomingTripsForVehicleOwner(vehicleOwner.getVehicleOwner_id());
        httpSession.setAttribute("upcomingRides", upcomingTripsForVehicleOwner);
        return "owner-dashboard";
    }


    @PostMapping("/add-vehicle")
    public String addVehicle(Vehicle vehicle, Model model, HttpSession session) {
        if(vehicleService.addVehicle(vehicle)){
            model.addAttribute("messageStatus", ControllerStringMessages.SUCCESS_STATUS);
            model.addAttribute("message",ControllerStringMessages.CAR_ADDED);
        }
        else {
            model.addAttribute("messageStatus", ControllerStringMessages.FAILURE_STATUS);
            model.addAttribute("message",ControllerStringMessages.ERROR_OCCURRED);
        }
        VehicleOwner vehicleOwner = vehicleOwnerService.getVehicleOwnerByOwnerId(vehicle.getVehicleowner_id());
        session.setAttribute("vehicleOwner", vehicleOwner);
        session.setAttribute("listOfVehicle", vehicleService.getVehicles(vehicleOwner.getVehicleOwner_id()));
        return "owner-dashboard";
    }

    @PostMapping("/delete-vehicle")
    public String deleteVehicle(Vehicle vehicle, Model model) {
        Boolean deleteVehicleStatus = vehicleService.deleteVehicle(vehicle.getVehicle_id());
        model.addAttribute("deleteVehicleStatus", deleteVehicleStatus);
        return "owner-dashboard";
    }

    @RequestMapping(value = "/open-owner-credit")
    public String openCredit( Model model) {
        return "payment";
    }

    @RequestMapping("/ride-history")
    public String showRideHistory(HttpSession session, Model model){
        return "ride-history";
    }


    @RequestMapping("/owner-dashboard")
    public String showOwnerDashboard(HttpSession session, Model model){
        return "owner-dashboard";
    }

    @PostMapping("/add-credits-owner")
    public String addCreditsOwner(Credits credits, HttpSession session, Model model) {
        if(vehicleOwnerService.buyCredits(credits.getUserId(), credits.getCredits())){
            model.addAttribute("messageStatus", ControllerStringMessages.SUCCESS_STATUS);
            model.addAttribute("message",ControllerStringMessages.CREDITS_ADDED);
        }
        else {
            model.addAttribute("messageStatus", ControllerStringMessages.FAILURE_STATUS);
            model.addAttribute("message",ControllerStringMessages.ERROR_OCCURRED);
        }
        VehicleOwner vehicleOwner = vehicleOwnerService.getVehicleOwnerByOwnerId(credits.getUserId());
        session.setAttribute("vehicleOwner", vehicleOwner);
        return "payment";
    }
}
