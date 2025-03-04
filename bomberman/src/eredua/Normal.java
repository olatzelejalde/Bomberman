package eredua;

public class Normal extends Bonba {
	
	public Normal(int x, int y) {
		super(x,y);
	}
	
	@Override
	public void eztanda() {
		if (!aktibatuta) {
			return;
		}
		aktibatuta = false;
		
		int x = getX();
		int y = getY();
		
		Jokoa jokoa = Jokoa.getJokoa();
		Laberinto laberinto = jokoa.getLaberinto();
		
		suntsituBlokea(x, y, laberinto); //posicion original de bomba
		suntsituBlokea(x - 1, y, laberinto); //arriba
		suntsituBlokea(x + 1, y, laberinto); //abajo
		suntsituBlokea(x, y - 1, laberinto); //izquierda
		suntsituBlokea(x, y + 1, laberinto); //derecha
		
		jokoa.kenduBonba(this);
	}

	public void suntsituBlokea(int x, int y, Laberinto laberinto) {
		if (laberinto != null && laberinto.koordenatuBarruan(x,y)) {
			Gelaxka g = laberinto.getGelaxkaPos(x, y);
			
			if (g != null && g.apurtuDaiteke()) {
				laberinto.kenduBlokea(x,y);
			}
		}
	}
}
