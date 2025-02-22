package eredua;

public abstract class Laberinto {
	private static Laberinto nireLaberinto;
	protected Gelaxka[][] matriz;
	private Bomberman bomberman;
	
	protected Laberinto() {
		this.matriz = new Gelaxka[11][17];
		for (int i = 0; i < 11; i++) {
			for (int j = 0; j < 17; j++) {
				matriz[i][j] = new Gelaxka("hutsik");
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
		if (x >= 0 && x < 11 && y >= 0 && y < 17) {
	        	return matriz[x][y];
	    	}
			return null;
	}

	public boolean bidePosizioa(int x, int y) {
		return matriz[x][y].bideaDago();
	}

	public void eguneratuGelaxka(int x, int y, String mota) {
		switch (mota) {
			case "hutsik":
				matriz[x][y] = new Gelaxka("hutsik");
				break;
			case "gogorra":
				matriz[x][y] = new Gelaxka("gogorra");
				break;	
			case "biguna":
				matriz[x][y] = new Gelaxka("biguna");
				break;
			case "bonba":
				bomberman.bonbaJarri(getNireLaberinto());
				break;	
			case "sua":
				matriz[x][y].suaJarri();
				break;		
		}
	}


}
