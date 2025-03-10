package eredua;

import java.util.List;
import java.util.Observable;

public class Jokoa extends Observable{
    private static Jokoa nireJokoa;
    private White bomberman;
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
    
    public void hasiJokoa(White bomberman, Laberinto laberinto){
        this.bomberman = bomberman;
        this.laberinto = laberinto;
        setChanged();
        notifyObservers();
    }
    
    public White getBomberman(){
        return this.bomberman;
    }
    
    public Laberinto getLaberinto(){
        return this.laberinto;
    }
    
    public Bonba getBonba() {
    	return this.bonba;
    }
    
    public void kokatuBonba() {
    	int x = bomberman.getX();
    	int y = bomberman.getY();
    		
    	// Validar antes de colocar la bomba
        if (x >= 0 && x < 11 && y >= 0 && y < 17) {
            bonba = new Normal(x, y);
            setChanged();
            notifyObservers();
        } else {
            System.out.println("🚨 ERROR: No se puede colocar la bomba en (" + x + ", " + y + ")");
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

                bonba.eztanda(); // Llamar a la explosión
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
    
    public void eguneratu() {
        if (bomberman.hildaDago()) {
            bukaera(false);
        }
    }

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
