package eredua;

public abstract class Bonba {
	private int x, y;
	protected boolean aktibatuta;

	
	public Bonba(int x, int y) {
		this.x = x;
		this.y = y;
		this.aktibatuta = true;
	}
	
	// Conseguir la psoicion X
	public int getX() {
		return x;
	}
	
	// Conseguir la posicion Y
	public int getY() {
		return y;
	}
	
	// Verificar si la bomba esta activada
	public boolean aktibatutaDago() {
		return aktibatuta;
	}
	
	// Metodo abstracto para explotar en Normal
	protected abstract void eztanda();
}
