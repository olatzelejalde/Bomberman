package eredua;

import java.util.Observable;

public class Gelaxka extends Observable {
	private Bloke bloke;
	private Bonba bonba;
	private boolean etsai;
	private boolean sua;
	private boolean bomberman;

	
	public Gelaxka(Bloke bloke, boolean bomberman, boolean etsai) {
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
			Jokoa.getJokoa().getLaberinto().kenduSuntsigarri();
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
	
	// Metodo etsaia kentzeko
	public void kenduEtsaia() {
		setEtsaia(false);
		
	}
	
	// Aldaketetaz jakinarazi bistari
	public void eguneratuBista() {
        String[] datuak = new String[3]; //0.egoera, 1.norabide, 2.tipoJokalari
        
        if (sua) {
            datuak[0] = "sua";
        } 
        else if (bomberman && bonbaDago()) {
            datuak[0] = "bombermanBonba";
        } 
        else if (bomberman) {
            datuak[0] = "bomberman";
            datuak[1] = Jokoa.getJokoa().getBomberman().getNorabidea();
        } 
        else if (bonbaDago()) {
            datuak[0] = "bonba";
        } 
        else if (etsaiaDago()) {
            datuak[0] = "etsaia";
        } 
        else if (blokeDu()) {
        	 if (apurtuDaiteke()) {
        		 datuak[0] = "blokBig";
             } 
             else {
            	 datuak[0] = "blokGo";
             }
        } 
        else {
            datuak[0] = "hutsik";
        }

        // Añadir tipo de jugador si es bomberman
        if (bomberman) {
            Bomberman b = Jokoa.getJokoa().getBomberman();
            if (b instanceof Black) {
            	datuak[2] = "black";
            }
            else {
            	datuak[2] = "white";
            }
        }

        setChanged();
        notifyObservers(datuak);
    }

}
