package eredua;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JOptionPane;

public class Jokoa extends Observable {
    private static Jokoa nireJokoa;
    private Bomberman bomberman;
    private Laberinto laberinto;
    private List<Bonba> bonbaAktiboak = new ArrayList<>();
    private boolean amaituta;

    private Jokoa() {
        this.amaituta = false;
    }

    public Bomberman getBomberman(){
        return this.bomberman;
    }
    
    // Conseguir el laberinto
    public Laberinto getLaberinto(){
        return this.laberinto;
    }
    
    // Conseguir la bomba
    public List<Bonba> getBonbak() {
    	return bonbaAktiboak;
    }
    
    public static Jokoa getJokoa() {
        if (nireJokoa == null) {
            nireJokoa = new Jokoa();
        }
        return nireJokoa;
    }

    // Inicia el juego
    public void hasiJokoa() {
        this.laberinto = new Classic();
        this.bomberman = new White(0, 0, 10);  // Usar White para bomberman
        this.laberinto.sortuLaberinto();
    }

    // Colocar una bomba
    public void kokatuBonba() {
        int x = bomberman.getX();
        int y = bomberman.getY();

        // Validar antes de colocar la bomba
        if (x >= 0 && x < 11 && y >= 0 && y < 17 && laberinto.getMatriz()[x][y] != null) {
            if (bomberman.getBonbaKop() > 0) {
                // Si el bomberman tiene bombas disponibles, colocar la bomba
                Bonba bonbaBerria = new Normal(x, y);
                bonbaAktiboak.add(bonbaBerria);
                laberinto.getMatriz()[x][y].setBonba(bonbaBerria);
                bomberman.bonba1Kendu(); // Disminuir el contador de bombas
                setChanged();
                notifyObservers();

                // Esperar 3 segundos antes de la explosión
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        // Comprobar si Bomberman sigue en la posición de la bomba
                        if (bomberman.getX() == x && bomberman.getY() == y) {
                            bomberman.hil();
                            bukaera(false);
                        }
                        // Llamar a la explosión
                        bonbaBerria.eztanda();

                        // Eliminar la bomba del laberinto y de la lista de bombas activas
                        bonbaAktiboak.remove(bonbaBerria);
                        laberinto.getMatriz()[x][y].setBonba(null);

                        setChanged();
                        notifyObservers();
                    }
                }, 3000);  // Esperar 3000ms (3 segundos)
            } else {
                // Si no tiene bombas disponibles, ponerlo en espera
                System.out.println("Ez daukazu bonbarik, itxaron 3 segundu...");
                if (bomberman instanceof White) {
                    ((White) bomberman).itxaronBonba();  // Llamar al método de espera de White
                }
            }
        } else {
            System.out.println("ERROR: No se puede colocar la bomba en (" + x + ", " + y + ")");
        }
    }
    
    // Actualizar si el bomberman ha muerto
    public void eguneratu() {
        if (bomberman.hildaDago()) {
            bukaera(false);
        }
    }
    
    public void bukaera(boolean irabazi) {
        amaituta = true;
        String mezua;
        if (irabazi) {
        	mezua = "Zorionak!! Irabazi duzu.";
        } else {
        	mezua = "Oooooo... galdu duzu :(";
        }
        JOptionPane.showMessageDialog(null, mezua, "JOKOAREN AMAIERA", JOptionPane.INFORMATION_MESSAGE);

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
}
