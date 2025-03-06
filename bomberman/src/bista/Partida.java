package bista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import eredua.Bomberman;
import eredua.Bonba;
import eredua.Classic;
import eredua.Jokoa;
import eredua.Laberinto;
import eredua.White;

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
    private Jokoa jokoa;
    private Laberinto laberinto;
    private White bomberman;
    private int oldX, oldY;
    
    private ImageIcon blokGoIcon = loadImage("/irudiak/hard5.png");
    private ImageIcon blokBigIcon = loadImage("/irudiak/soft1.png");
    private ImageIcon bomberIcon = loadImage("/irudiak/whitefront1.png");
    private ImageIcon fondoIcon = loadImage("/irudiak/stageBack1.png");
    private ImageIcon bonbaIcon = loadImage("/irudiak/bomb1.png");

    
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
    	bomberman = new White(0,0,laberinto);
    	jokoa = Jokoa.getJokoa();
    	jokoa.hasiJokoa(bomberman,laberinto);
    	jokoa.addObserver(this);
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
                if (fondoIcon != null) {
                    g.drawImage(fondoIcon.getImage(), 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        boardPanel.setLayout(new GridLayout(errenkada, zutabe));

        // Inicializar el tablero de etiquetas
        for (int i = 0; i < errenkada; i++) {
            for (int j = 0; j < zutabe; j++) {
                board[i][j] = new JLabel();
                boardPanel.add(board[i][j]);
            }
        }

        add(boardPanel, BorderLayout.CENTER);
        
        // Crear el laberinto
        laberinto.sortuLaberinto();
        tableroEguneratu();
        
        addKeyListener(this);
        setFocusable(true);
        requestFocusInWindow();
        setVisible(true);
    }    
    
    private void tableroEguneratu() {
        for (int i = 0; i < errenkada; i++) {
            for (int j = 0; j < zutabe; j++) {
                if (laberinto.getGelaxkaPos(i, j).blokeDu()) {
                    if (laberinto.getGelaxkaPos(i, j).apurtuDaiteke()) {
                        board[i][j].setIcon(blokBigIcon); // Bloke biguna
                    } else {
                        board[i][j].setIcon(blokGoIcon); // Bloke gogorra
                    }
                } else {
                    board[i][j].setIcon(null); // nada
                }
            }
        }
        
        // Bomberman en su posición actual
        board[bomberman.getX()][bomberman.getY()].setIcon(bomberIcon);
    }


    @Override
    public void keyPressed(KeyEvent e) {
    	requestFocus();
    	
    	oldX = bomberman.getX();
    	oldY = bomberman.getY();
    	
        int newX = oldX;
        int newY = oldY;

        switch (e.getKeyCode()) {
	        case KeyEvent.VK_UP:    if (oldX > 0) newX--; break;
	        case KeyEvent.VK_DOWN:  if (oldX < errenkada - 1) newX++; break;
	        case KeyEvent.VK_LEFT:  if (oldY > 0) newY--; break;
	        case KeyEvent.VK_RIGHT: if (oldY < zutabe - 1) newY++; break;
	        case KeyEvent.VK_SPACE: jokoa.kokatuBonba(); break;
	        default: return; // ignorar teclas no validas
	    }
        
        // Solo mover si las coordenadas son distintas y no hay bloke gogorra
        if ((newX != oldX || newY != oldY) && (!laberinto.getGelaxkaPos(newX, newY).blokeDu())) {
        	bomberman.mugitu(newX, newY);
        	tableroEguneratu();
        }
    }
    
    @Override
	public void update(Observable o, Object arg) {
		if (o instanceof Bomberman) {
			Bomberman b = (Bomberman) o;
			
			// Borrar icono en la posicion antigua
			board[oldX][oldY].setIcon(null);
		
			// Refrescar solo la casilla antigua
			board[oldX][oldY].repaint();
	        
			// Obtener la posicion nueva
			int newX = b.getX();
			int newY = b.getY();
			
			// Colocar icono en la posicion nueva
			board[newX][newY].setIcon(bomberIcon);
			
			// Refrescar solo la casilla nueva
			board[newX][newY].repaint();
			
			// Actualizar las coordenadas
			oldX = newX;
			oldY = newY;
			
			requestFocusInWindow(); // Mantiene el foco en la ventana
		}
		
		if (o instanceof Jokoa) {
			Jokoa jokoa = (Jokoa) o;
			Bonba bonba = jokoa.getBonba();
			
			if (bonba != null) {
				board[bonba.getX()][bonba.getY()].setIcon(bonbaIcon);
			}
			else {
				if (oldX != -1 && oldY != -1) {
					board[oldX][oldY].setIcon(null);
				}
			}
		}
	}

    @Override public void keyReleased(KeyEvent e) {}
    @Override public void keyTyped(KeyEvent e) {}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Partida());
    }
}
