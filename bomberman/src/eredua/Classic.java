package eredua;

import java.util.Random;

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
	            	Bloke blokeGog = blokeFactory.createBloke(BlokeFactory.getBlokeFactory().GOGORRA);
                    matriz[i][j] = new Gelaxka(blokeGog, false, false);
	            }
	            
	            // Beste posizioak: bloke bigunak, etsaiak edo hutsik
                else {
                    int prob1 = r.nextInt(100); // 0-99
                    // 40% probabilitate bloke biguna
                    if (prob1 < 40) { 
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
                        int prob2 = r.nextInt(100);
                        // 10% probabilitate etsaientzat
                        if (prob2 > 90 && getEtsaiak() < 6) {
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
