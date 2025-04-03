package bista;

import javax.swing.*;

import eredua.Black;
import eredua.Bomberman;
import eredua.Classic;
import eredua.Jokoa;
import eredua.Laberinto;
import eredua.Soft;
import eredua.White;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Hasiera extends JFrame {
    private JComboBox<String> labirintoa;
    private JComboBox<String> bomberman;
    private JButton startButton;
    private Image backgroundImage;

    public Hasiera() {
        setTitle("Labirintoa eta Bombermana aukeratu");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Cargar imagen de fondo
        backgroundImage = new ImageIcon("src/resources/background.jpg").getImage();

        // Panel con fondo personalizado
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(new GridLayout(3, 2));

        // Opciones
        labirintoa = new JComboBox<>(new String[]{"Classic", "Soft"});
        bomberman = new JComboBox<>(new String[]{"Normal", "Black"});
        startButton = new JButton("Iniciar Partida");

        backgroundPanel.add(new JLabel("Labirinto mota aukeratu:"));
        backgroundPanel.add(labirintoa);
        backgroundPanel.add(new JLabel("Bomberman mota aukeratu:"));
        backgroundPanel.add(bomberman);
        backgroundPanel.add(new JLabel("")); // Espacio vacío
        backgroundPanel.add(startButton);

        setContentPane(backgroundPanel);

        // Acción del botón
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String aukeratutakoLabirintoa = (String) labirintoa.getSelectedItem();
                String aukeratutakoBomberman = (String) bomberman.getSelectedItem();
                //JOptionPane.showMessageDialog(null, "Zure aukerak: " + aukeratutakoLabirintoa + " y " + aukeratutakoBomberman);
                Jokoa nJ= Jokoa.getJokoa();
                if (aukeratutakoBomberman == "White") {
                	if (aukeratutakoLabirintoa == "Classic") {
                    	nJ.hasiJokoa (new White(0,0,10), new Classic());
                    }
                    else if (aukeratutakoLabirintoa == "Soft"){
                    	nJ.hasiJokoa(new White(0,0,10), new Soft());
                    }
                }	
                else {
                	if (aukeratutakoLabirintoa == "Classic") {
                		nJ.hasiJokoa(new Black(0,0,0), new Classic());
                    }
                    else if (aukeratutakoLabirintoa == "Soft"){
                    	nJ.hasiJokoa(new Black(0,0,0), new Soft());	
                    }
                }
                dispose(); // Cierra la ventana actual
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Hasiera has = new Hasiera();
            has.setVisible(true);
        });
    }
}
