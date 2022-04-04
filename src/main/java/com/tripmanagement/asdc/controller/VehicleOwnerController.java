package com.tripmanagement.asdc.controller;

import com.tripmanagement.asdc.model.*;
import com.tripmanagement.asdc.service.TripService;
import com.tripmanagement.asdc.service.VehicleOwnerService;
import com.tripmanagement.asdc.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
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
        tripService.saveTrip(tripData);
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
    public String addVehicle(Vehicle vehicle, Model model) {
        System.out.println("getVehicle_name: " + vehicle.getVehicle_name());
        System.out.println("getNumber_plate: " + vehicle.getNumber_plate());
        System.out.println("getType: " + vehicle.getType());
        System.out.println("getKms_driven: " + vehicle.getKms_driven());
        System.out.println("getAvailable_seats: " + vehicle.getAvailable_seats());
        System.out.println("getFuel_consumed: " + vehicle.getFuel_consumed());
        System.out.println("vehicleOwner_id: " + vehicle.getVehicleowner_id());
        System.out.println("brand: " + vehicle.getBrand());
        if (vehicleService.addVehicle(vehicle))
            model.addAttribute("addVehicleStatus", "Vehicle added successfully");
        else
            model.addAttribute("addVehicleStatus", "Vehicle adding failed");
        VehicleOwner vehicleOwner = vehicleOwnerService.getVehicleOwnerByOwnerId(vehicle.getVehicleowner_id());
        model.addAttribute("vehicleOwner", vehicleOwner);
        model.addAttribute("listOfVehicle", vehicleService.getVehicles(vehicleOwner.getVehicleOwner_id()));
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
        System.out.println("credits "+credits.getCredits());
        System.out.println("credits "+credits.getUserId());
        vehicleOwnerService.buyCredits(credits.getUserId(), credits.getCredits());
        VehicleOwner vehicleOwner = vehicleOwnerService.getVehicleOwnerByOwnerId(credits.getUserId());
        session.setAttribute("vehicleOwner", vehicleOwner);
        return "payment";
    }
}
