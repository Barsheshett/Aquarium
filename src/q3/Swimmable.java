package q3;

import java.awt.Color;
import java.util.Observable;
import java.util.concurrent.CyclicBarrier;


/**
 * An abstract class that creates Swimmable-type Reference and other 
 * methods that must implements / override in the subclass 
 *
 * @authors  Eden Barsheshet ID: 203531918
 * 			 Toli Kot ID: 324413756
 * 
 * @see     Fish, Jellyfish, MultiColorFish, UnusualFish
 */
@SuppressWarnings("deprecation")
public abstract class Swimmable extends Observable implements Runnable , Comparable<Swimmable> , SeaCreature, Cloneable {
	protected int horSpeed;
	protected int verSpeed;
	protected int foodFrequency, FrequencyCounter = 0;
	private static int count=0;
	public final int ID;
	
	
/**
 * Default constructor	
 */
	public Swimmable() {
		horSpeed = 0;
		verSpeed = 0;
		this.ID = ++count;
	}
/**
 * Parameters constructor
 * @param horSpeed
 * @param verSpeed
 */
	public Swimmable(int horSpeed, int verSpeed, int foodFrequency) {
		this.ID = ++count;
		this.horSpeed = horSpeed;
		this.verSpeed = verSpeed;
		this.foodFrequency = foodFrequency;
	}
/**
 * getters & setters methods
 * @return
 */
	public int getHorSpeed() { return horSpeed; }
	public int getVerSpeed() { return verSpeed; }
	public void setHorSpeed(int horSpeed) { this.horSpeed = horSpeed; }
	public void setVerSpeed(int verSpeed) { this.verSpeed = verSpeed; }
	public int getFoodFrequency() {return foodFrequency;}
/**
 * abstract method	
 * @return
 */
	public abstract String getAnimalName ();
	public abstract void setSuspend();
	public abstract void setResume();
	public abstract void setBarrier(CyclicBarrier b);
	public abstract int getEatCount ();
	public abstract int getSize();
	public abstract Color getColor();
	public abstract int getId();
	public abstract void eatInc();
	public abstract Object clone();
	public abstract void setHungeryState(HungerState state);
	public abstract HungerState getState();
	abstract public int getX_front();
	abstract public int getY_front();
	abstract public int getX_dir();
	abstract public int getY_dir();
	abstract public void setState(int size, int x_front, int y_front , int horSpeed, int verSpeed , Color clr, int foodFrequency);
	 
	
	
/**
 * comparing Swimmable by size with implements Comparable interface
 */
	@Override
	public int compareTo(Swimmable o) {
		
		if(this.getSize() == o.getSize())
			return 0;
		else if(this.getSize() < o.getSize())
			return 1;

		return -1;
	} 
}
