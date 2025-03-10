package bista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import eredua.Bomberman;
import eredua.Bonba;
import eredua.Classic;
import eredua.Gelaxka;
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
    private int oldX, oldY, bonbaX = -1, bonbaY = -1;
    
    private ImageIcon fondoIcon = loadImage("/irudiak/stageBack1.png");
    private ImageIcon blokGoIcon = loadImage("/irudiak/hard5.png");
    private ImageIcon blokBigIcon = loadImage("/irudiak/soft1.png");
    private ImageIcon bomberIcon = loadImage("/irudiak/whitefront1.png");
    private ImageIcon bonbaIcon = loadImage("/irudiak/bomb1.png");
    private ImageIcon fuegoIcon = loadImage("/irudiak/kaBomb2.png");
    private ImageIcon whiteConBonbaIcon = loadImage("/irudiak/whitewithbomb1.png");
    
    
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
            	Gelaxka g = laberinto.getGelaxkaPos(i, j);
            	if (g.suaDago()) {
            		board[i][j].setIcon(fuegoIcon);
            	}
            	else if (g.blokeDu()) {
            		if (g.apurtuDaiteke()) {
                        board[i][j].setIcon(blokBigIcon); // Bloke biguna
                    } 
            		else {
                        board[i][j].setIcon(blokGoIcon); // Bloke gogorra
                    }
                } 
            	else {
                    board[i][j].setIcon(null); // nada
                }                           
            }
        }
        
     // Mantener la imagen de la bomba si está en una casilla
        if (bonbaX != -1 && bonbaY != -1) {
            board[bonbaX][bonbaY].setIcon(bonbaIcon);
        }
        
        // Bomberman en su posición actual
        board[bomberman.getX()][bomberman.getY()].setIcon(bomberIcon);
    }
    
 // Solo mover si las coordenadas son distintas y no hay bloke gogorra
    private void bombermanMugitu(int x, int y) {
        if ((x != oldX || y != oldY) && (!laberinto.getGelaxkaPos(x, y).blokeDu())) {
        	bomberman.mugitu(x, y);
        	tableroEguneratu();
        }
    }
    
    private void bonbaJarri() {
    	// Solo colocar una bomba si no hay otra
    	if (bonbaX == -1 && bonbaY == -1) {
    		bonbaX = bomberman.getX();
    		bonbaY = bomberman.getY();
    		board[bonbaX][bonbaY].setIcon(whiteConBonbaIcon);
    		
    		new Thread(() -> {
                try {
                    Thread.sleep(3000); // Tiempo hasta la explosión
                    eztandaEguneratu(bonbaX, bonbaY);
                    
                    Thread.sleep(2000); // Tiempo antes de limpiar el fuego
                    garbituSua(bonbaX, bonbaY);
                    bonbaX = -1;
                    bonbaY = -1;
                    tableroEguneratu();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }).start();
    	}	
    }
    
    // Colocar fuego en las casillas afectadas por la bomba
    private void eztandaEguneratu(int x, int y) {
    	laberinto = Jokoa.getJokoa().getLaberinto();
    	
    	if (x >= 0 && x < errenkada && y >= 0 && y < zutabe) {
    		board[x][y].setIcon(fuegoIcon);
    	}
    	if (x + 1 >= 0 && x + 1 < errenkada) {
    		board[x+1][y].setIcon(fuegoIcon);
    	}
    	if (x - 1 >= 0 && x - 1 < errenkada) {
    		board[x-1][y].setIcon(fuegoIcon);
    	}
    	if (y + 1 >= 0 && y + 1 < zutabe) {
    		board[x][y+1].setIcon(fuegoIcon);
    	}
    	if (y - 1 >= 0 && y - 1 < zutabe) {
    		board[x][y-1].setIcon(fuegoIcon);
    	}
    }
    
    // Quitar fuego en las casillas afectadas por la bomba
    private void garbituSua(int x, int y) {
    	laberinto = Jokoa.getJokoa().getLaberinto();
    	
    	if (x >= 0 && x < errenkada && y >= 0 && y < zutabe) {
    		board[x][y].setIcon(null);
    	}
    	if (x + 1 >= 0 && x + 1 < errenkada) {
    		board[x+1][y].setIcon(null);
    	}
    	if (x - 1 >= 0 && x - 1 < errenkada) {
    		board[x-1][y].setIcon(null);
    	}
    	if (y + 1 >= 0 && y + 1 < zutabe) {
    		board[x][y+1].setIcon(null);
    	}
    	if (y - 1 >= 0 && y - 1 < zutabe) {
    		board[x][y-1].setIcon(null);
    	}
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
	        case KeyEvent.VK_SPACE: bonbaJarri(); break;
	          
	        default: return; // ignorar teclas no validas 
	    }
        
        bombermanMugitu(newX, newY);
    }
    
    @Override
	public void update(Observable o, Object arg) {
		if (o instanceof Bomberman) {
			Bomberman b = (Bomberman) o;
			
			// Borrar la imagen de Bomberman en la posición anterior, pero no la bomba
            if (!(oldX == bonbaX && oldY == bonbaY)) {
                board[oldX][oldY].setIcon(null);
            }
			
            // Obtener la nueva posición de Bomberman
            int newX = b.getX();
            int newY = b.getY();
            
            // Colocar la imagen de Bomberman en la nueva posición
            board[newX][newY].setIcon(bomberIcon);
            
            // Si hay una bomba en su posición anterior, asegurarse de que siga ahí
            if (bonbaX != -1 && bonbaY != -1) {
                board[bonbaX][bonbaY].setIcon(bonbaIcon);
            }
            
            // Actualizar las coordenadas
            oldX = newX;
            oldY = newY;
 
			requestFocusInWindow(); // Mantiene el foco en la ventana
		}
		
		if (o instanceof Jokoa) {
			Jokoa jokoa = (Jokoa) o;
			Bonba bonba = jokoa.getBonba();
			
			if (bonba != null) {				
				int bx = bonba.getX();
			    int by = bonba.getY();

			    System.out.println("Bonba kokatu da: (" + bx + ", " + by + ")");

			    if (bx >= 0 && bx < errenkada && by >= 0 && by < zutabe) {
			        board[bx][by].setIcon(bonbaIcon);
			    } else {
			        System.out.println("ERROR: Bonba limiteen kanpoan dago: (" + bx + ", " + by + ")");
			    }
			}
			
			// Esperar 3 segundos, mostrar el fuego y quitarlo
            new Thread(() -> {
                try {
                    Thread.sleep(3000);
                    eztandaEguneratu(getX(),getY());
                    
                    Thread.sleep(2000);
                    garbituSua(getX(),getY());
                    bonbaX = -1;
                    bonbaY = -1;
                    
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
		}
	}

    @Override public void keyReleased(KeyEvent e) {}
    @Override public void keyTyped(KeyEvent e) {}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Partida());
    }

	
}
