package Controlleur;

import java.util.ArrayList;

import BD.CreationAdherent;
import Model.NonVolontaire;
import Vue.Fenetre;
import Vue.TotalEmprunt;

public class ListeEmprunt {
	private Fenetre fenetre;
	private boolean actif;
	private ArrayList<NonVolontaire> nonVolontaire;
	//La classe permet de lister les emprunts en cours ou pas, suivant le boolean actif
	public ListeEmprunt(Fenetre fenetre,boolean actif){
		this.fenetre=fenetre;
		this.actif=actif;
		remplissage();
	}
	
	public void remplissage(){
		//On charge les adhérents suivant le boolean actif(si on veut seulement ceux qui on les emprunts en cours ou non)
		setNonVolontaire(CreationAdherent.chargerAdherent(isActif()));
		System.out.println("qu'es-ce qui ce passe?");
		//On passe à la vue qui va nous afficher la liste
		new TotalEmprunt(getNonVolontaire(), getFenetre(), isActif());
	}

	public Fenetre getFenetre() {
		return fenetre;
	}

	public void setFenetre(Fenetre fenetre) {
		this.fenetre = fenetre;
	}

	public boolean isActif() {
		return actif;
	}

	public void setActif(boolean actif) {
		this.actif = actif;
	}

	public ArrayList<NonVolontaire> getNonVolontaire() {
		return nonVolontaire;
	}

	public void setNonVolontaire(ArrayList<NonVolontaire> nonVolontaire) {
		this.nonVolontaire = nonVolontaire;
	}
	
}
