package eredua;

import java.util.Random;

public class Soft extends Laberinto {
    private int etsaiKop;
    
    public Soft() {
        super();
        etsaiKop = 0;
    }
    
    public void sortuLaberinto() {
        Random r = new Random();
        Gelaxka[][] matriz = super.getMatriz();
        BlokeFactory blokeFactory = BlokeFactory.getBlokeFactory();

        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 17; j++) {
                // Bomberman gelaxka libre batean hasiko da
                if ((i == 0 && j == 0) || (i == 0 && j == 1) || (i == 1 && j == 0)) {
                    if (i == 0 && j == 0) {
                        matriz[i][j] = new Gelaxka(null, true);
                    }
                    else {
                        matriz[i][j] = new Gelaxka(null, false);
                    }
                }
                
                // Beste posizioak
                else {
                    int prob1 = r.nextInt(100);
                    if (prob1 < 40) {
                        // Bloke biguna (40% probabilitatea)
                    	Blokea blokeBiguna = blokeFactory.createBloke(BlokeFactory.BIGUNA);
                        matriz[i][j] = new Gelaxka(blokeBiguna, false);
                        gehituSuntsigarri();
                    } 
                    else {
                        // Bigarren probabilitatea etsaientzat
                        int prob2 = r.nextInt(100);
                        if (prob2 > 0 && etsaiKop < 8) {
                            matriz[i][j] = new Gelaxka(new Etsaia(), false);
                            etsaiKop++;
                        } else {
                            matriz[i][j] = new Gelaxka(null, false);
                        }
                    }
                }
            }
        }
    }
}
