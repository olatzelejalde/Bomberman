package eredua;

public class Normal extends Bonba {
	
	public Normal(int x, int y, Laberinto laberinto, Bomberman bomberman) {
		super(x,y,laberinto,bomberman);
	}

	protected void eztanda() {
		System.out.println("Eztanda (" + x + ", " + y + ") gelaxkan!!");

		eztandaPos(x,y);
		estaldura(x-1,y); // arriba
		estaldura(x+1,y); // abajo
		estaldura(x,y-1); // izquierda
		estaldura(x,y+1); // derecha

		new Thread(() -> {
			Thread.sleep(2000);
			suaKendu();
		}).start();
	}

	public void estaldura(int x, int y) {
		if ((x >= 0 && x < 11) && (x >= 0 && y < 17)) {
			Gelaxka g = laberinto.getGelaxkaPos(x,y);
			// Gogorra bada ez kendu
			if (!g.gogorraDa()) {
				eztandaPos(x,y);
			}
		}
	}

	public void eztandaPos(int x, int y) {
		Gelaxka g = laberinto.getGelaxkaPos(x,y);

		// Bloke biguna badago apurtu
		if (g.getMota().equals("biguna")) {
			laberinto.eguneratuGelaxka(x,y,"hutsik");
		}

		// Bomberman posizioan badago hil
		if (bomberman.getX() == x && bomberman.getY() == y) {
			bomberman.hil();
		}

		laberinto.eguneratuGelaxka(x,y,"sua");
	}

	public void suaKendu() {
		estalduraGarbitu(x,y); // posicion bonba
		estalduraGarbitu(x-1,y); // arriba
		estalduraGarbitu(x+1,y); // abajo
		estalduraGarbitu(x,y-1); // izquierda
		estalduraGarbitu(x,y+1); // derecha
	}

	public void estalduraKendu(int x, int y) {
		if ((x >= 0 && x < 11) && (x >= 0 && y < 17)) {
			// Gelaxka sua badu hutsik jarri
			if (laberinto.getGelaxkaPos(x,y).getMota().equals("sua")) {
				laberinto.eguneratuGelaxka(x,y,"hutsik");
			}
		}
	}



}
