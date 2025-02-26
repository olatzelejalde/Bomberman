package eredua;

public abstract class Bonba {
	private int x, y;

	public Bonba(int x, int y) {
		this.x = x;
		this.y = y;

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
