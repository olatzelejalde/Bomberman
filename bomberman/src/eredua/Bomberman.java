package eredua;

import java.util.Observable;

public class Bomberman extends Observable{
	private int x, y;
	private boolean bizirik;
	private Laberinto laberinto;
	private int bonbaKop;
	
	public Bomberman(int x, int y, Laberinto laberinto) {
		this.x = x;
		this.y = y;
		this.laberinto = laberinto;
		this.bizirik = true;
	}

	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setHil(boolean hil) {
		this.bizirik = false;
	}
	
	public void mugitu(int newX, int newY, Laberinto laberinto) {
		// Laberintoaren limiteen barruan
		if (laberinto != null && (newX >= 0 && newX < 11) && (newY >= 0 && newY < 17)) {
			// Posizio berrian bidea dagoen konprobatu
			if (laberinto.bidePosizioa(newX, newY)) {
				this.x = newX;
				this.y = newY;
				setChanged();
				notifyObservers();
				System.out.println("Bomberman mugitu da: (" + x + ", " + y + ")");
			}
			else {
				System.out.println("Ezin da mugitu posizio honetara");
			}
		}
		else {
			System.out.println("Laberintotik kanpo");
		}
		
	}
	
	public void bonbaJarri(Laberinto laberinto) {
		// Bonbak dituen konprobatu
		if (bonbaKop > 0) { 
			// Gelaxka hori hutsik badago begiratu
			if (!laberinto.getGelaxkaPos(x,y).blokeDu()) {
				laberinto.eguneratuGelaxka(x,y,null);
				Bonba bonba = new Normal(x,y);
				bonbaKop--;
				System.out.println("Bonba (" + x + ", " + y + ") gelaxkan!!");


			}
			else {
				System.out.println("Ezin da hemen bonba jarri husik ez dagoelako!!");
			}	
		}
		// Ez dauka bonbarik, beraz 3s itxaron
		else {
			
			System.out.println("Ez daukazu bonbarik! Beraz, itxaron behar duzu");
			itxaronBonba();
		}
		setChanged();
		notifyObservers();
	}

	public void itxaronBonba(){
		if (bonbaKop == 0) {
			new Thread(() -> {
				try {
					Thread.sleep(3000); // 3s itxaron bonba bat gehiago izateko
					bonbaKop = 1;
					System.out.println("Bonba bat gehiago duzu!!");
				}
				catch (InterruptedException e) {
					e.printStackTrace();
	        	}
			}).start();
		}
	}
	
	public boolean hildaDago() {
	    return !bizirik;
	}
	
	public void hil() {
	    if (bizirik) {
	        bizirik = false;
	        System.out.println("Bomberman hil da, jokoa bukatu da.");
	        Jokoa.getJokoa().bukaera(false);
	    }
	    setChanged();
        notifyObservers();	
	}
}
