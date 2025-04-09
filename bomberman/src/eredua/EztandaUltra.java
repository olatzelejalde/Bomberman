package eredua;

public class EztandaUltra implements BonbaPortaera {
	
	public EztandaUltra() {
		
	}

	// Metodo bonba eztanda egiteko
	public void eztanda(int x, int y) {
		Laberinto laberinto = Jokoa.getJokoa().getLaberinto();
        laberinto.getMatriz()[x][y].setBonba(null);
        
        System.out.println("Eztanda Ultra: (" + x + "," + y + ")");
        
        // Sua jarri bonbaren zutabe osoan
        for (int zutabe = 0; zutabe < 17; zutabe++) {
            laberinto.jarriSua(x, zutabe);
        }
        
        // Sua jarri bonbaren lerro osoan
        for (int lerro = 0; lerro < 11; lerro++) {
            laberinto.jarriSua(lerro, y);
        }
	}

	// Sua kendu bonbaren posizioan eta ondokoetan
	public void kenduSua(int x, int y) {
		Laberinto laberinto = Jokoa.getJokoa().getLaberinto();
		
		// Sua jarri bonbaren zutabe osoan
        for (int zutabe = 0; zutabe < 17; zutabe++) {
            laberinto.kenduSua(x, zutabe);
        }
        
        // Sua jarri bonbaren lerro osoan
        for (int lerro = 0; lerro < 11; lerro++) {
            laberinto.kenduSua(lerro, y);
        }
	}
}
