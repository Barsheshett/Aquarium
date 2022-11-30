package q3;


public class AnimalFactory implements AbstractSeaFactory{
	
	private int size;
	private int horSpeed;
	private int verSpeed;
	private int clr;
	private int foodFrequency;
	
	public AnimalFactory(int size,int horSpeed,int verSpeed,int clr, int foodFrequency)
	{
		this.size=size;
		this.horSpeed=horSpeed;
		this.verSpeed=verSpeed;
		this.clr=clr;
		this.foodFrequency = foodFrequency;
	}

	@Override
	public SeaCreature produceSeaCreature(String type) {
		SeaCreature seacreature = null;
		
		if(type.equals("Fish")) {
			seacreature = new Fish(size,0,0,horSpeed,verSpeed,clr,foodFrequency);
			return seacreature;
		}
		
		else if(type.equals("Jellyfish")) {
			seacreature = new Jellyfish(size,0,0,horSpeed,verSpeed,clr,foodFrequency);
			return seacreature;
		}
		
		return null;
	}

}
