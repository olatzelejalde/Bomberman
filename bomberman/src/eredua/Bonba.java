package eredua;

public abstract class Bonba {
	private int x, y;
	protected boolean aktibatuta;

	// Eraikitzailea
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
	
	// Bonba jarrita dagoen jakiteko
	public boolean aktibatutaDago() {
		return aktibatuta;
	}
	
	// Metodo abstraktua Normal klasean jartzeko
	protected abstract void hasiEztanda();
}
