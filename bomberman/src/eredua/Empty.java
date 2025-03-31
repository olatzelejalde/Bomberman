package eredua;

import java.util.Random;

public class Empty extends Laberinto {
	private int etsaiKop;
    
    public Empty() {
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
                
                // Bloke gogorrak posizio bakoitietan
                else if (i % 2 != 0 && j % 2 != 0) {
                	Blokea blokeGog = blokeFactory.createBloke(BlokeFactory.GOGORRA);
                    matriz[i][j] = new Gelaxka(blokeGog, false);
                }
                
                // Beste posizioak: etsaiak edo hutsik
                else {
                    int prob = r.nextInt(100);
                    if (prob > 95 && etsaiKop < 10) {
                        matriz[i][j] = new Gelaxka(new Etsaia(), false);
                        etsaiKop++;
                    } 
                    else {
                        matriz[i][j] = new Gelaxka(null, false);
                    }
                }
            }
        }
    }
}
