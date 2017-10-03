package Vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JCheckBox;
import javax.swing.JPanel;

//La classe qui gère le comboBox, il est présent pour empêcher les utilisateurs 
//de souscrire ou d'emprunter des parts qui ne sont pas
//de multiple de 5000 FCFA
public class CheckBox {

	JPanel panel = new JPanel();
	JCheckBox check;
	public CheckBox(String nom){ 
		check = new JCheckBox(nom);
		
		getPanel().setLayout(new FlowLayout(FlowLayout.CENTER));
		getCheck().setBackground(Color.blue);
		
		getPanel().add(check);
		getCheck().setPreferredSize(new Dimension(400,40));
	}
	public JPanel getPanel() {
		return panel;
	}
	public void setPanel(JPanel panel) {
		this.panel = panel;
	}
	public JCheckBox getCheck() {
		return check;
	}
	public void setCheck(JCheckBox check) {
		this.check = check;
	}
	
}
