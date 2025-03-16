package eredua;

import java.util.Observable;

public abstract class Laberinto extends Observable {
	private static Laberinto nireLaberinto;
	protected Gelaxka[][] matriz;
	private int suntsigarriak;

	
	protected Laberinto() {
		this.matriz = new Gelaxka[11][17];
		this.suntsigarriak = 0;
	}

	public static Laberinto getLaberinto() {
		if (nireLaberinto == null) {
			nireLaberinto = new Classic();
		}
		return nireLaberinto;
	}
	
	// Metodo abstracto para crear el laberinto en Classic
	public abstract void sortuLaberinto();
	
	// Consigue la matriz
	public Gelaxka[][] getMatriz() {
		return matriz;
	}
	
	// Devuelve la posicion de la celda
	public Gelaxka getGelaxkaPos(int x, int y) {
		if (koordenatuBarruan(x,y)) {
			return matriz[x][y];
		}
		return null;
	}
	
	// Verifica si esta dentro de las coordenadas
	public boolean koordenatuBarruan(int x, int y) {
		return (x >= 0 && x < 11) && (y >= 0 && y < 17);
	}

	// Verifica si hay camino por donde pasar
	public boolean bidePosizioa(int x, int y) {
		return !matriz[x][y].blokeDu() && !matriz[x][y].bonbaDago();
	}

	// Actualiza la celda si ha cambiado su estado
	public void eguneratuGelaxka(int x, int y, Blokea bloke) {
		if (koordenatuBarruan(x,y)) {
			matriz[x][y] = new Gelaxka(bloke, null);
		}
	}
	
	// Verifica si hay bloques blandos
	public boolean blokeakDaude() {
		return this.suntsigarriak > 0;
	}
	
	// Suma un bloque
	public void gehituSuntsigarri() {
		this.suntsigarriak++;
	}

	// Comprueba si hay bloques
	public void kenduSuntsigarri() {
		if (suntsigarriak > 0) {
			this.suntsigarriak--;
		}
		
		if (suntsigarriak == 0) {
			Jokoa.getJokoa().bukaera(true);
		}
	}
	
	
	
	// Pone fuego en las casillas adyacentes y si es bloque blando, lo rompe
	public void jarriSua(int x, int y) {
		if (koordenatuBarruan(x,y)) {
			Gelaxka g = matriz[x][y];
			
			// Solo pone fuego si la celda esta vacia o hay bloque blando
			if (!g.blokeDu() || g.apurtuDaiteke()) {
				g.setSua(true);
				
				// Rompe el bloque si es blando
				if (g.apurtuDaiteke()) {
					g.apurtuBlokea();
				}
				// Mata al bomberman si esta en alguna celda de fuego
				if (g.bombermanDago()) {
					Jokoa.getJokoa().getBomberman().hil();
					Jokoa.getJokoa().bukaera(false);
				}
			}
		}
	}
	
	// Quita el fuego cuando se cumple el tiempo de explosion
	public void kenduSua(int x, int y) {
		if (koordenatuBarruan(x,y)) {
			Gelaxka g = matriz[x][y];
			if (g.suaDago()) {
				g.setSua(false);
			}
		}
	}


}
