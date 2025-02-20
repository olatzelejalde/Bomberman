package eredua;

public abstract class Bonba {
	private int x, y;
	private Laberinto laberinto;
	
	public Bonba(int x, int y, Laberinto laberinto) {
		this.x = x;
		this.y = y;
		this.laberinto = laberinto;

		itxaronEztanda();
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}

	public void itxaronEztanda() {
		new Thread(() -> {
			try {
				Thread.sleep(3000); // 3s itxaron bonba eztanda egin arte
				System.out.println("Bonba eztanda egin du!!");
			}
			catch (InterruptedException e) {
	            e.printStackTrace();
	        }
			
		}).start();
	}
	
	protected abstract void eztanda();
}
