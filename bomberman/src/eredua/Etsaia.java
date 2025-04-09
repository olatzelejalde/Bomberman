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
             {x-1, y},  // Gora
             {x+1, y},  // Behera
             {x, y-1},  // Ezkerra
             {x, y+1}   // Eskuina
         };
         
         // Verificar movimientos válidos
         for (int[] pos : mugimenduak) {
             int newX = pos[0];
             int newY = pos[1];
             
             // Labirintoa nondik lortzen da?
	     Laberinto laberinto = Jokoa.getJokoa().getLaberinto();
             if (laberinto.koordenatuBarruan(newX, newY)) {
                 Gelaxka gelaxka = laberinto.getGelaxkaPos(newX, newY);
                 
                 // Gelaxka hutsetara edo Bonbermana dagoen posizioetara mugitu ahal da
                 if (gelaxka.pasatuDaiteke() || gelaxka.bombermanDago()) {
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
    	 // Singleton arazoa
         Laberinto laberinto = Laberinto.getLaberinto();
         Gelaxka gelaxkaHelmuga = laberinto.getGelaxkaPos(newX, newY);
         
         // Bombermana badago atakatu
         if (gelaxkaHelmuga.bombermanDago()) {
             Jokoa.getJokoa().bukaera(false); // Bonbermanak galdu du
             return;
         }
         
         // Etsaia mugitu
         laberinto.getGelaxkaPos(x, y).kenduEtsaia();
         this.x = newX;
         this.y = newY;
         gelaxkaHelmuga.setEtsaia(true);
         
         // Posizio berrian sua dagoen egiaztatu
         if (gelaxkaHelmuga.suaDago()) {
             hil();
         }
     }
     
     public void hil() {
         if (bizirik) {
             bizirik = false;
             Laberinto.getLaberinto().getGelaxkaPos(x, y).kenduEtsaia();
             Jokoa.getJokoa().etsaiaHil(); // Etsai bat hil bada abisatu
         }
     }
}
