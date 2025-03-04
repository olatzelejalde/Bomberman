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
		if (koordenatuBarruan(x,y)) {
			return matriz[x][y];
		}
		return null;
	}
	
	public boolean koordenatuBarruan(int x, int y) {
		if ((x >= 0 && x < 11) && (y >= 0 && y < 17)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public abstract void sortuLaberinto();

	public boolean bidePosizioa(int x, int y) {
		return !matriz[x][y].blokeDu();
	}

	public void eguneratuGelaxka(int x, int y, Blokea bloke) {
		if (koordenatuBarruan(x,y)) {
			matriz[x][y] = new Gelaxka(bloke);
		}
	}
	
	public void kenduBlokea(int x, int y) {
		if (koordenatuBarruan(x,y)) {
			Gelaxka g = matriz[x][y];
			
			if (g != null && g.apurtuDaiteke()) {
				g.apurtuBlokea();
				setChanged();
				notifyObservers();
			}
		}
	}


}
