package eredua;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class White extends Bomberman {
	private boolean bonbaItxaroten;
	private Timer bonbaTimer;
	private List<Normal> bonbaList;
	

	public White(int x, int y) {
		super(x, y);
		this.bonbaList = new ArrayList<>();
		this.bonbaItxaroten = false;
		for (int i = 0; i < 10; i++) { // solo 10
			bonbaList.add(new Normal(x, y));
		}
	}
	
	public Bonba getBonba() {
		return bonbaList.get(0);
	}
	
	// Bomberman dituen bonba kopurua lortu
	public int getBonbaKop() {
		return bonbaList.size();
	}
	
	public void kenduBonba() {
        bonbaList.remove(0);
    }

	
	public void gehituBonba() {
        bonbaList.add(new Normal(this.getX(), this.getY()));
    }

	// Bonba kokatzeko metodoa
	public void bonbaJarri() {
		// Bonbaren bat badauka, zuzenean jarriko du
		if (!bonbaList.isEmpty()) {
			Jokoa.getJokoa().kokatuBonba();
			kenduBonba();
			System.out.println("Bonba kokatu da. Bonba kopurua: " + getBonbaKop());
		}
		else if (!bonbaItxaroten) {
			System.out.println("Ez daukazu bonbarik!! 3 segundo itxaron bonba bat lortzeko.");
			itxaronBonba();
		}
		else {
			System.out.println("Bonba bat lortzen ari zara, itxaron!!");
		}
	}
	
	// Bonba bat lortzeko itxaron beharreko denbora 
	public void itxaronBonba() {
		bonbaItxaroten = true;
		
		// 3 segundo itxaron eta bonba bat lortu
		bonbaTimer = new Timer();
        bonbaTimer.schedule(new TimerTask() {
            @Override
            public void run() {
            	gehituBonba();
                bonbaItxaroten = false; 
                System.out.println("Bonba bat gehiago duzu!!");
            }
        }, 3000); 
	}	
}
