package q2;
import q1.MyDate;
/**
   * A class that creates Temperature-type instances and other 
 * methods that manipulate the change and / or display of the Temperature 
 *
 * @authors  Eden Barsheshet ID: 203531918
 * 			 Toli Kot ID: 324413756
 * 
 * @see     MyDate, Location
 */
public class Temperature {
	private double scale;
	private MyDate scale_date;
	
	
/**
 * Parameters constructor	
 * @param scale
 * @param day
 * @param month
 * @param year
 */
	public Temperature(double scale, int day, int month, int year) {
		this.scale = scale;
		this.scale_date = new MyDate(day, month, year);
	}
/**
 * Default constructor	
 */
	public Temperature() {
		this.scale = 0;
		this.scale_date = new MyDate();
	}
/**
 * Copy constructor	
 * @param other
 */
	public Temperature(Temperature other) {
		this.scale = other.scale;
		this.scale_date = new MyDate(other.scale_date.getDay(), other.scale_date.getMonth(), other.scale_date.getYear());
	}
/**
 * Parameters constructor & current date
 * @param scale
 */
	public Temperature(double scale) {
		this.scale = scale;
		this.scale_date = new MyDate();
	}
/**
 * getters and setters methods
 * @return
 */
	public double getScale() {
		return scale;
	}

	public void setScale(double scale) {
		this.scale = scale;
	}
/**
 * Override toString
 */
	@Override
	public String toString() {
		return scale + "°C" + " " + scale_date.toString();
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
		Temperature other = (Temperature) obj;
		if (Double.doubleToLongBits(scale) != Double.doubleToLongBits(other.scale))
			return false;
		if (scale_date == null) {
			if (other.scale_date != null)
				return false;
		} else if (!scale_date.equals(other.scale_date))
			return false;
		return true;
	}
/*
 * Printing method
 */
	public void printTemp() {
		if(scale > 0)
			System.out.println("+" + scale + "°C");
		else
			System.out.println(scale + "°C");
	}
/**
 * Printing method	
 */
	public void printTempFull() {
		if(scale > 0)
			System.out.print("+" + scale + "°C" + " " + scale_date.toString());
		else
			System.out.print(scale + "°C" + " " + scale_date.toString());
	}
/**
 * Compare Temperature instance with other Temperature instance
 * @param other
 * @return who have a bigger scale
 */
	public Temperature compareTemp(Temperature other) {
		if(this.scale > other.scale)
			return this;
		return other;
	}
	
}
