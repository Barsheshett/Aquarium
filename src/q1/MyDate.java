package q1;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.Date;



/**
 * A class that creates date-type instances and other 
 * methods that manipulate the change and / or display of the date 
 *
 * @authors  Eden Barsheshet ID: 203531918
 * 			 Toli Kot ID: 324413756
 * @see     Location
 */
public class MyDate {

	private int day;
	private int month;
	private int year;
	
	
	
/**
 * Default Constructor	
 */
	public MyDate() {
		Date date = new Date();
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		day = localDate.getDayOfMonth();
		month = localDate.getMonthValue();
		;
		year = localDate.getYear();
		;
	}
/**
 * Constructor with parameters
 * @param day
 * @param month
 * @param year
 */
	public MyDate(int day, int month, int year) {

		Date date = new Date();
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		int y = localDate.getYear();
		int m = localDate.getMonthValue();
		int d = localDate.getDayOfMonth();

		if (day > 31 | day < 1)
			this.day = d;
		else
			this.day = day;
		if (day > getMonthDay(month) | (day == 29 && month == 2 && !isLeapYear(year)))
			this.month = m;
		else
			this.month = month;
		if (year > 2100 | year < 1900)
			this.year = y;
		else
			this.year = year;
	}
/**
 * Copy Constructor
 * @param mydate
 */
	public MyDate(MyDate mydate) {
		this.day = mydate.day;
		this.month = mydate.month;
		this.year = mydate.year;
	}
/**
 * Abstract & getters & setters Methods & override toSting:
 */
	@Override
	public String toString() {
		return day + "/" + month + "/" + year;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
/**
 * Override equals method
 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MyDate other = (MyDate) obj;
		if (day != other.day)
			return false;
		if (month != other.month)
			return false;
		if (year != other.year)
			return false;
		return true;
	}
/**
 * 	printing methods:
 */
	public void printDate() {
		System.out.println(toString());
	}

	public void printMonthName() {
		System.out.println(day + " " + MyDict(month) + " " + year);
	}
/**
 * A dictionary that returns color in the form of a string.
 * 
 * @param temp
 * 			"temp" is the key of dictionary
 * 
 * @return the color
 */
	public String MyDict(int temp) {
		String s = String.valueOf(temp);
		Hashtable<String, String> my_dict = new Hashtable<String, String>();
		my_dict.put("1", "Jan");
		my_dict.put("2", "Feb");
		my_dict.put("3", "Mar");
		my_dict.put("4", "Apr");
		my_dict.put("5", "May");
		my_dict.put("6", "Jun");
		my_dict.put("7", "Jul");
		my_dict.put("8", "Aug");
		my_dict.put("9", "Sep");
		my_dict.put("10", "Oct");
		my_dict.put("11", "Nov");
		my_dict.put("12", "Dec");

		return my_dict.get(s);
	}
/**
 * A dictionary that returns amount of days in month by default.
 * 
 * @return amount of days
 */
	public int getMonthDay() {

		Hashtable<Integer, Integer> my_dict = new Hashtable<Integer, Integer>();
		my_dict.put(1, 31);
		my_dict.put(2, 28);
		my_dict.put(3, 31);
		my_dict.put(4, 30);
		my_dict.put(5, 31);
		my_dict.put(6, 30);
		my_dict.put(7, 31);
		my_dict.put(8, 31);
		my_dict.put(9, 30);
		my_dict.put(10, 31);
		my_dict.put(11, 30);
		my_dict.put(12, 31);

		return my_dict.get(month);
	}
	/**
	 * A dictionary that returns amount of days in month by getting number of month.
	 * 
	 * @param month
 * 			"month" is the key of dictionary
 * 
	 * @return amount of days
	 */
	public int getMonthDay(int month) {

		Hashtable<Integer, Integer> my_dict = new Hashtable<Integer, Integer>();
		my_dict.put(1, 31);
		my_dict.put(2, 28);
		my_dict.put(3, 31);
		my_dict.put(4, 30);
		my_dict.put(5, 31);
		my_dict.put(6, 30);
		my_dict.put(7, 31);
		my_dict.put(8, 31);
		my_dict.put(9, 30);
		my_dict.put(10, 31);
		my_dict.put(11, 30);
		my_dict.put(12, 31);

		return my_dict.get(month);
	}
/**
 * Checking if the year is a leap year by default.
 * 
 * @return True / False
 */
	public boolean isLeapYear() {
		if (year % 400 == 0)
			return true;
		if (year % 100 == 0)
			return true;
		if (year % 4 == 0)
			return true;
		return false;
	}
/**
 * Checking if the year is a leap year by default.
 * 
 * @param year - the year of current instance.
 * 
 * @return True / False
 */
	public boolean isLeapYear(int year) {
		if (year % 400 == 0)
			return true;
		if (year % 100 == 0)
			return true;
		if (year % 4 == 0)
			return true;
		return false;
	}
/**
 * Return a new instance of MyDate of the next date
 * 
 * @return next date
 */
	public MyDate nextDate() {

		if (day == 28 && month == 2) {
			MyDate temp = new MyDate(1, month + 1, year);
			return temp;
		}

		if (day == 30)
			if (month == 4 | month == 6 | month == 9 | month == 11) {
				MyDate temp = new MyDate(1, month + 1, year);
				return temp;
			}

		if (day == 31)
			if (month == 1 | month == 3 | month == 5 | month == 7 | month == 8 | month == 10) {
				MyDate temp = new MyDate(1, month + 1, year);
				return temp;
			}

		if (day == 31 && month == 12) {
			MyDate temp = new MyDate(1, 1, year + 1);
			return temp;
		}

		MyDate temp = new MyDate(day + 1, month, year);
		return temp;
	}
	/**
	 * Getting string and print the date by order of string.
	 * 
	 * @param temp - the String of Display.
	 */
	public void printFormatDate(String temp) {
		switch (temp) {
		case "ddmmyy":
			System.out.println(day + "/" + month + "/" + year % 100);
			break;
		case "ddmmyyyy":
			System.out.println(day + "/" + month + "/" + year);
			break;
		case "mmddyyyy":
			System.out.println(month + "/" + day + "/" + year);
			break;
		case "yyyymmdd":
			System.out.println(year + "/" + month + "/" + day);
			break;
		case "ddMMyyyy":
			printMonthName();
			break;

		default:
			printDate();
		}
	}
/**
 * Compare the Date with other Date, who is bigger.
 * 
 * @param other - instance of date
 * @return 1 , -1 or 0
 */
	public int compareDate(MyDate other) {
		if (equals(other))
			return 0;
		if (this.year < other.year)
			return -1;
		else if (this.year > other.year)
			return 1;
		else if (this.month < other.month)
			return -1;
		else if (this.month > other.month)
			return 1;
		else if (this.day < other.day)
			return -1;
		else
			return 1;
	}
/**
 * Return the previous date.
 *  
 * @return temp - new instance of date
 */
	public MyDate prevDate() {

		if (day == 1) {

			if (month == 1) {
				MyDate temp = new MyDate(31, 12, year - 1);
				return temp;
			}
			if (month == 3) {
				MyDate temp = new MyDate(28, 2, year);
				return temp;
			}
			if (month == 2 | month == 4 | month == 6 | month == 9 | month == 11) {
				MyDate temp = new MyDate(31, month - 1, year);
				return temp;
			} else if (month == 5 | month == 7 | month == 8 | month == 10 | month == 12) {
				MyDate temp = new MyDate(30, month - 1, year);
				return temp;
			}
		}
		MyDate temp = new MyDate(day - 1, month, year);
		return temp;
	}

}
