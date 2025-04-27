package eredua;

import java.util.Random;
import java.util.stream.IntStream;

public class Classic extends Laberinto {
	
	public Classic() {
		super();
		sortuLaberinto();
	}
	
	// Metodoa laberintoa sortzeko 
	public void sortuLaberinto() {
	    Random r = new Random();
	    Gelaxka[][] matriz = super.getMatriz();
	    BlokeFactory blokeFactory = BlokeFactory.getBlokeFactory();
	    Jokoa jokoa = Jokoa.getJokoa();
	    
	    IntStream.range(0, 11).forEach(i -> { // altuera Java8-n
	        IntStream.range(0,17).forEach(j -> { // zabalera Java8-n
	        	// Bomberman gelaxka libre batean hasiko da
	            if ((i == 0 && j == 0) || (i == 0 && j == 1) || (i == 1 && j == 0)) {
	                if (i == 0 && j == 0) {
	                	matriz[i][j] = new Gelaxka(null, true, false);
	                }
	                else {
		                matriz[i][j] = new Gelaxka(null, false, false);
	                }
	            }
	            
	            // Bloke gogorrak posizio bakoitietan
	            else if (i % 2 != 0 && j % 2 != 0) {
	            	Bloke blokeGog = blokeFactory.createBloke("gogorra");
                    matriz[i][j] = new Gelaxka(blokeGog, false, false);
	            }
	            
	            // Beste posizioak: bloke bigunak, etsaiak edo hutsik
                else {
                    int prob1 = r.nextInt(100); // 0-99
                    // 40% probabilitate bloke biguna
                    if (prob1 > 40) { 
                    	Bloke blokeBig = blokeFactory.createBloke("biguna");
                        matriz[i][j] = new Gelaxka(blokeBig, false, false);
                        gehituSuntsigarri();
                    }
                    else {
                        int prob2 = r.nextInt(100);
                        // 10% probabilitate etsaientzat
                        if (prob2 > 90 && jokoa.getEtsaiKop() < 6) {
                            matriz[i][j] = new Gelaxka(null, false, true);
                            jokoa.gehituEtsaia(new Etsaia(i,j));
                        }
                        else {
                            matriz[i][j] = new Gelaxka(null, false, false);
                        }
                    }
                }
	        
	        });
	    });
	}
	@Override
    public String getMota() {
        return "classic";
    }
}
