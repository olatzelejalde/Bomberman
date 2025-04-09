package eredua;

public class LaberintoFactory {
	private static LaberintoFactory nLF;
    
    
    private LaberintoFactory() {
    	
    }
    
    public static LaberintoFactory getLF() {
        if (nLF == null) {
        	nLF = new LaberintoFactory();
        }
        return nLF;
    }
    
    public Laberinto createLaberinto(String mota){
    	if (mota == null) {
            throw new IllegalArgumentException("Mota ezin daiteke null izan");
        }
    	
    	Laberinto laberinto = null;
    	
    	switch(mota.toLowerCase()) {
    		case "classic":
    			laberinto = new Classic();
    			break;
    		case "soft":
    			laberinto = new Soft();	
    			break;
    		case "empty":
    			laberinto = new Empty();
    			break;
    	}
    	return laberinto;
    	
    }
    
}
