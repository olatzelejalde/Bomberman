package eredua;

public class Etsaia {
	private int x, y;
    private boolean bizirik;

    public Etsaia(int x, int y) {
        this.x = x;
        this.y = y;
        this.bizirik = true;
    }
    
    public boolean bizirikDago() {
        return bizirik;
    }

    public void hil() {
        if (bizirik) {
            bizirik = false;
            System.out.println("Etsaia hil da!");
            Jokoa.getInstance().eguneratu();
    }
}
