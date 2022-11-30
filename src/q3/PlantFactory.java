package q3;

import java.awt.Color;

public class PlantFactory implements AbstractSeaFactory {

	private int size;
	private int x;
	private int y;
	private Color clr;
	
	public PlantFactory(int size,int x,int y)
	{
		this.size=size;
		this.x=x;
		this.y=y;
		clr = Color.green;
	}

	@Override
	public SeaCreature produceSeaCreature(String type) {
		SeaCreature seacreature = null;
		
		if(type.equals("Laminaria"))
			seacreature = new Laminaria(size,x,y,clr);
		
		else if(type.equals("Zostera"))
			seacreature = new Zostera(size,x,y,clr);
		
		return seacreature;
	}

}
