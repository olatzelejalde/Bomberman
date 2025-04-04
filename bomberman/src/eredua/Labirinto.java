package eredua;

public abstract class Labirinto {

	private Gelaxka[][] matriz;
	private int suntsigarriak;
	private int etsaiak;

	protected Labirinto() {
		this.matriz = new Gelaxka[11][17];
		this.suntsigarriak = 0;
		this.etsaiak = 0;
	}
	
	// Matrizea lortu
	public Gelaxka[][] getMatriz() {
		return matriz;
	}
	
	public void sortuLaberinto(String mota) {
		Labirinto labirinto = LaberintoFactory.getLaberintoFactory().createLabirinto(mota);
		this.matriz = labirinto.getMatriz();
	}
	
	// Gelaxkaren posizioa lortu
	public Gelaxka getGelaxkaPos(int x, int y) {
		if (koordenatuBarruan(x,y)) {
			return matriz[x][y];
		}
		return null;
	}
	
	// Koordenatuen barnean dagoen egiaztatu
	public boolean koordenatuBarruan(int x, int y) {
		return (x >= 0 && x < 11) && (y >= 0 && y < 17);
	}

	// Bidea dagoen egiaztatu
	public boolean bidePosizioa(int x, int y) {
		return !matriz[x][y].blokeDu() && !matriz[x][y].bonbaDago();
	}

	// Gelaxka eguneratu egoera aldatu bada
	public void eguneratuGelaxka(int x, int y, Blokea bloke) {
		if (koordenatuBarruan(x,y)) {
			matriz[x][y] = new Gelaxka(bloke, false, false);
		}
	}
	
	// Bloke bigunak dauden egiaztatu
	public boolean blokeakDaude() {
		return this.suntsigarriak > 0;
	}
	
	// Bloke suntsigarria gehitu
	public void gehituSuntsigarri() {
		this.suntsigarriak++;
	}

	// Bloke suntsigarria kendu
	public void kenduSuntsigarri() {
		if (suntsigarriak > 0) {
			this.suntsigarriak--;
		}
		else if (suntsigarriak == 0 && etsaiak == 0) {
			Jokoa.getJokoa().bukaera(true);
		}
	}
	
	public boolean etsaiakDaude() {
		return this.etsaiak > 0;
	}
	
	public int getEtsaiak() {
		return this.etsaiak;
	}
	
	public void gehituEtsaia() {
		this.etsaiak++;
	}
	
	public void kenduEtsaia() {
		if (etsaiak > 0) {
			this.etsaiak--;
		}
		else if (suntsigarriak == 0 && etsaiak == 0) {
			Jokoa.getJokoa().bukaera(true);
		}
	}
	
	// Alboko gelaxketan sua jarri eta blokea biguna bada, apurtu
	public void jarriSua(int x, int y) {
		if (koordenatuBarruan(x,y)) {
			Gelaxka g = matriz[x][y];
			
			// Bakarrik sua jarriko du gelaxka hutsik badago edo bloke biguna bada
			if (!g.blokeDu() || g.apurtuDaiteke() || g.bombermanDago()) {
				g.setSua(true);
				
				// Bloke biguna bada, apurtu
				if (g.apurtuDaiteke()) {
					g.apurtuBlokea();
				}
				// Bomberman-a hil baldin eta dagoen gelaxkan sua dago
				if (g.bombermanDago()) {
					Jokoa.getJokoa().getBomberman().hil();
					g.setBomberman(false);
					Jokoa.getJokoa().bukaera(false);
				}
			}
		}
	}
	
	// Sua kendu eztanda denbora amaitu denean
	public void kenduSua(int x, int y) {
		if (koordenatuBarruan(x,y)) {
			Gelaxka g = matriz[x][y];
			if (g.suaDago()) {
				g.setSua(false);
			}
		}
	}
}
