package eredua;

import java.util.Random;

public class Classic extends Laberinto {
	
	
	public Classic() {
		super();
	}
	
	// Metodo para crear el laberinto
	public void sortuLaberinto() {
	    Random r = new Random();

	    for (int i = 0; i < 11; i++) { // altura
	        for (int j = 0; j < 17; j++) { // anchura
	            // Bomberman inicia en una zona libre
	            if ((i == 0 && j == 0) || (i == 0 && j == 1) || (i == 1 && j == 0)) {
	                matriz[i][j] = new Gelaxka(null, null);
	                if (i == 0 && j == 0) {
	                	matriz[i][j] = new Gelaxka(null, Jokoa.getJokoa().getBomberman());
	                }
	            }
	            // Bloques duros en posiciones impares
	            else if (i % 2 != 0 && j % 2 != 0) {
	                matriz[i][j] = new Gelaxka(new Blokea(false), null);
	            }
	            // Otras posiciones: bloque blando o vacío
	            else {
	                int prob1 = r.nextInt(100); // 0-99
	                if (prob1 < 40) { // 40% de probabilidad de bloque blando
	                    matriz[i][j] = new Gelaxka(new Blokea(true), null);
	                    Laberinto.getLaberinto().gehituSuntsigarri();
	                } else { // 60% de probabilidad de celda vacía
	                    matriz[i][j] = new Gelaxka(null, null);
	                }
	            }
	        }
	    }
	}


}
