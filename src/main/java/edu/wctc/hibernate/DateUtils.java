package edu.wctc.hibernate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateUtils {

    private static final String DB_PATTERN = "yyyy-MM-dd";
    private static final String WEB_PATTERN = "yyyy-MM-dd HH:mm:ss.S";
    private static final String HUMAN_FRIENDLY_PATTERN = "MMMM d, yyyy";

    public static LocalDate parseDatabaseDate(String str) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DB_PATTERN);
            return LocalDate.parse(str, formatter);
        } catch (DateTimeParseException e) {
            // If something fails, return today's date
            e.printStackTrace();
            return LocalDate.now();
        }
    }

    public static String formatDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(HUMAN_FRIENDLY_PATTERN);

        if (date != null) {
            return date.format(formatter);
        }
        // return today's date
        return LocalDate.now().format(formatter);
    }

    public static LocalDate parseWebDate(String str) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(WEB_PATTERN);
            return LocalDate.parse(str, formatter);
        } catch (DateTimeParseException e) {
            return null;
        }
    }
}
