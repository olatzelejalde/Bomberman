package eredua;

public class Gelaxka {
	private Blokea bloke;
	private Bonba bonba;
	private boolean sua;

	public Gelaxka(Blokea bloke) {
		this.bloke = bloke;
		this.bonba = null;
		this.sua = false;
	}

	public boolean blokeDu() {
		return bloke != null;
	}
	
	public boolean apurtuDaiteke() {
		return bloke.suntsigarriaDa();
	}
	
	public void apurtuBlokea() {
		if (apurtuDaiteke()) {
			bloke = null;
			System.out.println("Blokea apurtu da!!");
		}
	}
	
	public boolean suaDago() {
		return sua != false;
	}

	public void setSua(boolean sua) {
		this.sua = sua;
	}

}
