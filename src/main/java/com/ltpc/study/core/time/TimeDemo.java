package com.ltpc.study.core.time;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

/**
 * Created with IntelliJ IDEA.
 * User: liutong
 * Date: 2018/10/17
 * Time: 3:43 PM
 * Description:
 **/
public class TimeDemo {

    public static void main(String[] args) {
        // 根据指定年月日获得日期
        LocalDate dateOfBirth = LocalDate.of(2015,01,03);
        // 2015-01-03
        System.out.println(dateOfBirth);
        // 产生新的指定年的日期
        dateOfBirth = dateOfBirth.withYear(1888);
        // 1888-01-03
        System.out.println(dateOfBirth);

        // 获取当前日期
        LocalDate today = LocalDate.now();
        // 2018-07-23
        System.out.println(today);
        // the year=2018,the month =31
        System.out.println("the year="+today.getYear()+",the month ="+today.getMonth().maxLength());
        // 获取月的第一天
        LocalDate firstDayOfMonth = today.with(TemporalAdjusters.firstDayOfMonth());
        // 如输出 2018-07-01
        System.out.println("the first day of this month, "+firstDayOfMonth);
        // 取月的第2天
        LocalDate secondDayOfThisMonth = today.withDayOfMonth(2);
        // 如输出 2018-07-02
        System.out.println("the second day of this month, "+secondDayOfThisMonth);
        // 获取月的最后一天
        LocalDate lastDayOfThisMonth = today.with(TemporalAdjusters.lastDayOfMonth());
        // 如输出 2018-07-31
        System.out.println("the last day of this month, "+lastDayOfThisMonth);
//        System.out.println("the last day, "+lastDayOfThisMonth.);
    }
}
