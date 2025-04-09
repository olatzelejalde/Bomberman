package eredua;

public class EztandaNormal implements BonbaPortaera {
	
	public EztandaNormal() {
		
	}

	// Metodo bonba eztanda egiteko
	public void eztanda(int x, int y) {
		Laberinto laberinto = Jokoa.getJokoa().getLaberinto();
        laberinto.getMatriz()[x][y].setBonba(null);
        
        System.out.println("Eztanda Normal: (" + x + "," + y + ")");
        
        // Sua jarri bonbaren posizioan eta ondokoetan
 		laberinto.jarriSua(x, y); // Bonbaren posizioa
 		laberinto.jarriSua(x - 1, y); // Goikoa
 		laberinto.jarriSua(x + 1, y); // Behekoa
 		laberinto.jarriSua(x, y - 1); // Ezkerrekoa
 		laberinto.jarriSua(x, y + 1); // Eskumakoa
	}

	// Sua kendu bonbaren posizioan eta ondokoetan
	public void kenduSua(int x, int y) {
		Laberinto laberinto = Jokoa.getJokoa().getLaberinto();
		laberinto.kenduSua(x, y); // Bonbaren posizioa
 		laberinto.kenduSua(x - 1, y); // Goikoa
 		laberinto.kenduSua(x + 1, y); // Behekoa
 		laberinto.kenduSua(x, y - 1); // Ezkerrekoa
 		laberinto.kenduSua(x, y + 1); // Eskumakoa
	}
}
