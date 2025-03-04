package eredua;

public class White extends Bomberman {
	private int	bonbaKop;
	private boolean bonbaItxaroten;
	
	public White(int x, int y, Laberinto laberinto) {
		super(x,y,laberinto);
		this.bonbaKop = 10;
		this.bonbaItxaroten = false;		
	}
	
	public int getBonbaKop() {
		return bonbaKop;
	}
	
	public void bonbaJarri() {
		if (bonbaKop > 0) {
			Jokoa.getJokoa().kokatuBonba();
			bonbaKop--;
			setChanged();
			notifyObservers();
		}
		else if (!bonbaItxaroten) {
			System.out.println("Ez daukazu bonbarik!! 3s itxaron bonba bat lortzeko.");
			itxaronBonba();
		}
		else {
			System.out.println("Bonba bat lortzen ari zara, itxaron!!");
		}
	}
	
	public void itxaronBonba() {
		bonbaItxaroten = true;
		
		new Thread(() -> {
            try {
                Thread.sleep(3000);
                bonbaKop = 1;
                bonbaItxaroten = false;
                System.out.println("Bonba bat gehiago duzu!!");
                setChanged();
                notifyObservers();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
	}
	
}
