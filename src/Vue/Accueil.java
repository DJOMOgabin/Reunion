package Vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.font.TextAttribute;

import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import BD.CreationAdherent;
import BD.CreationReunion;
import BD.LectureConfigReunion;
import BD.Requêtte;
import Model.NonVolontaire;
import Model.Reunion;
import Model.Volontaire;


public class Accueil {
	
	private Fenetre fenetre;
	private JPanel contenu;
	
	private Button contenu1=new Button("Enregistrer un nouvel adhérent");
	private ButtonAndText contenu2=new ButtonAndText("Matricule ou CNI de l'adhérent","Profil");
	private Button contenu3=new Button("Les emprunts");
	private Button contenu4=new Button("Profil de la reunion");
	private JMenuBar conteneur;
	

	public Button getContenu1() {
		return contenu1;
	}

	public void setContenu1(Button contenu1) {
		this.contenu1 = contenu1;
	}

	public ButtonAndText getContenu2() {
		return contenu2;
	}

	public void setContenu2(ButtonAndText contenu2) {
		this.contenu2 = contenu2;
	}

	public Button getContenu3() {
		return contenu3;
	}

	public void setContenu3(Button contenu3) {
		this.contenu3 = contenu3;
	}

	public Button getContenu4() {
		return contenu4;
	}

	public void setContenu4(Button contenu4) {
		this.contenu4 = contenu4;
	}

	public JMenuBar getConteneur() {
		return conteneur;
	}

	public void setConteneur(JMenuBar conteneur) {
		this.conteneur = conteneur;
	}

	public Accueil(Fenetre fene){

		fenetre=fene;
		fenetre.getFenetre().setTitle("Acceuil");
		fenetre.getFenetre().setSize(new Dimension(500,500));
		fenetre.getFenetre().setResizable(false);
		fenetre.getFenetre().setLocationRelativeTo(null);
		remplissage();
		conteneur = new Conteneur(getFenetre()).getMenuBar();
		fenetre.getFenetre().getContentPane().add(conteneur,BorderLayout.NORTH);
		fenetre.getFenetre().getContentPane().add(contenu,BorderLayout.CENTER);
		fenetre.getFenetre().setVisible(true);
	}
	
