package eredua;

import java.util.List;
import java.util.Observable;

import javax.swing.JOptionPane;

public class Jokoa extends Observable{package eredua;

import java.util.List;
import java.util.Observable;

public class Jokoa extends Observable{
    private static Jokoa nireJokoa;
    private Bomberman bomberman;
    private Laberinto laberinto;
    private Bonba bonba;
    private boolean amaituta;
    //private List<Etsaia> etsaiak;

    
    private Jokoa() {       
        this.amaituta = false;
        //this.etsaiak = etsaiak;
    }

    public static Jokoa getJokoa() {
        if (nireJokoa == null) {
        	nireJokoa = new Jokoa();
        }
        return nireJokoa;
    }
    
    // Metodo para empezar el juego
    public void hasiJokoa(){
    	this.laberinto = new Classic();
    	this.bomberman = new White(0, 0, 80);
    	this.laberinto.sortuLaberinto();
    }
    
    // Conseguir el bomberman
    public Bomberman getBomberman(){
        return this.bomberman;
    }
    
    // Conseguir el laberinto
    public Laberinto getLaberinto(){
        return this.laberinto;
    }
    
    // Conseguir la bomba
    public Bonba getBonba() {
    	return this.bonba;
    }
    
    // Metodo para colocar la bomba
    public void kokatuBonba() {
    	int x = bomberman.getX();
    	int y = bomberman.getY();
    		
    	// Validar antes de colocar la bomba
        if (laberinto.koordenatuBarruan(x, y) && laberinto.getMatriz()[x][y] != null) {
        	Gelaxka g = laberinto.getMatriz()[x][y];
        	
        	// Mirar si la celda tiene bloque blando o esta vacia
        	if (!g.blokeDu() || g.apurtuDaiteke()) {
        		bonba = new Normal(x, y);
                laberinto.getMatriz()[x][y].setBonba(bonba);
                setChanged();
                notifyObservers();
                
                // Iniciar el timer de la explosion
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
     
    // Actualizar si el bomberman ha muerto o si ha ganado
    public void eguneratu() {
        if (bomberman.hildaDago()) {
            bukaera(false);
        } 
        else if (!laberinto.blokeakDaude()) {
            bukaera(true);
        }
    }

    // Acabar partida
    public void bukaera(boolean irabazi) {
        amaituta = true;
        
        setChanged();
        notifyObservers(irabazi);
    }
    
    // Visualizar el laberinto
	public void bistaratu() {
		setChanged();
        notifyObservers();
        for (int i = 0; i < 11; i++) {
			for (int j = 0; j < 17; j++) {
				laberinto.getMatriz()[i][j].eguneratuBista();
			}
        }
	}

    /*
    private boolean etsaiGuztiakHilda() {
        for (Etsaia etsaia : etsaiak) {
            if (etsaia.bizirikDago()) {
                return false;
            }
        }
        return true;
    }*/
}
    private static Jokoa nireJokoa;
    private Bomberman bomberman;
    private Laberinto laberinto;
    private Bonba bonba;
    private boolean amaituta;
    //private List<Etsaia> etsaiak;

    
    private Jokoa() {       
        this.amaituta = false;
        //this.etsaiak = etsaiak;
    }

    public static Jokoa getJokoa() {
        if (nireJokoa == null) {
        	nireJokoa = new Jokoa();
        }
        return nireJokoa;
    }
    
    // Metodo para empezar el juego
    public void hasiJokoa(){
    	this.laberinto = new Classic();
    	this.bomberman = new White(0, 0, 10);
    	this.laberinto.sortuLaberinto();
		setChanged();
        notifyObservers();
    }
    
    // Conseguir el bomberman
    public Bomberman getBomberman(){
        return this.bomberman;
    }
    
    // Conseguir el laberinto
    public Laberinto getLaberinto(){
        return this.laberinto;
    }
    
    // Conseguir la bomba
    public Bonba getBonba() {
    	return this.bonba;
    }
    
    // Metodo para colocar la bomba
    public void kokatuBonba() {
    	int x = bomberman.getX();
    	int y = bomberman.getY();
    		
    	// Validar antes de colocar la bomba
        if (laberinto.koordenatuBarruan(x, y) && laberinto.getMatriz()[x][y] != null) {
        	Gelaxka g = laberinto.getMatriz()[x][y];
        	
        	// Mirar si la celda tiene bloque blando o esta vacia
        	if (!g.blokeDu() || g.apurtuDaiteke()) {
        		bonba = new Normal(x, y);
                laberinto.getMatriz()[x][y].setBonba(bonba);
                setChanged();
                notifyObservers();
                
                // Iniciar el timer de la explosion
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
     
    // Actualizar si el bomberman ha muerto o si ha ganado
    public void eguneratu() {
        if (bomberman.hildaDago()) {
            bukaera(false);
        } 
        else if (!laberinto.blokeakDaude()) {
            bukaera(true);
        }
    }

    // Acabar partida
    public void bukaera(boolean irabazi) {
        amaituta = true;
        
        if (irabazi) {
            JOptionPane.showMessageDialog(null, "Zorionak!! Irabazi duzu!!", "Bukaera", JOptionPane.INFORMATION_MESSAGE);
        } 
        else {
            JOptionPane.showMessageDialog(null, "Galdu duzu, saiatu berriro.", "Bukaera", JOptionPane.INFORMATION_MESSAGE);
        }
        
        setChanged();
        notifyObservers();
        System.exit(0);
    }

    
    // Visualizar el laberinto
	public void bistaratu() {
        for (int i = 0; i < 11; i++) {
			for (int j = 0; j < 17; j++) {
				laberinto.getMatriz()[i][j].eguneratuBista();
			}
        }
		setChanged();
        notifyObservers();
	}

    /*
    private boolean etsaiGuztiakHilda() {
        for (Etsaia etsaia : etsaiak) {
            if (etsaia.bizirikDago()) {
                return false;
            }
        }
        return true;
    }*/
}
