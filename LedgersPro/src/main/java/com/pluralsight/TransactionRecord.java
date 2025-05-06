package com.pluralsight;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class TransactionRecord {
    LocalDateTime addedOn;
    private String description;
    private String vendor;
    private float userDeposit;



    public TransactionRecord(LocalDateTime addedOn, String description, String vendor, float userDeposit) {

        this.addedOn = addedOn;
        this.description = description;
        this.vendor = vendor;
        this.userDeposit = userDeposit;
    }

    public LocalDateTime getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(LocalDateTime addedOn) {
        this.addedOn = addedOn;
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

    public void setUserDeposit(float userDeposit) {
        this.userDeposit = userDeposit;
    }

    @Override
    public String toString() {
        String date = addedOn.toLocalDate().toString(); // yyyy-MM-dd
        String time = addedOn.toLocalTime().withNano(0).toString(); // HH:mm:ss
        return date + "|" + time + "|" + description + "|" + vendor + "|" + String.format("%.2f", userDeposit);
    }



    public String toFileFormatString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd|HH:mm");

        return formatter.format(addedOn) + "|"
                + description + "|" +
                 vendor + "|-"
                 +userDeposit;
                }

//    public String toFileFormatString() {
//        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd|HH:mm|");
//        StringBuilder builder = new StringBuilder();
//
//        String sign = userDeposit >= 0 ? "+" : "-";
//        float amount = Math.abs(userDeposit);
//
//        builder.append(addedOn.format(dateFormatter))
//                .append(description)
//                .append("|")
//                .append(vendor)
//                .append("|")
//                .append(sign)
//                .append(String.format("%.2f",amount))
//                .append("\n");
//
//        return builder.toString();

//

//        return addedOn + "|"
//                + description + '|' +
//                vendor + '|'
//                + "+"+userDeposit;

    }


