package eredua;

import java.util.List;

public abstract class Bomberman {
    private int x, y;
    private boolean bizirik;
    private String norabidea = "behera"; 
    
    public Bomberman(int x, int y) {
        this.x = x;
        this.y = y;
        this.bizirik = true;
    }
    
 	// X posizioa lortu
 	public int getX() {
 		return x;																																			
 	}
 	
 	// Y posizioa lortu
 	public int getY() {
 		return y;
 	}
 	
 	// X posizioan kokatu
 	public void setX(int x) {
 		this.x = x;
 	}

 	// Y posizioan kokatu
 	public void setY(int y) {
 		this.y = y;
 	}
 	
 	// Hil den egiaztatu
 	public boolean hildaDago() {
 	    return !bizirik;
 	}

 	// Bonbermana hil
 	public void setHil(boolean hil) {
 		this.bizirik = false;
 	}

	// Norantz doan jakiteko
    public String getNorabidea() {
      	return norabidea;
    }
    
    public abstract Bonba getBonba();
    
    // Metodo bonba jartzeko
    public abstract void bonbaJarri();

 	// Metodo mugitzeko
    public void mugitu(int newX, int newY) {
        Laberinto laberinto = Jokoa.getJokoa().getLaberinto();

        if (laberinto.koordenatuBarruan(newX, newY) && laberinto != null) {
            if (laberinto.getGelaxkaPos(newX, newY).bonbaDago()) {
                System.out.println("Bonba bat jarri duzu posizio honetan, itxaron eztanda egin arte");
            }

            if (laberinto.bidePosizioa(newX, newY)) {
                // Mugitu baino lehen posizioa eguneratu
                if (newX < x) {
                    norabidea = "goruntz"; // Gora
                } 
                else if (newX > x) {
                    norabidea = "behera"; // Behera
                } 
                else if (newY < y) {
                    norabidea = "ezkerra"; // Ezkerra
                } 
                else if (newY > y) {
                    norabidea = "eskuina"; // Eskuina
                }

                // Kendu Bombermana gelaxka honetatik
                laberinto.getGelaxkaPos(x, y).setBomberman(false);
                // Gelaxka berrira mugitu
                laberinto.getGelaxkaPos(newX, newY).setBomberman(true);

                x = newX;
                y = newY;

                System.out.println("Bomberman mugitu da: (" + x + ", " + y + ")");
            } 
            else {
                System.out.println("Ezin da mugitu posizio honetara");
            }
        } 
        else {
            System.out.println("Laberintotik kanpo");
        }
    }
    
    // Bomberman hil
    public void hil() {
	    if (bizirik) {
	        bizirik = false;
	        Jokoa.getJokoa().bukaera(false);
	    }
	}
}
