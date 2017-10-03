package Vue;

import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Fenetre{
	
	//La classe qui g�n�re toutes les fen�tres de l'application
	//Toutes les vues ayant des fen�tres h�ritent de celles-ci et les personnalisent
	private JFrame fenetre = new JFrame();
	public Fenetre(){
		fenetre.setTitle("Plateforme");
		fenetre.setLocationRelativeTo(null);
		fenetre.setResizable(true);
		fenetre.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		fenetre.setBackground(Color.blue);
		fenetre.addWindowListener(new WindowAdapter(){
			//L'action � ex�cuter lorsqu'on ferme la fen�tre
	    	public void windowClosing(WindowEvent e)
	    	{
	    		//Une boite de dialogue pour la confirmation
	    		int op = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment quitter?",
						"Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(op == JOptionPane.OK_OPTION)
				{
					fenetre.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				}
				
	    	}
	    });
		
	}
	public JFrame getFenetre(){
		return this.fenetre;
	}
}

