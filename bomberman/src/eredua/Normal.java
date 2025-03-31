package eredua;

import java.util.Timer;
import java.util.TimerTask;

public class Normal extends Bonba {
	private Timer suaTimer, eztandaTimer, tocarFuegoTimer;
	
	
	public Normal(int x, int y) {
		super(x,y);
	}
	
	// Leherketarako timerra hasieratu
    @Override
    public void hasiEztanda() {
    	// 3 segundo itxaron eta sua jarri
        eztandaTimer = new Timer();
        eztandaTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                eztanda(); 
            }     
        }, 3000);
    }
	
	// Bonbaren eztanda kontrolatzeko metodoa
	public void eztanda() {
		super.aktibatutaDago();
		
		int x = getX();
		int y = getY();
		
		Laberinto laberinto = LaberintoFactory.getLaberintoFactory().getLaberinto();
		Gelaxka g = laberinto.getMatriz()[x][y];
		
		// Gelaxkatik bonba kendu
		g.setBonba(null);
				
		// Sua jarri bonbaren posizioan eta ondokoetan
		laberinto.jarriSua(x, y); // Bonbaren posizioa
		laberinto.jarriSua(x - 1, y); // Goikoa
		laberinto.jarriSua(x + 1, y); // Behekoa
		laberinto.jarriSua(x, y - 1); // Ezkerrekoa
		laberinto.jarriSua(x, y + 1); // Eskumakoa
	
		
		// 500ms-ro egiaztatu bomberman ez duela sua ukitu
		tocarFuegoTimer = new Timer();
		tocarFuegoTimer.scheduleAtFixedRate(new TimerTask() {
			@Override
            public void run() {
                // Bomberman-aren momentuko posizioa gorde  
                int bombermanX = Jokoa.getJokoa().getBomberman().getX();
                int bombermanY = Jokoa.getJokoa().getBomberman().getY();

                // Egiaztatu bomberman ea sua daukan gelaxka batean dagoen
                if (laberinto.getMatriz()[bombermanX][bombermanY].suaDago()) {
                    Jokoa.getJokoa().getBomberman().hil();
                    Jokoa.getJokoa().bukaera(false);
                    this.cancel(); // Egiaztapena eten
                }
            }
        }, 0, 500); 
		
		// 2 segundo itxaron
        suaTimer = new Timer();
        suaTimer.schedule(new TimerTask() {
            @Override
            public void run() {
            	// Sua kendu
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
