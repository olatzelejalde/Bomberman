package eredua;

public abstract class Bloke {
	protected boolean suntsigarri;
	
	public Bloke(boolean suntsigarri) {
		this.suntsigarri = suntsigarri;
	}
	
	public boolean suntsigarriaDa() {
		return suntsigarri;
	}
	
	protected abstract void apurtu();

}
