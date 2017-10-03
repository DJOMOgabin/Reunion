package Vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuBar;
import javax.swing.JPanel;

import Controlleur.ListeEmprunt;
import Vue.Accueil;

//Donne la possibilité de choisir si on veut lister tous les adhérents de la reunion ou uniquement les adhérents
//ayant les emprunts en cour
public class MenuEmprunt {
	
	private Fenetre fenetre;
	private JPanel contenu = new JPanel();
	private JPanel bottom = new JPanel();
	private Button contenu1 = new Button("Historique de tous les emprunts"); 
	private Button contenu2 = new Button("Les emprunts actifs"); 
	private Button contenu6 = new Button("Retour"); 
	private JMenuBar conteneur;

	public MenuEmprunt(Fenetre fene){

		fenetre = fene;
		fenetre.getFenetre().setTitle("Les emprunts");
		fenetre.getFenetre().setSize(new Dimension(500,500));
		fenetre.getFenetre().setResizable(false);
		fenetre.getFenetre().setLocationRelativeTo(null);
		remplissage();
		conteneur = new Conteneur(getFenetre()).getMenuBar();
		fenetre.getFenetre().getContentPane().add(conteneur,BorderLayout.NORTH);
		fenetre.getFenetre().getContentPane().add(contenu,BorderLayout.CENTER);
		fenetre.getFenetre().getContentPane().add(bottom,BorderLayout.SOUTH);
		fenetre.getFenetre().setVisible(true);
	}
	
	public void remplissage(){
		getContenu().setLayout(new GridLayout(4,1));
		getContenu().add(new JPanel());
		getContenu().add(getContenu1().getPanel());
		getContenu().add(getContenu2().getPanel());
		getContenu().add(new JPanel());

		getBottom().setLayout(new GridLayout(1,2));
		
		getContenu6().getBouton().setPreferredSize(new Dimension(100, 30));
		getBottom().add(getContenu6().getPanel());
		getBottom().add(new JPanel());
		
		//Les listeners
		//Le bouton retour
		getContenu6().getBouton().addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				getFenetre().getFenetre().getContentPane().removeAll();
				getConteneur().removeAll();
				new Accueil(getFenetre());			
			}
		});
		
		//tous les emprunts
		getContenu1().getBouton().addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				getFenetre().getFenetre().getContentPane().removeAll();
				getConteneur().removeAll();
				new ListeEmprunt(getFenetre(),false);
			}
		});

		//Les emprunts en cours
		getContenu2().getBouton().addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				getFenetre().getFenetre().getContentPane().removeAll();
				getConteneur().removeAll();
				new ListeEmprunt(getFenetre(),true);
			}
		});
	}

	public JMenuBar getConteneur() {
		return conteneur;
	}

	public void setConteneur(JMenuBar conteneur) {
		this.conteneur = conteneur;
	}

	public Fenetre getFenetre() {
		return fenetre;
	}

	public void setFenetre(Fenetre fenetre) {
		this.fenetre = fenetre;
	}

	public JPanel getContenu() {
		return contenu;
	}

	public void setContenu(JPanel contenu) {
		this.contenu = contenu;
	}

	public JPanel getBottom() {
		return bottom;
	}

	public void setBottom(JPanel bottom) {
		this.bottom = bottom;
	}

	public Button getContenu1() {
		return contenu1;
	}

	public void setContenu1(Button contenu1) {
		this.contenu1 = contenu1;
	}

	public Button getContenu2() {
		return contenu2;
	}

	public void setContenu2(Button contenu2) {
		this.contenu2 = contenu2;
	}

	public Button getContenu6() {
		return contenu6;
	}

	public void setContenu6(Button contenu6) {
		this.contenu6 = contenu6;
	}
	
}
