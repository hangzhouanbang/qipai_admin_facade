package com.anbang.qipai.admin.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class TimeUtil {
	//获得多少天前的毫秒数
		public static long getDate(long date,long day){
			day = day*24*60*60*1000; // 要加上的天数转换成毫秒数
			date+=day; // 相减得到新的毫秒数
			return date; 
		}
		
		//根据毫秒数获得天数
		public static int getDay(long date) {
			int day = (int) (date/24/60/60/1000);
			return day;
		}
		
		//根据天数获得毫秒数
		public static long getTimeOnDay(int day) {
			long time = day*24*60*60*1000;
			return time;
		}




    //获取本月的开始时间
    public static long getBeginDayTimeOfCurrentMonth(long currentTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(getNowYear(currentTime), getNowMonth(currentTime) - 1, 1);
        return getDayStartTime(calendar.getTime());
    }

    //获取本月的结束时间
    public static long getEndDayTimeOfCurrentMonth(long currentTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(getNowYear(currentTime), getNowMonth(currentTime) - 1, 1);
        int day = calendar.getActualMaximum(5);
        calendar.set(getNowYear(currentTime), getNowMonth(currentTime) - 1, day);
        return getDayEndTime(calendar.getTime());
    }

    //获取某个日期的开始时间
    public static long getDayStartTime(Date d) {
        Calendar calendar = Calendar.getInstance();
        if (null != d) calendar.setTime(d);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }

    //获取某个日期的结束时间
    public static long getDayEndTime(Date d) {
        Calendar calendar = Calendar.getInstance();
        if (null != d) calendar.setTime(d);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTimeInMillis();
    }

    //获取今年是哪一年
    public static Integer getNowYear(long currentTime) {
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTimeInMillis(currentTime);
        return Integer.valueOf(gc.get(1));
    }

    //获取本月是哪一月
    public static int getNowMonth(long currentTime) {
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTimeInMillis(currentTime);
        return gc.get(2) + 1;
    }



    /**
     * 获得某个时间的点钟(0-24)
     * @param currentTime
     * @return
     */
    public static int getClockByTime(long currentTime){

        SimpleDateFormat sdf=new SimpleDateFormat("HH");
        // 时间戳转换成时间
        String sd = sdf.format(currentTime);

        return Integer.parseInt(sd);
    }

    /**
     * start
     * 本周开始时间戳 - 以星期一为本周的第一天
     */
    public static long getWeekStartTime() {

        Calendar cal=Calendar.getInstance();
        //如果当前时间是星期天，则向上移动一天，再取本周的星期一，老外用周日到周六为一周，向前移动一天，则是中国人的本周
        if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
            cal.add(Calendar.DATE, -1);
        }
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

        return getDayStartTime(cal.getTime());
    }

}
