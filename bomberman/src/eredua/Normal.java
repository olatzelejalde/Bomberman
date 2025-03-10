package eredua;

public class Normal extends Bonba {
	
	public Normal(int x, int y) {
		super(x,y);
	}
	
	@Override
	public void eztanda() {
		if (!aktibatuta) {
			return;
		}
		aktibatuta = false;
		
		int x = getX();
		int y = getY();
		
		Jokoa jokoa = Jokoa.getJokoa();
		Laberinto laberinto = jokoa.getLaberinto();
		
		// Marcar fuego en la posición de la bomba y sus alrededores si están vacíos
		laberinto.jarriSua(x, y); //posicion original de bomba
		laberinto.jarriSua(x + 1, y); //abajo
		laberinto.jarriSua(x, y - 1); //izquierda
		laberinto.jarriSua(x, y + 1); //derecha
		
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
