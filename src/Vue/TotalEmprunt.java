package Vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.itextpdf.text.Font;

import Model.NonVolontaire;

//Affiche tous les adhérents et dire s'ils ont un emprunt en cours ou pas
//a aussi la possibilité d'afficher seulement les emprunts en cours
//Donne la possibilité de voir les informations sur l'adhérents si nous cliquons sur lui(son nom)
public class TotalEmprunt {
	
	private Fenetre fenetre;
	private JPanel contenu = new JPanel();
	private JPanel bottom = new JPanel();
	private ArrayList<NonVolontaire> NonVolontaire;
	private Button contenu6 = new Button("Retour"); 
	private boolean actif;
	private JMenuBar conteneur;

	public JMenuBar getConteneur() {
		return conteneur;
	}

	public void setConteneur(JMenuBar conteneur) {
		this.conteneur = conteneur;
	}

	public TotalEmprunt(ArrayList<NonVolontaire> NonVolontaire,Fenetre fene,boolean actif){

		this.actif=actif;
		fenetre=fene;
		this.NonVolontaire = NonVolontaire;
		fenetre.getFenetre().setTitle("Les emprunts");
		fenetre.getFenetre().setSize(new Dimension(500,500));
		fenetre.getFenetre().setResizable(false);
		fenetre.getFenetre().setLocationRelativeTo(null);
		remplissage();
		conteneur = new Conteneur(getFenetre()).getMenuBar();
		fenetre.getFenetre().getContentPane().add(conteneur,BorderLayout.NORTH);
		fenetre.getFenetre().getContentPane().add(new JScrollPane(contenu),BorderLayout.CENTER);
		fenetre.getFenetre().getContentPane().add(bottom,BorderLayout.SOUTH);
		fenetre.getFenetre().setVisible(true);
	}
	//Il permet de savoir si on doit afficher tous les adhérents ou uniquement ceux dont les emprunts sont en cours
	public boolean isActif() {
		return actif;
	}

	public void setActif(boolean actif) {
		this.actif = actif;
	}

