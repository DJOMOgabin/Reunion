package Vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import BD.LectureConfigReunion;

//Permet de retourner un panel ayant un label et un liste de valeur ci-contre
public class LabelAndCombox {
	private JPanel[] panel = new JPanel[3];
	private JComboBox<Integer> bouton;
	private JLabel text;
	private static int taille=0;
	
	//Il prend en paramètre le texte à écrire à côté et la taille de valeur maximal dans le combo
	public LabelAndCombox(String nomText,int taillelimite){
		for(int i=0;i<getPanel().length;i++){
			getPanel()[i]=new JPanel();
		}
		bouton = new JComboBox<Integer>();
		text = new JLabel();
		getText().setText(nomText);
		getText().setForeground(Color.gray);
		//Si la tailles entrée est inférieur à celle décrite lors de la création de la reunion
		//On mets cette dernière
		if(getTaille()<taillelimite)taillelimite=getTaille();		
		for(int i=1;i<=taillelimite;i++){
			bouton.addItem(i);
		}
		
		bouton.setBackground(Color.blue);
		getPanel()[1].setLayout(new FlowLayout(FlowLayout.CENTER));
		getPanel()[2].setLayout(new FlowLayout(FlowLayout.CENTER));
		
		getPanel()[1].add(getText());
		getPanel()[2].add(getBouton());		
		
		//getPanel()[0].setLayout(new GridLayout(1, 2));
		
		getPanel()[0].add(getPanel()[1]);
		getPanel()[0].add(getPanel()[2]);
		
		getText().setPreferredSize(new Dimension(290,40));
		getBouton().setPreferredSize(new Dimension(100,40));
		
	}

	public JPanel[] getPanel() {
		return panel;
	}

	public void setPanel(JPanel[] panel) {
		this.panel = panel;
	}

	public JComboBox<Integer> getBouton() {
		return bouton;
	}

	public void setBouton(JComboBox<Integer> bouton) {
		this.bouton = bouton;
	}

	public JLabel getText() {
		return text;
	}

	public void setText(JLabel text) {
		this.text = text;
	}

	public static int getTaille() {
		new LectureConfigReunion();
		taille = Integer.parseInt(LectureConfigReunion.getTaillaMax());
		return taille;
	}

}
