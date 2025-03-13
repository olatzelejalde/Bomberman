package eredua;

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
    	this.bomberman = new White(0,0, 10);
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
        if (x >= 0 && x < 11 && y >= 0 && y < 17 && laberinto.getMatriz()[x][y] != null) {
            bonba = new Normal(x, y);
            laberinto.getMatriz()[x][y].setBonba(bonba);
            setChanged();
            notifyObservers();
        } 
        else {
            System.out.println("ERROR: No se puede colocar la bomba en (" + x + ", " + y + ")");
        }
    		
    	// Esperar 3 segundos antes de la explosión
        new Thread(() -> {
            try {
                Thread.sleep(3000);
                // Comprobar si no se ha movido despues de poner la bomba
                if (bomberman.getX() == x && bomberman.getY() == y) {
                    bomberman.hil();
                    bukaera(false);
                }
                // Llamar a la explosión
                bonba.eztanda(); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
     
    // Actualizar si el bomberman ha muerto
    public void eguneratu() {
        if (bomberman.hildaDago()) {
            bukaera(false);
        }
    }

    // Acabar partida
    public void bukaera(boolean irabazi) {
        amaituta = true;
        
        if (irabazi) {
            System.out.println("Zorionak!! Irabazi duzu!!");
        } 
        else {
            System.out.println("Galdu duzu, saiatu berriro.");
        }
        setChanged();
        notifyObservers();
        System.exit(0);
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
