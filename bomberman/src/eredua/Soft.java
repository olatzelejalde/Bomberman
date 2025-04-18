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
        Jokoa jokoa = Jokoa.getJokoa();

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
                    if (prob1 > 40) {
                        // Bloke biguna (40% probabilitatea)
                    	Bloke blokeBig = blokeFactory.createBloke("biguna");
                        matriz[i][j] = new Gelaxka(blokeBig, false, false);
                        gehituSuntsigarri();
                    } 
                    else {
                        // Bigarren probabilitatea etsaientzat
                        int prob2 = r.nextInt(100);
                        if (prob2 > 0 && jokoa.getEtsaiKop() < 8) {
                            matriz[i][j] = new Gelaxka(null, false, true);
                            jokoa.gehituEtsaia(new Etsaia(i,j));
                        } 
                        else {
                            matriz[i][j] = new Gelaxka(null, false, false);
                        }
                    }
                }
            }
        }
    }
    
    @Override
    public String getMota() {
        return "soft";
    }
}
