package eredua;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;


public class Jokoa extends Observable{
    private static Jokoa nireJokoa;
    private Bomberman bomberman;
    private Laberinto laberinto;
    private boolean amaituta;
    private List<Etsaia> etsaiList;
    private Timer etsaiakTimer;

    
    private Jokoa() {       
        this.amaituta = false;
        this.etsaiList = new ArrayList<>();
    }

    public static Jokoa getJokoa() {
        if (nireJokoa == null) {
        	nireJokoa = new Jokoa();
        }
        return nireJokoa;
    }
    
    // Metodo jokoa hasteko
    public void hasiJokoa(String laberintoMota, String jokalariMota){
    	laberinto = LaberintoFactory.getLF().createLaberinto(laberintoMota);    	
    	bomberman = BombermanFactory.getBF().createBomberman(jokalariMota);
    	System.out.println("Laberinto mota: " + laberintoMota + " --- Jokalari mota: " + jokalariMota);
    	
    	hasieratuEtsaiak();
    }
    
	 private void hasieratuEtsaiak() { 
		 etsaiList = new ArrayList<>(); 
		 Gelaxka[][] matriz = laberinto.getMatriz();
	  
		 // Etsaiak laberintoan aurkitu 
		 for (int i = 0; i < 11; i++) { 
			 for (int j = 0; j < 17; j++) { 
				 if (matriz[i][j].etsaiaDago()) { 
					 etsaiList.add(new Etsaia(i,j)); 
				 } 
			 } 
		 }
	 
		 // Etsaiak segunduro mugitu 
		 etsaiakTimer = new Timer();
		 etsaiakTimer.scheduleAtFixedRate(new TimerTask() {
		 
		 @Override 
		 public void run() { 
			 mugituEtsaiak(); 
			 } 
		 }, 1000, 1000); 
	}
		 
	 // Etsai guztiak mugitu 
	 private void mugituEtsaiak() { 
		 for (Etsaia etsaia : new ArrayList<>(etsaiList)) { 
			 if (!etsaia.hildaDago()) { 
				 etsaia.mugitu(); 
			 } 
			 else {
				 etsaiList.remove(etsaia); 
			 } 
		 } 
		 
		 if (etsaiList.isEmpty()&& !amaituta) {
			 bukaera(true);
		 }
		 //setChanged(); 
		 //notifyObservers();
	}		 
    
    // Metodo etsaia gehitzeko
    public void gehituEtsaia(Etsaia etsaia) {
        etsaiList.add(etsaia);
    }
    
    // Metodo etsaia kentzeko
    public void kenduEtsaia(Etsaia etsaia) {
        etsaiList.remove(etsaia);
    }
    
    // Etsai kopurua lortu
    public int getEtsaiKop() {
    	return etsaiList.size();
    }
    
    // Laberinto lortu
    public Laberinto getLaberinto(){
        return laberinto;
    }
    
    // Bomberman lortu
    public Bomberman getBomberman(){
        return bomberman;
    }
    
    // Bonba kokatzeko metodoa
    public void kokatuBonba() {
		int x = bomberman.getX();
    	int y = bomberman.getY();
    		
    	// Egiaztatu bonba kokatu aurretik
        if (laberinto.koordenatuBarruan(x, y) && laberinto.getMatriz()[x][y] != null) {
        	Gelaxka g = laberinto.getMatriz()[x][y];
        	
        	// Begiratu ea gelaxka hutsik dagoen edo blokea biguna den
        	if (!g.blokeDu() || g.apurtuDaiteke()) {
        		Bonba bonba = getBomberman().getBonba();
        		bonba.setX(x);
        		bonba.setY(y);
                laberinto.getMatriz()[x][y].setBonba(bonba);
               
                
                // Eztandarako timerra hasieratu
                bonba.hasiEztanda();
                setChanged();
                notifyObservers();
        	}
        	else {
        		System.out.println("ERROR: Ezin da (" + x + ", " + y + ") posizioan bonbarik jarri");
        	}
        } 
        else {
            System.out.println("ERROR: Ezin da (" + x + ", " + y + ") posizioan bonbarik jarri, laberintotik kanpo dagoelako");
        }
    }
     
    // Eguneratu bomberman hil bada edo irabazi badu
    public void eguneratu() {
        if (bomberman.hildaDago()) {
            bukaera(false);
        } 
        else if (!laberinto.blokeakDaude()) {
            bukaera(true);
        }
    }

    // Partida amaitu
    public void bukaera(boolean irabazi) {
    	if (etsaiakTimer != null) {
            etsaiakTimer.cancel();
        }
    	
    	if (!amaituta) {
    		amaituta = true;
    		setChanged();
    		notifyObservers(irabazi);
        }
    }

    // Laberintoa bistaratu
    public void bistaratu() {
        String laberintoMota = laberinto.getMota();
        setChanged();
        notifyObservers(new String[] {"sortu", laberintoMota});
       
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 17; j++) {
                laberinto.getMatriz()[i][j].eguneratuBista();
            }
        }
    }


	public void bombermanMugitu(int dx, int dy) {
	    if (bomberman != null) {
	        int Xberria = bomberman.getX() + dx;
	        int Yberria = bomberman.getY() + dy;

	        if (!laberinto.koordenatuBarruan(Xberria, Yberria)) return;

	        bomberman.mugitu(Xberria, Yberria);
	        Gelaxka g = laberinto.getGelaxkaPos(Xberria, Yberria);

	        if (g.suaDago() || g.etsaiaDago()) {
	            bukaera(false); // Bomberman hil da
	        }

	        setChanged();
	        notifyObservers();
	    }
	}
}
