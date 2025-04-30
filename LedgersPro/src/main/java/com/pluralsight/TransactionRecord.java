package com.pluralsight;

import javax.swing.text.DateFormatter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.TimeZone;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class TransactionRecord {
    private String date;
    private String time;
    private String description;
    private String vendor;
    private double userDeposit;



    public TransactionRecord(String description, String vendor, double userDeposit) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        this.date = LocalDate.now().format(dateFormatter);
        this.time = LocalTime.now().format(timeFormatter);
        this.description = description;
        this.vendor = vendor;
        this.userDeposit = userDeposit;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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
//        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
//
//        String formattedDate = LocalDate.now().format(dateFormatter);
//        String formattedTime = LocalTime.now().format(timeFormatter);

        return date + "|"
                + time + "|"
                + description + '|' +
                 vendor + '|'
                 + "-"+userDeposit;
                }

    public String toFileFormatString() {
//        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
//
//        String formattedDate = LocalDate.now().format(dateFormatter);
//        String formattedTime = LocalTime.now().format(timeFormatter);
//

        return date + "|"
                + time + "|"
                + description + '|' +
                vendor + '|'
                + "+"+userDeposit;

    }
    }

