package com.tripmanagement.asdc.model;


import javax.persistence.*;
@Entity
@Table(name="vehicleowner")
public class VehicleOwner {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="vehicleowner_fname")
    private String vehicleowner_fname;

    @Column(name="vehicleowner_lname")
    private String vehicleowner_lname;

    @Column(name="phone")
    private String phone;

    @Column(name="address")
    private String address;

    @Column(name="email")
    private String email;

    @Column(name="vehicle_id")
    private int vehicle_id;

    @Column(name="password")
    private String password;

    public VehicleOwner() {

    }

    public VehicleOwner(int id, String vehicleowner_fname, String vehicleowner_lname, String phone, String address, String email, int vehicle_id, String password) {
        this.id = id;
        this.vehicleowner_fname = vehicleowner_fname;
        this.vehicleowner_lname = vehicleowner_lname;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.vehicle_id = vehicle_id;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getVehicle_id() {
        return vehicle_id;
    }

    public void setVehicle_id(int vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "VehicleOwner{" +
                "id=" + id +
                ", vehicleowner_fname='" + vehicleowner_fname + '\'' +
                ", vehicleowner_lname='" + vehicleowner_lname + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", vehicle_id=" + vehicle_id +
                ", password='" + password + '\'' +
                '}';
    }
}





