package bista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import eredua.Bomberman;
import eredua.Classic;
import eredua.Laberinto;

import javax.swing.JProgressBar;
import java.awt.FlowLayout;
import javax.swing.JSplitPane;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;
import java.awt.*;

public class Partida extends JFrame implements KeyListener, Observer {
    private static final int errenkada = 11;
    private static final int zutabe = 17;
    private static final int tam = 40;

    private JLabel[][] board = new JLabel[errenkada][zutabe];
    private Laberinto laberinto;
    private Bomberman bomberman;
    private ImageIcon blokGo = loadImage("/irudiak/hard5.png");
    private ImageIcon blokBig = loadImage("/irudiak/soft1.png");
    private ImageIcon bombermanIcon = loadImage("/irudiak/whitefront1.png");

    private ImageIcon loadImage(String path) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
        	System.out.println("Error cargando imagen: " + path);
            return null;
        }
    }
    
    public Partida() {
    	// hasierako posizioan jarri
    	laberinto = new Classic();
    	bomberman = new Bomberman(0,0,laberinto);
    	bomberman.addObserver(this);

    	setTitle("Bomberman");
        setSize(zutabe * tam, errenkada * tam);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(errenkada, zutabe));

        // Laberintoa osatu
        for (int err = 0; err < errenkada; err++) {
            for (int zut = 0; zut < zutabe; zut++) {
            	//int etsaiKop = 0;
                JLabel gelaxka = new JLabel();
                gelaxka.setOpaque(true);
                gelaxka.setPreferredSize(new Dimension(tam, tam));

                // blokeak jarri
                if (err % 2 != 0 && zut % 2 != 0) {
                	gelaxka.setIcon(blokGo); // Bloke gogorrak
                }
                else if (Math.random() > 0.4) {
                	gelaxka.setIcon(blokBig); // Bloke bigunak
                //} 
                //else if (Math.random() > 0.9 && etsaiKop < 6) { 
                	//gelaxka.setIcon(etsaia); // Etsaiak
                	//etsaiKop++;
                }

                board[err][zut] = gelaxka;
                boardPanel.add(gelaxka);
            }
        }

        // Bomberman gelaxkan kokatu
        board[bomberman.getX()][bomberman.getY()].setIcon(bombermanIcon);
        add(boardPanel, BorderLayout.CENTER);
        addKeyListener(this);
        setFocusable(true);
        requestFocusInWindow();
        setVisible(true);
        revalidate();
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
    	requestFocus();
        int newX = bomberman.getX();
        int newY = bomberman.getY();

        switch (e.getKeyCode()) {
	        case KeyEvent.VK_UP:    if (newX > 0) newX--; break;
	        case KeyEvent.VK_DOWN:  if (newX < errenkada - 1) newX++; break;
	        case KeyEvent.VK_LEFT:  if (newY > 0) newY--; break;
	        case KeyEvent.VK_RIGHT: if (newY < zutabe - 1) newY++; break;
	    }
        
        bomberman.mugitu(newX, newY, laberinto);
    }
    
    @Override
	public void update(Observable o, Object arg) {
		if (o instanceof Bomberman) {
			Bomberman b = (Bomberman) o;
			for (int i = 0; i < errenkada; i++) {
				for (int j = 0; j < zutabe; j++) {
					if (board[i][j].getIcon() == bombermanIcon) {
						board[i][j].setIcon(null);
					}
				}
			}
			
			board[b.getX()][b.getY()].setIcon(bombermanIcon);
		}
		
	}

    @Override public void keyReleased(KeyEvent e) {}
    @Override public void keyTyped(KeyEvent e) {}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Partida());
    }

	
}
