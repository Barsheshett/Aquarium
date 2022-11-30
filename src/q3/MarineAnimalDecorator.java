package q3;

import java.awt.Color;

public class MarineAnimalDecorator implements MarineAnimal{
	
	MarineAnimal marineanimal;
	
	@Override
	public void PaintFish(Color clr) {
		marineanimal.PaintFish(clr);
	}
	
	public MarineAnimalDecorator(MarineAnimal marineanimal) {
		this.marineanimal = marineanimal;
	}

}
