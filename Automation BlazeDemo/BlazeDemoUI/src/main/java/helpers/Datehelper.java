package helpers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;


class DateUtils extends org.apache.commons.lang3.time.DateUtils {

    protected static final Logger logger = LogManager.getLogger("DateUtils");

    public static String getTodayDate(String formatDate) {

        Calendar calendar = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat(formatDate);
        return dateFormat.format(calendar.getTime());
    }


    public static String getTodayDateInUTC(String formatDate, String timeZone) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(formatDate);
        return dateFormatter.format(ZonedDateTime.now(ZoneId.of(timeZone)));
    }

    public static String getTodayDateInTimeZone(String formatDate, String timeZone) {

        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(timeZone));
        SimpleDateFormat sdf = new SimpleDateFormat(formatDate);
        sdf.setTimeZone(TimeZone.getTimeZone(timeZone));
        return sdf.format(calendar.getTime());
    }

    public static String getTodayDateAndTime(String format) {
        return DateTimeFormatter
                .ofPattern(format)
                .format(LocalDateTime.now());
    }

       public static String getDayOfMonth() {
        Calendar cal = Calendar.getInstance();
        return String.valueOf(cal.get(Calendar.DATE));
    }

    public static String getTodayDatePlusDaysByLocal(String formatDate, int days, Locale locale) {

        Calendar calendar = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat(formatDate, locale);
        calendar.setTime(calendar.getTime());
        calendar.add(Calendar.DATE, days);
        String todayPlus = dateFormat.format(calendar.getTime());

        return todayPlus;
    }

    public static String getTodayDatePlusDays(String formatDate, int days) {
        Calendar calendar = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat(formatDate);
        calendar.setTime(calendar.getTime());
        calendar.add(Calendar.DATE, days);
        return dateFormat.format(calendar.getTime());
    }

    public static String getTodayDateMinusDays(String formatDate, int days) {
        Calendar calendar = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat(formatDate);
        calendar.setTime(calendar.getTime());
        calendar.add(Calendar.DATE, -days);
        return dateFormat.format(calendar.getTime());
    }

    public static String getTodayDateAndTimePlusMonth(String formatDate, int month) {
        DateFormat dateFormat = new SimpleDateFormat(formatDate);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, month);
        return dateFormat.format(calendar.getTime());
    }

     public static String getTodayDatePlusMinutes(String formatDate, int minutes) {
        String today;
        today = LocalDateTime.now().plusMinutes(minutes).format(DateTimeFormatter.ofPattern(formatDate).withLocale(Locale.US));

        return today;
    }

    public static String getTodayDateInTimeZonePlusMinutes(String format, String timeZoneName, int minutes) {
        return LocalDateTime.now(ZoneId.of(timeZoneName)).plusMinutes(minutes).format(DateTimeFormatter.ofPattern(format));
    }

    public static String getTodayDateInTimeZonePlusDays(String format, String timeZoneName, int days) {
        return LocalDateTime.now(ZoneId.of(timeZoneName)).plusDays(days).format(DateTimeFormatter.ofPattern(format));
    }

    public static String getTodayDatePlusMonths(String formatDate, int months) {
        return LocalDateTime.now().plusMonths(months).format(DateTimeFormatter.ofPattern(formatDate).withLocale(Locale.US));

    }

    public static String getTodayDatePlusYears(String formatDate, int years) {
        return LocalDateTime.now().plusYears(years).format(DateTimeFormatter.ofPattern(formatDate).withLocale(Locale.US));

    }

      public static String getDatePlusMinutesAndSeconds(String date, String formatDate, int minutes, int seconds) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatDate);
        LocalDateTime localDateTime = LocalDateTime.parse(date, formatter);
        return localDateTime.plusMinutes(minutes).plusSeconds(seconds).format(formatter);
    }

    public static String getTodayDateByLocale(String formatDate, Locale locale) {

        Calendar calendar = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat(formatDate, locale);
        return dateFormat.format(calendar.getTime());
    }

    public static String getTodayDatePlusDaysByLocale(String formatDate, int days, Locale locale) {

        Calendar calendar = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat(formatDate, locale);
        calendar.setTime(calendar.getTime());
        calendar.add(Calendar.DATE, days);
        return dateFormat.format(calendar.getTime());
    }

    public static String getDateOrTimeFormattedByLocale(String actualDateTime, String actualFormat, String actualLocale, String expectedLocale) {
        String formattedDateByLocale = actualDateTime;
        try {
            Calendar calendar = Calendar.getInstance();
            DateFormat dateFormat = new SimpleDateFormat(actualFormat);

            //Actual timezone of the datetime string
            dateFormat.setTimeZone(TimeZone.getTimeZone(actualLocale));
            calendar.setTime(dateFormat.parse(actualDateTime));

            //Set the new timezone
            dateFormat.setTimeZone(TimeZone.getTimeZone(expectedLocale));
            //Due to bug: 30068805
            if (actualLocale.equals("UTC") && TimeZone.getTimeZone(expectedLocale).useDaylightTime()) {
                calendar.add(Calendar.HOUR, 1);
            }
            if (TimeZone.getTimeZone(actualLocale).useDaylightTime() && expectedLocale.equals("UTC")) {
                calendar.add(Calendar.HOUR, -1);
            }
            formattedDateByLocale = dateFormat.format(calendar.getTime());

        } catch (ParseException e) {
            logger.error("Error while parsing date or time", e);
        }
        return formattedDateByLocale;
    }

    public static String changeDateOrTimeFormat(String expectedFormat, String actualFormat, String dateOrTime) {
        String retValue = dateOrTime;
        try {
            DateFormat dateformat = new SimpleDateFormat(actualFormat);
            Date date = dateformat.parse(dateOrTime);
            retValue = new SimpleDateFormat(expectedFormat).format(date);
        } catch (ParseException e) {
            logger.error(e);
        }
        return retValue;
    }
}
