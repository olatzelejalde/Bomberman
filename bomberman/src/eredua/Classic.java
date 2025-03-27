package eredua;

import java.util.Random;

public class Classic extends Laberinto {
	
	public Classic() {
		super();
	}
	
	// Labirintoa sortzeko metodoa
	public void sortuLaberinto() {
	    Random r = new Random();

	    for (int i = 0; i < 11; i++) { // altuera
	        for (int j = 0; j < 17; j++) { // zabalera
	        	// Bombermana gelaxka libre batean hasiko da
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
	                matriz[i][j] = new Gelaxka(new BlokeGogorra(), false);
	            }
	            // Beste posizioak: bloke bigunak edo hutsik
	            else {
	                int prob1 = r.nextInt(100); // 0-99
	                if (prob1 < 40) { 
	                	// Bloke bigunen probabilitatea 40% 
	                    matriz[i][j] = new Gelaxka(new BlokeBiguna(), false);
	                    Laberinto.getLaberinto().gehituSuntsigarri();
	                } else { 
	                	// Gelaxka hutsaren probabilitatea 60%
	                    matriz[i][j] = new Gelaxka(null, false);
	                }
	            }
	        }
	    }
	}
}
