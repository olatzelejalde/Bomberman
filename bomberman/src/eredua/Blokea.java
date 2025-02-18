package eredua;


public abstract class Blokea {
    protected boolean suntsiezina;
    protected boolean apurtuta;

    public Blokea(boolean suntsiezina) {
        this.suntsiezina = suntsiezina;
        this.apurtuta = false;
    }

    public boolean suntsiezinaDa() {
        return suntsiezina;
    }

    public boolean apurtutaDago() {
        return apurtuta;
    }

    public void apurtu() {
        if (!suntsiezina) {
            apurtuta = true;
            System.out.println("Blokea apurtu da!");
        } else {
            System.out.println("Ezin da bloke hau apurtu.");
        }
    }
}
