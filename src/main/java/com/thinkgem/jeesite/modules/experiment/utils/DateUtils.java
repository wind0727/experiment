package com.thinkgem.jeesite.modules.experiment.utils;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import org.apache.commons.lang.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateUtils {
	private static Logger log = LoggerFactory.getLogger(DateUtils.class);
	private static final String datePattern = "yyyy-MM-dd";
	private static final String yearMonthPattern = "yyyy-MM";
	private static final String timestampPattern = "yyyy-MM-dd HH:mm:ss";
	private static final String timePattern = "HH:mm:ss";
	private static SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * format the date to 'yyyy-mm-dd'
	 * 
	 * @param date
	 *          String
	 * @return Date
	 */
	public static Date parseDate(String date) {
		try {
			return org.apache.commons.lang.time.DateUtils.parseDate(date,
					new String[] { datePattern });
		} catch (Exception ex) {
			log.error("Date format occurs error .", ex);
			return null;
		}
	}
	
	public static Date parseDateFullPattern(String date) throws ParseException {
		return org.apache.commons.lang.time.DateUtils.parseDate(date,
					new String[] { timestampPattern,datePattern,yearMonthPattern,timePattern});
	}
	
	/**
	 * format the date to 'yyyy-mm'
	 * 
	 * @param date
	 *          String
	 * @return Date
	 */
	public static Date parseDateOfYearMonth(String yearMonth) {
		try {
			return org.apache.commons.lang.time.DateUtils.parseDate(yearMonth,
					new String[] { yearMonthPattern });
		} catch (Exception ex) {
			log.error("Date format occurs error .", ex);
			return null;
		}
	}
	
	public static String format(Date date) {
		if (date == null) {
			return "";
		} else {
			return DateFormatUtils.format(date, datePattern);
		}
	}
	
	public static String formatYearMonth(Date date) {
		if (date == null) {
			return "";
		} else {
			return DateFormatUtils.format(date, yearMonthPattern);
		}
	}

	public static Date parseTimestamp(String timestamp) {
		try {
			return org.apache.commons.lang.time.DateUtils.parseDate(timestamp,
					new String[] { timestampPattern });
		} catch (Exception ex) {
			log.error("Date format occurs error .", ex);
			return null;
		}
	}
	public static Date parseTime(String time) {
		try {
			return org.apache.commons.lang.time.DateUtils.parseDate(time,
					new String[] { timePattern });
		} catch (Exception ex) {
			log.error("Date format occurs error .", ex);
			return null;
		}
	}
	public static String formatTimestamp(Timestamp timestamp) {
		if (timestamp == null) {
			return "";
		} else {
			return DateFormatUtils.format(timestamp, timestampPattern);
		}
	}
	
	public static String formatTime(Time time) {
		if (time == null) {
			return "";
		} else {
			return DateFormatUtils.format(time, timePattern);
		}
	}
	
	public static String formatTime(Timestamp time) {
		if (time == null) {
			return "";
		} else {
			return DateFormatUtils.format(time, timePattern);
		}
	}
	
	public static void trimToDate(Calendar calendar) {
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
	}

	/**
	 * rollDays distance to today ; n before today? negative : positive
	 * 
	 * @param n
	 *          int
	 * @return Timestamp
	 */
	public static Timestamp rollDays(int n) {
		long dayMils = 86400000l;
		return new Timestamp(System.currentTimeMillis() + dayMils * n);
	}
	
	public static void main(String[] args) throws Exception {
		
//		Date first = parseDate("2016-06-01");
//		
//		// 月份
//		List<String> months = getYearMonthsOfDateInterval(first, new Date());
//		
//		//指定月份第一天和最后一天
//		
//		for (String yearMonth : months) {
//			
//			Date firstDate = getFirstOrEndDayOfYearMonth(yearMonth, true);
//			Date endDate = getFirstOrEndDayOfYearMonth(yearMonth, false);
//			System.out.println("firstDate : " + format(firstDate) + ", endDate : " + format(endDate));
//		}
//		
//		months.add(DateUtils.formatYearMonth(first));
//		months.add(DateUtils.formatYearMonth(new Date()));
//		
//		System.out.println(months);
		
		
		
//		System.out.println(format(getDayByWeek(2016, 40, true)));
//		System.out.println(format(getDayByWeek(2016, 40, false)));
//		
//		SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd");
//		
//		FORMAT.applyPattern("w");
//		
//		System.out.println(FORMAT.format(parseDate("2016-10-01")));
		
		
//        Calendar c=new GregorianCalendar();  
//        c.setTime(DateUtils.parseDateOfYearMonth("2016-10")); 
        
//        System.out.println(c.getActualMaximum(Calendar.WEEK_OF_MONTH));\
		
//		System.out.println(getWeeksByYearMonth("201610"));
		
//		System.out.println(getLastYearMonth(new Date()));
//		
//		long cou = 50;
//		
//		System.out.println(Double.valueOf(0));
		
//		SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		
//		String sD = "2014-12-13 08:00:00";
//		String eD = "2014-12-13 08:20:00";
//		
//		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
//		
//		 long sTime = (parseTimestamp(sD)).getTime();
//		 long dTime = (parseTimestamp(eD)).getTime();
//		 
//		 long time = (long)((dTime - sTime)) ;
//		 
//		 String t = formatter.format(time - TimeZone.getDefault().getRawOffset());
		 
		 
//		 System.out.println(format(getCurrYearFirst()));
		
//		String yearMonth = "2016-11";
//		
//		if (Long.valueOf(yearMonth.substring(5)) < 10) {
//			
//			System.out.println(yearMonth.substring(6));
//			
//		} else {
//			
//			System.out.println(yearMonth.substring(5));
//		}
		
//		Calendar calendar = Calendar.getInstance(); 
//		calendar.set(2016, 1, 1);
//		int days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
//		System.out.println(getMonthDay("2016-08"));
		
//		System.out.println(Math.round(Double.valueOf(3)/Double.valueOf(4)));
		
//		DecimalFormat decimalFormat = new DecimalFormat("#.##");
//		
//		System.out.println(decimalFormat.format(2.3434));
//		
//		System.out.println(Math.round(14 * 0.03));
		
//		DecimalFormat DFTWO = new DecimalFormat("#.##");
//		
//		double a = Double.valueOf(DFTWO.format((Double.valueOf(21) / Double.valueOf(37))));
//		
//		System.out.println(a);
//		k
//		System.out.println("=====" + (0.57 * 100));
//		
//		BigDecimal b1 = new BigDecimal(Double.toString(0.57));
//		BigDecimal b2 = new BigDecimal(Double.toString(100));
//		System.out.println(b1.multiply(b2).longValue());
		
//		System.out.println(divDouble(Double.valueOf(0), Double.valueOf(1), 2));
		
		System.out.println("------" + TimeZone.getTimeZone("GMT+8").toString());
		
	}
	
	public static int getMonthDay(String yearMonth) {
		
		if (yearMonth == null || "".equals(yearMonth)) return 0;
		
		int year = Integer.valueOf(yearMonth.substring(0, 4));
		int month = Integer.valueOf(yearMonth.substring(5)) + 1;
		Calendar calendar = Calendar.getInstance(); 
		calendar.set(year, month, 1);
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		
	}
	
	public static Date getCurrYearFirst(){
        Calendar currCal=Calendar.getInstance();  
        int currentYear = currCal.get(Calendar.YEAR);
        return getYearFirst(currentYear);
    }
	
	private static Date getYearFirst(int year){  
		Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        Date currYearFirst = calendar.getTime();
        return currYearFirst; 
    }  
	
	public static String getLastYearMonth(Date date) {
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime(date);
	    calendar.set( Calendar.MONTH, calendar.get( Calendar.MONTH ) - 1 );
	    return formatYearMonth(calendar.getTime());
	}
	
	public static List<Integer> getWeeksByYearMonth(String yearMonth) {
		
		List<Integer> weeks = new ArrayList<Integer>();
		
		if (yearMonth == null || "".equals(yearMonth)) return weeks;
		
		Calendar c=new GregorianCalendar();  
        c.setTime(DateUtils.parseDateOfYearMonth(yearMonth)); 
        
        int weekCount = c.getActualMaximum(Calendar.WEEK_OF_MONTH);
        
        FORMAT.applyPattern("w");
        
        int firstWeek = Integer.valueOf(FORMAT.format(parseDate(yearMonth + "-01")));
        
        weeks.add(firstWeek);
        
        for (int i = 1; i < weekCount; i++) {
        	
        	weeks.add(firstWeek + i);
        	
        }
		
		return weeks;
		
	}

	/**
	 * 
	 * @param first
	 *          Date
	 * @param end
	 *          Date
	 * @return List<String>. format YearMonth patten is decided by cfg
	 *         ('global.yearMonthFormat')
	 */

	public static List<String> getYearMonthsOfDateInterval(Date first, Date end) {
		List<String> dateList = new ArrayList<String>();
		Calendar firstCalendar = Calendar.getInstance();
		Calendar endCalendar = Calendar.getInstance();
		firstCalendar.setTime(first);
		endCalendar.setTime(end);
		firstCalendar.add(Calendar.MONTH, 1);
		endCalendar.add(Calendar.MONTH, -1);
		while (!firstCalendar.after(endCalendar)) {
			dateList.add(DateFormatUtils.format(firstCalendar.getTime(),yearMonthPattern));
			firstCalendar.add(Calendar.MONTH, 1);
		}
		return dateList;
	}

	/**
	 * true behalf the firstDay of month, false behalf the endDay of month
	 * 
	 * @param bool
	 * @return
	 */
	public static Date getFirstOrEndDayOfMonth(boolean bool) {
		Date date = null;
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		calendar.set(Calendar.DAY_OF_MONTH, 1);
		if (!bool) {// 当月最后一天.
			calendar.roll(Calendar.MONTH, 1);
			calendar.roll(Calendar.DAY_OF_YEAR, -1);
		}
		return calendar.getTime();
	}
	
	/**
	 * 指定某月份第一天，最后一天.
	 * @param yearMonth: 2014-02
	 */
	public static Date getFirstOrEndDayOfYearMonth(String yearMonth, boolean bool) {
		Calendar calendar=new GregorianCalendar();  
        calendar.setTime(DateUtils.parseDateOfYearMonth(yearMonth));  
        calendar.add(calendar.DATE, 0); 
//        calendar.roll(calendar.DATE, -1);
        if (!bool) { //某月最后一天.
        	calendar.roll(calendar.DATE, -1);
        }
        return calendar.getTime();  
	}
	
	/**
	 * 指定某年，某一周第一天，最后一天.
	 * @param year
	 * @param week
	 * @param flag
	 * @return
	 */
	public static Date getDayByWeek(int year, int week, boolean bool) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.WEEK_OF_YEAR, week);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);//设置周一  
		cal.setFirstDayOfWeek(Calendar.MONDAY); 
		if (!bool)//某周最后一天.
			cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek() + 6);
		return cal.getTime();
	}
	
	/**
	 * 当前周.
	 * @return
	 */
	public static int getWeekNumber() {
		Calendar calendar = Calendar.getInstance();
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		calendar.setTime(new Date());
		return calendar.get(Calendar.WEEK_OF_YEAR);
	}
	
	public static void main2(String[] args) throws ParseException {
//		System.out.println(DateUtils.format(getFirstOrEndDayOfMonth(false)));
//		System.out.println(DateUtils.format(getFirstOrEndDayOfMonth(true)));
		
//		System.out.println(DateUtils.format(DateUtils.getDayByWeek(2014, 3, true)));
//		System.out.println(DateUtils.format(DateUtils.getDayByWeek(2014, 3, false)));
		
//		System.out.println(getWeekNumber());
		
		Date date = formatDate(new Date(), -1);
		System.out.println(DateUtils.format(date));
		
	}

	/**
	 * 根据某一日期算出多少天后的某日.
	 * 
	 * @param initDate Date
	 * @param days int
	 * @return Date
	 */
	public static Date formatDate(Date initDate, int days) {
		Calendar strCal = new GregorianCalendar();
		Date formatDate = null;
		strCal.setTime(initDate);
		Calendar endCal = new GregorianCalendar(strCal.get(Calendar.YEAR), strCal
				.get(Calendar.MONTH), strCal.get(Calendar.DAY_OF_MONTH) + days);
		if (endCal != null) {
			formatDate = endCal.getTime();
		}
		return formatDate;
	}
	
	/**  
	  * 根据所给日期返回两日期相差的秒数  
	  * @param startDate  
	  * @param endDate  
	  * @return 返回两个日期间隔的毫秒数  
	  */  
	   public static long getSecond(Date startDate,Date endDate)   
	   {   
	         long startTime = startDate.getTime();   
	         long endTime = endDate.getTime();   
	         long interval = (endTime - startTime)/1000;   
	        
	         return interval;   
	   }    
	 
	   /**  
	   * 根据所秒数,计算相差的时间并以**时**分**秒返回
	   */  
	   public static String getBeapartDate(long interval) {   
			String beapartdate = "";
			int nDay = (int) interval / (24 * 60 * 60);
			int nHour = (int) (interval - nDay * 24 * 60 * 60) / (60 * 60);
			int nMinute = (int) (interval - nDay * 24 * 60 * 60 - nHour * 60 * 60) / 60;
			int nSecond = (int) interval - nDay * 24 * 60 * 60 - nHour * 60 * 60 - nMinute* 60;
			String day = nDay == 0 ? "" : nDay+"天";
			String hour = nHour == 0 ? "" : nHour+"小时";
			String minute = nMinute == 0 ? "" : nMinute + "分";
			String second = nSecond == 0 ? "" : nSecond + "秒";
			beapartdate = day+hour+minute+second; 
	        return beapartdate;  
	   }
	   
	   public static int getIntervalDays(Date fDate, Date oDate) {

	       if (null == fDate || null == oDate) {

	           return -1;

	       }

	       long intervalMilli = oDate.getTime() - fDate.getTime();

	       return (int) (intervalMilli / (24 * 60 * 60 * 1000));

	    }
       
}
