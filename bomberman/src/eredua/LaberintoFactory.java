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
    
    public Labirinto createLaberinto(String mota){
    	if (mota == null) {
            throw new IllegalArgumentException("Mota ezin daiteke null izan");
        }
    	
    	Labirinto labirinto = null;
    	
    	switch(mota.toLowerCase()) {
    		case CLASSIC:
    			labirinto = new Classic();
    			break;
    		case SOFT:
    			labirinto = new Soft();	
    			break;
    		case EMPTY:
    			labirinto = new Empty();
    			break;
    	}
    	return labirinto;
    	
    }
    
}
