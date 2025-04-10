package eredua;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Etsaia {
	private int x, y;
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

  	// Bonberman hil
  	public void setHil(boolean hil) {
  		this.bizirik = false;
  	}

	// Metodo mugitzeko
     public void mugitu() {
         if (!bizirik) return;
         
         Laberinto laberinto = Jokoa.getJokoa().getLaberinto();
         List<int[]> mugimenduAukerak = new ArrayList<>();
         
         // Mugimendu posibleak
         int[][] mugimenduak = {
             {x-1, y},  // Gora
             {x+1, y},  // Behera
             {x, y-1},  // Ezkerra
             {x, y+1}   // Eskiuna
         };
         
         // Mugimendu posibleak konprobatu
         for (int[] pos : mugimenduak) {
             int newX = pos[0];
             int newY = pos[1];
             
             if (laberinto.koordenatuBarruan(newX, newY)) {
                 Gelaxka g = laberinto.getGelaxkaPos(newX, newY);
                 
                 // Gelaxka libreetara edo Bomberman-gana mugitu
                 if (g.pasatuDaiteke() || g.bombermanDago()) {
                     mugimenduAukerak.add(pos);
                 }
             }
         }
         
         // Ausaz aukeratu nora joan, posible bada
         if (!mugimenduAukerak.isEmpty()) {
             Random r = new Random();
             int[] aukeratua = mugimenduAukerak.get(r.nextInt(mugimenduAukerak.size()));
             eguneratuPosizioa(aukeratua[0], aukeratua[1]);
         }
     }
     
     private void eguneratuPosizioa(int newX, int newY) {
         Laberinto laberinto = Jokoa.getJokoa().getLaberinto();
         Gelaxka helmuga = laberinto.getGelaxkaPos(newX, newY);
         
         // Atakatu Bomberman badago 
         if (helmuga.bombermanDago()) {
             Jokoa.getJokoa().bukaera(false); // Jokalaria galdu
             return;
         }
         
         // Etsaia mugitu
         laberinto.getGelaxkaPos(x,y).kenduEtsaia();
         
         // Posizio berrian jarri
         this.x = newX;
         this.y = newY;
         helmuga.setEtsaia(this);
         
         // Posizio berrian sua badago konprobatu
         if (helmuga.suaDago()) {
             hil();
         }
     }
     
     public void hil() {
         if (bizirik) {
             bizirik = false;
             Laberinto laberinto = Jokoa.getJokoa().getLaberinto();
             laberinto.getGelaxkaPos(x, y).kenduEtsaia();
             Jokoa.getJokoa().kenduEtsaia(this);
         }
     }
     

}
