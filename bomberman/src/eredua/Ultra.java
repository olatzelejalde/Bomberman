package eredua;

import java.util.Timer;
import java.util.TimerTask;

public class Ultra extends Bonba{
	private Timer suaTimer, eztandaTimer, tocarFuegoTimer;
	
	
	public Ultra(int x, int y) {
		super(x,y);
	}
	
	// Leherketarako timerra hasieratu
	@Override
	public void hasiEztanda() {
		//3 segundo itxaron eta sua jarri
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
		setAktibatuta(false);
		int h = 1;					// Bonbaren hedapena
		
		int x = getX();
		int y = getY();
		
		Laberinto laberinto = Laberinto.getLaberinto();
		Gelaxka g = laberinto.getMatriz()[x][y];
		
		// Gelaxkatik bonba kendu 
		g.setBonba(null);
		
		while (h <= 20) {
			laberinto.jarriSua(x, y); // Bonbaren posizioa
			laberinto.jarriSua(x + h, y);
			laberinto.jarriSua(x, y + h);
			laberinto.jarriSua(x, y + h);	
			
			h ++;
		}
		
		// 500ms-ro egiaztatu bomberman-a ez duela sua ukitu
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
                    // Egiaztapena eten
                    this.cancel();
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
