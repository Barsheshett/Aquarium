package start;
import java.util.Arrays;
import java.util.Random;
import q1.MyDate;
import q2.Location;
import q2.Temperature;
import q3.Fish;
import q3.Swimmable;

/**
 * Methods of useful use
 * 
 * @authors  Eden Barsheshet ID: 203531918
 * 			 Toli Kot ID: 324413756
 * 
 *@see     Program
 */
public class Utility {
/**
 * getting array of Dates and return a date with Consecutive dates	
 * @param arr
 * @return MyDate instance
 */
	public static MyDate threeDates(MyDate arr[]) {
		int counter = 0;
		int index = 0;
		
		for(int i = 0; i < arr.length; i++ ) {
			
			for(int j = 0; j < arr.length; j ++ ) {
				if(arr[j].equals(arr[i].prevDate())) {
					index = j;
					counter++;
				}
			}
			
			for(int k = 0; k < arr.length; k ++ ) {
				if(arr[k].equals(arr[i].nextDate()))
					counter++;
			}
			
			if(counter == 2)
				return arr[index];
			counter = 0;
		}
		MyDate date = new MyDate();
		return date;
	};
/**
 * getting array of Locations and return index of all the max Temperature with location
 * @param loc
 * @return index
 */
	public static int getMaxTemp(Location loc[]) {
		int index = 0;
		Location l = new Location(loc[0]);

		for(int i = 0; i < loc.length; i++) {
			if(!loc[i].getTemp().isEmpty()) {
				if(l.getAverage() < loc[i].getAverage()) {
					l = loc[i]; 
					index = i;
				}
			}
			else
				continue;
		}
		return index;
	}
/**
 * Print the tAquarium by Instructions
 * @param swim
 */
	public static void printAquarium(Swimmable swim[]) {
		if(swim.length == 0) {
			System.out.println("No Fish in Aquarium");
			System.exit(0);
		}
			
		System.out.println("Aquarium[type/color/actual size/eat count]:");
		for(Swimmable s: swim)
			System.out.printf("%-18s%-12s%-8d%-5d\n", s.getAnimalName(),s.getColor(),s.getSize(),s.getEatCount());
	}
/**
 * Randomly feeds fish in the aquarium	
 * @param swim
 * @param amount
 */
	public static void feedAquaticAnimal(Swimmable swim[] , int amount) {
		
		if(swim.length == 0) {
			System.out.println("No Fish in Aquarium");
			System.exit(0);
		}
		
		Random rand = new Random();
		
		for(int i = 0; i < amount; i ++) {
			int int_random = rand.nextInt(swim.length);
			swim[int_random].eatInc();
		}
	}
/**
 * Count the fish that are larger than the first fish in the array	
 * @param swim
 * @return
 */
	public static int countAquaticAnimal(Swimmable swim[]) {
		
		if(swim.length == 0) {
			System.out.println("No Fish in Aquarium");
			System.exit(0);
		}
		
		int count = 0;
		
		for(int i = 1; i < swim.length; i ++) {
			if(swim[i].getSize() > swim[0].getSize())
				count ++;
		}
		return count;
	}
/**
 * 	Sort the aquariums by fish size
 * @param swim
 */
	public static void sortAquaticAnimal(Swimmable swim[]) {
		
		if(swim.length > 1) {
			Arrays.sort(swim);
		}
		else {
			return;
		}
		
		
	}
}
