package com.wangpiece.ious.utils;

import org.apache.commons.lang.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

/**
 * @author wang.xu
 * @desc
 * @date 2018-12-20 23:16
 */
public class DateUtils {

    /**
     * 获取当前时间
     * @param pattern
     * @return
     */
    public static String getCurrentDate(String pattern) {
        if(StringUtils.isEmpty(pattern)){
            pattern = "YYYY-MM-dd HH:mm:ss";
        }
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 获取当前时间
     * @return
     */
    public static String getCurrentDate() {
        return getCurrentDate("YYYY-MM-dd HH:mm:ss");
    }

    /**
     * 根据传入的时间，获取两个时间的差值，返回天数
     * @param beginTime
     * @param endTime
     * @return
     */
    public static Integer getDays(String beginTime, String endTime){
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
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
        String beginTime = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return getDays(beginTime,endTime);
    }


    public static void main(String[] args) {
//        System.out.println( LocalDate.now());
//        System.out.println( LocalTime.now());
//        System.out.println( LocalDateTime.now().format(DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss")));
//        System.out.println( ZonedDateTime.now());
//        System.out.println( Instant.now());

        System.out.println(getDays("2018-12-29"));
        System.out.println(getDays("2018-12-17","2018-12-29"));
        System.out.println(getDays("2018-12-29","2018-12-17"));
    }
}
