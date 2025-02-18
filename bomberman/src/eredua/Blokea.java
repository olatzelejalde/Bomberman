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

    public  abstract void apurtu() {}
}
