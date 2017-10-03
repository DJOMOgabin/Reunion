package Vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import BD.CreationAdherent;
import BD.CreationReunion;
import BD.LectureConfigReunion;
import Model.NonVolontaire;
import Model.Reunion;
import Model.Volontaire;

//La fonction qui affiche le profil de l'adhérent, qu'il soit actionnaire ou pas
//En fonction de cela, certaines option comme la souscription et ses revenu n'apparaissent pas
public class ProfilAdherent {

	private Fenetre fenetre;
	private JPanel contenu = new JPanel();
	private JPanel haut = new JPanel();
	private JPanel bas = new JPanel();
	private JPanel contenuGauche = new JPanel();
	private JPanel contenuDroite = new JPanel();
	private JPanel[] GaucheInt = new JPanel[8];
	private JPanel DroitInt = new JPanel();
	private LabelAndButton droitint2 =  new LabelAndButton("Charger", "Browse");
	private JPanel bottom = new JPanel();
	private Button contenu6 = new Button("Retour");
	private Button contenu7 = new Button("Emprunter");
	private Button contenu8 = new Button("Rembourser");
	private Button contenu10 = new Button("Revenu");
	private Button contenu11 = new Button("Souscrire");
	private Button contenu12 = new Button("Liste Adhérents");
	private JLabel profil = new JLabel();
	private JMenuBar conteneur;
	
	public JMenuBar getConteneur() {
		return conteneur;
	}
	public void setConteneur(JMenuBar conteneur) {
		this.conteneur = conteneur;
	}

	private NonVolontaire adherent;
	private boolean volontaire;
	
