package bista;

import java.util.Observable;
import java.util.Observer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import eredua.Bomberman;
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
	    if (arg instanceof String[]) {
	        String[] datuak = (String[]) arg;
	        
	        String egoera = datuak[0];  // egoera
	        String norabidea = datuak[1]; // norabidea

	        switch (egoera) {
	            case "sua":
	                this.setIcon(suaIcon);
	                break;
	            case "bombermanBonba":
	                this.setIcon(whiteBonbarekin);
	                break;
	            case "bomberman":
	                switch (norabidea) {
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
	                break;
	            case "bonba":
	                this.setIcon(bonbaIcon);
	                break;
	            case "blokBig":
	                this.setIcon(blokBigIcon);
	                break;
	            case "blokGo":
	                this.setIcon(blokGoIcon);
	                break;
	            case "hutsik":
	                this.setIcon(null);
	                break;
	            default:
	                break;
	        }
	    }
	}

}
