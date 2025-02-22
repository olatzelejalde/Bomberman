package eredua;

public class Normal extends Bonba {
	private Bomberman bomberman;
	
	public Normal(int x, int y, Laberinto laberinto, Jokoa jokoa, Bomberman bomberman) {
		super(x,y,laberinto,jokoa);
		this.bomberman = bomberman;
	}
	
	@Override
	protected void eztanda() {
		System.out.println("Eztanda (" + getX() + ", " + getY() + ") gelaxkan!!");

		eztandaPos(getX(), getY());
		estaldura(getX() - 1, getY()); // goian
    	estaldura(getX() + 1, getY()); // behean
    	estaldura(getX(), getY() - 1); // ezkerra
    	estaldura(getX(), getY() + 1); // eskuina

		new Thread(() -> {
			try {
				Thread.sleep(2000);
				suaKendu();
			} 
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();
	}

	public void estaldura(int x, int y) {
		if ((x >= 0 && x < 11) && (y >= 0 && y < 17)) {
			Gelaxka g = Laberinto.getNireLaberinto().getGelaxkaPos(x,y);
			// Biguna bada apurtu
			if (g.apurtuDaiteke()) {
				eztandaPos(x,y);
			}
			else if (!g.blokeDu()) {
				g.suaJarri();
			}
		}
	}

	public void eztandaPos(int x, int y) {		
		Gelaxka g = Laberinto.getNireLaberinto().getGelaxkaPos(x,y);
		
		// Bloke biguna badago apurtu
		if (g.apurtuDaiteke()) {
			g.apurtuBlokea();
		}

		// Bomberman posizioan badago hil
		Bomberman b = Jokoa.getJokoa().getBomberman();
		if (b.getX() == x && b.getY() == y) {
			b.hil();
			Jokoa.getJokoa().bukaera(false); //false --> ez du irabazi
		}

		g.suaJarri();
	}

	public void suaKendu() {
		estalduraKendu(getX(), getY()); // bonbaren posizioa
        estalduraKendu(getX() - 1, getY()); // goian
        estalduraKendu(getX() + 1, getY()); // behean
        estalduraKendu(getX(), getY() - 1); // ezkerra
        estalduraKendu(getX(), getY() + 1); // eskuma
	}

	public void estalduraKendu(int x, int y) {
		if ((x >= 0 && x < 11) && (y >= 0 && y < 17)) {
			// Gelaxka sua badu kendu
			Gelaxka g = Laberinto.getNireLaberinto().getGelaxkaPos(x, y);
			
			if (g.suaDago()) {
				g.suaKendu();
			}
		}
	}



}
