package q3;

import java.awt.Color;
import java.awt.Graphics;

public class Laminaria extends Immobile{

	
	/**
	 * Constractor
	 * @param size
	 * @param x
	 * @param y
	 * @param clr
	 */
	public Laminaria(int size, int x , int y, Color clr) {
		super(size,x,y);
		super.name = "Laminaria";
	}
	
	/**Draw planet Leminaria**/
	@Override
	public void drawCreature(Graphics g) {
		g.setColor(clr);
		g.fillArc(x-size/20,  y-size,  size/10,  size*4/5,  0,  360);
		g.fillArc(x-size*3/20, y-size*13/15, size/10, size*2/3, 0, 360);
		g.fillArc(x+size/20, y-size*13/15, size/10, size*2/3, 0, 360);
		g.drawLine(x, y, x, y-size/5);
		g.drawLine(x, y, x+size/10, y-size/5);
		g.drawLine(x, y, x+size/10, y-size/5);
	}
}
