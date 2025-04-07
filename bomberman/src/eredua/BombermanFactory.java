package eredua;

public class BombermanFactory {
	private static BombermanFactory nBF;
	
	private BombermanFactory() {
		
	}

	public static BombermanFactory getBF() {
		if (nBF == null) {
			nBF = new BombermanFactory();
		}
		return nBF;
	}
	
	public Bomberman sortuBomberman(String pMota) {
		switch(pMota) {
		case "White":
			return new White(0, 0, 10);
		
		case "Black":
			return new Black(0, 0, 0);
		
		default:
			return new White(0, 0, 10);
		}
			
	}
}
