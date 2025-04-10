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

        // Atzealdeko argazkia kargatu
        backgroundImage = new ImageIcon("src/resources/background.jpg").getImage();

        // Aukeratutako atzealdearekin panela sortu
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(new GridLayout(3, 2));

        // Aukerak
        labirintoa = new JComboBox<>(new String[]{"Classic", "Soft"});
        bomberman = new JComboBox<>(new String[]{"Normal", "Black"});
        startButton = new JButton("PARTIDA HASI");

        backgroundPanel.add(new JLabel("Labirinto mota aukeratu:"));
        backgroundPanel.add(labirintoa);
        backgroundPanel.add(new JLabel("Bomberman mota aukeratu:"));
        backgroundPanel.add(bomberman);
        backgroundPanel.add(new JLabel("")); // Hutsik
        backgroundPanel.add(startButton);

        setContentPane(backgroundPanel);

        // Botoiaren ekintza
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String aukeratutakoLabirintoa = (String) labirintoa.getSelectedItem();
                String aukeratutakoBomberman = (String) bomberman.getSelectedItem();
                //JOptionPane.showMessageDialog(null, "Zure aukerak: " + aukeratutakoLabirintoa + " y " + aukeratutakoBomberman);
                Jokoa nJ= Jokoa.getJokoa();
                if (aukeratutakoBomberman == "White") {
                	if (aukeratutakoLabirintoa == "Classic") {
                    	nJ.hasiJokoa("Classic", "White");
                    }
                    else if (aukeratutakoLabirintoa == "Soft"){
                    	nJ.hasiJokoa("Soft", "White");
                    }
                }	
                else {
                	if (aukeratutakoLabirintoa == "Classic") {
                		nJ.hasiJokoa("Classic", "Black");
                    }
                    else if (aukeratutakoLabirintoa == "Soft"){
                    	nJ.hasiJokoa("Soft", "Black");	
                    }
                }
                dispose(); // Leihoa itxi
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

