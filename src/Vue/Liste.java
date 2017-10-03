package Vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import BD.CreationAdherent;
import BD.Requêtte;
import Model.NonVolontaire;
import Model.Volontaire;

//Classe qui permet de fabriquer la liste des adhérents en présenter
public class Liste implements ActionListener{

	private Fenetre fenetre = new Fenetre();
	private Fenetre fenetreArriere;
	private JPanel contenu = new JPanel();
	private JPanel bottom = new JPanel();
	private Button contenu6 = new Button("Retour");
	private Button contenu7 = new Button("Liste globale");
	private boolean parcourir;
	
	public Liste(Fenetre fenetrearriere,JMenuBar conteneur,boolean parcourir,JTextField zoneText){
		fenetre.getFenetre().setTitle("Liste des adhérents");
		fenetre.getFenetre().setSize(new Dimension(380,400));
		fenetre.getFenetre().setResizable(false);
		fenetre.getFenetre().setLocationRelativeTo(null);
		this.parcourir=parcourir;
		this.fenetreArriere=fenetrearriere;
		remplissage(conteneur,zoneText);
		fenetre.getFenetre().getContentPane().add(new JScrollPane(this.contenu),BorderLayout.CENTER);
		fenetre.getFenetre().getContentPane().add(this.bottom,BorderLayout.SOUTH);
		fenetre.getFenetre().setVisible(true);
	}
	
	public void remplissage(JMenuBar conteneur,JTextField zoneText){
		ArrayList<NonVolontaire> listeAdherent = CreationAdherent.chargerAdherent(false);
		JPanel[] liste = new JPanel[listeAdherent.size()];
		String[] matricule = new String[liste.length];
		getContenu().setLayout(new GridLayout(liste.length,1));
		for(int i=0;i<liste.length;i++){
			liste[i] = new JPanel();
			liste[i].setLayout(new FlowLayout(FlowLayout.CENTER));
			matricule[i] = listeAdherent.get(i).getMatricule();
			liste[i].add(Accueil.label(listeAdherent.get(i).getNom()+": ", listeAdherent.get(i).getMatricule()));
			getContenu().add(liste[i]);
		}
		
		getContenu6().getBouton().setPreferredSize(new Dimension(100,30));
		getContenu7().getBouton().setPreferredSize(new Dimension(150,30));
		
		getBottom().setLayout(new GridLayout(1,2));
		getBottom().add(getContenu6().getPanel());
		if(!isParcourir())getBottom().add(getContenu7().getPanel());
		
		//Les listeners
		//retour
		getContenu6().getBouton().addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				getFenetre().getFenetre().setVisible(false);				
			}
		});
		//Liste globales
		getContenu7().getBouton().addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				
			}
		});
		
		//Information lors du click à la souris sur un adhérent
		if(!isParcourir()){
			for(int i=0;i<liste.length;i++)
				liste[i].addMouseListener(new Click(conteneur, matricule[i]));
		}else{
			for(int i=0;i<liste.length;i++)
			liste[i].addMouseListener(new Parcourre(getFenetre(),matricule[i],zoneText));
		}
		
		//Les actionnaires globalement
		getContenu7().getBouton().addActionListener(new Revenu(conteneur));
		
	}
	public void actionPerformed(ActionEvent arg0) {
		
	}

	public Fenetre getFenetre() {
		return fenetre;
	}

	public void setFenetre(Fenetre fenetre) {
		this.fenetre = fenetre;
	}

	public Fenetre getFenetreArriere() {
		return fenetreArriere;
	}

	public void setFenetreArriere(Fenetre fenetreArriere) {
		this.fenetreArriere = fenetreArriere;
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

	public Button getContenu7() {
		return contenu7;
	}

	public void setContenu7(Button contenu7) {
		this.contenu7 = contenu7;
	}

	public boolean isParcourir() {
		return parcourir;
	}

	public void setParcourir(boolean parcourir) {
		this.parcourir = parcourir;
	}
	class Revenu implements ActionListener{
		private JMenuBar conteneur;
		
		public Revenu(JMenuBar conteneur){
			this.conteneur=conteneur;
		}
		
		public void actionPerformed(ActionEvent arg0) {
			new ListeVolontaire(getFenetreArriere(),this.conteneur);
			getFenetre().getFenetre().setVisible(false);
			
		}
	}

	class Click implements MouseListener{
		
		private JMenuBar conteneur;
		private String matricule;
		public Click(JMenuBar conteneur,String matricule){
			this.conteneur=conteneur;	
			this.matricule=matricule;
		}

		public void mouseClicked(MouseEvent arg0) {
			if(getConteneur()!=null)getConteneur().removeAll();
			getFenetreArriere().getFenetre().getContentPane().removeAll();
			if(getFenetre()!=null)getFenetre().getFenetre().setVisible(false);
			String type = Requêtte.Type(getMatricule());
			if(type.equalsIgnoreCase("Involontaire")){
				NonVolontaire involontaire = CreationAdherent.chargerAdherentInvolontaire(getMatricule());
				new ProfilAdherent(getFenetreArriere(), involontaire);
				
			}else {
				Volontaire volontaire = CreationAdherent.chargerAdherent(getMatricule());
				new ProfilAdherent(getFenetreArriere(), volontaire);
			}
		}

		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		public JMenuBar getConteneur() {
			return conteneur;
		}

		public void setConteneur(JMenuBar conteneur) {
			this.conteneur = conteneur;
		}

		public String getMatricule() {
			return matricule;
		}

		public void setMatricule(String matricule) {
			this.matricule = matricule;
		}
		
	}
	class Parcourre implements MouseListener{
		
		private Fenetre fenetre;
		private String matricule;
		private JTextField text;
		
		public Parcourre(Fenetre fenetre,String matricule,JTextField text){
			this.fenetre=fenetre;
			this.matricule=matricule;
			this.text=text;
		}

		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			getText().setText(getMatricule());
			getFenetre().getFenetre().setVisible(false);			
		}

		public void mouseEntered(MouseEvent arg0) {
			
		}

		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		public Fenetre getFenetre() {
			return fenetre;
		}

		public void setFenetre(Fenetre fenetre) {
			this.fenetre = fenetre;
		}

		public String getMatricule() {
			return matricule;
		}

		public void setMatricule(String matricule) {
			this.matricule = matricule;
		}

		public JTextField getText() {
			return text;
		}

		public void setText(JTextField text) {
			this.text = text;
		}
		
	}
}