package bista;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;
import java.util.Observer;


public class Menu extends JFrame implements Observer {
	private JComboBox<String> cmbLaberinto, cmbJokalari;
	private JButton btnStart;
	private JLabel lblTitle, lblLaberinto, lblJokalari;
	private Controler nireControler;

	private ImageIcon fondoIcon = loadImage("/irudiak/background.png");
    
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
	
	public Menu() {
		setTitle("HASIERAKO MENU");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (fondoIcon != null) {
                	g.drawImage(fondoIcon.getImage(), 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 50, 50));
		
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 0, 5, 0);

        JLabel lblTitle = new JLabel("", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 48));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));

        // Panel de controles con transparencia
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new GridBagLayout());
        controlPanel.setOpaque(false);
        controlPanel.setBorder(BorderFactory.createEmptyBorder(15, 30, 15, 30));

        // Configuración de controles
        Font labelFont = new Font("Arial", Font.BOLD, 15);
        Font comboFont = new Font("Arial", Font.PLAIN, 15);

        // Combo Laberinto
        JLabel lblLaberinto = new JLabel("Laberinto mota");
        lblLaberinto.setFont(labelFont);
        lblLaberinto.setForeground(Color.WHITE);
        
        cmbLaberinto = new JComboBox<>(new String[]{"Classic", "Soft", "Empty"});
        cmbLaberinto.setFont(comboFont);
        cmbLaberinto.setBackground(new Color(255, 255, 255, 200));
        cmbLaberinto.setMaximumRowCount(3);

        // Combo Jokalari
        JLabel lblJokalari = new JLabel("Jokalari mota");
        lblJokalari.setFont(labelFont);
        lblJokalari.setForeground(Color.WHITE);
	cmbJokalari.setSelectedIndex(0);
        
        cmbJokalari = new JComboBox<>(new String[]{"White", "Black"});
        cmbJokalari.setFont(comboFont);
        cmbJokalari.setBackground(new Color(255, 255, 255, 200));
	cmbJokalari.setSelectedIndex(0);
		
        // Botón Start
        btnStart = new JButton("START GAME");
        btnStart.setFont(new Font("Arial", Font.BOLD, 15));
        btnStart.setBackground(new Color(70, 130, 180));
        btnStart.setForeground(Color.WHITE);
        btnStart.setFocusPainted(false);
        btnStart.setPreferredSize(new Dimension(150, 35));
        btnStart.setBorder(BorderFactory.createCompoundBorder(
        	    BorderFactory.createLineBorder(new Color(30, 80, 130), 1), 
        	    BorderFactory.createEmptyBorder(5, 15, 5, 15)
        	));
        btnStart.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Añadir componentes al panel de controles
        GridBagConstraints gbcControl = new GridBagConstraints();
        gbcControl.gridwidth = GridBagConstraints.REMAINDER;
        gbcControl.fill = GridBagConstraints.HORIZONTAL;
        gbcControl.insets = new Insets(5, 0, 5, 0);

        controlPanel.add(lblLaberinto, gbcControl);
        controlPanel.add(cmbLaberinto, gbcControl);
        controlPanel.add(Box.createRigidArea(new Dimension(0, 10)), gbcControl);
        controlPanel.add(lblJokalari, gbcControl);
        controlPanel.add(cmbJokalari, gbcControl);

        // Añadir componentes al panel principal
        mainPanel.add(lblTitle, gbc);
        mainPanel.add(controlPanel, gbc);
        mainPanel.add(btnStart, gbc);

        // Añadir panel principal al JFrame
        add(mainPanel);

        // Configurar acción del botón
        btnStart.addActionListener(getControler());
				
	}
    
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
    
    
    
    /************************CONTROLER********************************/
    
    private Controler getControler() {
		if (nireControler == null) {
			nireControler = new Controler();
		}
		return nireControler;
    }
    
    private class Controler implements ActionListener {

    	@Override
    	public void actionPerformed(ActionEvent e) {
    	    if (e.getSource() == btnStart) {
    	        String laberintoMota = (String) cmbLaberinto.getSelectedItem();
    	        String jokalariMota = (String) cmbJokalari.getSelectedItem();
    	        
    	        Menu.this.dispose(); // Itxi
    	        
    	        SwingUtilities.invokeLater(() -> {
                    Partida partida = new Partida(laberintoMota, jokalariMota);
                    partida.setVisible(true);
                });
    	    }
    	}	
    }

	
}
