package eredua;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Etsaia {
	private int x;
	private int y;
	private boolean bizirik;
	
	public Etsaia(int x, int y) {
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

	// Metodo mugitzeko
     public void mugitu() {
         if (!bizirik) return;

         List<int[]> mugimenduAukerak = new ArrayList<>();
         
         // Posibles movimientos
         int[][] mugimenduak = {
             {x-1, y},  // Arriba
             {x+1, y},  // Abajo
             {x, y-1},  // Izquierda
             {x, y+1}   // Derecha
         };
         
         // Verificar movimientos válidos
         for (int[] pos : mugimenduak) {
             int newX = pos[0];
             int newY = pos[1];
             
             // Labirintoa nondik lortzen da?
	     Laberinto laberinto = Jokoa.getJokoa().getLaberinto();
             if (labirinto.koordenatuBarruan(newX, newY)) {
                 Gelaxka gelaxka = labirinto.getGelaxkaPos(newX, newY);
                 
                 // Puede moverse a casillas vacías o donde esté el Bomberman
                 if (gelaxka.pasatuDaiteke() || gelaxka.bombermanDago()) {
                     mugimenduAukerak.add(pos);
                 }
             }
         }
         
         // Si hay movimientos posibles, elegir uno al azar
         if (!mugimenduAukerak.isEmpty()) {
             Random r = new Random();
             int[] aukeratua = mugimenduAukerak.get(r.nextInt(mugimenduAukerak.size()));
             eguneratuPosizioa(aukeratua[0], aukeratua[1]);
         }
     }
     
     private void eguneratuPosizioa(int newX, int newY) {
    	 // Singleton arazoa
         Labirinto labirinto = Labirinto.getLabirinto();
         Gelaxka gelaxkaHelmuga = labirinto.getGelaxkaPos(newX, newY);
         
         // Atacar si hay Bomberman
         if (gelaxkaHelmuga.bombermanDago()) {
             Jokoa.getJokoa().bukaera(false); // Pierde el jugador
             return;
         }
         
         // Mover el enemigo
         labirinto.getGelaxkaPos(x, y).kenduEtsaia();
         this.x = newX;
         this.y = newY;
         gelaxkaHelmuga.setEtsaia(true);
         
         // Verificar si hay fuego en la nueva posición
         if (gelaxkaHelmuga.suaDago()) {
             hil();
         }
     }
     
     public void hil() {
         if (bizirik) {
             bizirik = false;
             Labirinto.getLabirinto().getGelaxkaPos(x, y).kenduEtsaia();
             Jokoa.getJokoa().etsaiaHil(); // Notificar que un enemigo ha muerto
         }
     }
}
