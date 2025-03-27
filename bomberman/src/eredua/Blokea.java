package eredua;

public abstract class Blokea {
	protected boolean suntsigarri;
	
	// Eraikitzailea
	public Blokea(boolean suntsigarri) {
		this.suntsigarri = suntsigarri;
	}
	
	public abstract boolean blokeBigunaDa();

}
