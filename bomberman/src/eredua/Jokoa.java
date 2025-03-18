package eredua;

import java.util.Observable;
import javax.swing.JOptionPane;

public class Jokoa extends Observable{
    private static Jokoa nireJokoa;
    private Bomberman bomberman;
    private Laberinto laberinto;
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
    	this.laberinto = new Classic();
    	this.bomberman = new White(0, 0, 10);
    	this.laberinto.sortuLaberinto();
    	setChanged();
    	notifyObservers();
    }
    
    // Bomberman-a lortu
    public Bomberman getBomberman(){
        return this.bomberman;
    }
    
    // Labirintoa sortu
    public Laberinto getLaberinto(){
        return this.laberinto;
    }
    
    // Bonba lortu
    public Bonba getBonba() {
    	return this.bonba;
    }
    
    // Bonba kokatzeko metodoa
    public void kokatuBonba() {
    	int x = bomberman.getX();
    	int y = bomberman.getY();
    		
    	// Egiaztatu bonba kokatu aurretik
        if (laberinto.koordenatuBarruan(x, y) && laberinto.getMatriz()[x][y] != null) {
        	Gelaxka g = laberinto.getMatriz()[x][y];
        	
        	// Begiratu ea gelaxka hutsik dagoen edo blokea biguna den
        	if (!g.blokeDu() || g.apurtuDaiteke()) {
        		bonba = new Normal(x, y);
                laberinto.getMatriz()[x][y].setBonba(bonba);
                setChanged();
                notifyObservers();
                
                // Eztandarako timerra hasieratu
                bonba.hasiEztanda();
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
        else if (!laberinto.blokeakDaude()) {
            bukaera(true);
        }
    }

    // Partida amaitu
    public void bukaera(boolean irabazi) {
        amaituta = true;
        setChanged();
        notifyObservers(irabazi);
        // System.exit(0);
    }

    // Labirintoa bistaratu
	public void bistaratu() {
		setChanged();
        notifyObservers();
        for (int i = 0; i < 11; i++) {
			for (int j = 0; j < 17; j++) {
				laberinto.getMatriz()[i][j].eguneratuBista();
			}
        }
	}
}
