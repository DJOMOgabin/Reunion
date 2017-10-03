package Vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import BD.CreationAdherent;
import BD.Requêtte;
import Model.Volontaire;

//Permet de souscrire aux parts pour un actionnaire
public class Part {

	private Fenetre fenetre = new Fenetre();
	private boolean enregistrer;
	private boolean save=false;
	private Volontaire adherent;
	private Fenetre fenetreArriere;
	private String matricule;
	private JPanel contenu = new JPanel();
	private JPanel bottom = new JPanel();
	private ButtonAndText contenu1 = new ButtonAndText("Matricule de l'adhérent","Parcourir");
	private LabelAndCombox contenu2 = new LabelAndCombox("Nombre de parts",LabelAndCombox.getTaille());
	private Button contenu6 = new Button("Retour");
	private Button contenu7 = new Button("Souscrire");
	
	public Part(boolean enregistrer,String matricule,Volontaire adherent,Fenetre fenetrearriere,JMenuBar conteneur){
		fenetre.getFenetre().setTitle("Part");
		fenetre.getFenetre().setSize(new Dimension(350,400));
		fenetre.getFenetre().setResizable(false);
		fenetre.getFenetre().setLocationRelativeTo(null);
		this.enregistrer=enregistrer;
		this.matricule=matricule;
		this.adherent=adherent;
		this.fenetreArriere=fenetrearriere;
		remplissage(conteneur);
		fenetre.getFenetre().getContentPane().add(this.contenu,BorderLayout.CENTER);
		fenetre.getFenetre().getContentPane().add(this.bottom,BorderLayout.SOUTH);
		fenetre.getFenetre().setVisible(true);
	}
	
	@SuppressWarnings("deprecation")
	public void remplissage(JMenuBar conteneur){
		getContenu().setLayout(new GridLayout(4,1));
		if(isEnregistrer()){
			getContenu1().getBouton().setEnabled(false);;
			getContenu1().getText().setText(getMatricule());
			getContenu1().getText().disable();
		}
		
		getContenu1().getBouton().setPreferredSize(new Dimension(120,30));
		getContenu1().getText().setPreferredSize(new Dimension(150,30));
		getContenu2().getBouton().setPreferredSize(new Dimension(90,30));
		getContenu2().getText().setPreferredSize(new Dimension(120,30));
		
		getContenu().add(new JPanel());
		getContenu().add(getContenu1().getPanel()[0]);
		getContenu().add(getContenu2().getPanel()[0]);
		getContenu().add(new JPanel());

		getBottom().setLayout(new GridLayout(1,2));
		getContenu6().getBouton().setPreferredSize(new Dimension(100, 30));
		getContenu7().getBouton().setPreferredSize(new Dimension(100, 30));
		
		getBottom().add(getContenu6().getPanel());
		getBottom().add(getContenu7().getPanel());
		
		//Les listeners
		getContenu6().getBouton().addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				setSave(false);
				getFenetre().getFenetre().setVisible(false);				
			}
		});
		//Souscrire
		getContenu7().getBouton().addActionListener(new ListenerOkButton(conteneur));
		
		//Parcourir
		getContenu1().getBouton().addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				new Liste(getFenetre(), null, true, getContenu1().getText());
				
			}
		});
	}

	public Fenetre getFenetre() {
		return fenetre;
	}

	public void setFenetre(Fenetre fenetre) {
		this.fenetre = fenetre;
	}

	public boolean isEnregistrer() {
		return enregistrer;
	}

	public void setEnregistrer(boolean enregistrer) {
		this.enregistrer = enregistrer;
	}

	public JPanel getContenu() {
		return contenu;
	}

	public void setContenu(JPanel contenu) {
		this.contenu = contenu;
	}

	public ButtonAndText getContenu1() {
		return contenu1;
	}

	public void setContenu1(ButtonAndText contenu1) {
		this.contenu1 = contenu1;
	}

	public LabelAndCombox getContenu2() {
		return contenu2;
	}

	public void setContenu2(LabelAndCombox contenu2) {
		this.contenu2 = contenu2;
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

	public String getMatricule() {
		return matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	public boolean isSave() {
		return save;
	}

	public void setSave(boolean save) {
		this.save = save;
	}

	public Volontaire getAdherent() {
		return adherent;
	}

	public void setAdherent(Volontaire adherent) {
		this.adherent = adherent;
	}

	public Fenetre getFenetreArriere() {
		return fenetreArriere;
	}

	public void setFenetreArriere(Fenetre fenetreArriere) {
		this.fenetreArriere = fenetreArriere;
	}

	public JPanel getBottom() {
		return bottom;
	}

	public void setBottom(JPanel bottom) {
		this.bottom = bottom;
	}
	
	//La class qui permet de faire la souscription
	class ListenerOkButton implements ActionListener{
		
		private JMenuBar conteneur;
		public ListenerOkButton(JMenuBar conteneur){
			this.conteneur=conteneur;
		}
		
			public void actionPerformed(ActionEvent arg0) {
	    		int op = JOptionPane.showConfirmDialog(null, "Voulez-vous souscrire aux parts?",
						"Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
	    		if(op==JOptionPane.OK_OPTION){
	    			setSave(true);
	    			Volontaire volontaire=null;
	    			int part = (Integer)(getContenu2().getBouton().getSelectedItem());
	    			System.out.println("Part: "+part);
	    			int id=-1;
	    			if(getAdherent()!=null) {
	    				volontaire = (Volontaire)getAdherent();
	    				volontaire.souscrire(part);
	    				id = CreationAdherent.EnregistrerAdherent(volontaire);
	    			}else {
	    				String type = Requêtte.Type(getContenu1().getText().getText());
	    				if(type!=null && type.equalsIgnoreCase("Volontaire")){
	    					volontaire = CreationAdherent.chargerAdherent(getContenu1().getText().getText());
	    					volontaire.setPart(volontaire.getPart()+part);
	    					id = CreationAdherent.EnregistrerAdherent(volontaire);
	    					System.out.println("volontaire: "+volontaire.getMatricule());
	    					setAdherent(volontaire);
	    				}else{
	    					JOptionPane.showConfirmDialog(null,"Cette adhérent n'est pas actionnaire",
	    							"Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
	    					return;
	    				}
	    			}
	    			System.out.println("id: "+id);
	    			if(id!=-1){
	    				//si la souscription à reussi
	    				getFenetreArriere().getFenetre().getContentPane().removeAll();
	    				if(getConteneur()!=null)conteneur.removeAll();
	    				int pro;
	    				if(getContenu()==null || getBottom()==null){
	    					pro = JOptionPane.showConfirmDialog(null, "Souscription reussi",
		    						"Confirmation", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
	    				}else{
	    					pro = JOptionPane.showConfirmDialog(null, "Enregistrement reussi\n"
		    						+ "\tSon matricule est: "+getAdherent().getMatricule()+"\nVoir Profil?",
		    						"Confirmation", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
	    				}
	    				if(pro==JOptionPane.OK_OPTION){
	    					new ProfilAdherent(getFenetreArriere(),volontaire);
	    				}else if(pro==JOptionPane.CANCEL_OPTION){
	    					new Accueil(getFenetreArriere());	    					
	    				}
    					getFenetre().getFenetre().setVisible(false);
	    			}else{
	    				JOptionPane.showMessageDialog(null, "Un problème est survenu lors de l'enregistrement");
	    			}
	    		}
			}

			public JMenuBar getConteneur() {
				return conteneur;
			}

			public void setConteneur(JMenuBar contenu) {
				this.conteneur = contenu;
			}
					
		}
}