	public ProfilAdherent(Fenetre fene,NonVolontaire adherent,Boolean volontaire){

		fenetre = fene;
		fenetre.getFenetre().setTitle("Profil de l'adhérent avec le matricule "+adherent.getNom());
		fenetre.getFenetre().setSize(new Dimension(500,600));
		fenetre.getFenetre().setResizable(false);
		fenetre.getFenetre().setLocationRelativeTo(null);
		this.adherent=adherent;
		this.volontaire=volontaire;
		remplissage();
		conteneur = new Conteneur(getFenetre()).getMenuBar();
		fenetre.getFenetre().getContentPane().add(conteneur,BorderLayout.NORTH);
		fenetre.getFenetre().getContentPane().add(contenu,BorderLayout.CENTER);
		fenetre.getFenetre().getContentPane().add(bottom,BorderLayout.SOUTH);
		fenetre.getFenetre().setVisible(true);
	}
	//Remplissage du profil de l'adhérent
	@SuppressWarnings("deprecation")
	public void remplissage(){
		getContenu12().getBouton().setPreferredSize(new Dimension(200, 30));
		getContenuGauche().setPreferredSize(new Dimension(240,410));
		getContenuGauche().add(TotalEmprunt.information(getAdherent()));
		getContenuGauche().add(getContenu12().getPanel());
		getProfil().setPreferredSize(new Dimension(220,200));
		BufferedImage bim;
		if(getAdherent().getPhoto()!=null){
			try {
				bim = ImageIO.read(new FileInputStream(getAdherent().getPhoto()));
				Image resizedImg=bim.getScaledInstance(210,190,Image.SCALE_FAST);
				getProfil().setIcon(new ImageIcon(resizedImg));
			} catch (FileNotFoundException e1) {
			} catch (IOException e1) {
			}
		}
		getDroitInt().add(getProfil());
		
		getDroitint2().getBouton().setPreferredSize(new Dimension(100,30));
		getDroitint2().getText().setPreferredSize(new Dimension(100,30));
		
		getContenuDroite().setPreferredSize(new Dimension(240,290));
		
		getContenuDroite().add(getDroitInt());
		getContenuDroite().add(getDroitint2().getPanel()[0]);
		
		
		getHaut().setLayout(new GridLayout(1, 2));
		
		getHaut().add(getContenuGauche());
		getHaut().add(getContenuDroite());
		
		if(isVolontaire()){
			getBas().setLayout(new GridLayout(1,2));
			
			getContenu10().getBouton().setPreferredSize(new Dimension(150, 30));
			getContenu11().getBouton().setPreferredSize(new Dimension(150, 30));
			getBas().add(getContenu10().getPanel());
			getBas().add(new JPanel());
			getBas().add(getContenu11().getPanel());
		}
		
		getContenu().setPreferredSize(new Dimension(490,520));
		getContenu().add(getHaut());
		getContenu().add(getBas(),BorderLayout.SOUTH);
		
		getBottom().setLayout(new GridLayout(1,2));
		
		getContenu6().getBouton().setPreferredSize(new Dimension(100, 30));
		getContenu7().getBouton().setPreferredSize(new Dimension(100, 30));
		getContenu8().getBouton().setPreferredSize(new Dimension(100, 30));
		

		getBottom().add(getContenu6().getPanel());
		
		//S'il a un emprunt en cour, on affiche plutôt le bouton de remboursement
		//par contre s'il est en règle,(sans emprunt et non exclu) il a droit au bouton d'emprunt
		if(getAdherent().isEtat()){
			getBottom().add(getContenu8().getPanel());			
		}else{
			getBottom().add(getContenu7().getPanel());			
		}
		
		if(getAdherent().getStatut().equals("E")) getContenu7().getBouton().enable(false);
		
		//Les listeners
		//Retour
		getContenu6().getBouton().addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				getFenetre().getFenetre().getContentPane().removeAll();
				getConteneur().removeAll();
				new Accueil(getFenetre());		
			}
		});
		
		//Emprunter
		getContenu7().getBouton().addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				new LectureConfigReunion();
				Reunion reunion = CreationReunion.ChargerReunion(LectureConfigReunion.getUserName());
				int taille = reunion.getCapitale()/Reunion.getPart();
				LabelAndCombox nombre = new LabelAndCombox("Nombre de part à emprunter",taille);
				nombre.getBouton().setPreferredSize(new Dimension(60,30));
				nombre.getText().setPreferredSize(new Dimension(180,30));
				int pro = JOptionPane.showConfirmDialog(null,nombre.getPanel()[0],"Montant",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
				if(pro==JOptionPane.OK_OPTION){
					int montant = (Integer) nombre.getBouton().getSelectedItem();
					String result = getAdherent().emprunter(montant);
					if(result.equalsIgnoreCase("L'opération a reussi avec succes!!!")){
						JOptionPane.showConfirmDialog(null,"L'opération s'est déroulé avec succès!!!","Confirmation",
								JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
						getFenetre().getFenetre().getContentPane().removeAll();
						getConteneur().removeAll();
						CreationAdherent.EnregistrerAdherent(getAdherent());
						new ProfilAdherent(getFenetre(), getAdherent(),isVolontaire());
					}else{
						JOptionPane.showConfirmDialog(null,result,"Confirmation",
								JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
		
		//Liste des adhérents
		getContenu12().getBouton().addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				new Liste(getFenetre(), getConteneur(),false,null);				
			}
		});
		//Rembourser
		getContenu8().getBouton().addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				int pro = JOptionPane.showConfirmDialog(null,"Vous devez une somme de "+getAdherent().getMontantRembourser()
						+" FCFA à la reunion, voulez-vous rembourser?","Rembourser",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
				if(pro==JOptionPane.OK_OPTION){
					if(getAdherent().rembourser()){
						CreationAdherent.EnregistrerAdherent(getAdherent());
						JOptionPane.showConfirmDialog(null,"Emprunt remboursé","Rembourser",
								JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
						getFenetre().getFenetre().getContentPane().removeAll();
						getConteneur().removeAll();
						CreationAdherent.EnregistrerAdherent(getAdherent());
						new ProfilAdherent(getFenetre(), getAdherent(),isVolontaire());
					}else{
						JOptionPane.showConfirmDialog(null,"Cet adhérent n'a pas d'emprunt en cours","Rembourser",
								JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
					}
				}
				
			}
		});
		
		//Souscrire
		getContenu11().getBouton().addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {

				int pro = JOptionPane.showConfirmDialog(null, "Voulez-vous souscrire?","Confirmation",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(pro==JOptionPane.OK_OPTION){
					new Part(true, getAdherent().getMatricule(),(Volontaire)getAdherent(), getFenetre(),getConteneur());
				}
				
			}
		});
		//Revenu de l'adhérent
		getContenu10().getBouton().addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				Volontaire involontaire = (Volontaire)getAdherent();
				new LectureConfigReunion();
				Reunion reunion = CreationReunion.ChargerReunion(LectureConfigReunion.getUserName());
				involontaire.AJour(reunion.getNombrePart(), reunion.getCapitale());
				JOptionPane.showConfirmDialog(null,"La présente reunion détient un montant de \n"
				+involontaire.getRevenu()+" FCFA (avec une nombre de parts de "+
						involontaire.getPart()+" part(s))qui vous revient de droit!","Revenu",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
				
			}
		});
		
		//Charger la photo
		getDroitint2().getBouton().addActionListener(new ActionListener() {

			JFileChooser directory = new JFileChooser();
			public void actionPerformed(ActionEvent arg0) {
				directory.setFileSelectionMode(JFileChooser.FILES_ONLY);

				//directory.setSelectedFile(new File(nouveau));
				int returnval = directory.showOpenDialog(getFenetre().getFenetre());
				if(returnval==JFileChooser.APPROVE_OPTION){
					String nouveau = directory.getSelectedFile().getAbsolutePath();
					System.out.println(nouveau);
					getAdherent().setPhoto(nouveau);
					CreationAdherent.EnregistrerAdherent(getAdherent());
					getProfil().setPreferredSize(new Dimension(220,200));
					getProfil().removeAll();
					try {
						BufferedImage bim=ImageIO.read(new FileInputStream(getAdherent().getPhoto()));
						Image resizedImg=bim.getScaledInstance(210,190,Image.SCALE_FAST);
						getProfil().setIcon(new ImageIcon(resizedImg));
					} catch (FileNotFoundException e) {
					} catch (IOException e) {
						JOptionPane.showConfirmDialog(null, "Erreur de chargement","Error",
								JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
					}
					getFenetre().getFenetre().setVisible(true);
					
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

	
	public ProfilAdherent(Fenetre fenetre,NonVolontaire adherent){
		this(fenetre,adherent, false);
	}
	
	public ProfilAdherent(Fenetre fenetre,Volontaire adherent){
		this(fenetre,adherent, true);
	}
	
	public JPanel getContenuGauche() {
		return contenuGauche;
	}

	public void setContenuGauche(JPanel contenuGauche) {
		this.contenuGauche = contenuGauche;
	}

	public JPanel getContenuDroite() {
		return contenuDroite;
	}

	public void setContenuDroite(JPanel contenuDroite) {
		this.contenuDroite = contenuDroite;
	}

	public JPanel[] getGaucheInt() {
		return GaucheInt;
	}

	public void setGaucheInt(JPanel[] gaucheInt) {
		GaucheInt = gaucheInt;
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

	public JPanel getBas() {
		return bas;
	}

	public void setBas(JPanel bas) {
		this.bas = bas;
	}

	public Button getContenu8() {
		return contenu8;
	}

	public void setContenu8(Button contenu8) {
		this.contenu8 = contenu8;
	}

	public Button getContenu10() {
		return contenu10;
	}

	public void setContenu10(Button contenu10) {
		this.contenu10 = contenu10;
	}

	public Button getContenu11() {
		return contenu11;
	}

	public void setContenu11(Button contenu11) {
		this.contenu11 = contenu11;
	}

	public JPanel getHaut() {
		return haut;
	}

	public void setHaut(JPanel haut) {
		this.haut = haut;
	}

	public NonVolontaire getAdherent() {
		return adherent;
	}

	public void setAdherent(NonVolontaire adherent) {
		this.adherent = adherent;
	}

	public boolean isVolontaire() {
		return volontaire;
	}

	public void setVolontaire(boolean volontaire) {
		this.volontaire = volontaire;
	}

	public JPanel getDroitInt() {
		return DroitInt;
	}

	public void setDroitInt(JPanel droitInt) {
		DroitInt = droitInt;
	}

	public LabelAndButton getDroitint2() {
		return droitint2;
	}

	public void setDroitint2(LabelAndButton droitint2) {
		this.droitint2 = droitint2;
	}

	public Button getContenu12() {
		return contenu12;
	}
	
	public JLabel getProfil() {
		return profil;
	}

	public void setProfil(JLabel profil) {
		this.profil = profil;
	}

	public void setContenu12(Button contenu12) {
		this.contenu12 = contenu12;
	}
	
	
}
