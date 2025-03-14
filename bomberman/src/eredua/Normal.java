package eredua;

import java.util.Timer;
import java.util.TimerTask;

public class Normal extends Bonba {
	private Timer suaTimer, eztandaTimer, tocarFuegoTimer;
	
	
	public Normal(int x, int y) {
		super(x,y);
	}
	
	// Metodo para iniciar el timer de la explosion
    @Override
    public void hasiEztanda() {
    	// Esperar 3s y luego poner el fuego
        eztandaTimer = new Timer();
        eztandaTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                eztanda(); 
            }     
        }, 3000);
    }
	
	// Metodo para la explosion de la bomba
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
		
		// Verificar cada 500ms si el bomberman toca el fuego
		tocarFuegoTimer = new Timer();
		tocarFuegoTimer.scheduleAtFixedRate(new TimerTask() {
			@Override
            public void run() {
                // Obtener la posicion actual del bomberman
                int bombermanX = Jokoa.getJokoa().getBomberman().getX();
                int bombermanY = Jokoa.getJokoa().getBomberman().getY();

                // Verificar si el bomberman esta en una celda con fuego
                if (laberinto.getMatriz()[bombermanX][bombermanY].suaDago()) {
                    Jokoa.getJokoa().getBomberman().hil();
                    Jokoa.getJokoa().bukaera(false); 
                    this.cancel(); // Detener la verificación
                }
            }
        }, 0, 500); 
		
		
		// Esperar 2s y luego limpiar el fuego
        suaTimer = new Timer();
        suaTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                laberinto.kenduSua(x, y);
                laberinto.kenduSua(x - 1, y);
                laberinto.kenduSua(x + 1, y);
                laberinto.kenduSua(x, y - 1);
                laberinto.kenduSua(x, y + 1);
                tocarFuegoTimer.cancel();
            }
        }, 2000); 
	}
	
	
}
