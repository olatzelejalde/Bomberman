package eredua;

public abstract class Bomberman {
    private int x, y;
    private boolean bizirik;
    protected int bonbaKop;
    private String norabidea = "behera"; 

    public Bomberman(int x, int y, int bonbaKop) {
        this.x = x;
        this.y = y;
        this.bizirik = true;
        this.bonbaKop = bonbaKop;
    }

 // Conseguir la posicion X
 	public int getX() {
 		return x;
 	}
 	
 	// Conseguir la posicion Y
 	public int getY() {
 		return y;
 	}
 	
 	// Colocarlo en la posicion x
 	public void setX(int x) {
 		this.x = x;
 	}

 	// COlocarlo en la posicion y
 	public void setY(int y) {
 		this.y = y;
 	}
 	
 	// Verifica si ha muerto
 	public boolean hildaDago() {
 	    return !bizirik;
 	}

 	// Matar al bomberman
 	public void setHil(boolean hil) {
 		this.bizirik = false;
 	}
    public String getNorabidea() {
        return norabidea;
    }
    
 // Metodo abstacto para poner bomba en Normal
 	public abstract void bonbaJarri();

    public void mugitu(int newX, int newY) {
        Laberinto laberinto = Jokoa.getJokoa().getLaberinto();

        if (laberinto.koordenatuBarruan(newX, newY) && laberinto != null) {
            if (laberinto.getGelaxkaPos(newX, newY).bonbaDago()) {
                System.out.println("Bonba bat jarri duzu posizio honetan, itxaron eztanda egin arte");
            }

            if (laberinto.bidePosizioa(newX, newY)) {
                // Actualizar dirección antes de mover
                if (newX < x) {
                    norabidea = "goruntz"; // Arriba
                } else if (newX > x) {
                    norabidea = "behera"; // Abajo
                } else if (newY < y) {
                    norabidea = "ezkerra"; // Izquierda
                } else if (newY > y) {
                    norabidea = "eskuina"; // Derecha
                }

                // Quitar bomberman de la celda actual
                laberinto.getGelaxkaPos(x, y).setBomberman(null);
                // Mover a la celda nueva
                laberinto.getGelaxkaPos(newX, newY).setBomberman(this);

                x = newX;
                y = newY;

                System.out.println("Bomberman mugitu da: (" + x + ", " + y + ")");
            } else {
                System.out.println("Ezin da mugitu posizio honetara");
            }
        } else {
            System.out.println("Laberintotik kanpo");
        }
    }
    public void hil() {
	    if (bizirik) {
	        bizirik = false;
	        Jokoa.getJokoa().bukaera(false);
	    }
	}
}