	public void remplissage(){
		contenu = new JPanel();		
		contenu.setLayout(new GridLayout(6, 1));
		
		contenu.add(new JPanel());
		contenu.add(contenu1.getPanel());
		contenu.add(contenu2.getPanel()[0]);
		contenu.add(contenu3.getPanel());
		contenu.add(contenu4.getPanel());
		
		//Les listeners 
		contenu1.getBouton().addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
				getFenetre().getFenetre().getContentPane().removeAll();
				getConteneur().removeAll();
				new Enregistrer(getFenetre());
				
			}
		});
		//Le keyListener du profil
		class Profil implements KeyListener{

			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode()==KeyEvent.VK_ENTER){
					String type = Requêtte.Type(contenu2.getText().getText());
					if(type!=null && type.equalsIgnoreCase("Volontaire")){
						Volontaire volontaire = CreationAdherent.chargerAdherent(contenu2.getText().getText());
						System.out.println("volontaire: "+volontaire.getMatricule());
						getFenetre().getFenetre().getContentPane().removeAll();
						getConteneur().removeAll();
						new ProfilAdherent(getFenetre(),volontaire);
					}else if(type!=null && type.equalsIgnoreCase("Involontaire")){
						NonVolontaire involontaire = CreationAdherent.chargerAdherentInvolontaire(contenu2.getText().getText());
						System.out.println("involontaire: "+involontaire.getMatricule());
						getFenetre().getFenetre().getContentPane().removeAll();
						getConteneur().removeAll();
						new ProfilAdherent(getFenetre(),involontaire);
					}else if(type==null){

						JOptionPane.showConfirmDialog(null,"Cette adhérent n'existe",
								"Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
					}
				}
				
			}

			public void keyReleased(KeyEvent arg0) {}

			public void keyTyped(KeyEvent arg0) {}
			
		}
		contenu2.getBouton().addKeyListener(new Profil());
		
		contenu2.getText().addKeyListener(new Profil());
		
		contenu2.getBouton().addActionListener(new ActionListener() {
			

			//Le profil d'un adhérent connaissant son matricule ou sa CNI
			public void actionPerformed(ActionEvent e) {
				String type = Requêtte.Type(contenu2.getText().getText());
				if(type!=null && type.equalsIgnoreCase("Volontaire")){
					Volontaire volontaire = CreationAdherent.chargerAdherent(contenu2.getText().getText());
					System.out.println("volontaire: "+volontaire.getMatricule());
					getFenetre().getFenetre().getContentPane().removeAll();
					getConteneur().removeAll();
					new ProfilAdherent(getFenetre(),volontaire);
				}else if(type!=null && type.equalsIgnoreCase("Involontaire")){
					NonVolontaire involontaire = CreationAdherent.chargerAdherentInvolontaire(contenu2.getText().getText());
					System.out.println("involontaire: "+involontaire.getMatricule());
					getFenetre().getFenetre().getContentPane().removeAll();
					getConteneur().removeAll();
					new ProfilAdherent(getFenetre(),involontaire);
				}else if(type==null){

					JOptionPane.showConfirmDialog(null,"Cette adhérent n'existe",
							"Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		//Le menu des emprunts
		contenu3.getBouton().addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
				getFenetre().getFenetre().getContentPane().removeAll();
				getConteneur().removeAll();
				new MenuEmprunt(getFenetre());
			}
		});
		//Les informations relatives sur la reunion
		contenu4.getBouton().addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
				JPanel info = information();
				JOptionPane.showConfirmDialog(null,info,
						"Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
				
			}
		});
		
	}
	//La fonction qui retourne un panel qui permet de donner les informations sur la reunion
	public static JPanel information(){
		String user="";
		new LectureConfigReunion();
		if(LectureConfigReunion.isEffectuer()){
			user = LectureConfigReunion.getUserName();
			System.out.println("user: "+user);
		}
		Reunion reunion = CreationReunion.ChargerReunion(user);
		JPanel contenuGauche = new JPanel();
		
		JPanel[] GaucheInt = new JPanel[9];
		for(int i=0;i<GaucheInt.length;i++){
			GaucheInt[i] = new JPanel();
			GaucheInt[i].setPreferredSize(new Dimension(300,30));
			GaucheInt[i].setLayout(new FlowLayout(FlowLayout.LEFT));
		}
		//Les informations sont stockées dans la BD mais l'affichage est manipulée par
		//Le programmeur, question d'optimisations
		//DJANGUI est le nom de la reunion
		GaucheInt[0].add(label("Nom de la reunion: ","DJANGUI"));
		GaucheInt[1].add(label("Fond: ",""+reunion.getCapitale()+" FCFA"));
		GaucheInt[2].add(label("Gestionnaire: ",reunion.getGerant()));
		GaucheInt[3].add(label("Debut de session: ",""+reunion.getDateDebut()));
		GaucheInt[4].add(label("Fin de session: ",""+reunion.getDateFinReunion()));
		GaucheInt[5].add(label("Debut souscription: ",""+reunion.getDateDebut()));
		GaucheInt[6].add(label("Fin de souscription: ",""+reunion.getDateFinSouscription()));
		GaucheInt[7].add(label("Debut de emprunt: ",""+reunion.getDateFinSouscription()));
		GaucheInt[8].add(label("Fin de emprunt: ",""+reunion.getDateFinEmprunt()));
				
		contenuGauche.setPreferredSize(new Dimension(320,310));
		contenuGauche.setLayout(new GridLayout(GaucheInt.length, 1));
		
		for(int i=0;i<GaucheInt.length;i++){
			contenuGauche.add(GaucheInt[i]);
		}
		return contenuGauche;
	}
	
	//La fonction qui permet d'afficher une phrase donc l'un est en gras et italique et l'autre en en écriture simmple
	public static JPanel label(String attrib,String value){
		JPanel panel = new JPanel();
		attrib="<html><b><i>"+attrib+"</i></b></html>";
		java.awt.Font police = new java.awt.Font("Time new roman",TextAttribute.UNDERLINE_ON, 15);
		JLabel labelGauche = new JLabel(attrib);
		JLabel labelDroite = new JLabel(value);
		
		labelGauche.setFont(police);
		panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		panel.add(labelGauche);
		panel.add(labelDroite);

		panel.setPreferredSize(new Dimension(310,25));
		
		return panel;
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

}
