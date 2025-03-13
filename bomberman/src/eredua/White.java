package eredua;

public class White extends Bomberman {
	private int	bonbaKop;
	private boolean bonbaItxaroten;
	
	
	public White(int x, int y, int bonbaKop) {
		super(x,y,bonbaKop);
		this.bonbaItxaroten = false;		
	}
	
	// Conseguir la cantidad de bombas que le quedan
	public int getBonbaKop() {
		return bonbaKop;
	}
	
	// Metodo para colocar la bomba
	public void bonbaJarri() {
		if (bonbaKop > 0) {
			Jokoa.getJokoa().kokatuBonba();
			bonbaKop--;
		}
		else if (!bonbaItxaroten) {
			System.out.println("Ez daukazu bonbarik!! 3s itxaron bonba bat lortzeko.");
			itxaronBonba();
		}
		else {
			System.out.println("Bonba bat lortzen ari zara, itxaron!!");
		}
	}
	
	// Metodo para esperar a conseguir una bomba al de 3s cuando ya no te quedaban mas
	public void itxaronBonba() {
		bonbaItxaroten = true;
		
		new Thread(() -> {
            try {
                Thread.sleep(3000);
                bonbaKop = 1;
                bonbaItxaroten = false;
                System.out.println("Bonba bat gehiago duzu!!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
	}
	
}
