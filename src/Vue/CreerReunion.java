package Vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import BD.LectureConfigReunion;
import Controlleur.EnregistrerReunion;
import Model.Translate;

public class CreerReunion{

	private Fenetre fenetre;
	private JPanel contenu;
	private JPanel bottom;	
	private Text contenu2 = new Text("Nom du gestionnaire");
	private Text contenu3 = new Text("Nom d'utilisateur");
	private JPasswordField password = new JPasswordField();
	private DateChooser contenu4 = new DateChooser("Date de debut");
	private DateChooser contenu5 = new DateChooser("Date de fin de session");
	private DateChooser contenu1 = new DateChooser("Date de fin de souscrption");
	private DateChooser contenu10 = new DateChooser("Date de fin d'emprunt");
	private Text contenu11 = new Text("Nombre de parts maximale");
	private Button contenu6 = new Button("Annuler");
	private Button contenu7 = new Button("Créer");
	private ArrayList<Translate> request = new ArrayList<Translate>();
	private JMenuBar conteneur;
	
	//La vue qui permet de créer une reunion, avec ses différents champs
	public CreerReunion(Fenetre fene){
		fenetre = fene;
		fenetre.getFenetre().setTitle("Création de la reunion principal");
		fenetre.getFenetre().setSize(new Dimension(500,700));
		fenetre.getFenetre().setResizable(false);
		fenetre.getFenetre().setLocationRelativeTo(null);
		remplissage();
		conteneur = new Conteneur(getFenetre()).getMenuBar();
		//fenetre.getFenetre().getContentPane().add(conteneur,BorderLayout.NORTH);
		fenetre.getFenetre().getContentPane().add(contenu,BorderLayout.CENTER);
		fenetre.getFenetre().getContentPane().add(bottom,BorderLayout.SOUTH);
		fenetre.getFenetre().setVisible(true);
	}
	
	public void remplissage(){
		contenu = new JPanel();		
		contenu.setLayout(new GridLayout(8, 1));
		bottom = new JPanel();		
		bottom.setLayout(new GridLayout(1,2));
		
		contenu6.getBouton().setPreferredSize(new Dimension(100, 30));
		contenu7.getBouton().setPreferredSize(new Dimension(100, 30));
		bottom.add(contenu6.getPanel());
		bottom.add(contenu7.getPanel());
		
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));

		panel.setBorder(BorderFactory.createTitledBorder("Mot de passe"));
		
		panel.add(getPassword());
		getPassword().setPreferredSize(new Dimension(400,40));
		
		contenu.add(contenu2.getPanel());
		contenu.add(contenu3.getPanel());
		contenu.add(getContenu11().getPanel());
		contenu.add(panel);
		contenu.add(contenu4.getTotal());
		contenu.add(contenu5.getTotal());
		contenu.add(contenu1.getTotal());	
		contenu.add(contenu10.getTotal());	
		
		//Les listeners
		contenu6.getBouton().addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent arg0) {
				//S'il existe déjà une reunion, on donne la possibilité à l'utilisateur de retourner sur la page d'authentification
				new LectureConfigReunion();
				if(LectureConfigReunion.getUserName().equals(""))System.exit(0);
				else {
					getFenetre().getFenetre().getContentPane().removeAll();
					new Authentification(getFenetre());
				}
				
			}
		});
		
		getContenu7().getBouton().addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent arg0) {
				
				int op = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment enregistrer?",
						"Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				
				if(op==JOptionPane.OK_OPTION){
					//les manipulations , on enlève le espace dans le userName avant de le stocker
					String user = getContenu3().getText().getText().replaceAll(" ", "");
					getContenu3().getText().setText(user);
					try{
						//On recuplère le nombre maximale et s'il n'en est pas un, on passe au catch
						int max = Integer.parseInt(getContenu11().getText().getText());
					     System.out.println(max);
					     //On passe les paramètres au controleur pour qu'il en registre un nouvelle reunion dans la base de données
							getRequest().add(new Translate("debut",getContenu4()));
							getRequest().add(new Translate("session",getContenu5()));
							getRequest().add(new Translate("souscription",getContenu1()));
							getRequest().add(new Translate("emprunt",getContenu10()));
							getRequest().add(new Translate("gestionnaire",getContenu2()));
							getRequest().add(new Translate("user",getContenu3()));
							getRequest().add(new Translate("password",getPassword()));
							getRequest().add(new Translate("max",getContenu11()));
							
							//On appelle le controleur
							new EnregistrerReunion(getRequest(),getFenetre(),getConteneur());		
					}catch(NumberFormatException e){
					    	 System.out.println("mauvais format de valeur");
					    	 JOptionPane.showConfirmDialog(null,"La valeur maximale de parts n'est pas un entier",
										"Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
					}
					
								
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
	
	public Text getContenu11() {
		return contenu11;
	}

	public void setContenu11(Text contenu11) {
		this.contenu11 = contenu11;
	}

	public JMenuBar getConteneur() {
		return conteneur;
	}

	public void setConteneur(JMenuBar conteneur) {
		this.conteneur = conteneur;
	}

	public Text getContenu2() {
		return contenu2;
	}

	public void setContenu2(Text contenu2) {
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

	public ArrayList<Translate> getRequest() {
		return request;
	}

	public void setRequest(ArrayList<Translate> request) {
		this.request = request;
	}
	
	
	
	public Text getContenu3() {
		return contenu3;
	}

	public void setContenu3(Text contenu3) {
		this.contenu3 = contenu3;
	}

	public JPasswordField getPassword() {
		return password;
	}

	public void setPassword(JPasswordField password) {
		this.password = password;
	}

	public DateChooser getContenu4() {
		return contenu4;
	}

	public void setContenu4(DateChooser contenu4) {
		this.contenu4 = contenu4;
	}

	public DateChooser getContenu5() {
		return contenu5;
	}

	public void setContenu5(DateChooser contenu5) {
		this.contenu5 = contenu5;
	}

	public DateChooser getContenu1() {
		return contenu1;
	}

	public void setContenu10(DateChooser contenu10) {
		this.contenu1 = contenu10;
	}

	public DateChooser getContenu10() {
		return contenu10;
	}

	public void setContenu1(DateChooser contenu1) {
		this.contenu1 = contenu1;
	}
	
}
