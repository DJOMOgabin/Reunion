package Vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Controlleur.EnregistrerAdherent;
import Model.Translate;

//La classe permet d'enregistrer un adhérent dans la base de données
public class Enregistrer{

	private Fenetre fenetre;
	private JPanel contenu;
	private JPanel bottom;	
	private Text contenu1 = new Text("Nom de l'adhérent");
	private Text contenu2 = new Text("N° de CNI");
	private DateChooser contenu3 = new DateChooser("Date d'adhésion");
	private Text contenu4 = new Text("Adresse");
	private Text contenu5 = new Text("Telephone");
	private Button contenu6 = new Button("Annuler");
	private Button contenu7 = new Button("Soumettre");
	private CheckBox contenu8 = new CheckBox("Voulez-vous devenir un actionnaire?");
	private ArrayList<Translate> request = new ArrayList<Translate>();
	private JMenuBar conteneur;
	
	public JMenuBar getConteneur() {
		return conteneur;
	}

	public void setConteneur(JMenuBar conteneur) {
		this.conteneur = conteneur;
	}

	public Enregistrer(Fenetre fene){
		this.fenetre=fene;		
		fenetre.getFenetre().setTitle("Enregistrer");
		fenetre.getFenetre().setSize(new Dimension(500,600));
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
		contenu = new JPanel();		
		contenu.setLayout(new GridLayout(7, 1));
		bottom = new JPanel();		
		bottom.setLayout(new GridLayout(1,2));
		
		contenu6.getBouton().setPreferredSize(new Dimension(100, 30));
		contenu7.getBouton().setPreferredSize(new Dimension(100, 30));
		
		bottom.add(contenu6.getPanel());
		bottom.add(contenu7.getPanel());
		
		contenu.add(new JPanel());
		contenu.add(contenu1.getPanel());
		contenu.add(contenu2.getPanel());
		contenu.add(contenu3.getTotal());
		contenu.add(contenu4.getPanel());
		contenu.add(contenu5.getPanel());
		contenu.add(contenu8.getPanel());
		
		//Les listeners
		//retour
		getContenu6().getBouton().addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent arg0) {
				getFenetre().getFenetre().getContentPane().removeAll();
				getConteneur().removeAll();
				new Accueil(getFenetre());
				
			}
		});
		
		//Le bouton de validation
		getContenu7().getBouton().addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent arg0) {
				
				//On try si la CNI ne contient que des chiffres, il ne derange pas mais 
				//Par mesure de logique
				try{
					Long.parseLong(getContenu2().getText().getText());
					int op = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment enregistrer?",
						"Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if(op==JOptionPane.OK_OPTION){
						//On passe les paramètres au controleur pour qu'il en registre un nouvel adhérent dans la base de données
						getRequest().add (new Translate("nom", getContenu1()));
						getRequest().add(new Translate("numero",getContenu2()));
						getRequest().add(new Translate("date",getContenu3()));
						getRequest().add(new Translate("adresse",getContenu4()));
						getRequest().add(new Translate("telephone",getContenu5()));
						getRequest().add(new Translate("actionnaire",getContenu8()));
						
						//On appelle le controleur
						new EnregistrerAdherent(getRequest(),getFenetre(),getContenu(),getBottom(),getConteneur());					
					}
				}catch(NumberFormatException e){
					JOptionPane.showConfirmDialog(null, "La CNI ne doit contenir que de chiffres",
							"Erreur", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				}
				
				

			}
		});
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

	public Text getContenu1() {
		return contenu1;
	}

	public void setContenu1(Text contenu1) {
		this.contenu1 = contenu1;
	}

	public Text getContenu2() {
		return contenu2;
	}

	public void setContenu2(Text contenu2) {
		this.contenu2 = contenu2;
	}

	public Text getContenu4() {
		return contenu4;
	}

	public void setContenu4(Text contenu4) {
		this.contenu4 = contenu4;
	}

	public Text getContenu5() {
		return contenu5;
	}

	public void setContenu5(Text contenu5) {
		this.contenu5 = contenu5;
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

	public ArrayList<Translate> getRequest() {
		return request;
	}

	public void setRequest(ArrayList<Translate> request) {
		this.request = request;
	}

	public CheckBox getContenu8() {
		return contenu8;
	}

	public void setContenu8(CheckBox contenu8) {
		this.contenu8 = contenu8;
	}

	public DateChooser getContenu3() {
		return contenu3;
	}

	public void setContenu3(DateChooser contenu3) {
		this.contenu3 = contenu3;
	}

	
	
}
