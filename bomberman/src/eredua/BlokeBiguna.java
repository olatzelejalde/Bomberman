package eredua;

//Bloke Biguna: Suntsigarria den bloke bat
public class BlokeBiguna extends Blokea {
	public BlokeBiguna() {
		super(true); // Bloke bigunak suntsigarriak dira
	}
	
	@Override
    public boolean blokeBigunaDa() {
        return true;
    }
}
