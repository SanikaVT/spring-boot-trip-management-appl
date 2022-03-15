package com.tripmanagement.asdc.controller;

import com.tripmanagement.asdc.model.UserLogin;
import com.tripmanagement.asdc.model.VehicleOwner;
import com.tripmanagement.asdc.service.NotificationService;
import com.tripmanagement.asdc.service.RegistrationService;
import com.tripmanagement.asdc.service.VehicleOwnerService;
import com.tripmanagement.asdc.stringsAndConstants.StringMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RegistrationController {

    @Autowired
    RegistrationService registrationService;

    @Autowired
    VehicleOwnerService vehicleOwnerService;

    @Autowired
    NotificationService notificationService;

    @RequestMapping("/")
    public String basePage() {
        return "login";
    }

    @RequestMapping("/login")
    public String userLogin(UserLogin userLogin, Model model, UserLogin userLogin2, UserLogin userLogin3, UserLogin userLogin4) {
        String message = registrationService.checkEmailPassword(userLogin.getEmail(), userLogin.getPassword());
        if (message.equalsIgnoreCase(StringMessages.SUCCESS)){
        notificationService.sendEmail(StringMessages.LOGIN_SUCCESSFUL,StringMessages.AUTH_SUCCESSFUL,userLogin.getEmail());
            return "dashboard";
        }
        else {
            model.addAttribute("error_message", message);
            return "login";
        }
    }

    /*
     * Will return Forget Password Page
     * */
    @RequestMapping("/forgot-password")
    public String forgotPassword() {
        return "forgot-password";
    }

    /*
     * Will return Forget Password Page
     * */
    @RequestMapping("/register-user")
    public String registerUser() {
        return "register";
    }

    /*
     * Will open dashboard depending upon user type
     * */
    @RequestMapping("/dashboard")
    public String dashboard() {
        // TODO: open dashboard depending upon user type
        return "dashboard";
    }

    @PostMapping("/register-vehicle-owner")
    public String registerVehicleOwner(VehicleOwner vehicleOwner, BindingResult result, Model model) {
        // TODO: Link with Vehicle service
        // service.checkEmailExists(vehicleOwner.email)
        // service.registerVehicleOwner(vehicleOwner)
        System.out.println("Name: "+ vehicleOwner.getVehicleowner_fname());
        System.out.println("Last Name: "+ vehicleOwner.getVehicleowner_lname());
        System.out.println("Phone Number: "+ vehicleOwner.getPhone());
        System.out.println("Email: "+ vehicleOwner.getEmail());
        System.out.println("Password: "+ vehicleOwner.getPassword());

        if(!registrationService.checkUserExistByEmail(vehicleOwner.getEmail())) {
            vehicleOwnerService.saveVehicleOwner(vehicleOwner);
            return "login";
        }
        else {
            model.addAttribute("error_message", StringMessages.USER_ALREADY_EXIST);
            return "register";
        }

    }

    @PostMapping("/add-customer")
    public String registerCustomer(VehicleOwner vehicleOwner, BindingResult result, Model model) {
        // TODO: Link with service
        System.out.println("Name: "+ vehicleOwner.getVehicleowner_fname());
        System.out.println("Last Name: "+ vehicleOwner.getVehicleowner_lname());
        System.out.println("Phone Number: "+ vehicleOwner.getPhone());
        System.out.println("Email: "+ vehicleOwner.getEmail());
        System.out.println("Password: "+ vehicleOwner.getPassword());

        return "login";
    }

}
