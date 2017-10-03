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

//Affiche tous les adhérents défaillant en une couleur en fonction s'ils sont défaillant ou exclu
public class TotalDefaillant {
	
	private Fenetre fenetre;
	private JPanel contenu = new JPanel();
	private JPanel bottom = new JPanel();
	private ArrayList<NonVolontaire> NonVolontaire;
	private Button contenu6 = new Button("Retour"); 
	private JMenuBar conteneur;

	public JMenuBar getConteneur() {
		return conteneur;
	}

	public void setConteneur(JMenuBar conteneur) {
		this.conteneur = conteneur;
	}

	public TotalDefaillant(ArrayList<NonVolontaire> NonVolontaire,Fenetre fene){

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

	public void remplissage(){
		getContenu().setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel[] empruntGauche = new JLabel[getNonVolontaire().size()];
		JLabel[] empruntDroite = new JLabel[empruntGauche.length];
		JPanel[] empruntPourDeux = new JPanel[empruntGauche.length];
		JPanel[] gauche = new JPanel[empruntGauche.length];
		JPanel[] droite = new JPanel[empruntGauche.length];
		JPanel panel = new JPanel();
		String statut;
		
		panel.setLayout(new GridLayout(empruntGauche.length,1));
		for(int i=0;i<empruntGauche.length;i++){
			empruntGauche[i] = new JLabel();
			empruntDroite[i] = new JLabel();
			gauche[i] = new JPanel();
			droite[i] = new JPanel();
			empruntPourDeux[i] = new JPanel();
			
			gauche[i].setLayout(new FlowLayout(FlowLayout.LEFT));
			droite[i].setLayout(new FlowLayout(FlowLayout.RIGHT));
			
			empruntGauche[i].setFont(new java.awt.Font("Times new roman",Font.UNDERLINE,15));
			empruntDroite[i].setFont(new java.awt.Font("Times new roman",Font.UNDERLINE,15));
			if(getNonVolontaire().get(i).isEtat()) {
				statut="En cours";				
			}else {
				statut="En règle";
			}
			//Les couleurs de défaillant 
			//Orange pour défaillant
			if(getNonVolontaire().get(i).getStatut().equals("D")){
				empruntGauche[i].setForeground(Color.orange);
				empruntDroite[i].setForeground(Color.orange);
			}else if(getNonVolontaire().get(i).getStatut().equals("E")){
				//Route pour exclu
				empruntGauche[i].setForeground(Color.red);
				empruntDroite[i].setForeground(Color.red);
			}
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
		
		JLabel label = new JLabel("   Adhérents défaillants");
		label.setFont(new java.awt.Font("Algerian", Font.BOLD, 25));
		label.setForeground(Color.blue);
		label.setPreferredSize(new Dimension(400,40));
		
		getContenu().setLayout(new FlowLayout(FlowLayout.CENTER));
		getContenu().setPreferredSize(new Dimension(450,390));
		
		getContenu().add(label);		
		getContenu().add(panel);

		getBottom().setLayout(new GridLayout(1,2));
		
		getContenu6().getBouton().setPreferredSize(new Dimension(100, 30));
		getBottom().add(getContenu6().getPanel());
		getBottom().add(new JPanel());
		
		//Les listeners
		getContenu6().getBouton().addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				getFenetre().getFenetre().getContentPane().removeAll();
				getConteneur().removeAll();
				new Accueil(getFenetre());
			}
		});
		for(int i=0;i<getNonVolontaire().size();i++){
			empruntPourDeux[i].addMouseListener(new Information(i));
		}
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
			
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void mousePressed(MouseEvent e) {
				JPanel info = TotalEmprunt.information(getNonVolontaire().get(getIndice()));
				JOptionPane.showConfirmDialog(null,info,
						"Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
				
			}
			
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			public int getIndice() {
				return indice;
			}

			public void setIndice(int indice) {
				this.indice = indice;
			}
			
			
	}
	
}
