package eredua;

public class LaberintoFactory {
	private static LaberintoFactory nireLaberintoFactory;
    private static final String CLASSIC = "classic";
    private static final String SOFT = "soft";
    private static final String EMPTY = "empty";
    
    
    private LaberintoFactory() {
    	
    }
    
    public static LaberintoFactory getLaberintoFactory() {
        if (nireLaberintoFactory == null) {
        	nireLaberintoFactory = new LaberintoFactory();
        }
        return nireLaberintoFactory;
    }
    
    public Laberinto createLaberinto(String mota){
    	if (mota == null) {
            throw new IllegalArgumentException("Mota ezin daiteke null izan");
        }
    	
    	Laberinto laberinto = null;
    	
    	switch(mota.toLowerCase()) {
    		case CLASSIC:
    			laberinto = new Classic();
    			break;
    		case SOFT:
    			laberinto = new Soft();	
    			break;
    		case EMPTY:
    			laberinto = new Empty();
    			break;
    	}
    	return laberinto;
    	
    }
    
}
