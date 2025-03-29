package bista;

import java.util.Observable;
import java.util.Observer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

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
		if (arg instanceof String) {
            String egoera = (String) arg;
            switch (egoera) {
                case "sua":
                    this.setIcon(suaIcon);
                    break;
                case "bombermanBonba":
                    this.setIcon(whiteBonbarekin);
                    break;
                case "bomberman":
                    this.setIcon(bomberIcon);
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
