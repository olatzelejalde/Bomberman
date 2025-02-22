package eredua;

import java.util.List;

public class Jokoa {
    private static Jokoa instance;
    private Bomberman bomberman;
    //private List<Etsaia> etsaiak;
    private boolean amaituta;

    public Jokoa(Bomberman bomberman) {
        this.bomberman = bomberman;
        //this.etsaiak = etsaiak;
        this.amaituta = false;
    }

    public static Jokoa getInstance(Bomberman bomberman) {
        if (instance == null) {
            instance = new Jokoa(bomberman);
        }
        return instance;
    }
    public void eguneratu() {
        if (bomberman.hildaDago()) {
            bukaera(false);
        }
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

    public void bukaera(boolean irabazi) {
        amaituta = true;
        if (irabazi) {
            System.out.println("Zorionak! Irabazi duzu!");
        } else {
            System.out.println("Galdu duzu! Saiatu berriro.");
        }
        System.exit(0);
    }
}
