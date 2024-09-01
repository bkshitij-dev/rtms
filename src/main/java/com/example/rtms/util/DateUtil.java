package com.example.rtms.util;

import com.example.rtms.constant.AppConstants;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/*
 * @author Kshitij
 * @created 01-Sep-2024
 */
public final class DateUtil {

    private static final String ISO_DATE_FORMAT = "yyyy-MM-dd";
    private static final String ISO_TIME_FORMAT = "HH:mm";
    private static final String ISO_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm";

    private DateUtil() throws Exception {
        throw new Exception(AppConstants.NOT_INSTANTIABLE);
    }

    public static LocalDate getDate(String dateString) {
        return getDate(dateString, ISO_DATE_FORMAT);
    }

    public static LocalDate getDate(String dateString, String dateFormat) {
        LocalDate date = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
        try {
            date = LocalDate.parse(dateString, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Error parsing date: " + e.getMessage());
        }
        return date;
    }

    public static LocalTime getTime(String timeString) {
        return getTime(timeString, ISO_TIME_FORMAT);
    }

    public static LocalTime getTime(String timeString, String timeFormat) {
        LocalTime time = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(timeFormat);
        try {
            time = LocalTime.parse(timeString, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Error parsing time: " + e.getMessage());
        }
        return time;
    }

    public static LocalDateTime getDateTime(String dateTimeString) {
        return getDateTime(dateTimeString, ISO_DATE_TIME_FORMAT);
    }

    public static LocalDateTime getDateTime(String dateTimeString, String timeFormat) {
        LocalDateTime time = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(timeFormat);
        try {
            time = LocalDateTime.parse(dateTimeString, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Error parsing time: " + e.getMessage());
        }
        return time;
    }
}
