package eredua;

import java.util.Timer;
import java.util.TimerTask;

public class Normal extends Bonba implements BonbaPortaera {
    private Timer suaTimer, eztandaTimer, tocarFuegoTimer;
    
    public Normal(int x, int y) {
        super(x, y);
    }
    
    @Override
    public void bonbaJarri(Bomberman b) {
        Jokoa j = Jokoa.getJokoa();
        j.setBonba(this);
        j.kokatuBonba();
        System.out.println("Bonba normal kokatu da. Pos: (" + getX() + "," + getY() + ")");
    }
    
    // Eztandarako timerra hasieratu
    public void hasiEztanda() {
    	// 3s itxaron eta sua jarri
        eztandaTimer = new Timer();
        eztandaTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                eztanda(); 
            }     
        }, 3000);
    }
    
    // Metodo bonbaren eztandarako
    public void eztanda() { 
    	super.setAktibatuta(true);
    	
    	int x = getX();
        int y = getY();
        
        Laberinto laberinto = Jokoa.getJokoa().getLaberinto();
        Gelaxka g = laberinto.getMatriz()[x][y];
        
        // Gelaxkatik bonba kendu
        g.setBonba(null);
        
        System.out.println("Eztanda: (" + x + "," + y + ")");
        //laberinto.kenduBonba(bonbaX, bonbaY);
        
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
            	// Bomberman momentuko posizioa gorde
                Bomberman b = Jokoa.getJokoa().getBomberman();
                int bombermanX = b.getX();
                int bombermanY = b.getY();
                
                // Egiaztatu bomberman ea sua daukan gelaxka batean dagoen
                if (laberinto.getMatriz()[bombermanX][bombermanY].suaDago()) {
                    Jokoa.getJokoa().getBomberman().hil();
                    Jokoa.getJokoa().bukaera(false);
                    this.cancel(); // Egiaztapena eten
                }
            }
        }, 0, 500);
        
        // 2s itxaron
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
