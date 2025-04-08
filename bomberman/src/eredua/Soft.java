package eredua;

import java.util.Random;

public class Soft extends Laberinto {
    
    public Soft() {
        super();
        sortuLaberinto();
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
                        matriz[i][j] = new Gelaxka(null, true, false);
                    }
                    else {
                        matriz[i][j] = new Gelaxka(null, false, false);
                    }
                }
                
                // Beste posizioak
                else {
                    int prob1 = r.nextInt(100);
                    if (prob1 < 40) {
                        // Bloke biguna (40% probabilitatea)
                    	Bloke blokeBig = blokeFactory.createBloke(BlokeFactory.getBlokeFactory().BIGUNA);
                        matriz[i][j] = new Gelaxka(blokeBig, false, false);
                        gehituSuntsigarri();
                    } 
                    
                    //cuando el etsaia este hecho, cambiaremos este else por el comentado debajo
                    else {
                    	matriz[i][j] = new Gelaxka(null, false, false);
                    }
                    /*
                    else {
                        // Bigarren probabilitatea etsaientzat
                        int prob2 = r.nextInt(100);
                        if (prob2 > 0 && getEtsaiak() < 8) {
                            matriz[i][j] = new Gelaxka(new Etsaia(), false, true);
                            gehituEtsaia();
                        } else {
                            matriz[i][j] = new Gelaxka(null, false, false);
                        }
                    }*/
                }
            }
        }
    }
}
