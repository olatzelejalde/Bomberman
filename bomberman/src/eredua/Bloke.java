package eredua;

public abstract class Bloke {
	private boolean suntsigarri;

	public Bloke(boolean suntsigarri) {
		this.suntsigarri = suntsigarri;
	}

	public abstract boolean blokeBigunaDa();
	
	public boolean suntsigarriaDa() {
        return suntsigarri;
    }
}
