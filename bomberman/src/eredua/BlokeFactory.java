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
    
    public Bloke createBloke(String mota) {
        if (mota == null) {
            throw new IllegalArgumentException("Mota ezin daiteke null izan");
        }
        
        Bloke bloke = null;
        
        switch(mota.toLowerCase()) {
            case "biguna":
                bloke = new BlokeBiguna();
                break;
            case "gogorra":
                bloke = new BlokeGogorra();
                break;
        }
        return bloke;
    }
    
}
