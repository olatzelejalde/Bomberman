package eredua;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Black extends Bomberman {
	private boolean bonbaItxaroten;
	private Timer bonbaTimer;
	private List<Ultra> bonbaList;
	

	public Black(int x, int y) {
		super(x, y);
		this.bonbaList = new ArrayList<Ultra>();
		this.bonbaItxaroten = false;
		for (int i = 0; i < 1; i++) { // solo 1
			bonbaList.add(new Ultra(x, y));
		}
	}
	
	// Bomberman dituen bonba kopurua lortu
	public int getBonbaKop() {
		return bonbaList.size();
	}
	
	public void gehituBonba() {
        bonbaList.add(new Ultra(this.getX(), this.getY()));
    }
	
	// Bonba bat 3 segunduro jarri ahalko ditu 
	@Override
	public void bonbaJarri() {
		if (!bonbaList.isEmpty()) {
			BonbaPortaera bp = bonbaList.remove(getBonbaKop()-1);
			bp.bonbaJarri(this);
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
