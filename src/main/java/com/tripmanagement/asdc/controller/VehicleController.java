package com.tripmanagement.asdc.controller;

import com.tripmanagement.asdc.model.ChartData;
import com.tripmanagement.asdc.model.FuelEconomy;
import com.tripmanagement.asdc.model.Vehicle;
import com.tripmanagement.asdc.model.VehicleOwner;
import com.tripmanagement.asdc.service.BookingService;
import com.tripmanagement.asdc.service.TripService;
import com.tripmanagement.asdc.service.VehicleOwnerService;
import com.tripmanagement.asdc.service.VehicleService;
import com.tripmanagement.asdc.stringsAndConstants.ControllerStringMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class VehicleController {

    @Autowired
    VehicleService vehicleService;

    @PostMapping("/add-fuel-economy")
    public String updateFuelEconomy(FuelEconomy fuelEconomy, Model model, HttpSession httpSession) {
        if(vehicleService.updateFuelEconomy(fuelEconomy)){
            model.addAttribute("messageStatus", ControllerStringMessages.SUCCESS_STATUS);
            model.addAttribute("message",ControllerStringMessages.TRIP_DETAILS);
        }
        else {
            model.addAttribute("messageStatus", ControllerStringMessages.FAILURE_STATUS);
            model.addAttribute("message",ControllerStringMessages.ERROR_OCCURRED);
        }
        int vehicleOwnerId = vehicleService.getVehicleDetails(fuelEconomy.getVehicle_id()).getVehicleowner_id();
        httpSession.setAttribute("listOfVehicle", vehicleService.getVehicles(vehicleOwnerId));
        return "owner-dashboard";
    }

    @PostMapping("/add-trip")
    public String addTripDetailsAll(FuelEconomy fuelEconomy, Model model, HttpSession httpSession) {
        if(vehicleService.updateFuelEconomy(fuelEconomy)){
            model.addAttribute("messageStatus", ControllerStringMessages.SUCCESS_STATUS);
            model.addAttribute("message",ControllerStringMessages.TRIP_DETAILS);
        }
        else {
            model.addAttribute("messageStatus", ControllerStringMessages.FAILURE_STATUS);
            model.addAttribute("message",ControllerStringMessages.ERROR_OCCURRED);
        }
        int vehicleOwnerId = vehicleService.getVehicleDetails(fuelEconomy.getVehicle_id()).getVehicleowner_id();
        httpSession.setAttribute("listOfVehicle", vehicleService.getVehicles(vehicleOwnerId));
        getChartData(vehicleOwnerId, model);
        return "all-vehicles";
    }

    @RequestMapping("/show-all-vehicles")
    public String showAllVehicles(HttpSession session, Model model){
        getChartData(((VehicleOwner)session.getAttribute("vehicleOwner")).getVehicleOwner_id(), model);
        return "all-vehicles";
    }

    @PostMapping("/add-new-vehicle")
    public String addNewVehicle(Vehicle vehicle, Model model, HttpSession session) {
        if(vehicleService.addVehicle(vehicle)){
            model.addAttribute("messageStatus", ControllerStringMessages.SUCCESS_STATUS);
            model.addAttribute("message",ControllerStringMessages.CAR_ADDED);
        }
        else {
            model.addAttribute("messageStatus", ControllerStringMessages.FAILURE_STATUS);
            model.addAttribute("message",ControllerStringMessages.ERROR_OCCURRED);
        }
        session.setAttribute("listOfVehicle", vehicleService.getVehicles(vehicle.getVehicleowner_id()));
        getChartData(vehicle.getVehicleowner_id(), model);
        return "all-vehicles";
    }

    private void getChartData(int vehicleOwnerId, Model model){
        ArrayList<ChartData> vehicleUseChart = vehicleService.getFuelConsumedChart(vehicleOwnerId);
        model.addAttribute("vehicleKmChartDataset",vehicleUseChart.get(0));
        model.addAttribute("vehicleFuelChartDataset",vehicleUseChart.get(1));
    }


}
