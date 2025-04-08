package eredua;

public abstract class Bonba implements BonbaPortaera{
	private int x, y;
	private boolean aktibatuta;

	public Bonba(int x, int y) {
		this.x = x;
		this.y = y;
		this.aktibatuta = true;
	}
	
	// X posizioa lortu
	public int getX() {
		return x;
	}
	
	// Y posizioa lortu
	public int getY() {
		return y;
	}
	
	// X posizioan jarri
	public void setX(int x) {
		this.x = x;
	}
	
	// Y posizioan jarri
	public void setY(int y) {
		this.y = y;
	}
	
	// Bonba jarrita dagoen jakiteko
	public boolean aktibatutaDago() {
		return aktibatuta;
	}
	
	public void setAktibatuta(boolean b) {
		aktibatuta = b;
	}
	
	// Métodos del interfaz BonbaPortaera
    public abstract void bonbaJarri(Bomberman b);
    public abstract void hasiEztanda();

}
