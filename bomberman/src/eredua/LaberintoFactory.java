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
    	
    	switch(mota.toLowerCase()) {
    		case CLASSIC:
    			return new Classic();
    		case SOFT:
    			return new Soft();	
    		case EMPTY:
    			return new Empty();	
    		default:
                throw new IllegalArgumentException("Laberinto mota ezezaguna: " + mota);	
    	}
    	
    }
    
}
