package com.helpers;

import org.apache.commons.lang3.time.DateFormatUtils;
import java.lang.management.ManagementFactory;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.*;


public class DateZoneHelper extends org.apache.commons.lang3.time.DateUtils
{


    public static String YYYY = "yyyy";

    public static String YYYY_MM = "yyyy-MM";

    public static String YYYY_MM_DD = "yyyy-MM-dd";

    public static String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    public static String yyyyMMddHHmmssSSS = "yyyyMMddHHmmssSSS";

    public static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    public static String MMM_dd_yyyy_h_mm_a="MMM dd, yyyy, h:mm a";

  
    public static String americaPattern=MMM_dd_yyyy_h_mm_a;


    public static ZonedDateTime getZonedDateTimeFromTimestamp(long timestamp,int offset)
    {
        Instant instant = Instant.ofEpochSecond(timestamp);
        ZoneId zoneId = DateZoneUtils.getZoneIdFromOffset(offset);
        ZonedDateTime zonedDateTime = instant.atZone(zoneId);

        return zonedDateTime;
    }


    public static String parseZonedDateTime2Str(ZonedDateTime zonedDateTime,String pattern)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        String formattedDate = zonedDateTime.format(formatter);
        return formattedDate;
    }


    public static ZonedDateTime parseTZStr2ZonedDateTime(String dateString,String pattern,int offset)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        ZoneId zoneId = DateZoneUtils.getZoneIdFromOffset(offset);
        ZonedDateTime zonedDateTime=ZonedDateTime.parse(dateString, formatter);
        ZonedDateTime newZonedDateTime = zonedDateTime.withZoneSameInstant(zoneId);
        return newZonedDateTime;
    }


    public static ZonedDateTime parseNoTZStr2ZonedDateTime(String dateString,String pattern,int offset)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern).withZone(java.time.ZoneOffset.UTC);;
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(dateString, formatter);
        ZoneId zoneId = DateZoneUtils.getZoneIdFromOffset(offset);
        ZonedDateTime newZonedDateTime = zonedDateTime.withZoneSameInstant(zoneId);
        return newZonedDateTime;
    }

    public static String parseTimestamp2Str(long timestamp,int offset,String pattern)
    {
        ZonedDateTime zonedDateTime=DateZoneUtils.getZonedDateTimeFromTimestamp( timestamp, offset);
        return DateZoneUtils.parseZonedDateTime2Str(zonedDateTime,pattern);
    }


    public static ZonedDateTime offsetZonedDateTime(ZonedDateTime oldZonedDateTime,int offset)
    {
        ZoneId zoneId = DateZoneUtils.getZoneIdFromOffset(offset);
        ZonedDateTime offsetDateTime = oldZonedDateTime.withZoneSameInstant(zoneId);
        return offsetDateTime;
    }

    public static ZonedDateTime offsetDate(Date date,int offset)
    {
        Instant instant = date.toInstant();
        ZoneId zoneId = DateZoneUtils.getZoneIdFromOffset(offset);
        ZonedDateTime oldZonedDateTime = ZonedDateTime.ofInstant(instant, zoneId);

        ZonedDateTime offsetDateTime = DateZoneUtils.offsetZonedDateTime(oldZonedDateTime,offset);

        return offsetDateTime;
    }


    public static Date getDateFromZonedDateTime(ZonedDateTime zonedDateTime)
    {
        Date date = Date.from(zonedDateTime.toInstant());
        return date;
    }


    public static ZonedDateTime getZonedDateTimeFromDate(Date date,int offset)
    {
        ZoneId zoneId = DateZoneUtils.getZoneIdFromOffset(offset);

        Instant instant = date.toInstant();

        ZonedDateTime zonedDateTime = instant.atZone(zoneId);

        return zonedDateTime;
    }


    public static String getZonedDateTimeStrFromDate(Date date,int offset)
    {

        ZonedDateTime zonedDateTime =DateZoneUtils.getZonedDateTimeFromDate(date,offset);

 
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX");


        String formattedDateTime = zonedDateTime.format(formatter);


        return formattedDateTime;
    }


    public static String getOffsetString(int offset) {
        if (offset == 0) {
            return "Z";
        }

        String sign = offset > 0 ? "+" : "-";
        int hours = Math.abs(offset);
        return String.format("%s%02d:00", sign, hours);
    }

    public static ZoneId getZoneIdFromOffset(int offset)
    {
        String offsetStr=DateZoneUtils.getOffsetString(offset);
        ZoneId zoneId = ZoneId.ofOffset("UTC", ZoneOffset.of(offsetStr));
        return zoneId;
    }

}
