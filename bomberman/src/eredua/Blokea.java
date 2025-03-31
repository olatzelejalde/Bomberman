package eredua;

public abstract class Blokea {
	private boolean suntsigarri;

	public Blokea(boolean suntsigarri) {
		this.suntsigarri = suntsigarri;
	}

	public abstract boolean blokeBigunaDa();
	
	public boolean suntsigarriaDa() {
        return suntsigarri;
    }
}
