package eredua;

public abstract class Bonba {
	private int x, y;
	protected boolean aktibatuta;

	public Bonba(int x, int y) {
		this.x = x;
		this.y = y;
		this.aktibatuta = true;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public boolean aktibatutaDago() {
		return aktibatuta;
	}
	
	protected abstract void eztanda();
}
