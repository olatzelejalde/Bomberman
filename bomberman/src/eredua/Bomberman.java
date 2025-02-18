package eredua;

public class Bomberman {
	private int x, y;
	private boolean bizirik;
	private int bonbaKop;
	
	public Bomberman(int x, int y) {
		this.x = 0;
		this.y = 0;
		this.bizirik = true;
		this.bonbaKop = 0;
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
	
	public void mugitu(int newXx, int newY, Laberinto laberinto) {
		// Laberintoaren limiteen barruan
		if ((newX >= 0 && newX < 11) && (newY >= 0 && newY < 17)) {
			// Posizio berrian bidea dagoen konprobatu
			if (laberinto.bidePosizioa(newX, newY)) {
				this.x = newX;
				this.y = newY;
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
		
	}
}
