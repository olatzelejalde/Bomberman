package eredua;

import java.util.Random;

public class Classic extends Laberinto {
	private int etsaiKop;
	
	public Classic() {
		super();
		etsaiKop = 0;
		
	}
	
	// Metodoa laberintoa sortzeko 
	public void sortuLaberinto() {
	    Random r = new Random();
	    Gelaxka[][] matriz = super.getMatriz();
	    BlokeFactory blokeFactory = BlokeFactory.getBlokeFactory();

	    for (int i = 0; i < 11; i++) { // altuera
	        for (int j = 0; j < 17; j++) { // zabalera
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
	            	Blokea blokeGog = blokeFactory.createBloke(BlokeFactory.GOGORRA);
                    matriz[i][j] = new Gelaxka(blokeGog, false, false);
	            }
	            
	            // Beste posizioak: bloke bigunak, etsaiak edo hutsik
                else {
                    int prob1 = r.nextInt(100); // 0-99
                    // 40% probabilitate bloke biguna
                    if (prob1 < 40) { 
                    	Blokea blokeBiguna = blokeFactory.createBloke(BlokeFactory.BIGUNA);
                        matriz[i][j] = new Gelaxka(blokeBiguna, false, false);
                        gehituSuntsigarri();
                    }
                    else {
                        int prob2 = r.nextInt(100);
                        // 10% probabilitate etsaientzat
                        if (prob2 > 90 && etsaiKop < 6) {
                        	// Birpasatu 
                            matriz[i][j] = new Gelaxka(new Etsaia(), false, true);
                            etsaiKop++;
                        } else {
                            matriz[i][j] = new Gelaxka(null, false, false);
                        }
                    }
                }
	        }
	    }
	}
}
