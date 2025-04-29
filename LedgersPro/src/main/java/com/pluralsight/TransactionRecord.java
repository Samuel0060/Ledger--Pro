package com.pluralsight;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.TimeZone;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class TransactionRecord {
    LocalDateTime depositedOn;
    private String description;
    private String vendor;
    private double userDeposit;



    public TransactionRecord(String description, String vendor, double userDeposit) {
        this.depositedOn = LocalDateTime.now();
        this.description = description;
        this.vendor = vendor;
        this.userDeposit = userDeposit;
    }

    public LocalDateTime getDepositedOn() {
        return depositedOn;
    }

    public void setDepositedOn(LocalDateTime depositedOn) {
        this.depositedOn = depositedOn;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public double getUserDeposit() {
        return userDeposit;
    }

    public void setUserDeposit(double userDeposit) {
        this.userDeposit = userDeposit;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd|hh:mm:ss");
        String formattedDepositedOn = depositedOn.format(formatter);
        return formattedDepositedOn + "|"
                + description + '|' +
                 vendor + '|'
                 + "-"+userDeposit;
                };

    public String toFileFormatString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd|hh:mm:ss");
        String formattedDepositedOn = depositedOn.format(formatter);
        return formattedDepositedOn + "|"
                + description + '|' +
                vendor + '|'
                + "+"+ userDeposit;
    }
    }

