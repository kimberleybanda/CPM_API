package com.brokeroffice.springbootws.helpers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateFormatter {

    public static String formatDate(String dateTimeString) {
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return dateTime.format(formatter);
    }

    public static String formatDateReg(String dateTimeString) {
        String dateRegex = "\\d{4}-\\d{2}-\\d{2}";
        Pattern pattern = Pattern.compile(dateRegex);
        Matcher matcher = pattern.matcher(dateTimeString);

        if (matcher.find()) {
            return matcher.group();
        } else {
            return "Invalid date format";
        }
    }

}
