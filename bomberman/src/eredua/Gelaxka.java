package eredua;

import java.util.Observable;

public class Gelaxka extends Observable {
	private Blokea bloke;
	private Bonba bonba;
	private boolean sua;
	private boolean bomberman;

	// Eraikitzaileak
	public Gelaxka(Blokea bloke, boolean bomberman) {
		this.bloke = bloke;
		this.bonba = null;
		this.sua = false;
		this.bomberman = bomberman;
	}
	
	// Gelaxkan bloke dagoen jakiteko
	public boolean blokeDu() {
		return bloke != null;
	}
	
	// Blokea apurtu ahal den jakiteko
	public boolean apurtuDaiteke() {
		return bloke != null && bloke.suntsigarriaDa();
	}
	
	// Blokea apurtu apurtu ahal bada
	public void apurtuBlokea() {
		if (apurtuDaiteke()) {
			bloke = null;
			System.out.println("Blokea apurtu da!!");
			eguneratuBista();
			Laberinto.getLaberinto().kenduSuntsigarri();
		}
	}
	
	// Gelaxkan sua dagoen jakiteko
	public boolean suaDago() {
		return sua;
	}
	
	// Gelaxkan Bombermana dagoen jakiteko
	public boolean bombermanDago() {
		return bomberman;
	}
	
	// Gelaxkan bonba dagoen jakiteko
	public boolean bonbaDago() {
		if (bonba != null) {
			return true;
		}
		else {
			return false;
		}
	}
	
	// Bombermana gelaxkan jarri
	public void setBomberman(boolean b) {
		bomberman = b;
		eguneratuBista();
	}
	
	// Bonba gelaxkan jarri
	public void setBonba(Bonba bonba) {
		this.bonba = bonba;
		eguneratuBista();
	}
	
	// Sua gelaxkan jarri
	public void setSua(boolean sua) {
		this.sua = sua;
		eguneratuBista();
	}
	
	// Aldaketetaz jakinarazi bistari
	public void eguneratuBista() {
		setChanged();
        notifyObservers();
	}
}
