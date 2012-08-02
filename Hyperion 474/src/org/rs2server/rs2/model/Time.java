package org.rs2server.rs2.model;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Gets the time and date.
 * 
 * @author Ryan Greene
 * 
 */
public class Time {

	/**
	 * Instance for the time.
	 */
	private static Time time = new Time();

	/**
	 * Gets the time.
	 * 
	 * @return The time.
	 */
	public static Time getTime() {
		return time;
	}

	/**
	 * Instance for the calendar.
	 */
	Calendar cal = new GregorianCalendar();

	/**
	 * The day.
	 */
	private int day = cal.get(Calendar.DAY_OF_MONTH);

	/**
	 * The month.
	 */
	private int month = cal.get(Calendar.MONTH);

	/**
	 * The year.
	 */
	private int year = cal.get(Calendar.YEAR);

	/**
	 * The hour.
	 */
	private int hour = cal.get(Calendar.HOUR);

	/**
	 * The minute.
	 */
	private int min = cal.get(Calendar.MINUTE);

	/**
	 * The second.
	 */
	private int sec = cal.get(Calendar.SECOND);

	/**
	 * Gets the day.
	 * 
	 * @return The day.
	 */
	public int getDay() {
		return day;
	}

	/**
	 * Gets the month.
	 * 
	 * @return The month.
	 */
	public int getMonth() {
		return month + 1;
	}

	/**
	 * Gets the year.
	 * 
	 * @return The the year.
	 */
	public int getYear() {
		return year;
	}

	/**
	 * Gets the hour.
	 * 
	 * @return The hour.
	 */
	public int getHour() {
		return hour;
	}

	/**
	 * Gets the minute.
	 * 
	 * @return The minute.
	 */
	public int getMin() {
		return min;
	}

	/**
	 * Gets the second.
	 * 
	 * @return The second.
	 */
	public int getSec() {
		return sec;
	}

	/**
	 * Holds the month names.
	 */
	private String[] monthNames = { "January", "Febuary", "March", "April",
			"May", "June", "July", "Augest", "September", "October",
			"November", "December" };

	/**
	 * Gets the month name.
	 * 
	 * @return The month name.
	 */
	public String getMonthName() {
		return monthNames[getMonth() - 1];
	}
}