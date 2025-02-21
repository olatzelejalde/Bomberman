package bista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JProgressBar;
import java.awt.FlowLayout;
import javax.swing.JSplitPane;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;
import java.awt.*;

public class Jokoa extends JFrame implements KeyListener {
    private static final int errenkada = 11;
    private static final int zutabe = 17;
    private static final int tam = 40;

    private JLabel[][] board = new JLabel[errenkada][zutabe];
    private int bZ = 0;
    private int bE = 0; // Bomberman-en hasierako posizioa

    private ImageIcon blokGo = new ImageIcon("hard5.png");
    private ImageIcon blokBig = new ImageIcon("soft1.png");
    private ImageIcon bomberman = new ImageIcon("whitefront1.png");
    private ImageIcon etsaia = new ImageIcon("doria2.png");
    
    public Jokoa() {
        setTitle("Bomberman");
        setSize(zutabe * tam, errenkada * tam);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(errenkada, zutabe));

        // Labirintoa osatu
        for (int err = 0; err < errenkada; err++) {
            for (int zut = 0; zut < zutabe; zut++) {
            	int etsaiKop = 0;
                JLabel gelaxka = new JLabel();
                gelaxka.setOpaque(true);
                gelaxka.setPreferredSize(new Dimension(tam, tam));

                if (err % 2 == 0 && zut % 2 == 0) {
                	gelaxka.setIcon(blokGo); // Bloke gogorrak
                } else if (Math.random() > 0.4) {
                	gelaxka.setIcon(blokBig); // Bloke biguna
                } else if (Math.random() > 0.9 && etsaiKop < 6) { 
                	gelaxka.setIcon(etsaia); // Etsaia
                	etsaiKop++;
                }

                board[err][zut] = gelaxka;
                boardPanel.add(gelaxka);
            }
        }

        // Bomberman gelaxkan kokatu
        board[bE][bZ].setIcon(bomberman);

        add(boardPanel);
        addKeyListener(this);
        setFocusable(true);
        setVisible(true);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int eBerria = bE; // Fila = Errenkada
        int zBerria = bZ; // Columna = Zutabe

        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP: eBerria--; break;
            case KeyEvent.VK_DOWN: eBerria++; break;
            case KeyEvent.VK_LEFT: zBerria--; break;
            case KeyEvent.VK_RIGHT: zBerria++; break;
        }

        // Blokea ez den egiaztatu
        if (board[eBerria][zBerria].getIcon() != blokBig || board[eBerria][zBerria].getIcon() != blokGo) {
            board[bE][bZ].setIcon(null); // 
            bE = eBerria;
            bZ = zBerria;
            board[bE][bZ].setIcon(bomberman); // Irudia mugitu
        }
    }

    @Override public void keyReleased(KeyEvent e) {}
    @Override public void keyTyped(KeyEvent e) {}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Jokoa());
    }
}
