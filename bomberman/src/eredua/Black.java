package eredua;

import java.util.Timer;
import java.util.TimerTask;

public class Black extends Bomberman {
	private boolean bonbaItxaroten;
	private Timer bonbaTimer;
	
	public Black(int x, int y, int bonbaKop) {
		super(x, y, bonbaKop);
		this.bonbaItxaroten = false;		
	}
	
	
}
