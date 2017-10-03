package Vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Controlleur.ControlleurAccueil;

//La page qui permet d'authentifier l'utilisateur
public class Authentification {
	private JPanel contenu = new JPanel();
	private JPanel paneNom = new JPanel();
	private JPanel panePwd = new JPanel();
	private JPanel paneLabelNom = new JPanel();
	private JPanel paneLabelPwd = new JPanel();
	private JLabel labelNom = new JLabel("Nom d'utilisateur");
	private JLabel labelPwd = new JLabel("Mot de passe de la base");
	private JTextField textNom = new JTextField();
	private JPasswordField textPwd = new JPasswordField();
	private JPanel paneTextNom = new JPanel();
	private JPanel paneTextPwd = new JPanel();
	private JButton annuler = new JButton("Annuler");
	private JButton continuer = new JButton("OK");
	private JPanel bottom = new JPanel();
	private JPanel bottomDroit = new JPanel();
	private JPanel bottomGauche = new JPanel();
	private Fenetre fenetre;
	private JLabel titre = new JLabel("BIENVENUE",JLabel.CENTER);
	private Font police = new Font("algerian", Font.ITALIC, 40);
	private Dimension dim = new Dimension(150,30);
	
	
	public static String userName;
	public static String password;
	
	public static String getUserName() {
		return userName;
	}

	public static void setUserName(String userName) {
		Authentification.userName = userName;
	}

	public static String getPassword() {
		return password;
	}

	public static void setPassword(String password) {
		Authentification.password = password;
	}

	public Authentification(Fenetre fene){
		this.fenetre=fene;
		
		fenetre.getFenetre().setTitle("Acceuil");
		fenetre.getFenetre().setSize(new Dimension(400,300));
		fenetre.getFenetre().setResizable(false);
		fenetre.getFenetre().setLocationRelativeTo(null);
		init();
		fenetre.getFenetre().getContentPane().add(contenu,BorderLayout.CENTER);
		fenetre.getFenetre().getContentPane().add(bottom,BorderLayout.SOUTH);
		fenetre.getFenetre().setVisible(true);
		
	}
	
