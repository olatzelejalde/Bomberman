package eredua;

import java.util.Observable;

public abstract class Bomberman{
	private int x, y;
	private boolean bizirik;
	protected int bonbaKop;
	
	
	public Bomberman(int x, int y, int bonbaKop) {
		this.x = x;
		this.y = y;
		this.bizirik = true;
		this.bonbaKop = bonbaKop;
	}

	// Conseguir la posicion X
	public int getX() {
		return x;
	}
	
	// Conseguir la posicion Y
	public int getY() {
		return y;
	}
	
	// Colocarlo en la posicion x
	public void setX(int x) {
		this.x = x;
	}

	// COlocarlo en la posicion y
	public void setY(int y) {
		this.y = y;
	}
	
	// Verifica si ha muerto
	public boolean hildaDago() {
	    return !bizirik;
	}

	// Matar al bomberman
	public void setHil(boolean hil) {
		this.bizirik = false;
	}
	
	// Metodo abstacto para poner bomba en Normal
	public abstract void bonbaJarri();
	
	// Verifica si puede moverse a las nuevas coordenadas
	public void mugitu(int newX, int newY) {
		Laberinto laberinto = Jokoa.getJokoa().getLaberinto();
		
		// Mirar si esta dentro del laberinto
		if (laberinto.koordenatuBarruan(newX, newY) && laberinto != null) {
			// Mirar si en la nueva posicion hay camino
			if (laberinto.bidePosizioa(newX, newY)) {
				// Quitar bomberman de la celda actual
				laberinto.getGelaxkaPos(x, y).setBomberman(null);
				// Mover a la celda nueva
				laberinto.getGelaxkaPos(newX, newY).setBomberman(this);
				
				setX(newX);
				setY(newY);
						
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
	
	// Matar al bomberman y acabar partida
	public void hil() {
	    if (bizirik) {
	        bizirik = false;
	        Jokoa.getJokoa().bukaera(false);
	    }
	}
}
