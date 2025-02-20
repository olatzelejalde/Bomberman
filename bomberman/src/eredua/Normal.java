package eredua;

public class Normal extends Bonba {
	
	private Bomberman bomberman;
	public Normal(int x, int y, Laberinto laberinto, Bomberman bomberman) {
		super(x,y,laberinto,bomberman);
		this.bomberman = bomberman;
	}
	
	@Override
	protected void eztanda() {
		System.out.println("Eztanda (" + x + ", " + y + ") gelaxkan!!");

		eztandaPos(x,y);
		estaldura(x-1,y); // goian
		estaldura(x+1,y); // behean
		estaldura(x,y-1); // ezkerra
		estaldura(x,y+1); // eskuina

		new Thread(() -> {
			Thread.sleep(2000);
			suaKendu();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
		
		// Hil etsaiak
        if (laberinto.etsaiaPosizioan(x, y)) {
            laberinto.hilEtsaia(x, y);
        }

		// Bomberman posizioan badago hil
		if (bomberman.getX() == x && bomberman.getY() == y) {
			bomberman.hil();
			Jokoa.getInstance().bukaera(false); //false --> ez du irabazi
		}

		laberinto.eguneratuGelaxka(x,y,"sua");
	}

	public void suaKendu() {
		estalduraGarbitu(x,y); // bonba posizioa
		estalduraGarbitu(x-1,y); // goian
		estalduraGarbitu(x+1,y); // behean
		estalduraGarbitu(x,y-1); // ezkerrean
		estalduraGarbitu(x,y+1); // eskuinean
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
