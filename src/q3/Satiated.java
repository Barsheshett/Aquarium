package q3;

public class Satiated implements HungerState {

	@Override
	public void doAction(Swimmable swim) {
		swim.setHungeryState(this);
		
	}

}
