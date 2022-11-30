package q3;

import java.awt.Color;

public class Memento {
	private int ID;
	private int horSpeed;
	private int verSpeed;
	private int size;
	private Color col;
	private int x_front;
	private int y_front;
	private int foodFrequency;
	private Swimmable swim = null;
	
	public Memento(Swimmable swim) {
		this.swim = swim;
		this.ID = swim.getId();
		this.horSpeed = swim.getHorSpeed();
		this.verSpeed = swim.getVerSpeed();
		this.size = swim.getSize();
		this.col = swim.getColor();
		this.x_front = swim.getX_front();
		this.y_front = swim.getY_front();
		this.foodFrequency = swim.getFoodFrequency();
	}
	
	public Memento(Immobile imm) {
		ID = imm.getID();
		size = imm.getSize();
		x_front = imm.getX();
		y_front = imm.getY();
	}

	public int getID() {
		return ID;
	}

	public int getHorSpeed() {
		return horSpeed;
	}

	public int getVerSpeed() {
		return verSpeed;
	}

	public int getSize() {
		return size;
	}

	public Color getCol() {
		return col;
	}

	public int getX_front() {
		return x_front;
	}

	public int getY_front() {
		return y_front;
	}

	public Swimmable getSwim() {
		return swim;
	}

	public int getFoodFrequency() {
		return foodFrequency;
	}

	
	
}
