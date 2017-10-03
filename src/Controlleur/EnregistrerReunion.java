package Controlleur;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;

import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import BD.CreationReunion;
import Model.Reunion;
import Model.Translate;
import Vue.Authentification;
import Vue.DateChooser;
import Vue.Fenetre;
import Vue.Text;

public class EnregistrerReunion {

	private ArrayList<Translate> request;
	private Fenetre fenetre;
	//Il permet d'enregistrer une reunion dans la base de données, il va intereagir avec la class qui va persister la reunion
	public EnregistrerReunion(ArrayList<Translate> request,Fenetre fenetre,JMenuBar conteneur){
		//request est un objet crée pour faire passer tous genre de paramètres dans une autre classe
		this.request=request;
		this.fenetre=fenetre;
		manipulation(conteneur);
	}
	
	@SuppressWarnings("deprecation")
	public void manipulation(JMenuBar conteneur){
		//On recupère les paramètres nécessaire pour la création d'une nouvelle reunion
		Date debut = new Date(((DateChooser)get("debut")).getDate().getDate().getTime());
		Date session = new Date(((DateChooser)get("session")).getDate().getDate().getTime());
		Date souscription = new Date(((DateChooser)get("souscription")).getDate().getDate().getTime());
		Date emprunt = new Date(((DateChooser)get("emprunt")).getDate().getDate().getTime());
		String gerant = ((Text)get("gestionnaire")).getText().getText();
		String user = ((Text)get("user")).getText().getText();
		String max = ((Text)get("max")).getText().getText();
		System.out.println(user);
		String password = ""+((JPasswordField)get("password")).getText().hashCode();
		//On crée un objet reunion 
		Reunion reunion = new Reunion(debut, session, souscription,emprunt, gerant,user);
		//On enregistre dans le fichier le userName, le hashé du password et le nombre max de part
		//qui va nous permettre de s'authentifier directement à cette reunion çi
		String nouveau="user: "+user+"\npassword: "+password+"\nmax: "+max;
		PrintWriter print;
		try {
			print = new PrintWriter(new BufferedWriter(new FileWriter("parametres.txt",false)));
			print.println(nouveau);
			print.close();
			//On persiste dans la base de données notre la reunion
			int id = CreationReunion.EnregistrerReunion(reunion);
			
			if(id!=-1) {
				//Si la reunion est créée, on affiche un retro-action et on vide la fenêtre puis la fait passer 
				//à la classe qui gère l'authentification pour que le gerant s'authentifie
				JOptionPane.showConfirmDialog(null, "Les paramètres ont bel et bien été enregistrés?",
					"Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
				getFenetre().getFenetre().getContentPane().removeAll();
				conteneur.removeAll();
				new Authentification(getFenetre());
			}//Sinon, on renvoie une message disant que la procedure a échouée
			else JOptionPane.showConfirmDialog(null, "Il y'a eu une érreur lors de l'enregistrement de la reunion",
					"Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
		} catch (IOException e) {
			//Le fichier d'authentification à de problème d'ouverture
			JOptionPane.showConfirmDialog(null, "Veuillez créer un fichier parametres.txt dans projet Djangui");
		}
		System.out.println(debut);
	}
	//fonction qui permet de recupérer un paramètre passé à la classe dans notre objet request
	public Object get(String nom){
		for(int i=0;i<getRequest().size();i++){
			if(getRequest().get(i).getNom().equalsIgnoreCase(nom)){
				return getRequest().get(i).getValue();
			}
		}
		return null;
	}

	public ArrayList<Translate> getRequest() {
		return request;
	}

	public void setRequest(ArrayList<Translate> request) {
		this.request = request;
	}

	public Fenetre getFenetre() {
		return fenetre;
	}

	public void setFenetre(Fenetre fenetre) {
		this.fenetre = fenetre;
	}
	
}
