package eredua;

//Bloke Gogorra: Ez suntsigarria den bloke bat
public class BlokeGogorra extends Blokea {
	public BlokeGogorra() {
		super(false); // Bloke gogorrak ez dira suntsigarriak
	}

	@Override
    public boolean blokeBigunaDa() {
        return false;
    }
}