	//On rempli la fenêtre
	public void remplissage(){
		getContenu().setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel[] empruntGauche = new JLabel[getNonVolontaire().size()];
		JLabel[] empruntDroite = new JLabel[getNonVolontaire().size()];
		JPanel[] empruntPourDeux = new JPanel[getNonVolontaire().size()];
		JPanel[] gauche = new JPanel[getNonVolontaire().size()];
		JPanel[] droite = new JPanel[getNonVolontaire().size()];
		JPanel panel = new JPanel();
		String statut;
		
		panel.setLayout(new GridLayout(getNonVolontaire().size(),1));
		for(int i=0;i<getNonVolontaire().size();i++){
			empruntGauche[i] = new JLabel();
			empruntDroite[i] = new JLabel();
			gauche[i] = new JPanel();
			droite[i] = new JPanel();
			empruntPourDeux[i] = new JPanel();
			
			gauche[i].setLayout(new FlowLayout(FlowLayout.LEFT));
			droite[i].setLayout(new FlowLayout(FlowLayout.RIGHT));
			System.out.println(getNonVolontaire().get(i).getNom());
			empruntGauche[i].setFont(new java.awt.Font("Times new roman",Font.UNDERLINE,15));
			empruntDroite[i].setFont(new java.awt.Font("Times new roman",Font.UNDERLINE,15));
			//On change de couleur en fonction du statut
			if(getNonVolontaire().get(i).isEtat()) {
				empruntGauche[i].setForeground(Color.green);
				empruntDroite[i].setForeground(Color.green);
				statut="En cours";			
			}else {
				statut="En règle";
				empruntGauche[i].setForeground(Color.black);
				empruntDroite[i].setForeground(Color.black);
			}
			empruntGauche[i].setPreferredSize(new Dimension(200,20));
			empruntGauche[i].setPreferredSize(new Dimension(200,20));
			
			empruntGauche[i].setText(getNonVolontaire().get(i).getNom());
			empruntDroite[i].setText(statut);
			
			gauche[i].add(empruntGauche[i]);
			droite[i].add(empruntDroite[i]);
			
			empruntPourDeux[i].setLayout(new GridLayout(1, 2));
			
			empruntPourDeux[i].add(gauche[i]);
			empruntPourDeux[i].add(droite[i]);
			
			panel.add(empruntPourDeux[i]);
		}
		
		JLabel label;
		if(isActif()) label = new JLabel("Liste des Emprunts en cours");
		else label = new JLabel("Liste des Emprunts éffectués");
		label.setFont(new java.awt.Font("Algerian", Font.BOLD, 22));
		label.setForeground(Color.blue);
		label.setPreferredSize(new Dimension(400,40));
		
		getContenu().add(label);		
		getContenu().add(panel);

		getBottom().setLayout(new GridLayout(1,2));
		getContenu().setLayout(new FlowLayout(FlowLayout.CENTER));
		getContenu().setPreferredSize(new Dimension(450,390));
		
		getContenu6().getBouton().setPreferredSize(new Dimension(100, 30));
		getBottom().add(getContenu6().getPanel());
		getBottom().add(new JPanel());
		
		//Les listeners
		//Le bouton  retourné
		getContenu6().getBouton().addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				getConteneur().removeAll();
				getFenetre().getFenetre().getContentPane().removeAll();
				new MenuEmprunt(getFenetre());			
			}
		});
		//pour tous les adhérents listé
		for(int i=0;i<getNonVolontaire().size();i++){
			empruntPourDeux[i].addMouseListener(new Information(i));
		}
	}
	//Qui permet d'afficher les informations sur les adhérents
	public static JPanel information(NonVolontaire adherent){
		JPanel contenuGauche = new JPanel();
		JPanel[] GaucheInt;
		if(adherent.isEtat())GaucheInt = new JPanel[10];
		else GaucheInt = new JPanel[8];
		for(int i=0;i<GaucheInt.length;i++){
			GaucheInt[i] = new JPanel();
			GaucheInt[i].setPreferredSize(new Dimension(220,30));
			GaucheInt[i].setLayout(new FlowLayout(FlowLayout.LEFT));
		}
		
		GaucheInt[0].add(Accueil.label("Matricule: ",adherent.getMatricule()));
		GaucheInt[1].add(Accueil.label("Nom: ",adherent.getNom()));
		GaucheInt[2].add(Accueil.label("CNI: ",adherent.getNumeroCNI()));
		GaucheInt[3].add(Accueil.label("Date d'adhésion: ",""+adherent.getDateAdhesion()));
		GaucheInt[4].add(Accueil.label("Adresse: ",adherent.getAdresse()));
		GaucheInt[5].add(Accueil.label("Tel: ",adherent.getTelephone()));
				
		if(adherent.getStatut().equalsIgnoreCase("A")){
			GaucheInt[6].add(Accueil.label("Statut: ","[Actif]"));			
		}else if(adherent.getStatut().equalsIgnoreCase("D")){
			GaucheInt[6].add(Accueil.label("Statut: ","[Défaillant]"));			
		}else{
			GaucheInt[6].add(Accueil.label("Statut: ","[Exclu]"));			
		}

		if(adherent.isEtat()){
			GaucheInt[7].add(Accueil.label("Emprunt:"," [En cours]"));
		}else{
			GaucheInt[7].add(Accueil.label("Emprunt: ","[Aucun]"));
		}		

		if(adherent.isEtat()){
			GaucheInt[8].add(Accueil.label("Date limite:",""+adherent.getDateRembourser()));
			GaucheInt[9].add(Accueil.label("A Rembourser:",""+adherent.getMontantRembourser()+" FCFA"));
		}
				System.out.println(GaucheInt.length);
		contenuGauche.setPreferredSize(new Dimension(240,310));
		contenuGauche.setLayout(new GridLayout(GaucheInt.length, 1));
		
		for(int i=0;i<GaucheInt.length;i++){
			contenuGauche.add(GaucheInt[i]);
		}
		return contenuGauche;
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
	
	public Button getContenu6() {
		return contenu6;
	}

	public void setContenu6(Button contenu6) {
		this.contenu6 = contenu6;
	}

	public ArrayList<NonVolontaire> getNonVolontaire() {
		return NonVolontaire;
	}

	public void setNonVolontaire(ArrayList<NonVolontaire> NonVolontaire) {
		this.NonVolontaire = NonVolontaire;
	}
	
	class Information implements MouseListener{
		
		private int indice;
		
		public Information(int indice){
			this.indice=indice;
		}
			
			public void mouseReleased(MouseEvent e) {}
			
			public void mousePressed(MouseEvent e) {}
			
			public void mouseExited(MouseEvent e) {}
			
			public void mouseEntered(MouseEvent e) {}
			
			public void mouseClicked(MouseEvent e) {
				JPanel info = information(getNonVolontaire().get(getIndice()));
				JOptionPane.showConfirmDialog(null,info,
						"Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
				
			}

			public int getIndice() {
				return indice;
			}

			public void setIndice(int indice) {
				this.indice = indice;
			}
			
			
	}
	
}
