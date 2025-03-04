package eredua;

import java.util.Observable;

public abstract class Bomberman extends Observable{
	private int x, y;
	private boolean bizirik;
	private Laberinto laberinto;
	
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
	
	public boolean hildaDago() {
	    return !bizirik;
	}

	public void setHil(boolean hil) {
		this.bizirik = false;
	}
	
	public void mugitu(int newX, int newY) {
		// Laberintoaren limiteen barruan
		if (laberinto.koordenatuBarruan(newX, newY) && laberinto != null) {
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
	
	public abstract void bonbaJarri();
	
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
