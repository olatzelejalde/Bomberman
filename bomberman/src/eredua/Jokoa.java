package eredua;

import java.util.List;
import java.util.Observable;

public class Jokoa extends Observable{
    private static Jokoa instance;
    private Bomberman bomberman;
    //private List<Etsaia> etsaiak;
    private boolean amaituta;

    public Jokoa() {
        //this.etsaiak = etsaiak;
        this.amaituta = false;
    }

    public static Jokoa getJokoa() {
        if (jokoa == null) {
            jokoa = new Jokoa();
        }
        return jokoa;
    }
    
    public void hasiJokoa(Bomberman pBomberman){
        this.bomberman = pBomberman;
    }
    
    public Bomberman getBomberman(){
        return this.bomberman;
    }
    
    public void eguneratu() {
        if (bomberman.hildaDago()) {
            bukaera(false);
        }
    }

    public void bukaera(boolean irabazi) {
        amaituta = true;
        if (irabazi) {
            System.out.println("Zorionak! Irabazi duzu!");
        } else {
            System.out.println("Galdu duzu! Saiatu berriro.");
        }
        setChanged();
        notifyObersers();
        System.exit(0);
    }

    /*
    private boolean etsaiGuztiakHilda() {
        for (Etsaia etsaia : etsaiak) {
            if (etsaia.bizirikDago()) {
                return false;
            }
        }
        return true;
    }*/
}
