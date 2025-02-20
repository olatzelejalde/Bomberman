package eredua;

public abstract class Laberinto {
	private static Laberinto nireLaberinto;
	protected Gelaxka[][] matriz;
	
	protected Laberinto() {
		this.matriz = new Gelaxka[11][17];
		for (int i = 0; i < 11; i++) {
			for (int j = 0; j < 17; j++) {
				matriz[i][j] = new Gelaxka();
			}
		}
	}

	public static Laberinto getNireLaberinto() {
		if (nireLaberinto == null) {
			nireLaberinto = new Laberinto();
		}
		return nireLaberinto;
	}

	public Gelaxka getGelaxkaPos(int x, int y) {
		return matriz[x][y];
	}

	public boolean bidePosizioa(int x, int y) {
		return matriz[x][y].bideaDago();
	}

	public void eguneratuGelaxka(int x, int y, String mota) {
		switch (mota) {
			case "hutsik":
				matriz[x][y] = new Gelaxka("hutsik", false);
				break;
			case "gogorra":
				matriz[x][y] = new Gelaxka("gogorra", true);
				break;	
			case "biguna":
				matriz[x][y] = new Gelaxka("biguna", false);
				break;
			case "bonba":
				matriz[x][y].bonbaJarri();
				break;	
			case "sua":
				matriz[x][y].suaJarri();
				break;		
		}
	}


}
