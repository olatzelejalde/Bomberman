package eredua;

public class Gelaxka {
	private Bloke bloke;
	private Bonba bonba;
	private boolean sua;

	public Gelaxka(Bloke bloke) {
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

	public void suaJarri() {
		if (bloke != null && bloke.suntsigarriaDa()) {
			bloke = null;
		}
	}
	
	public void suaKendu() {
		this.sua = false;
	}
	
	public boolean suaDago() {
		return sua;
	}
	
	public boolean bonbaDago() {
		return bonba != null;
	}
	
	public void bonbaJarri(Bonba bonba) {
		this.bonba = bonba;
	}
	
	public void bonbaKendu() {
		this.bonba = null;
	}

}
