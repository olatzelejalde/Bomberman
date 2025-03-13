package eredua;

public class Normal extends Bonba {
	
	
	public Normal(int x, int y) {
		super(x,y);
	}
	
	// Metodo para la explosion de la bomba
	@Override
	public void eztanda() {
		aktibatuta = false;
		
		int x = getX();
		int y = getY();
		
		Laberinto laberinto = Jokoa.getJokoa().getLaberinto();
		
		// Marcar fuego en la posición de la bomba y sus alrededores si están vacíos
		laberinto.jarriSua(x, y); //posicion original de bomba
		laberinto.jarriSua(x - 1, y); //arriba
		laberinto.jarriSua(x + 1, y); //abajo
		laberinto.jarriSua(x, y - 1); //izquierda
		laberinto.jarriSua(x, y + 1); //derecha
		
		// Eliminar la bomba de la celda
		laberinto.getMatriz()[x][y].setBonba(null);
		
		// Esperar 2s y luego limpiar el fuego
        new Thread(() -> {
            try {
                Thread.sleep(2000);
                laberinto.kenduSua(x, y);
                laberinto.kenduSua(x - 1, y);
                laberinto.kenduSua(x + 1, y);
                laberinto.kenduSua(x, y - 1);
                laberinto.kenduSua(x, y + 1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
	}
}
