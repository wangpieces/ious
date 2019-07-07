package com.wangpiece.ious.utils;

import com.alibaba.druid.sql.dialect.oracle.ast.expr.OracleSizeExpr;
import org.apache.commons.lang.StringUtils;
import org.apache.tomcat.jni.Local;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Calendar;
import java.util.Locale;

/**
 * @author wang.xu
 * @desc
 * @date 2018-12-20 23:16
 */
public class DateUtils {

    public static final String PATTERN_SHORT = "yyyy-MM-dd";
    public static final String PATTERN_LONG= "yyyy-MM-dd HH:mm:ss";

    /**
     * 获取当前时间
     * @param pattern
     * @return
     */
    public static String getCurrentDate(String pattern) {
        if(StringUtils.isEmpty(pattern)){
            pattern = PATTERN_LONG;
        }
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 获取当前时间
     * @return
     */
    public static String getCurrentDate() {
        return getCurrentDate(PATTERN_LONG);
    }

    /**
     * 根据传入的时间，获取两个时间的差值，返回天数
     * @param beginTime
     * @param endTime
     * @return
     */
    public static Integer getDays(String beginTime, String endTime){
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(PATTERN_SHORT);
            long tempBegintime = simpleDateFormat.parse(beginTime).getTime();
            long tempEndTime = simpleDateFormat.parse(endTime).getTime();
            int days = (int) ((tempEndTime -tempBegintime) / (1000*3600*24));
            return (days > 0) ? (days + 1) : days ;
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 计算传入的时间与当前的时间差
     * @param endTime
     * @return
     */
    public static Integer getDays(String endTime){
        String beginTime = LocalDate.now().format(DateTimeFormatter.ofPattern(PATTERN_SHORT));
        return getDays(beginTime,endTime);
    }

    /**
     * 获取几天前或者几天后的时间
     * @param day
     * @return
     */
    public static String getPlusDays(Integer day){
        LocalDate localDate = LocalDate.now().plusDays(day-1);
        String plusDay = localDate.format(DateTimeFormatter.ofPattern(PATTERN_SHORT));
        return plusDay;
    }


    /**
     * 获取与传入时间相隔的时间，传入时间为分钟
     * @param currentDate
     * @param minutes
     * @return
     */
    public static String getPlusMinutes(String currentDate,Integer minutes){
        DateTimeFormatter df = DateTimeFormatter.ofPattern(PATTERN_LONG);
        LocalDateTime parse = LocalDateTime.parse(currentDate,df);
        LocalDateTime plus = parse.plusMinutes(5);
        String plusDate = plus.format(DateTimeFormatter.ofPattern(PATTERN_LONG));
        return plusDate;
    }


    public static void main(String[] args) {
//        System.out.println( LocalDate.now());
//        System.out.println( LocalTime.now());
//        System.out.println( LocalDateTime.now().format(DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss")));
//        System.out.println( ZonedDateTime.now());
//        System.out.println( Instant.now());

//        System.out.println(getDays("2018-12-29"));
//        System.out.println(getDays("2018-12-17","2018-12-29"));
//        System.out.println(getDays("2018-12-29","2018-12-17"));
//        getPlusDays(7);
//        getPlusDays(1);
//        getPlusDays(2);
        String c = getCurrentDate();
        System.out.println("c="+c);
        String plusMinutes = getPlusMinutes(c, 5);
        System.out.println("plusMinutes="+plusMinutes);
    }
}
