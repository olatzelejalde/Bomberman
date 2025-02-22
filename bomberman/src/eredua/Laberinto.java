package eredua;

public abstract class Laberinto {
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

	public static Laberinto getNireLaberinto() {
		if (nireLaberinto == null) {
			nireLaberinto = new Classic();
		}
		return nireLaberinto;
	}

	public Gelaxka getGelaxkaPos(int x, int y) {
		return matriz[x][y];
	}
	
	protected abstract void sortuLaberinto();

	public boolean bidePosizioa(int x, int y) {
		return !matriz[x][y].blokeDu();
	}

	public void eguneratuGelaxka(int x, int y, Bloke bloke) {
		matriz[x][y] = new Gelaxka(bloke);
	}


}
