package eredua;

import java.util.Observable;

public abstract class Laberinto extends Observable {
	private static Laberinto nireLaberinto;
	protected Gelaxka[][] matriz;
	
	protected Laberinto() {
		this.matriz = new Gelaxka[11][17];
		for (int i = 0; i < 11; i++) {
			for (int j = 0; j < 17; j++) {
				matriz[i][j] = new Gelaxka(null);
			}
		}
	}

	public static Laberinto getLaberinto() {
		if (nireLaberinto == null) {
			nireLaberinto = new Classic();
		}
		return nireLaberinto;
	}

	public Gelaxka getGelaxkaPos(int x, int y) {
		return matriz[x][y];
	}
	
	public abstract void sortuLaberinto();

	public boolean bidePosizioa(int x, int y) {
		return !matriz[x][y].blokeDu();
	}

	public void eguneratuGelaxka(int x, int y, Blokea bloke) {
		matriz[x][y] = new Gelaxka(bloke);
	}


}
