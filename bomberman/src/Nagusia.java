package main;

import javax.swing.SwingUtilities;
import bista.Menu;
import bista.Partida;
import eredua.Jokoa;


public class Nagusia {
	
	public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
        	Menu menu = new Menu();
        	menu.setVisible(true);            
        });
    }
}
