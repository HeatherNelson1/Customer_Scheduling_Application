package Model;


import Utilities.DataProvider;
import javafx.scene.control.Alert;

import java.time.LocalDateTime;

/**
 * Customers class holds the customers
 */
public class Customers {
    private int customerId;
    private String customerName;
    private String customerAddress;
    private String customerPhone;
    private String customerPostal;
    private LocalDateTime createdDate;
    private String createdBy;
    private LocalDateTime lastUpdated;
    private String lastUpdatedBy;
    private int divisionId;
    private String divisionName;
    private int countryId;


    public Customers(int customerId, String customerName, String customerAddress, String customerPhone, String customerPostal, LocalDateTime createdDate, String createdBy, LocalDateTime lastUpdated, String lastUpdatedBy, int divisionId, String divisionName, int countryId) {


        this.customerId = customerId;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerPhone = customerPhone;
        this.customerPostal = customerPostal;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
        this.lastUpdated = lastUpdated;
        this.lastUpdatedBy = lastUpdatedBy;
        this.divisionId = divisionId;
        this.divisionName =divisionName;
        this.countryId = countryId;
    }

    public String getDivisionName() {
        return divisionName;
    }

    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    public void updateCustomer()
    {
        //selected customer
    }
    public void deleteCustomer()
    {
        //void or boolean?
        // boolean....if there are not appts...
    }
    public void lookupFirstLevel()
    {
        //with combo box, lookup and return the first level division filtered by the selected country.
    }

    @Override
    public String toString(){
        return customerName;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;

    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;

    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {

        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public int getDivisionId() {
        return divisionId;

    }

    public void setDivisionId(int divsionId) {
        this.divisionId = divisionId;
    }

    public int getCountryId() {

        return countryId;
    }



    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }



    public String getCustomerPostal() {
        return customerPostal;
    }

    public void setCustomerPostal(String customerPostal) {
        this.customerPostal = customerPostal;
    }


    }
