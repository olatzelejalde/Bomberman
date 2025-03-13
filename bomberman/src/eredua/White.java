package eredua;

import java.util.Timer;
import java.util.TimerTask;

public class White extends Bomberman {
	private boolean bonbaItxaroten;
	
	
	public White(int x, int y, int bonbaKop) {
		super(x,y,bonbaKop);
		this.bonbaItxaroten = false;		
	}
	
	// Metodo para colocar la bomba
	public void bonbaJarri() {
		System.out.println("Bonba kopurua: " + getBonbaKop());
        if (getBonbaKop() > 0) {
            Jokoa.getJokoa().kokatuBonba();
            bonbaKop--; // Disminuir la cantidad de bombas
            System.out.println("Bonba bat jarri duzu. " + getBonbaKop() + " bonba gelditzen dira");
        } else if (!bonbaItxaroten) {
            System.out.println("Ez daukazu bonbarik!! 3 segundotan itxaron bonba bat lortzeko.");
            itxaronBonba(); // Llamar a la función de esperar
        } else {
            System.out.println("Bonba bat lortzen ari zara, itxaron!!");
        }
    }

    // Método para esperar 3 segundos para restaurar una bomba
    public void itxaronBonba() {
        bonbaItxaroten = true; // Establecer que está esperando
        System.out.println("3 segunduak  hasi dira...");
        
        // Usar un Timer para esperar 3 segundos
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                bonbaKop = 1;  // Restaurar solo 1 bomba
                bonbaItxaroten = false; // Ya no está esperando
                System.out.println("Bonba berria kokatu dezakezu!");
            }
        };
        
        timer.schedule(task, 3000);  // Esperar 3 segundos
        System.out.println("3 segunduak pasatu dira...");
    }

    public int getBonbaKop() {
        return super.getBonbaKop();
    }

}
