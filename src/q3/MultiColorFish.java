package q3;
/**
 * An class that creates MultiColorFish-type instances and other 
 * methods that manipulate the change and / or display of the MultiColorFish.
 * extends Fish
 *
 * @authors  Eden Barsheshet ID: 203531918
 * 			 Toli Kot ID: 324413756
 * 
 * @see     Fish
 */
public class MultiColorFish extends Fish{
/**
 * Parameters constructor
 * @param size
 * @param x_front
 * @param y_front
 * @param horSpeed
 * @param verSpeed
 * @param col
 */
	public MultiColorFish(int size, int x_front, int y_front, int horSpeed, int verSpeed, int col) {
		super(size, x_front, y_front, horSpeed, verSpeed, col,100);
	}
/**
 * Override method:
 * by activate - Feeds the fish (eatCount field increased by)
 * if eatCount = "Maximum amount of food" => increase Size of fish by 1
 * change the color when size grow by 1
 */
	@Override
	public void eatInc() {
		
		if(this.getEatCount() == this.getEAT_DISTANCE()) {
			this.changeFish();
			this.changeColor();
			this.setEatCount(0);
		}
		this.setEatCount(this.getEatCount()+1);
	}
	
}
