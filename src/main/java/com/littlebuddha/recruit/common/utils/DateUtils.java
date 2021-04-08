package com.littlebuddha.recruit.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期工具类
 */
public class DateUtils {

    /**
     * 获取完整的时间---并为字符串格式
     * @param date
     * @return
     */
    public static String getFullDate(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(date);
        return format;
    }

    public static void main(String[] args) {
        System.out.println(getFullDate(new Date()));
    }
}
