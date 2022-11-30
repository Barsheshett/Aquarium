package q2;
import java.util.ArrayList;
import q1.MyDate;

/**
  * A class that creates location-type instances and other 
 * methods that manipulate the change and / or display of the Location 
 *
 * @authors  Eden Barsheshet ID: 203531918
 * 			 Toli Kot ID: 324413756
 * 
 * @see     MyDate, Temperature
 */
public class Location {
	private String name;
	private ArrayList<Temperature> temp = new ArrayList<Temperature>();

	
/**
 * 	Parameters constructor
 * @param name
 */
	public Location(String name) {
		this.name = name;
	}
/**
 * Default constructor
 */
	public Location() {
		this.name = null;
	}
/**
 * Copy constructor
 * @param other
 */
	public Location(Location other) {
		this.name = other.name;
		this.temp = other.temp;
	}
/**
 * getters & setters methods
 * @return
 */
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public ArrayList<Temperature> getTemp() {
		return temp;
	}
	public void setTemp(ArrayList<Temperature> temp) {
		this.temp = temp;
	}
/**
 * Print method by default	
 */
	public void printLocation() {
		if(temp.isEmpty())
			System.out.println(name + " no temperature measurements available.");
		else {
			System.out.print(name + " " + "temperature measurements: ");
			
			for(Temperature tem: temp) {
				System.out.print(" ");
				tem.printTempFull();
				System.out.print(" | ");
			}
		}
		System.out.println();
	}
/**
 * Print methods by getting	range of temperatures
 * @param n - range of temperatures
 */
	public void printLocation(double n) {
		if(temp.isEmpty()) {
			System.out.println(name + " no temperature measurements available.");
			return;
		}
		
		System.out.print(name + " " + "temperature measurements: ");
		double temp1 = getAverage() + n;
		double temp2 = getAverage() - n;
		
		for(Temperature tem: temp) {
			if(tem.getScale() >= temp2 && tem.getScale() <= temp1) {
				System.out.print(" ");
				tem.printTempFull();
				System.out.print(" | ");
			}
		}
		System.out.println();
	}
/**
 * 	Calculates the average of temperatures at the Location
 * @return average
 */
	public double getAverage() {
		int sum = 0;
		
		for(Temperature tem: temp) {
			sum += tem.getScale();
		}
		
		if(sum == 0 | temp.size() == 0) {
			System.out.println("null");
			System.exit(0);
		}
		
		return sum/temp.size();
	}
/**
 * Add new temperature and date to location by getting parameters
 * @param tem
 * @param day
 * @param month
 * @param year
 */
	public void addTemp(double tem , int day , int month, int year) {
		Temperature t = new Temperature(tem, day, month, year);
		temp.add(t);
	}
/**
 * Add new temperature and date to location by getting parameter of scale and the current date	
 * @param tem
 */
	public void addTemp(double tem) {
		Temperature t = new Temperature(tem);
		temp.add(t);
	}
	
	/*
	 * public Temperature getMax() {
	 * 
	 * if(temp.isEmpty()) return null;
	 * 
	 * Temperature t = new Temperature(temp.get(0)); double max =
	 * temp.get(0).getScale();
	 * 
	 * for(int i = 0; i < temp.size(); i++) { if(max < temp.get(i).getScale()) t =
	 * temp.get(i); }
	 * 
	 * return t; }
	 */
/**
 * Return the max temperature at the Location	
 * @return
 */
	public Temperature getMax() {
		
		if(temp.isEmpty()) return null;
		
		Temperature t = new Temperature(temp.get(0));
		
		for(Temperature tem: temp)
		{
			if(tem.equals(tem.compareTemp(t)))
					t = tem;
		}
		return t;
	}
	
}
