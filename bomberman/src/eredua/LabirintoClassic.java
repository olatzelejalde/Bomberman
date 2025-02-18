package eredua;

import java.util.Random;

public class Classic extends Laberinto {
	public Classic() {
		super();
		hasieratuClassic();
	}
	
	public void hasieratuClassic() {
		Random r = new Random();
		
		for (int i = 0; i < 11; i++) { //altuera
			for (int j = 0; j < 17; j++) { //zabalera
				// Bomberman posizioa hasieran
				if ((i == 0 && j == 0) || (i == 0 && j == 1) || (i == 1 && j == 0)) {
					getGelaxka()[i][j] = new Gelaxka("hutsik", false);
				}
				// Bloke gogorrak posizio inparretan
				else if (i % 2 != 0 && j % 2 != 0) {
					getGelaxka()[i][j] = new Gelaxka("suntsiezina", true);
				}
				// Bloke bigunak 40% probabilitatearekin
				else if (r.nextInt(100) < 40) {
					getGelaxka()[i][j] = new Gelaxka("apurkorra", false);
				}
				// Posizio hutsik
				else {
					getGelaxka()[i][j] = new Gelaxka("hutsik", false);
				}
			}
		}
	}

}
