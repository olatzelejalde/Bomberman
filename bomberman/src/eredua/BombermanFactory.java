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
    
    public Bomberman createBomberman(String mota) {
        if (mota == null) {
            throw new IllegalArgumentException("Mota ezin daiteke null izan");
        }
        
        Bomberman b = null;
        
        switch(mota.toLowerCase()) {
            case "white":
                b = new White(0,0);
                break;
            case "black":
            	b = new Black(0,0);
                break;
        }
        return b;
    }
}

