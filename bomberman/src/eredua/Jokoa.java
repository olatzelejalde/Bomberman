package eredua;

import java.util.Observable;
import javax.swing.JOptionPane;

public class Jokoa extends Observable{
    private static Jokoa nireJokoa;
    private Bomberman bomberman;
    private Bonba bonba;
    private boolean amaituta;

    private Jokoa() {       
        this.amaituta = false;
    }

    public static Jokoa getJokoa() {
        if (nireJokoa == null) {
        	nireJokoa = new Jokoa();
        }
        return nireJokoa;
    }
    
    // Metodo jokoa hasteko
    public void hasiJokoa(){
    	Laberinto.getLaberinto().sortuLaberinto();
        this.bomberman = new White(0, 0, 10);
        setChanged();
        notifyObservers("sortu");
        bistaratu();
    }
    
    // Bomberman lortu
    public Bomberman getBomberman(){
        return this.bomberman;
    }
    
    // Bonba lortu
    public Bonba getBonba() {
    	return this.bonba;
    }
    
    // Bonba kokatzeko metodoa
    public void kokatuBonba() {
    	int x = bomberman.getX();
    	int y = bomberman.getY();
    	Laberinto laberinto = Laberinto.getLaberinto();
    		
    	// Egiaztatu bonba kokatu aurretik
        if (laberinto.koordenatuBarruan(x, y) && laberinto.getMatriz()[x][y] != null) {
        	Gelaxka g = laberinto.getMatriz()[x][y];
        	
        	// Begiratu ea gelaxka hutsik dagoen edo blokea biguna den
        	if (!g.blokeDu() || g.apurtuDaiteke()) {
        		bonba = new Normal(x, y);
                laberinto.getMatriz()[x][y].setBonba(bonba);
               
                
                // Eztandarako timerra hasieratu
                bonba.hasiEztanda();
                setChanged();
                notifyObservers();
        	}
        	else {
        		System.out.println("ERROR: Ezin da (" + x + ", " + y + ") posizioan bonbarik jarri");
        	}
        } 
        else {
            System.out.println("ERROR: Ezin da (" + x + ", " + y + ") posizioan bonbarik jarri, laberintotik kanpo dagoelako");
        }
    }
     
    // Eguneratu bombermana hil bada edo irabazi badu
    public void eguneratu() {
        if (bomberman.hildaDago()) {
            bukaera(false);
        } 
        else if (!Laberinto.getLaberinto().blokeakDaude()) {
            bukaera(true);
        }
    }

    // Partida amaitu
    public void bukaera(boolean irabazi) {
    	if (!amaituta) {
    		amaituta = true;
    		setChanged();
    		notifyObservers(irabazi);
        }
    }

    // Laberintoa bistaratu
	public void bistaratu() {
        Laberinto laberinto = Laberinto.getLaberinto();
        for (int i = 0; i < 11; i++) {
			for (int j = 0; j < 17; j++) {
				laberinto.getMatriz()[i][j].eguneratuBista();
			}
        }
	}
	
	public void bombermanMugitu (int dx, int dy) {
        if (bomberman != null) {
            int Xberria = bomberman.getX() + dx;
            int Yberria = bomberman.getY() + dy;
            bomberman.mugitu(Xberria, Yberria);
            setChanged();
            notifyObservers();
        }
    }
}
