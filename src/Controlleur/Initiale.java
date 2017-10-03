package Controlleur;

import javax.swing.UIManager;

import BD.LectureConfigReunion;
import Vue.Authentification;
import Vue.CreerReunion;
import Vue.Fenetre;

public class Initiale {

	public static void main(String[] args) {
		//Il permet de gérer l'éffet d'affichage
		// set system properties here that affect Quaqua
	     // for example the default layout policy for tabbed
	     // panes:
	     System.setProperty(
	        "Quaqua.tabLayoutPolicy","wrap"
	     );
	     // set the Quaqua Look and Feel in the UIManager
	     try { 
	         UIManager.setLookAndFeel(ch.randelshofer.quaqua.QuaquaManager.getLookAndFeel());
	     // set UI manager properties here that affect Quaqua...
	     } catch (Exception e) {
	         // take an appropriate action here...
	         e.printStackTrace();
	     }
	     //On lit le fichier de configuration pour savoir s'il existe une reunion active actuelle
		new LectureConfigReunion();
		if(LectureConfigReunion.getUserName().equals("")){
			//sinon, on crée une nouvelle reunion
			new CreerReunion(new Fenetre());
		}else{
			//si, on s'authentifie
			new Authentification(new Fenetre());
		}
	}

}
