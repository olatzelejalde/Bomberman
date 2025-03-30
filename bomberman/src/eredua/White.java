package eredua;

import java.util.Timer;
import java.util.TimerTask;

public class White extends Bomberman {
	private boolean bonbaItxaroten;
	private Timer bonbaTimer;
	
	// Eraikitzailea
	public White(int x, int y, int bonbaKop) {
		super(x, y, bonbaKop);
		this.bonbaItxaroten = false;		
	}
	
	// Bomberman-a dituen bonba kopurua lortu
	public int getBonbaKop() {
		return super.getBonbaKop();
	}
	
	// Bonba kokatzeko metodoa
	public void bonbaJarri() {
		// Bonbaren bat badauka, zuzenean jarriko du
		if (getBonbaKop() > 0) {
			Jokoa.getJokoa().kokatuBonba();
			super.setBonbaKop(getBonbaKop() - 1);
			System.out.println("Bonba kokatu da. Bonba kopurua: " + getBonbaKop());
		}
		// Bonbarik ez badauka, 3 segundo itxaron beharko ditu beste bonba bat lortzeko 
		else if (!bonbaItxaroten) {
			System.out.println("Ez daukazu bonbarik!! 3 segundo itxaron bonba bat lortzeko.");
			itxaronBonba();
		}
		// Bonba jada kokatzen ari da
		else {
			System.out.println("Bonba bat lortzen ari zara, itxaron!!");
		}
	}
	
	// Metodo honek bonba bat lortzeko itxaron beharreko denbora kontrolatuko du
	public void itxaronBonba() {
		bonbaItxaroten = true;
		
		// 3 segundo itxaron eta bonba bat lortu
		bonbaTimer = new Timer();
        bonbaTimer.schedule(new TimerTask() {
            @Override
            public void run() {
            	setBonbaKop(1);
                bonbaItxaroten = false; 
                System.out.println("Bonba bat gehiago duzu!!");
            }
        }, 3000); 
	}	
}
