package q3;

public class Hungry implements HungerState {

	@Override
	public void doAction(Swimmable swim) {
		swim.setHungeryState(this);
	}
}
