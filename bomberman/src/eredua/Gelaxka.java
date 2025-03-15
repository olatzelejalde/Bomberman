package eredua;

import java.util.Observable;

public class Gelaxka extends Observable {
	private Blokea bloke;
	private Bonba bonba;
	private boolean sua;
	private Bomberman bomberman;

	
	public Gelaxka(Blokea bloke, Bomberman bomberman) {
		this.bloke = bloke;
		this.bonba = null;
		this.sua = false;
		this.bomberman = bomberman;
	}

	// Verificar si hay bloque en la celda
	public boolean blokeDu() {
		return bloke != null;
	}
	
	// Verificar si se puede romper el bloque
	public boolean apurtuDaiteke() {
		return bloke != null && bloke.suntsigarriaDa();
	}
	
	// Romper el bloque si es rompible
	public void apurtuBlokea() {
		if (apurtuDaiteke()) {
			bloke = null;
			Laberinto.getLaberinto().kenduSuntsigarri();
			System.out.println("Blokea apurtu da!!");
			eguneratuBista();
		}
	}
	
	// Verificar si hay fuego en la celda
	public boolean suaDago() {
		return sua;
	}
	
	// Verificar si esta el bomberman en la celda
	public boolean bombermanDago() {
		if (bomberman != null) {
			return true;
		}
		else {
			return false;
		}
	}
	
	// Verificar si hay bomba en la celda
	public boolean bonbaDago() {
		if (bonba != null) {
			return true;
		}
		else {
			return false;
		}
	}
	
	// Poner bomberman en la celda
	public void setBomberman(Bomberman b) {
		bomberman = b;
		eguneratuBista();
	}
	
	// Poner bomba en la celda
	public void setBonba(Bonba bonba) {
		this.bonba = bonba;
		eguneratuBista();
	}
	
	// Poner fuego en la celda
	public void setSua(boolean sua) {
		this.sua = sua;
		eguneratuBista();
	}
	
	// Notificar a la vista de cambios
	public void eguneratuBista() {
		setChanged();
        notifyObservers();
	}
}
