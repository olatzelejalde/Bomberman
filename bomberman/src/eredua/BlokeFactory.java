package eredua;

public class BlokeFactory {
	private static BlokeFactory nireBlokeFactory;
	public static final String BIGUNA = "biguna";
    public static final String GOGORRA = "gogorra";
    
   
    private BlokeFactory() {
    	
    }
    
    public static BlokeFactory getBlokeFactory() {
        if (nireBlokeFactory == null) {
        	nireBlokeFactory = new BlokeFactory();
        }
        return nireBlokeFactory;
    }
    
    public Blokea createBloke(String mota) {
        if (mota == null) {
            throw new IllegalArgumentException("Mota ezin daiteke null izan");
        }
        
        switch(mota.toLowerCase()) {
            case BIGUNA:
                return new BlokeBiguna();
            case GOGORRA:
                return new BlokeGogorra();
            default:
                throw new IllegalArgumentException("Bloke mota ezezaguna: " + mota);
        }
    }
    
}
