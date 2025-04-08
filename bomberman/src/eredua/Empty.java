package eredua;

import java.util.Random;

public class Empty extends Laberinto {
    
    public Empty() {
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
                
                //cuando el etsaia este hecho, cambiaremos este else por el comentado debajo
                else {
                    matriz[i][j] = new Gelaxka(null, false, false);
                }
                /*
                // Beste posizioak: etsaiak edo hutsik
                else {
                    int prob = r.nextInt(100);
                    if (prob > 95 && getEtsaiak() < 10) {
                        matriz[i][j] = new Gelaxka(new Etsaia(), false, true);
                        gehituEtsaia();
                    } 
                    else {
                        matriz[i][j] = new Gelaxka(null, false, false);
                    }
                }*/
            }
        }
    }
}
