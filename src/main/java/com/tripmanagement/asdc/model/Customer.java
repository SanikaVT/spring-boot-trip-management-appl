package com.tripmanagement.asdc.model;


import javax.persistence.*;
@Entity
@Table(name="customer")
public class Customer {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="customer_id")
    private int customer_id;

    @Column(name="customer_fname")
    private String customer_fname;

    @Column(name="customer_lname")
    private String customer_lname;

    @Column(name="mobile_no")
    private String mobile_no;

    @Column(name="email")
    private String email;

    @Column(name="password")
    private String password;

    @Column(name="available_credits")
    private int available_credits;


    public Customer() {

    }

    public Customer(int customer_id, String customer_fname, String customer_lname,String mobile_no, String address, String email, String owner_tag, String password, int available_credits)
    {
       this.customer_id=customer_id;
       this.customer_fname=customer_fname;
       this.customer_lname=customer_lname;
       this.mobile_no=mobile_no;
       this.email=email;
       this.password=password;
       this.available_credits=available_credits;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getCustomer_lname() {
        return customer_lname;
    }

    public void setCustomer_lname(String customer_lname) {
        this.customer_lname = customer_lname;
    }

    public String getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
    }

    public String getEmail(){
        return email;
    }

    public String getCustomer_fname() {
        return customer_fname;
    }

    public void setCustomer_fname(String customer_fname) {
        this.customer_fname = customer_fname;
    }

    public void setEmail(String email){
        this.email=email;
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
        String query1 = "Customer{" +
                "customer_id=" + customer_id;
        String query2 = ", customer_fname='" + customer_fname + '\'';
        String query3 = ", customer_lname='" + customer_lname + '\'' +
                ", mobile_no='" + mobile_no + '\'';
        String query4 = ", available_seats='" + available_credits + '\'' +
                ", email='" + email + '\'';
        String query5 = ", password='" + password + '\'' +
                '}';
        return query1 +
                query2 +
                query3 +
                query4 +
                query5;
    }
}





