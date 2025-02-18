package eredua;

public abstract class Laberinto {
	private Gelaxka[][] gelaxkak;
	private int zabalera = 17;
	private int altuera = 11;
	
	public Laberinto() {
		this.gelaxkak = new Gelaxka[zabalera][altuera];
	}
	
	public Gelaxka getGelaxka(int x, int y) {
		return gelaxkak[x][y];
	}

	public Gelaxka[][] getGelaxkak() {
		return gelaxkak;
	}

	public boolean bidePosizioa(int x, int y) {
		return gelaxkak[x][y].bideaDago();
	}

	public void eguneratuGelaxka(int x, int y, String mota) {
		switch (mota) {
			case "hutsik":
				gelaxkak[x][y] = new Gelaxka("hutsik", false);
				break;
			case "suntsiezina":
				gelaxkak[x][y] = new Gelaxka("suntsiezina", true);
				break;	
			case "apurkorra":
				gelaxkak[x][y] = new Gelaxka("apurkorra", false);
				break;
			case "bonba":
				gelaxkak[x][y].bonbaJarri();
				break;	
			case "sua":
				gelaxkak[x][y].suaJarri();
				break;		
		}
	}


}

