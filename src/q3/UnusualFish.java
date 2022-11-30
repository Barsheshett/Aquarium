package q3;
/**
 * An class that creates UnusualFish-type instances and other 
 * methods that manipulate the change and / or display of the UnusualFish.
 * extends Fish
 *
 * @authors  Eden Barsheshet ID: 203531918
 * 			 Toli Kot ID: 324413756
 * 
 * @see     Fish
 */
public class UnusualFish extends Fish{
	private int factor;
/**
 * Parameters constructor
 * @param size
 * @param x_front
 * @param y_front
 * @param horSpeed
 * @param verSpeed
 * @param col
 * @param factor
 */
	public UnusualFish(int size, int x_front, int y_front, int horSpeed, int verSpeed, int col, int factor) {
		super(size, x_front, y_front, horSpeed, verSpeed, col,100);
		this.factor = factor;
	}
/**
 * get & set method
 * @return
 */
	public int getFactor() {
		return factor;
	}

	public void setFactor(int factor) {
		this.factor = factor;
	}
	
/**
 * return the size method	
 */
	public int getSize() {
		return super.getSize() * factor; 
	}
	 
}
