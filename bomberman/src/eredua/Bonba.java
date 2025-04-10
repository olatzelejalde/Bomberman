package eredua;

import java.util.Timer;
import java.util.TimerTask;

public abstract class Bonba {
	private int x, y;
	private boolean aktibatuta;
	private BonbaPortaera bp;
	private Timer suaTimer, eztandaTimer;

	
	public Bonba(int x, int y, BonbaPortaera bPortaera) {
		this.x = x;
		this.y = y;
		this.aktibatuta = true;
		this.bp = bPortaera;
	}
	
	// X posizioa lortu
	public int getX() {
		return x;
	}
	
	// Y posizioa lortu
	public int getY() {
		return y;
	}
	
	// X posizioan jarri
	public void setX(int x) {
		this.x = x;
	}
	
	// Y posizioan jarri
	public void setY(int y) {
		this.y = y;
	}
	
	// Bonba jarrita dagoen jakiteko
	public boolean aktibatutaDago() {
		return aktibatuta;
	}
	
	// Bonba aktibatu
	public void setAktibatuta(boolean b) {
		aktibatuta = b;
	}
    
    // Eztandarako timerra hasieratu
    public void hasiEztanda() {
    	// 3s itxaron eta sua jarri
        eztandaTimer = new Timer();
        eztandaTimer.schedule(new TimerTask() {
            @Override
            public void run() {
            	setAktibatuta(true);
                bp.eztanda(x, y); 
                kenduSua();
            }     
        }, 3000);
    }
    
    // Sua kentzeko timerra hasieratu
    public void kenduSua() {
		// 2s itxaron
	    suaTimer = new Timer();
	    suaTimer.schedule(new TimerTask() {
	        @Override
	        public void run() {
	            bp.kenduSua(x, y);
	        }
	    }, 2000);
    }

}
