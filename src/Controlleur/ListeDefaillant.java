package Controlleur;

import java.util.ArrayList;

import BD.CreationAdherent;
import Model.NonVolontaire;
import Vue.Fenetre;
import Vue.TotalDefaillant;

public class ListeDefaillant {
	private Fenetre fenetre;
	private ArrayList<NonVolontaire> nonVolontaire;
	//La classe va nous permettre de lister tous les adhérents qui sont défaillants ou exclus
	public ListeDefaillant(Fenetre fenetre){
		this.fenetre=fenetre;
		remplissage();
	}
	
	public void remplissage(){
		//On recupère les adhérents non actifs
		setNonVolontaire(CreationAdherent.chargerAdherentDefaillant());
		System.out.println("qu'es-ce qui ce passe?");
		//On passe à la vue pour les afficher
		new TotalDefaillant(getNonVolontaire(), getFenetre());
	}

	public Fenetre getFenetre() {
		return fenetre;
	}

	public void setFenetre(Fenetre fenetre) {
		this.fenetre = fenetre;
	}

	public ArrayList<NonVolontaire> getNonVolontaire() {
		return nonVolontaire;
	}

	public void setNonVolontaire(ArrayList<NonVolontaire> nonVolontaire) {
		this.nonVolontaire = nonVolontaire;
	}
	
}
