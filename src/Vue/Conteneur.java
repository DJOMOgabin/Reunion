package Vue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import BD.CreationReunion;
import BD.LectureConfigReunion;
import Controlleur.ListeDefaillant;
import Controlleur.ListeEmprunt;
import Model.Reunion;

//Cette classe me permet de créer tous mes conteneurs
public class Conteneur {
 
	private static JMenuBar menuBar = new JMenuBar();
	private JMenu[] menu = new JMenu[4];
	private JMenuItem[] menuItem = new JMenuItem[13];
	private Fenetre fenetre;
	
	public Conteneur(Fenetre fenetre){
		this.fenetre=fenetre;
		construction();
	}

	//La classe qui gère le menu des pages, il est identique sur toutes les pages
	public void construction(){
		for(int i=0;i<menu.length;i++){
			getMenu()[i] = new JMenu();
		}
		for(int i=0;i<menuItem.length;i++){
			getMenuItem()[i]= new JMenuItem();
		}
		
		//Les différents menus
		getMenu()[0].setText("Fichier");
		getMenu()[1].setText("About");
		getMenu()[2].setText("Liste");
		getMenu()[3].setText("Exit");
		
		
		//Les differents itemMenus
		getMenuItem()[0].setText("Accueil");
		getMenuItem()[1].setText("Souscription");
		//getMenuItem()[2].setText("Emprunt");
		getMenuItem()[3].setText("Reunion");
		getMenuItem()[4].setText("Liste des emprunts");
		getMenuItem()[5].setText("Emprunts en cour");
		getMenuItem()[6].setText("Récapitulatif global");
		getMenuItem()[7].setText("Close");
		getMenuItem()[8].setText("About DJANGUI");
		getMenuItem()[9].setText("About US");
		getMenuItem()[10].setText("Exit Appli");
		getMenuItem()[11].setText("Delete DJANGUI");
		getMenuItem()[12].setText("Adhérents défectueux");
		
		//Remplissage du menu
		getMenu()[0].add(getMenuItem()[0]);
		getMenu()[0].addSeparator();
		getMenu()[0].add(getMenuItem()[1]);
		//getMenu()[0].add(getMenuItem()[2]);
		getMenu()[0].addSeparator();
		getMenu()[0].add(getMenu()[2]);
		getMenu()[0].add(getMenuItem()[3]);
		getMenu()[0].addSeparator();
		getMenu()[0].add(getMenuItem()[7]);
		
		getMenu()[2].add(getMenuItem()[4]);
		getMenu()[2].add(getMenuItem()[5]);
		getMenu()[2].add(getMenuItem()[6]);
		getMenu()[2].add(getMenuItem()[12]);
		

		getMenu()[3].add(getMenuItem()[10]);
		getMenu()[3].add(getMenuItem()[11]);

		getMenu()[1].add(getMenuItem()[8]);
		getMenu()[1].add(getMenuItem()[9]);
		
		
		getMenuBar().add(getMenu()[0]);
		getMenuBar().add(getMenu()[1]);
		getMenuBar().add(getMenu()[3]);
		
		
		
		//Les listeners
		//Fermer la fenêtre
		getMenuItem()[10].addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				getFenetre().getFenetre().getContentPane().removeAll();
				getMenuBar().removeAll();
				new Authentification(getFenetre());
				
			}
		});
		getMenuItem()[7].addActionListener(new fermer());
		//Recapitulatif global
		getMenuItem()[6].addActionListener(new Revenu(getMenuBar()));
		//Souscrire aux parts
		getMenuItem()[1].addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				new Part(false, "", null, getFenetre(),getMenuBar());				
			}
		});
		//Reunion
		getMenuItem()[3].addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				JPanel info = Accueil.information();
				JOptionPane.showConfirmDialog(null,info,
						"Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
				
			}
		});
		//Supprimer la reunion
		getMenuItem()[11].addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				int pro=JOptionPane.showConfirmDialog(null,"Voulez-vous vraiment supprimer la reunion?",
						"Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(pro==JOptionPane.OK_OPTION){
					new LectureConfigReunion();
					Reunion reunion = CreationReunion.ChargerReunion(LectureConfigReunion.getUserName());
					CreationReunion.SupprimerReunion(reunion);
					LectureConfigReunion.SupprimerFichier();
					JOptionPane.showConfirmDialog(null,"La reunion a été supprimé",
							"Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
					getFenetre().getFenetre().getContentPane().removeAll();
					getMenuBar().removeAll();
					new CreerReunion(getFenetre());
				}
				
			}
		});
		//Liste des emprunts
		getMenuItem()[4].addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				getFenetre().getFenetre().getContentPane().removeAll();
				getMenuBar().removeAll();
				new ListeEmprunt(getFenetre(), false);
				
			}
		});
		//Liste des emprunts encours
		getMenuItem()[5].addActionListener(new ActionListener() {
					
			public void actionPerformed(ActionEvent arg0) {
				getFenetre().getFenetre().getContentPane().removeAll();
				getMenuBar().removeAll();
				new ListeEmprunt(getFenetre(), true);
				
			}
		});
		
		//Accueil
		getMenuItem()[0].addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				getFenetre().getFenetre().getContentPane().removeAll();
				getMenuBar().removeAll();
				new Accueil(getFenetre());
				
			}
		});
		
		//Les adhérents défectueux
		getMenuItem()[12].addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				getFenetre().getFenetre().getContentPane().removeAll();
				if(getMenuBar()!=null)getMenuBar().removeAll();
				new ListeDefaillant(getFenetre());
				
			}
		});
				
	}
	public JMenuBar getMenuBar() {
		return menuBar;
	}

	public static void setMenuBar(JMenuBar menuBar) {
		Conteneur.menuBar = menuBar;
	}

	public JMenu[] getMenu() {
		return menu;
	}

	public void setMenu(JMenu[] menu) {
		this.menu = menu;
	}

	public JMenuItem[] getMenuItem() {
		return menuItem;
	}

	public void setMenuItem(JMenuItem[] menuItem) {
		this.menuItem = menuItem;
	}

	public Fenetre getFenetre() {
		return fenetre;
	}

	public void setFenetre(Fenetre fenetre) {
		this.fenetre = fenetre;
	}
	//La classe qui permet de fermer une fenêtre
	public class fermer implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			int quitter = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment quitter?",
					"Générateur d'interface", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if(quitter== JOptionPane.OK_OPTION){
				System.exit(0);
			}
		}
	}
	class Revenu implements ActionListener{
		private JMenuBar conteneur;
		
		public Revenu(JMenuBar conteneur){
			this.conteneur=conteneur;
		}
		
		public void actionPerformed(ActionEvent arg0) {
			new ListeVolontaire(getFenetre(), this.conteneur);
		}
	}
}
