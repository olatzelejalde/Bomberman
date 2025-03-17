package bista;

import java.awt.EventQueue;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import eredua.Bomberman;
import eredua.Gelaxka;
import eredua.Jokoa;

public class GelaxkaBista extends JLabel implements Observer {

	// Erabiliko diren irudiak atributu gisa kargatu
    private ImageIcon blokGoIcon = loadImage("/irudiak/hard5.png");
    private ImageIcon blokBigIcon = loadImage("/irudiak/soft1.png");
    private ImageIcon bomberIcon = loadImage("/irudiak/whitefront1.png");
    private ImageIcon bonbaIcon = loadImage("/irudiak/bomb1.png");
    private ImageIcon suaIcon = loadImage("/irudiak/kaBomb2.png");
    private ImageIcon whiteBonbarekin = loadImage("/irudiak/whitewithbomb1.png");
    private ImageIcon ezkerra = loadImage("/irudiak/whiteleft2.png");
    private ImageIcon eskuina = loadImage("/irudiak/whiteright2.png");
    private ImageIcon atzera = loadImage("/irudiak/whiteup2.png");
    private ImageIcon aurrera = loadImage("/irudiak/whitedown2.png");

	// Irudiak kargatzeko metodoa
	private ImageIcon loadImage(String path) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        }
        else {
        	System.out.println("Errorea egon da irudia kargatzeko: " + path);
            return null;
        }
    }

	// Eraikitzailea
	public GelaxkaBista() {
	}
	
	@Override
	public void update(Observable o, Object arg) {
	    Gelaxka g = (Gelaxka) o;

		// Sua dagoen konprobatu
	    if (g.suaDago()) {
	        this.setIcon(suaIcon);
	    } 
		// Bomberman bonba kokatzen	badago
	    else if (g.bombermanDago() && g.bonbaDago()) {
	        this.setIcon(whiteBonbarekin);
	    }
	    else if (g.bombermanDago()) {
	    	Jokoa joko = Jokoa.getJokoa();
			// Bomberman mota lortu
	        Bomberman bomber = joko.getBomberman();
	        if (bomber != null) {// Bomberman-a ez da printzipioz null-a izango, baina konprobatzearren
				// Bomberman-aren irudia norabidearen arabera aldatu
	            switch (bomber.getNorabidea()) {
	                case "ezkerra":
	                    this.setIcon(ezkerra);
	                    break;
	                case "eskuina":
	                    this.setIcon(eskuina);
	                    break;
	                case "goruntz":
	                    this.setIcon(atzera);
	                    break;
	                case "behera":
	                    this.setIcon(aurrera);
	                    break;
	                default:
	                    this.setIcon(bomberIcon);
	                    break;
	            }
	        }
	    }
		// Bonba kokatu badu jada
	    else if (g.bonbaDago()) {
	        this.setIcon(bonbaIcon);
	    }
		// Blokea duen konprobatu eta haren mota	
	    else if (g.blokeDu()) {
			// Bloke biguna
	        if (g.apurtuDaiteke()) {
	            this.setIcon(blokBigIcon);
	        }
			// Bloke gogorra
			else {
	            this.setIcon(blokGoIcon);
	        }
	    }
		// Ezer ez badago, hutsik utzi
		else {
	        this.setIcon(null);
	    }
	}
}
