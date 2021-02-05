package com.twkf.helper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtil {
	/**
	 * "yyyy-MM-dd HH:mm:ss"
	 * */
	public static String getCurrentTime(String timeStr,int a){
		SimpleDateFormat sdf = new SimpleDateFormat(timeStr);
		Calendar beforeTime = Calendar.getInstance();
 		beforeTime.add(Calendar.DATE, a);// a天之后的时间
 		Date beforeD = beforeTime.getTime();
 		String time = sdf.format(beforeD);
		return time;
	}

	public static String plusDay(String newDate,int num) throws ParseException{
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // 日期格式
		Date date = dateFormat.parse(newDate); // 指定日期
		Date newDates = addDate(date, num); // 指定日期加上20天
		return dateFormat.format(newDates);
	}

	/**
	 * "yyyy-MM-dd HH:mm:ss"
	 * */
	public static String plusDayByFormat(String newDate,int num,String timeStr) throws ParseException{
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 日期格式
		SimpleDateFormat sdf = new SimpleDateFormat(timeStr);
		Date date = dateFormat.parse(newDate); // 指定日期
		Date newDates = addDate(date, num); // 指定日期加上20天
		return sdf.format(newDates);
	}

	public static Date addDate(Date date,long day) {
		long time = date.getTime(); // 得到指定日期的毫秒数
		day = day*24*60*60*1000; // 要加上的天数转换成毫秒数
		time+=day; // 相加得到新的毫秒数
		return new Date(time); // 将毫秒数转换成日期
	}
	public static int calculateTime(String startTime,String endTime){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long diff;
		long days = 0;
		long hours = 0;
		long minutes = 0;
		try {
			Date d1 = df.parse(startTime);
			Date d2 = df.parse(endTime);
			diff =d2.getTime()-d1.getTime();//这样得到的差值是微秒级别
			days = diff / (1000 * 60 * 60 * 24);
			hours = (diff-days*(1000 * 60 * 60 * 24))/(1000* 60 * 60);
			minutes = (diff-days*(1000 * 60 * 60 * 24)-hours*(1000* 60 * 60))/(1000* 60);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (int) (days);
	}
	/**
	 * yyyy-MM-dd HH:mm:ss "yyyy-MM-dd"
	 * */
	public static String getNowTime(String strTime){
		SimpleDateFormat df = new SimpleDateFormat(strTime);// 设置日期格式
		return df.format(new Date());// new Date()为获取当前系统时间
	}
	public static  int compare_date(String DATE1, String DATE2) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date dt1 = df.parse(DATE1);
			Date dt2 = df.parse(DATE2);
			if (dt1.getTime() > dt2.getTime()) {
				System.out.println("dt1 在dt2前");
				return 1;
			} else if (dt1.getTime() < dt2.getTime()) {
				System.out.println("dt1在dt2后");
				return -1;
			} else {
				return 0;
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return 0;
	}

	public static String getDay(int a) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, a);
		date = calendar.getTime();
		return sdf.format(date);
	}

	// 将时间转换为时间戳
	public static String dateToStamp(String s) {
		String res = null;
		// 设置时间格式，将该时间格式的时间转换为时间戳
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date;
		try {
			date = simpleDateFormat.parse(s);
			long time = date.getTime();
			res = String.valueOf(time);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return res;
	}

	// 将时间戳转换为时间
	public static String stampToTime(String s) {
		String res;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		long lt = new Long(s);// 将时间戳转换为时间
		Date date = new Date(lt);// 将时间调整为yyyy-MM-dd HH:mm:ss时间样式
		res = simpleDateFormat.format(date);
		return res;
	}
}
