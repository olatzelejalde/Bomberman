package eredua;

import java.util.Observable;

public class Gelaxka extends Observable {
	private Blokea bloke;
	private Bonba bonba;
	private boolean etsai;
	private boolean sua;
	private boolean bomberman;

	public Gelaxka(Blokea bloke, boolean bomberman, boolean etsai) {
		this.bloke = bloke;
		this.bonba = null;
		this.etsai = etsai;
		this.sua = false;
		this.bomberman = bomberman;
	}
	
	// Gelaxkan bloke dagoen jakiteko
	public boolean blokeDu() {
		return bloke != null;
	}
	
	// Blokea apurtu ahal den jakiteko
	public boolean apurtuDaiteke() {
		return bloke != null && bloke.blokeBigunaDa();
	}
	
	// Etsaia pasatzeko bidea dagoen jakiteko
	public boolean pasatuDaiteke() {
	    return !blokeDu() && !bonbaDago() && !etsaiaDago() && !bombermanDago();
	}
	
	// Blokea apurtu apurtu ahal bada
	public void apurtuBlokea() {
		if (apurtuDaiteke()) {
			bloke = null;
			System.out.println("Blokea apurtu da!!");
			eguneratuBista();
			// Singleton arazoa
			Labirinto.getLabirinto().kenduSuntsigarri();
		}
	}
	
	// Gelaxkan etsaia dagoen jakiteko
	public boolean etsaiaDago() {
		return etsai;
	}
	
	// Gelaxkan sua dagoen jakiteko
	public boolean suaDago() {
		return sua;
	}
	
	// Gelaxkan Bomberman dagoen jakiteko
	public boolean bombermanDago() {
		return bomberman;
	}
	
	// Gelaxkan bonba dagoen jakiteko
	public boolean bonbaDago() {
		return bonba != null;
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
	
	// Sua gelaxkan jarri
	public void setEtsaia(boolean etsai) {
		this.etsai = etsai;
		eguneratuBista();
	}
	
	// Aldaketetaz jakinarazi bistari
	public void eguneratuBista() {
        String egoera;
        
        if (this.sua) {
            egoera = "sua";
        } 
        else if (bomberman && bonbaDago()) {
        	egoera = "bombermanBonba";
        } 
        else if (bomberman) {
        	egoera = "bomberman";
        } 
        else if (bonbaDago()) {
        	egoera = "bonba";
        } 
        else if (etsaiaDago()) {
        	egoera = "bonba";
        } 
        else if (blokeDu()) {
            if (apurtuDaiteke()) {
            	egoera = "blokBig";
            } 
            else {
            	egoera = "blokGo";
            }
        } 
        else {
        	egoera = "hutsik";
        }
        setChanged();
        notifyObservers(new String[]{egoera, Jokoa.getJokoa().getBomberman().getNorabidea()});

    }
}
