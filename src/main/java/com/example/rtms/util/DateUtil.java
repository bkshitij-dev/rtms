package com.example.rtms.util;

import com.example.rtms.constant.AppConstants;

import java.time.*;
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
    public static final String ISO_FULL_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

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

    public static String getDateTimeString(LocalDateTime dateTime) {
        return getDateTimeString(dateTime, ISO_DATE_TIME_FORMAT);
    }

    public static String getDateTimeString(LocalDateTime dateTime, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return dateTime.format(formatter);
    }

    public static LocalDateTime getDateTimeLocalDate(LocalDateTime dateTime) {
        String dateTimeStr = getDateTimeString(dateTime, ISO_DATE_TIME_FORMAT);
        return getDateTime(dateTimeStr, ISO_DATE_TIME_FORMAT);
    }

    public static String getTimeDifference(String startTime, String endTime) {
        LocalDateTime start = getDateTime(startTime, ISO_FULL_DATE_TIME_FORMAT);
        LocalDateTime end = getDateTime(endTime.split("\\.")[0], ISO_FULL_DATE_TIME_FORMAT);

        ZonedDateTime startZonedDateTime = start.atZone(ZoneId.systemDefault());
        ZonedDateTime endZonedDateTime = end.atZone(ZoneId.systemDefault());

        Instant startInstant = startZonedDateTime.toInstant();
        Instant endInstant = endZonedDateTime.toInstant();

        Duration duration = Duration.between(startInstant, endInstant);

        long minutes = duration.toMinutes();
        long hours = duration.toHours();

        StringBuilder timeDifference = new StringBuilder();
        if (hours > 0) {
            timeDifference.append(hours + " hrs ");
        }
        if (minutes > 0) {
            timeDifference.append(minutes + " min");
        }
        return timeDifference.toString();
    }

    public static String getCurrentDateTime() {
        return getDateTimeString(LocalDateTime.now());
    }

    public static LocalDateTime getCurrentLocalDateTime() {
        return LocalDateTime.now();
    }

    public static LocalDateTime getDateTimeOffset(LocalDateTime dateTime, long hours) {
        return dateTime.plusHours(hours);
    }
}
