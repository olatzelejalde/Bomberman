package bista;

import java.awt.EventQueue;

import javax.swing.border.EmptyBorder;

import eredua.Bomberman;
import eredua.Gelaxka;
import eredua.Jokoa;

import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;
import java.awt.*;

public class Partida extends JFrame implements Observer {
    private static final int errenkada = 11;
    private static final int zutabe = 17;
    private static final int tam = 40;
    private Controler nireControler;

    private GelaxkaBista[][] board = new GelaxkaBista[errenkada][zutabe];
    private Jokoa jokoa;
    
    private ImageIcon fondoIcon = loadImage("/irudiak/stageBack1.png");
    
    
    private ImageIcon loadImage(String path) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        }
        else {
        	System.out.println("Errorea irudia kargatzeko: " + path);
            return null;
        }
    }
    
    public Partida() {
    	setTitle("Bomberman");
        setSize(zutabe * tam, errenkada * tam);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        // JPanel-ari argazkia jarri fondoan
        JPanel boardPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (fondoIcon != null) {
                    g.drawImage(fondoIcon.getImage(), 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        boardPanel.setLayout(new GridLayout(errenkada, zutabe));

        jokoa = Jokoa.getJokoa();
    	jokoa.addObserver(this);
    	jokoa.hasiJokoa();
    	
        Gelaxka[][] matriz = jokoa.getLaberinto().getMatriz();
        // Tableroa hasieratu
        for (int i = 0; i < errenkada; i++) {
            for (int j = 0; j < zutabe; j++) {
                board[i][j] = new GelaxkaBista();
                boardPanel.add(board[i][j]);
                matriz[i][j].addObserver(board[i][j]);
            }
        }
        jokoa.bistaratu();
        
        add(boardPanel, BorderLayout.CENTER);       
        addKeyListener(getControler());
        setFocusable(true);
        requestFocusInWindow();
        setVisible(true);
    }        
    
   @Override
   public void update(Observable o, Object arg) {
    	if (arg instanceof Boolean) {
            boolean irabazi = (Boolean) arg;
            String mensaje = irabazi ? "Zorionak!! Irabazi duzu!!" : "Galdu duzu, saiatu berriro.";
            JOptionPane.showMessageDialog(this, mensaje, "Bukaera", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
            
    	}
	}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Partida());
    }
    
    
   /************************CONTROLER********************************/
    
    private Controler getControler() {
		if (nireControler == null) {
			nireControler = new Controler();
		}
		return nireControler;
    }
    
    private class Controler implements KeyListener {

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
	    public void keyPressed(KeyEvent e) {
	    	requestFocus();
	    	
	    	Bomberman bomberman = jokoa.getBomberman();
	    	
	    	int x = jokoa.getBomberman().getX();
		    int y = jokoa.getBomberman().getY();
		    

	        switch (e.getKeyCode()) {
		        case KeyEvent.VK_UP:    bomberman.mugitu(x - 1, y); break;
		        case KeyEvent.VK_DOWN:  bomberman.mugitu(x + 1, y); break;
		        case KeyEvent.VK_LEFT:  bomberman.mugitu(x, y - 1); break;
		        case KeyEvent.VK_RIGHT: bomberman.mugitu(x, y + 1); break;
		        case KeyEvent.VK_SPACE: bomberman.bonbaJarri(); break;
		        // Gainontzeko teklak ez dira kontuan hartuko  
		        default: return; 
	        }
	    }

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
		}	
    }	
}
