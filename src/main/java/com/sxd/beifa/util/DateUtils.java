package com.sxd.beifa.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * JDK8 时间转化
 *
 *
 * @author SXD
 * @date 2018/2/12
 */
public class DateUtils {

    public static Date formatDate(String dateStr,String pattern){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        LocalDate localDate = LocalDate.parse(dateStr,dateTimeFormatter);
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDate.atStartOfDay().atZone(zone).toInstant();
        return Date.from(instant);
    }
}
