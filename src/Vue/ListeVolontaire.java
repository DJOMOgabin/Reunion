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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import BD.CreationAdherent;
import BD.CreationReunion;
import BD.LectureConfigReunion;
import Model.Reunion;
import Model.Volontaire;

//Classe qui permet de fabriquer la liste des adhérents volontaire et leur revenu
public class ListeVolontaire{

	private Fenetre fenetre;
	private Fenetre fenetreArriere;
	private JPanel contenu = new JPanel();
	private JPanel bottom = new JPanel();
	private Button contenu6 = new Button("Retour");
	
	public ListeVolontaire(Fenetre fenetrearriere,JMenuBar conteneur){
		fenetre=new Fenetre();
		fenetre.getFenetre().setTitle("Liste des Volontaire");
		fenetre.getFenetre().setSize(new Dimension(350,400));
		fenetre.getFenetre().setResizable(false);
		fenetre.getFenetre().setLocationRelativeTo(null);
		this.fenetreArriere=fenetrearriere;
		remplissage(conteneur);
		fenetre.getFenetre().getContentPane().add(new JScrollPane(this.contenu),BorderLayout.CENTER);
		fenetre.getFenetre().getContentPane().add(this.bottom,BorderLayout.SOUTH);
		fenetre.getFenetre().setVisible(true);
	}
	
	public void remplissage(JMenuBar conteneur){
		getContenu6().getBouton().setPreferredSize(new Dimension(280,30));		
		getBottom().add(getContenu6().getPanel());
		ArrayList<Volontaire> listeAdherent = CreationAdherent.chargerAdherentVolontaire();
		JPanel[] liste = new JPanel[listeAdherent.size()+1];
		String[] matricule = new String[liste.length-1];
		getContenu().setPreferredSize(new Dimension(330,290));
		getContenu().setLayout(new GridLayout(liste.length,1));
		liste[0] = new JPanel();
		liste[0].setLayout(new FlowLayout(FlowLayout.CENTER));
		new LectureConfigReunion();		
		Reunion reunion = CreationReunion.ChargerReunion(LectureConfigReunion.getUserName());
		liste[0].add(Accueil.label("Fond de la reunion: ", ""+reunion.getCapitale()+" FCFA"));
		System.out.println(reunion.getCapitale());
		getContenu().add(liste[0]);
		System.out.println(liste.length);
		for(int i=1;i<liste.length;i++){
			liste[i] = new JPanel();
			liste[i].setLayout(new FlowLayout(FlowLayout.CENTER));
			matricule[i-1] = listeAdherent.get(i-1).getMatricule();
			System.out.println(matricule[i-1]);
			liste[i].add(Accueil.label(listeAdherent.get(i-1).getNom()+": ",listeAdherent.get(i-1).getRevenu()+" FCFA"));
			getContenu().add(liste[i]);
		}
		
		//Les listeners
		//Retour
		getContenu6().getBouton().addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				getFenetre().getFenetre().setVisible(false);				
			}
		});
		
		//Information lors du click à la souris sur un adhérent
		for(int i=1;i<liste.length;i++){
			liste[i].addMouseListener(new Click(getFenetre(), conteneur, matricule[i-1]));
		}
		liste[0].addMouseListener(new MouseListener() {
			
			public void mouseReleased(MouseEvent arg0) {}
			
			public void mousePressed(MouseEvent arg0) {}
			
			public void mouseExited(MouseEvent arg0) {}
			
			public void mouseEntered(MouseEvent arg0) {}
			
			public void mouseClicked(MouseEvent arg0) {
				JPanel info =Accueil.information();
				JOptionPane.showConfirmDialog(null,info,
						"Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
			}
		});
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

	class Click implements MouseListener{
		
		private Fenetre fenetre;
		private JMenuBar conteneur;
		private String matricule;
		public Click(Fenetre fenetre,JMenuBar conteneur,String matricule){
			this.fenetre=fenetre;
			this.conteneur=conteneur;	
			this.matricule=matricule;
		}

		public void mouseClicked(MouseEvent arg0) {
			if(getConteneur()!=null)getConteneur().removeAll();
			getFenetreArriere().getFenetre().getContentPane().removeAll();
			Volontaire volontaire = CreationAdherent.chargerAdherent(getMatricule());
			new ProfilAdherent(getFenetreArriere(), volontaire);
			if(getFenetre()!=null)getFenetre().getFenetre().setVisible(false);
		}

		public void mouseEntered(MouseEvent arg0) {}

		public void mouseExited(MouseEvent arg0) {}

		public void mousePressed(MouseEvent arg0) {}

		public void mouseReleased(MouseEvent arg0) {}

		public Fenetre getFenetre() {
			return fenetre;
		}

		public void setFenetre(Fenetre fenetre) {
			this.fenetre = fenetre;
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
}