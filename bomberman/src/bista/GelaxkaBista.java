package bista;

import java.util.Observable;
import java.util.Observer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import eredua.Bomberman;
import eredua.Jokoa;

public class GelaxkaBista extends JLabel implements Observer {

    private ImageIcon blokGoIcon = loadImage("/irudiak/hard5.png");
    private ImageIcon blokBigIcon = loadImage("/irudiak/soft1.png");
    private ImageIcon whiteIcon = loadImage("/irudiak/whitefront1.png");
    private ImageIcon blackIcon = loadImage("/irudiak/blackfront1.png");
    private ImageIcon bonbaIcon = loadImage("/irudiak/bomb1.png");
    private ImageIcon suaIcon = loadImage("/irudiak/kaBomb2.png");
    private ImageIcon whiteBonbarekin = loadImage("/irudiak/whitewithbomb1.png");
    private ImageIcon ezkerraWhite = loadImage("/irudiak/whiteleft2.png");
    private ImageIcon eskuinaWhite = loadImage("/irudiak/whiteright2.png");
    private ImageIcon atzeraWhite = loadImage("/irudiak/whiteup2.png");
    private ImageIcon aurreraWhite = loadImage("/irudiak/whitedown2.png");
    private ImageIcon blackBonbarekin = loadImage("/irudiak/blackwithbomb1.png");
    private ImageIcon ezkerraBlack = loadImage("/irudiak/blackleft2.png");
    private ImageIcon eskuinaBlack = loadImage("/irudiak/blackright2.png");
    private ImageIcon atzeraBlack = loadImage("/irudiak/blackup2.png");
    private ImageIcon aurreraBlack = loadImage("/irudiak/blackdown2.png");

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


	public GelaxkaBista() {
	}
	
	@Override
	public void update(Observable o, Object arg) {
	    if (arg instanceof String[]) {
	        String[] datuak = (String[]) arg;
	        
	        String egoera = datuak[0];  // egoera
	        String norabidea = datuak[1]; // norabidea
	        String jokalariMota = datuak.length > 2 ? datuak[2] : "white";

	        switch (egoera) {
	            case "sua":
	                this.setIcon(suaIcon);
	                break;
	            case "bombermanBonba":
	            	if (jokalariMota.equals("black")) {
	            		this.setIcon(blackBonbarekin);
		                break;
	            	}
	            	else {
	            		this.setIcon(whiteBonbarekin);
		                break;
	            	}
	            case "bomberman":
	            	if ("black".equals(jokalariMota)) {
                        switch (norabidea) {
                            case "ezkerra": 
                            	this.setIcon(ezkerraBlack); 
                            	break;
                            case "eskuina": 
                            	this.setIcon(eskuinaBlack);
                            	break;
                            case "goruntz": 
                            	this.setIcon(atzeraBlack);
                            	break;
                            case "behera": 
                            	this.setIcon(aurreraBlack); 
                            	break;
                            default: 
                            	this.setIcon(blackIcon); 
                            	break;
                        }
                    } else { //white
                        switch (norabidea) {
                            case "ezkerra": 
                            	this.setIcon(ezkerraWhite); 
                            	break;
                            case "eskuina": 
                            	this.setIcon(eskuinaWhite);
                            	break;
                            case "goruntz": 
                            	this.setIcon(atzeraWhite); 
                            	break;
                            case "behera": 
                            	this.setIcon(aurreraWhite); 
                            	break;
                            default: 
                            	this.setIcon(whiteIcon); 
                            	break;
                        }
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
