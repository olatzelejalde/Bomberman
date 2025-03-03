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
    private ImageIcon fondo = loadImage("/irudiak/stageBack1.png");

    
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
    	laberinto = new Classic();
    	bomberman = new Bomberman(0,0,laberinto);
    	bomberman.addObserver(this);

    	setTitle("Bomberman");
        setSize(zutabe * tam, errenkada * tam);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        
        // Panel del tablero con fondo
        JPanel boardPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (fondo != null) {
                    g.drawImage(fondo.getImage(), 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        boardPanel.setLayout(new GridLayout(errenkada, zutabe));

        for (int i = 0; i < errenkada; i++) {
            for (int j = 0; j < zutabe; j++) {
                board[i][j] = new JLabel();
                boardPanel.add(board[i][j]);
            }
        }

        add(boardPanel, BorderLayout.CENTER);
        
        laberinto.sortuLaberinto();
        irudiakJarri();
        
        addKeyListener(this);
        setFocusable(true);
        requestFocusInWindow();
        setVisible(true);
    }    
    
    private void irudiakJarri() {
        for (int i = 0; i < errenkada; i++) {
            for (int j = 0; j < zutabe; j++) {
                if (laberinto.getGelaxkaPos(i, j).blokeDu() == true) {
                    if (laberinto.getGelaxkaPos(i, j).apurtuDaiteke()) {
                        board[i][j].setIcon(blokBig); // Bloke biguna
                    } else {
                        board[i][j].setIcon(blokGo); // Bloke gogorra
                    }
                } else {
                    board[i][j].setIcon(null); // nada
                }
            }
        }
        
        // Bomberman en su posición inicial
        board[bomberman.getX()][bomberman.getY()].setIcon(bombermanIcon);
    }


    @Override
    public void keyPressed(KeyEvent e) {
    	requestFocus();
    	int oldX = bomberman.getX();
    	int oldY = bomberman.getY();
        int newX = oldX;
        int newY = oldY;

        switch (e.getKeyCode()) {
	        case KeyEvent.VK_UP:    if (oldX > 0) newX--; break;
	        case KeyEvent.VK_DOWN:  if (oldX < errenkada - 1) newX++; break;
	        case KeyEvent.VK_LEFT:  if (oldY > 0) newY--; break;
	        case KeyEvent.VK_RIGHT: if (oldY < zutabe - 1) newY++; break;
	        default: return; // ignorar teclas no validas
	    }
        
        // Solo mover si las coordenadas son distintas
        if (newX != oldX || newY != oldY) {
        	bomberman.mugitu(newX, newY, laberinto);
            revalidate();
            repaint();
        }
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
			requestFocusInWindow(); // Mantiene el foco en la ventana
	        revalidate();
	        repaint();
		}
		
	}

    @Override public void keyReleased(KeyEvent e) {}
    @Override public void keyTyped(KeyEvent e) {}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Partida());
    }

	
}