	public void init(){
		
		titre.setFont(police);
		textPwd.setEchoChar('*');
		contenu.setLayout(new GridLayout(3, 1, 10, 10));
		paneLabelNom.setLayout(new FlowLayout(FlowLayout.RIGHT));
		paneLabelPwd.setLayout(new FlowLayout(FlowLayout.RIGHT));
		paneTextNom.setLayout(new FlowLayout(FlowLayout.CENTER));
		paneTextPwd.setLayout(new FlowLayout(FlowLayout.CENTER));
		textNom.setPreferredSize(dim);
		textPwd.setPreferredSize(dim);
		titre.setForeground(Color.blue);
		
		paneNom.setLayout(new GridLayout(1, 2, 10, 10));
		panePwd.setLayout(new GridLayout(1, 2, 10, 10));
		
		paneLabelNom.add(labelNom);
		paneLabelPwd.add(labelPwd);
		
		paneTextNom.add(textNom);
		paneTextPwd.add(textPwd);
		
		paneNom.add(paneLabelNom);
		paneNom.add(paneTextNom);
		panePwd.add(paneLabelPwd);
		panePwd.add(paneTextPwd);
		
		contenu.add(titre);
		contenu.add(paneNom);
		contenu.add(panePwd);
		
		bottomGauche.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		bottomDroit.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		bottom.setLayout(new GridLayout(1, 2));
		bottomGauche.add(annuler);
		bottomDroit.add(continuer);
		bottom.add(bottomGauche);
		bottom.add(bottomDroit);
		
		//Les listeners
		//Le bouton d'annulation
		annuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				
			}
		});
		//Echapper de la page
		class Echap implements KeyListener{
			public void keyTyped(KeyEvent arg0) {}
			
			public void keyReleased(KeyEvent arg0) {}
			
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode()==KeyEvent.VK_ESCAPE)System.exit(0);
			}
		}
		//On l'applique sur tous les champs
		annuler.addKeyListener(new Echap());
		continuer.addKeyListener(new Echap());
		textNom.addKeyListener(new Echap());
		textPwd.addKeyListener(new Echap());
		
		//Le bouton de connexion
		
		class OkActionListener implements ActionListener{
			
			@SuppressWarnings("deprecation")
			
			public void actionPerformed(ActionEvent e) {
				
				if(textNom.getText().equalsIgnoreCase("")){
					JOptionPane.showMessageDialog(null, "Vous devez remplir le champs d'utilisateur");
				}else{
					setPassword(textPwd.getText());
					setUserName(textNom.getText());
					String user = getUserName().replaceAll(" ", "");
					int password = getPassword().hashCode();
					setUserName(user);
					new ControlleurAccueil(getUserName(), password, getFenetre());
					
				}
			}
		}
		
		continuer.addActionListener(new OkActionListener());
		
		//KeyListener d'entré
		//On écoute la touche entrée du clavier
		class Clavier implements KeyListener{
		      public void keyTyped(KeyEvent e) {}
		      @SuppressWarnings("deprecation")
			public void keyPressed(KeyEvent e) {
		         if(e.getKeyCode() == KeyEvent.VK_ENTER){
		        	 if(getTextNom().getText().equalsIgnoreCase("")){
							JOptionPane.showMessageDialog(null, "Vous devez remplir le champs d'utilisateur");
						}else{
							setPassword(getTextPwd().getText());
							setUserName(getTextNom().getText());
							String user = getUserName().replaceAll(" ", "");
							int password = getPassword().hashCode();
							setUserName(user);
							new ControlleurAccueil(getUserName(), password, getFenetre());
							
						}
		         }
		      }
		      public void keyReleased(KeyEvent e) {}   
		}
		
		//Les Keyboards listeners pour entrer
		textNom.addKeyListener(new Clavier());
		textPwd.addKeyListener(new Clavier());
		continuer.addKeyListener(new Clavier());
		
	}
	
	public JPanel getContenu() {
		return contenu;
	}

	public void setContenu(JPanel contenu) {
		this.contenu = contenu;
	}

	public JPanel getPaneNom() {
		return paneNom;
	}

	public void setPaneNom(JPanel paneNom) {
		this.paneNom = paneNom;
	}

	public JPanel getPanePwd() {
		return panePwd;
	}

	public void setPanePwd(JPanel panePwd) {
		this.panePwd = panePwd;
	}

	public JPanel getPaneLabelNom() {
		return paneLabelNom;
	}

	public void setPaneLabelNom(JPanel paneLabelNom) {
		this.paneLabelNom = paneLabelNom;
	}

	public JPanel getPaneLabelPwd() {
		return paneLabelPwd;
	}

	public void setPaneLabelPwd(JPanel paneLabelPwd) {
		this.paneLabelPwd = paneLabelPwd;
	}

	public JLabel getLabelNom() {
		return labelNom;
	}

	public void setLabelNom(JLabel labelNom) {
		this.labelNom = labelNom;
	}

	public JLabel getLabelPwd() {
		return labelPwd;
	}

	public void setLabelPwd(JLabel labelPwd) {
		this.labelPwd = labelPwd;
	}

	public JTextField getTextNom() {
		return textNom;
	}

	public void setTextNom(JTextField textNom) {
		this.textNom = textNom;
	}

	public JPasswordField getTextPwd() {
		return textPwd;
	}

	public void setTextPwd(JPasswordField textPwd) {
		this.textPwd = textPwd;
	}

	public JPanel getPaneTextNom() {
		return paneTextNom;
	}

	public void setPaneTextNom(JPanel paneTextNom) {
		this.paneTextNom = paneTextNom;
	}

	public JPanel getPaneTextPwd() {
		return paneTextPwd;
	}

	public void setPaneTextPwd(JPanel paneTextPwd) {
		this.paneTextPwd = paneTextPwd;
	}

	public JButton getAnnuler() {
		return annuler;
	}

	public void setAnnuler(JButton annuler) {
		this.annuler = annuler;
	}

	public JButton getContinuer() {
		return continuer;
	}

	public void setContinuer(JButton continuer) {
		this.continuer = continuer;
	}

	public JPanel getBottom() {
		return bottom;
	}

	public void setBottom(JPanel bottom) {
		this.bottom = bottom;
	}

	public JPanel getBottomDroit() {
		return bottomDroit;
	}

	public void setBottomDroit(JPanel bottomDroit) {
		this.bottomDroit = bottomDroit;
	}

	public JPanel getBottomGauche() {
		return bottomGauche;
	}

	public void setBottomGauche(JPanel bottomGauche) {
		this.bottomGauche = bottomGauche;
	}

	public Fenetre getFenetre() {
		return fenetre;
	}

	public void setFenetre(Fenetre fenetre) {
		this.fenetre = fenetre;
	}

	public JLabel getTitre() {
		return titre;
	}

	public void setTitre(JLabel titre) {
		this.titre = titre;
	}

	public Font getPolice() {
		return police;
	}

	public void setPolice(Font police) {
		this.police = police;
	}

	public Dimension getDim() {
		return dim;
	}

	public void setDim(Dimension dim) {
		this.dim = dim;
	}
	
}
