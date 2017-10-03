package Controlleur;

import javax.swing.JOptionPane;

import BD.LectureConfigReunion;
import BD.Requêtte;
import Vue.Accueil;
import Vue.CreerReunion;
import Vue.Fenetre;

public class ControlleurAccueil {

	private String user;
	private int password;
	private Fenetre fenetre;
	//Le controlleur de la page d'authentification
	public ControlleurAccueil(String user,int password,Fenetre fenetre){
		this.fenetre=fenetre;
		this.user=user;
		this.password=password;
		construction();
	}
	
	//Vérification du nom d'utilisateur et du mot de passe, et donner la possibilité d'en créer une nouvelle reunion en cas
	//d'oublie de nom ou de mot de passe
	public void construction(){
		new LectureConfigReunion();
		if(LectureConfigReunion.getUserName().equalsIgnoreCase(getUser())){
			if(LectureConfigReunion.getPassword().equals(""+getPassword())){
				getFenetre().getFenetre().getContentPane().removeAll();
				new Accueil(getFenetre());
			}else{
				int pro = JOptionPane.showConfirmDialog(null,"Mot de passe incorrect, \nvoulez-vous créer une nouvelle reunion?",
						"Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
				if(pro==JOptionPane.OK_OPTION){
					getFenetre().getFenetre().getContentPane().removeAll();
					Requêtte.Supprimer();
					new CreerReunion(getFenetre());
				}
			}
		}else{
			int pro = JOptionPane.showConfirmDialog(null,"Nom d'utilisateur incorrect, \nvoulez-vous créer une nouvelle reunion?",
					"Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
			if(pro==JOptionPane.OK_OPTION){
				getFenetre().getFenetre().getContentPane().removeAll();
				Requêtte.Supprimer();
				new CreerReunion(getFenetre());
			}
		}
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public int getPassword() {
		return password;
	}

	public void setPassword(int password) {
		this.password = password;
	}

	public Fenetre getFenetre() {
		return fenetre;
	}

	public void setFenetre(Fenetre fenetre) {
		this.fenetre = fenetre;
	}
	
	
}
