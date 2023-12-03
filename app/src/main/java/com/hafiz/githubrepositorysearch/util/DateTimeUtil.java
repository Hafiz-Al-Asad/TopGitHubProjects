package com.hafiz.githubrepositorysearch.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateTimeUtil {
    public static final String SERVER_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String SERVER_DATE_FORMAT = "yyyy-MM-dd";
    public static final String SERVER_TIME_FORMAT = "HH:mm:ss";
    public static final String CLIENT_DATE_TIME_FORMAT = "MMMM dd, yyyy hh:mm a";
    public static final String CLIENT_DATE_FORMAT = "MMMM dd, yyyy";
    public static final String CLIENT_TIME_FORMAT_12H = "hh:mm a";
    public static final String CLIENT_TIME_FORMAT_24H = "HH:mm";
    public static final String CLIENT_MONTH_YEAR_FORMAT = "MMMM yyyy";
    public static final String CLIENT_MONTH_FORMAT = "MMMM";
    public static final String CLIENT_HOUR_24H_FORMAT = "HH";
    public static final String CLIENT_HOUR_12H_FORMAT = "hh";
    public static final String CLIENT_MINUTE_FORMAT = "mm";
    public static final String CLIENT_AM_PM_FORMAT = "a";
    public static final String DATE_AS_FILE_NAME_FORMAT = "yyyyMMdd_HHmmss";
    public static final String GITHUB_DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    public static final String CLIENT_DATE_TIME_FORMAT_SHOW_FROM_GITHUB = "MM-dd-yyyy HH:mm";

    public static String getFormattedDate(String format) {
        if (format == null) {
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format, Locale.US);
        return simpleDateFormat.format(new Date());
    }

    public static String getFormattedDate(String format, Date date) {
        if (format == null || date == null) {
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format, Locale.US);
        return simpleDateFormat.format(date);
    }

    public static String getFormattedDate(String format, Calendar calendar) {
        if (calendar == null) {
            return null;
        }
        return getFormattedDate(format, calendar.getTime());
    }

    public static String getFormattedDate(String format, Long date) {
        if (format == null || date == null) {
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format, Locale.US);
        return simpleDateFormat.format(new Date(date));
    }

    public static String getFormattedDate(String format, String date) {
        Long dateLong;
        try {
            dateLong = Long.valueOf(date);
        } catch (NumberFormatException e) {
            return null;
        }
        return getFormattedDate(format, dateLong);
    }

    public static String getFormattedClientDateShort(String date) {
        return getFormattedDate(CLIENT_DATE_FORMAT, date);
    }

    public static String getFormattedClientDateShort(Calendar calendar) {
        return getFormattedDate(CLIENT_DATE_FORMAT, calendar);
    }

    public static String getFormattedClientDateLong(String date) {
        return getFormattedDate(CLIENT_DATE_TIME_FORMAT, date);
    }

    public static String getFormattedClientDateLong() {
        return getFormattedDate(CLIENT_DATE_TIME_FORMAT);
    }

    public static String getFormattedClientDate(String date) {
        return getFormattedClientDateShort(date);
    }

    public static String getFormattedDateAsFileName() {
        return getFormattedDate(DATE_AS_FILE_NAME_FORMAT);
    }

    public static String getFormattedServerDate(Calendar calendar) {
        return getFormattedDate(SERVER_DATE_FORMAT, calendar);
    }

    public static String getFormattedServerDateTime(Calendar calendar) {
        return getFormattedDate(SERVER_DATE_TIME_FORMAT, calendar);
    }

    public static String getFormattedClientDateTime(Calendar calendar) {
        return getFormattedDate(CLIENT_DATE_TIME_FORMAT, calendar);
    }

    public static String getFormattedClientDate(Calendar calendar) {
        return getFormattedDate(CLIENT_DATE_FORMAT, calendar);
    }

    public static String getFormattedClientTime(Calendar calendar) {
        return getFormattedDate(CLIENT_TIME_FORMAT_12H, calendar);
    }

    public static Calendar getStartOfDay(Calendar calendar) {
        if (calendar == null) {
            return null;
        }
        Calendar tempCalender = (Calendar) calendar.clone();
        tempCalender.set(Calendar.HOUR_OF_DAY, 0);
        tempCalender.set(Calendar.MINUTE, 0);
        tempCalender.set(Calendar.SECOND, 0);
        tempCalender.set(Calendar.MILLISECOND, 0);
        return tempCalender;
    }

    public static Calendar getEndOfDay(Calendar calendar) {
        if (calendar == null) {
            return null;
        }
        Calendar tempCalender = (Calendar) calendar.clone();
        tempCalender.set(Calendar.HOUR_OF_DAY, 23);
        tempCalender.set(Calendar.MINUTE, 59);
        tempCalender.set(Calendar.SECOND, 59);
        tempCalender.set(Calendar.MILLISECOND, 999);
        return tempCalender;
    }

    public static Calendar getStartOfMonth(Calendar calendar) {
        if (calendar == null) {
            return null;
        }
        Calendar tempCalendar = getStartOfDay(calendar);
        tempCalendar.set(Calendar.DAY_OF_MONTH, 1);
        return tempCalendar;
    }

    public static Calendar getEndOfMonth(Calendar calendar) {
        if (calendar == null) {
            return null;
        }
        Calendar tempCalendar = getEndOfDay(calendar);
        tempCalendar.set(Calendar.DAY_OF_MONTH, tempCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return tempCalendar;
    }

    public static Calendar getNextDay(Calendar calendar) {
        if (calendar == null) {
            return null;
        }
        Calendar tempCalendar = (Calendar) calendar.clone();
        tempCalendar.add(Calendar.DAY_OF_MONTH, 1);
        return tempCalendar;
    }

    public static String getFormattedServerDateStartTime(Calendar calendar) {
        Calendar tempCalender = (Calendar) calendar.clone();
        tempCalender.set(Calendar.HOUR_OF_DAY, 0);
        tempCalender.set(Calendar.MINUTE, 0);
        tempCalender.set(Calendar.SECOND, 0);
        tempCalender.set(Calendar.MILLISECOND, 0);
        return getFormattedDate(SERVER_DATE_TIME_FORMAT, tempCalender);
    }

    public static String getFormattedServerDateEndTime(Calendar calendar) {
        Calendar tempCalender = (Calendar) calendar.clone();
        tempCalender.set(Calendar.HOUR_OF_DAY, 23);
        tempCalender.set(Calendar.MINUTE, 59);
        tempCalender.set(Calendar.SECOND, 59);
        tempCalender.set(Calendar.MILLISECOND, 999);
        return getFormattedDate(SERVER_DATE_TIME_FORMAT, tempCalender);
    }

    public static String getFormattedMonthAndYear(Calendar calendar) {
        return getFormattedDate(CLIENT_MONTH_YEAR_FORMAT, calendar);
    }

    public static Date getDate(String format, String date) {
        if (format == null || Utils.isEmptyString(date)) {
            return null;
        }
        try {
            DateFormat dateFormat = new SimpleDateFormat(format, Locale.US);
            return dateFormat.parse(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Calendar getCalendar(String format, String date) {
        if (format == null || Utils.isEmptyString(date)) {
            return null;
        }
        Date dateObj = getDate(format, date);
        if (dateObj == null) {
            return null;
        }
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dateObj);
            return calendar;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Calendar getCalendarFromYMD(String date) {
        return getCalendar(SERVER_DATE_FORMAT, date);
    }

    public static Calendar getCalendarFromServerDate(String date) {
        return getCalendar(SERVER_DATE_FORMAT, date);
    }

    public static Calendar getCalendarFromServerTime(String date) {
        return getCalendar(SERVER_TIME_FORMAT, date);
    }

    public static Calendar getCalendarFromServerDateTime(String date) {
        return getCalendar(SERVER_DATE_TIME_FORMAT, date);
    }

    public static String getFormattedServerYMD(Calendar calendar) {
        return getFormattedDate(SERVER_DATE_FORMAT, calendar);
    }

    public static String getDateTime(Calendar calendar) {
        Calendar tempCalender = (Calendar) calendar.clone();
        return getFormattedDate(CLIENT_DATE_TIME_FORMAT, tempCalender);
    }

    public static String getDate(Calendar calendar) {
        return getFormattedClientDateShort(calendar);
    }

    public static String getTime(Calendar calendar) {
        return getFormattedClientTime12Hour(calendar);
    }

    public static String getFormattedClientTime12Hour(Calendar calendar) {
        Calendar tempCalender = (Calendar) calendar.clone();
        return getFormattedDate(CLIENT_TIME_FORMAT_12H, tempCalender);
    }

    public static String getFormattedClientTime24Hour(Calendar calendar) {
        Calendar tempCalender = (Calendar) calendar.clone();
        return getFormattedDate(CLIENT_TIME_FORMAT_24H, tempCalender);
    }

    public static Calendar getCalendar(Long timestamp) {
        if (timestamp == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timestamp);
        return calendar;
    }

    public static Long getTimestamp(Calendar calendar) {
        if (calendar == null) {
            return null;
        }
        return calendar.getTimeInMillis();
    }

    public static String getMonth(Calendar calendar) {
        return getFormattedDate(CLIENT_MONTH_FORMAT, calendar);
    }

    public static String getDayOfMonth(Calendar calendar) {
        if (calendar == null) {
            return null;
        }
        return String.format(Locale.US, "%d", calendar.get(Calendar.DAY_OF_MONTH));
    }

    public static String getDay(Calendar calendar) {
        if (calendar == null) {
            return null;
        }
        return calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.US);
    }

    public static String getDayShort(Calendar calendar) {
        if (calendar == null) {
            return null;
        }
        return calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.US);
    }

    public static String getHour(Calendar calendar) {
        return getFormattedDate(CLIENT_HOUR_24H_FORMAT, calendar);
    }

    public static String getHour12H(Calendar calendar) {
        return getFormattedDate(CLIENT_HOUR_12H_FORMAT, calendar);
    }

    public static String getMinute(Calendar calendar) {
        return getFormattedDate(CLIENT_MINUTE_FORMAT, calendar);
    }

    public static String getAmPm(Calendar calendar) {
        return getFormattedDate(CLIENT_AM_PM_FORMAT, calendar);
    }

    public static boolean isSameDay(Calendar c1, Calendar c2) {
        return c1 != null && c2 != null
                && c1.get(Calendar.DAY_OF_YEAR) == c2.get(Calendar.DAY_OF_YEAR)
                && c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR);
    }

    public static Calendar getCalendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        if (date != null) {
            calendar.setTime(date);
        }
        return calendar;
    }

    public static Calendar parseServerDate(String strDate) {
        return getCalendar(SERVER_DATE_FORMAT, strDate);
    }

    public static Calendar parseServerTime(String strTime) {
        return getCalendar(SERVER_TIME_FORMAT, strTime);
    }

    public static Calendar parseServerDateTime(String strDateTime) {
        return getCalendar(SERVER_DATE_TIME_FORMAT, strDateTime);
    }

    public static String convertServerDate(String strDate) {
        return getFormattedClientDate(parseServerDate(strDate));
    }

    public static String convertServerTime(String strTime) {
        return getFormattedClientTime(parseServerTime(strTime));
    }

    public static String convertServerDateTime(String strDateTime) {
        return getFormattedClientDateTime(parseServerDateTime(strDateTime));
    }

    public static int differenceInDays(Calendar start, Calendar end) {
        int diff = 0;
        if (start != null && end != null) {
            long startMillis = start.getTimeInMillis();
            long endMillis = end.getTimeInMillis();
            diff = (int) (Math.abs(endMillis - startMillis) / (24 * 60 * 60 * 1000)) + 1;
        }
        return diff;
    }

    public static boolean isBeforeToday(Calendar calendar) {
        if (calendar == null) {
            return false;
        }
        Calendar today = DateTimeUtil.getStartOfDay(Calendar.getInstance());
        Calendar tempCalender = DateTimeUtil.getStartOfDay((Calendar) calendar.clone());
        return tempCalender.before(today);
    }

    public static String formatDateTimeGithub(String inputDateTime) {
        try {
            // Parse the input string into a Date object
            SimpleDateFormat inputFormat = new SimpleDateFormat(GITHUB_DATE_TIME_FORMAT, Locale.US);
            Date date = inputFormat.parse(inputDateTime);


            if (date != null) {
                // Format the Date object into the desired format
                SimpleDateFormat outputFormat = new SimpleDateFormat(CLIENT_DATE_TIME_FORMAT_SHOW_FROM_GITHUB, Locale.US);
                return outputFormat.format(date);
            } else {
                // Handle the case where parsing fails
                return "Invalid date format";
            }
        } catch (ParseException e) {
            e.printStackTrace();
            // Handle parsing exception as needed
            return "Error parsing date";
        }
    }
}
