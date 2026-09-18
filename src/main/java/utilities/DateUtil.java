package utilities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    public DateUtil() {
    }

    public static String getCurrentDate() {

        return LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public static String getCurrentTime() {

        return LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }
    public static String getTimeStamp() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
    }

}
