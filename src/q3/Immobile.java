package q3;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Immobile implements SeaCreature{
	String name;	
	private static int counter = 0;
	public final int ID;
	protected int size;
	protected int x;
	protected int y;
	protected Color clr;
	
	/**
	 * Constractor
	 * @param size
	 * @param x
	 * @param y
	 */
	public Immobile(int size, int x, int y) {
		ID = ++counter;
		this.size = size;
		this.x = x;
		this.y = y;
		clr = Color.GREEN;
	}
	/**
	 * Getters;
	 * @return
	 */
	public String getName() {return name;}
	public int getID() {return ID;}
	public int getSize() {return size;}
	public int getX() {return x;}
	public int getY() {return y;}
	public Color getClr() {return clr;}
	public abstract void drawCreature(Graphics g);
	
	
	/**
	 * Memento state restorage
	 * @param size
	 * @param x
	 * @param y
	 * @param clr
	 */
	public void setState(int size, int x, int y, Color clr) {
		this.size = size; 
		this.x = x;
		this.y = y;
		this.clr = clr;
	}
	
}
