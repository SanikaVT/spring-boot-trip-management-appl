package com.tripmanagement.asdc.model;


import javax.persistence.*;
@Entity
@Table(name="vehicleowner")
public class VehicleOwner {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="vehicleOwner_id")
    private int vehicleOwner_id;

    @Column(name="vehicleowner_fname")
    private String vehicleowner_fname;

    @Column(name="vehicleowner_lname")
    private String vehicleowner_lname;

    @Column(name="phone")
    private String phone;

    @Column(name="email")
    private String email;

    @Column(name="password")
    private String password;

    @Column(name="available_credits")
    private int available_credits;

    public VehicleOwner() {

    }

    public VehicleOwner(int vehicleOwner_id, String vehicleowner_fname, String vehicleowner_lname, String phone, String email, String password, int available_seats) {
        this.vehicleOwner_id = vehicleOwner_id;
        this.vehicleowner_fname = vehicleowner_fname;
        this.vehicleowner_lname = vehicleowner_lname;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.available_credits=available_seats;
    }

    public String getVehicleowner_fname() {
        return vehicleowner_fname;
    }

    public void setVehicleowner_fname(String vehicleowner_fname) {
        this.vehicleowner_fname = vehicleowner_fname;
    }

    public String getVehicleowner_lname() {
        return vehicleowner_lname;
    }

    public void setVehicleowner_lname(String vehicleowner_lname) {
        this.vehicleowner_lname = vehicleowner_lname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getVehicleOwner_id() {
        return vehicleOwner_id;
    }

    public void setVehicleOwner_id(int vehicleOwner_id) {
        this.vehicleOwner_id = vehicleOwner_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public int getAvailable_credits() {
        return available_credits;
    }

    public void setAvailable_credits(int available_credits) {
        this.available_credits = available_credits;
    }

    @Override
    public String toString() {
        String query1 = "VehicleOwner{" +
                ", vehicleowner_fname='" + vehicleowner_fname + '\'';
        String query2 = ", vehicleowner_lname='" + vehicleowner_lname + '\'' +
                ", phone='" + phone + '\'';
        String query3 = ", available_credits='" + available_credits + '\'' +
                ", email='" + email + '\'';
        String query4 = ", password='" + password + '\'' +
                '}';
        return query1 +
                query2 +
                query3 +
                query4;
    }
}





