package eredua;

public class BlokeBiguna extends Blokea{
	public BlokeBiguna() {
		super(false);
	}
	
	@Override 
	public void apurtu() {
        if (!apurtuta) {
            apurtuta = true;
            System.out.println("Bloke biguna apurtu da!");
        }
    }
}
