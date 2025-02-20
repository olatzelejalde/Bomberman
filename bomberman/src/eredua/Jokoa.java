package eredua;

import java.util.List;

public class Jokoa {
    private Bomberman bomberman;
    private List<Etsaia> etsaiak;
    private boolean amaituta;

    public Jokoa(Bomberman bomberman, List<Etsaia> etsaiak) {
        this.bomberman = bomberman;
        this.etsaiak = etsaiak;
        this.amaituta = false;
    }

    public void eguneratu() {
        if (bomberman.hildaDago()) {
            bukaera(false);
        } else if (etsaiGuztiakHilda()) {
            bukaera(true);
        }
    }

    private boolean etsaiGuztiakHilda() {
        for (Etsaia etsaia : etsaiak) {
            if (etsaia.bizirikDago()) {
                return false;
            }
        }
        return true;
    }

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
