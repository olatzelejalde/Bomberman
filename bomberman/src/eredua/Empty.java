package eredua;

import java.util.Random;
import java.util.stream.IntStream;

public class Empty extends Laberinto {
    
    public Empty() {
        super();
        sortuLaberinto();
    }
    
    public void sortuLaberinto() {
        Random r = new Random();
        Gelaxka[][] matriz = super.getMatriz();
        BlokeFactory blokeFactory = BlokeFactory.getBlokeFactory();
        Jokoa jokoa = Jokoa.getJokoa();

        IntStream.range(0, 11).forEach(i -> { // altuera Java8-n
        	IntStream.range(0,17).forEach(j -> { // Zabalera Java8-n
                // Bomberman gelaxka libre batean hasiko da
                if ((i == 0 && j == 0) || (i == 0 && j == 1) || (i == 1 && j == 0)) {
                    if (i == 0 && j == 0) {
                        matriz[i][j] = new Gelaxka(null, true, false);
                    }
                    else {
                        matriz[i][j] = new Gelaxka(null, false, false);
                    }
                }
                // Beste posizioak: etsaiak edo hutsik
                else {
                    int prob = r.nextInt(100);
                    if (prob > 95 && jokoa.getEtsaiKop() < 10) {
                        matriz[i][j] = new Gelaxka(null, false, true);
                        jokoa.gehituEtsaia(new Etsaia(i,j));
                    } 
                    else {
                        matriz[i][j] = new Gelaxka(null, false, false);
                    }
                }
            });
        });
    }
    
    @Override
    public String getMota() {
        return "empty";
    }
}
