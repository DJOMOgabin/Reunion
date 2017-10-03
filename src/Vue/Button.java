package Vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

//Une classe qui créer des boutons avec une dimension utilisable dans tous le programme,
//à l'exception de quelque différence près
public class Button {

	JPanel panel = new JPanel();
	JButton bouton;
	public Button(String nom){ 
		bouton = new JButton(nom);
		
		getPanel().setLayout(new FlowLayout(FlowLayout.CENTER));
		getBouton().setBackground(Color.blue);
		
		getPanel().add(bouton);
		getBouton().setPreferredSize(new Dimension(400,40));
	}
	public JPanel getPanel() {
		return panel;
	}
	public void setPanel(JPanel panel) {
		this.panel = panel;
	}
	public JButton getBouton() {
		return bouton;
	}
	public void setBouton(JButton bouton) {
		this.bouton = bouton;
	}
	
}
