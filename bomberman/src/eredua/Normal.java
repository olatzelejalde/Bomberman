package eredua;

public class Normal extends Bonba {
	
	private Bomberman bomberman;
	public Normal(int x, int y, Laberinto laberinto, Bomberman bomberman, Jokoa jokoa) {
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
		    } catch (InterruptedException e) {
		        e.printStackTrace();
		    }
		}).start();
	}

	public void estaldura(int x, int y) {
		if ((x >= 0 && x < 11) && (y >= 0 && y < 17)) {
			Gelaxka g = getLaberinto().getGelaxkaPos(x,y);
			if(g.getMota().equals("gogorra")) {
				eztandaPos(getX(),getY());
			}
			
		}
	}

	public void eztandaPos(int x, int y) {
		if (getLaberinto() == null || getLaberinto().getGelaxkaPos(x, y) == null) return;
		Gelaxka g = getLaberinto().getGelaxkaPos(x,y);

		// Bloke biguna badago apurtu
		if (g.getMota().equals("biguna")) {
			getLaberinto().eguneratuGelaxka(x,y,"hutsik");
		}
		/*
		// Hil etsaiak
        	if (laberinto.etsaiaPosizioan(x, y)) {
	         	laberinto.hilEtsaia(x, y);
       	 	}*/

		// Bomberman posizioan badago hil
		if (bomberman.getX() == x && bomberman.getY() == y) {
			bomberman.hil();
			Jokoa.getInstance(bomberman).bukaera(false); //false --> ez du irabazi
		}

		getLaberinto().eguneratuGelaxka(x,y,"sua");
	}

	public void suaKendu() {
		estalduraKendu(getX(), getY()); // bonba posizioa
	        estalduraKendu(getX() - 1, getY()); // goian
	        estalduraKendu(getX() + 1, getY()); // behean
	        estalduraKendu(getX(), getY() - 1); // ezkerrean
	        estalduraKendu(getX(), getY() + 1); // eskuinean
	}

	public void estalduraKendu(int x, int y) {
		if ((x >= 0 && x < 11) && (y >= 0 && y < 17)) {
			// Gelaxka sua badu hutsik jarri
			if (getLaberinto().getGelaxkaPos(x,y).getMota().equals("sua")) {
				getLaberinto().eguneratuGelaxka(x,y,"hutsik");
			}
		}
	}
}
