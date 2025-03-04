package eredua;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Jokoa extends Observable{
    private static Jokoa nireJokoa;
    private White bomberman;
    private Laberinto laberinto;
    private List<Bonba> bonbaList;
    private boolean amaituta;
    //private List<Etsaia> etsaiak;

    private Jokoa() {
    	this.bonbaList = new ArrayList<>();        
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
    
    public void kokatuBonba() {
    	int x = bomberman.getX();
    	int y = bomberman.getY();
    		
    	Bonba bonba = new Normal(x,y);
    	bonbaList.add(bonba);
    		
    	setChanged();
    	notifyObservers();
    		
    	// Temporizador de explosión
        new Thread(() -> {
        	try {
        		Thread.sleep(3000);
                bonba.eztanda();
                kenduBonba(bonba);
            } 
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
    
    public void kenduBonba(Bonba bonba) {
    	bonbaList.remove(bonba);
    	setChanged();
    	notifyObservers();
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
