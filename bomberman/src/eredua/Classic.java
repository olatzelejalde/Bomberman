
package eredua;

import java.util.Random;

public class Classic extends Laberinto {
	
	public Classic() {
		super();
	}
	
	protected void sortuLaberinto() {
		Random r = new Random();
		
		for (int i = 0; i < 11; i++) { //altuera
			for (int j = 0; j < 17; j++) { //zabalera
				// Bomberman posizioa hutsik hasieratu
				if ((i == 0 && j == 0) || (i == 0 && j == 1) || (i == 1 && j == 0)) {
					matriz[i][j] = new Gelaxka(null);
				}
				// Bloke gogorrak posizio bakoitietan
				else if (i % 2 != 0 && j % 2 != 0) {
					matriz[i][j] = new Gelaxka(new Blokea(false));
				}
				// Beste gelaxkak
				else {
					int prob1 = r.nextInt(100); // 0-100
					// %40 baino handiago bloke biguna
					if (prob1 > 40) {
						matriz[i][j] = new Gelaxka(new Blokea(true));
					}
					else {
						// %90 baino txikiagoa hutsik
						int prob2 = r.nextInt(100); // 0-100
						if (prob2 < 90) {
							// Hurrengorako etsaia jartzeko
							matriz[i][j] = new Gelaxka(null);
						}
					}
				}
			}
		}
	}

}
