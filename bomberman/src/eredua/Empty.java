package eredua;

import java.util.Random;

public class Empty extends Laberinto {
    
    public Empty() {
        super();
    }
    
    public void sortuLaberinto() {
        Random r = new Random();
        Gelaxka[][] matriz = super.getMatriz();
        BlokeFactory blokeFactory = BlokeFactory.getBlokeFactory();

        // Bomberman (0,0) gelaxkan jarri
        matriz[0][0] = new Gelaxka(null, true, false);
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 17; j++) {
                if (Math.random() > 0.95 && getEtsaiak() < 10) {
                	// Birpasatu Gelaxka eraikitzailea
                	// matriz[i][j] = new Gelaxka(null, false, true);
                	matriz[i][j] = new Gelaxka(new Etsaia(), false, true);
                	gehituEtsaia();
                }
            }
        }
    }
}
