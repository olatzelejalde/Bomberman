package eredua;

public abstract class Bonba {
	private int x, y;
	private Laberinto laberinto;
	protected Jokoa jokoa;
	
	public Bonba(int x, int y, Laberinto laberinto, Jokoa jokoa) {
		this.x = x;
		this.y = y;
		this.laberinto = laberinto;
		this.jokoa = jokoa;

		itxaronEztanda();
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public Laberinto getLaberinto() {
        return laberinto;
    }
	
	public void itxaronEztanda() {
		new Thread(() -> {
			try {
				Thread.sleep(3000); // 3s itxaron bonba eztanda egin arte
				System.out.println("Bonbak eztanda egin du!!");
				eztanda();
			}
			catch (InterruptedException e) {
	            e.printStackTrace();
	        }
			
		}).start();
	}
	
	protected abstract void eztanda();
}
