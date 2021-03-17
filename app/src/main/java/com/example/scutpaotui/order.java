package com.example.scutpaotui;


import java.sql.Timestamp;
import java.util.Date;

public class order {
    private String name;
    private double price;
    private String pickUpAddress;
    private String deliveryAddress;
    private String notes;
    private java.util.Date issueDate;


    public order(){
        this.name="";
        this.price = 0;
        this.pickUpAddress = "";
        this.deliveryAddress = "";
        this.notes = "";
        issueDate = new Date(System.currentTimeMillis());
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setPickUpAddress(String pickUpAddress) {
        this.pickUpAddress = pickUpAddress;
    }

    public String getPickUpAddress() {
        return pickUpAddress;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getNotes() {
        return notes;
    }

    public Timestamp getsqlIssueDate() {
        Timestamp sqlDate=new Timestamp(issueDate.getTime());
        return sqlDate;
    }
    public Date getIssueDate(){return issueDate;}
    public void setIssueDate(Timestamp timestamp){issueDate=timestamp;}
}
