package Controlleur;

import java.sql.Date;
import java.util.ArrayList;

import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.sun.jmx.snmp.Timestamp;

import BD.CreationAdherent;
import Model.Adherent;
import Model.NonVolontaire;
import Model.Translate;
import Model.Volontaire;
import Vue.Accueil;
import Vue.CheckBox;
import Vue.DateChooser;
import Vue.Enregistrer;
import Vue.Fenetre;
import Vue.Part;
import Vue.ProfilAdherent;
import Vue.Text;


public class EnregistrerAdherent {
	
	private boolean reussite;
	private ArrayList<Translate> request = new ArrayList<Translate>();
	private Fenetre fenetre;
	//La classe permet d'enregistrer un adhérent
	public EnregistrerAdherent(ArrayList<Translate> request,Fenetre fenetre,JPanel contenu,JPanel bottom,JMenuBar conteneur){
		this.request=request;
		this.fenetre=fenetre;
		enregistrer(conteneur);
	}
	
	public void enregistrer(JMenuBar conteneur){
		//On recupère les paramètres passé à request
		System.out.println(((Text)getRequest().get(0).getValue()).getText().getText());
		String nom = ((Text)get("nom")).getText().getText();
		String CNI = ((Text)get("numero")).getText().getText();
		String telephone = ((Text)get("telephone")).getText().getText();
		String adresse = ((Text)get("adresse")).getText().getText();
		java.util.Date time = ((DateChooser)get("date")).getDate().getDate();
		Date date;
		if(time!=null)date=new Date(((DateChooser)get("date")).getDate().getDate().getTime());
		else date = new Date(new Timestamp().getDateTime());
		//if(time=null)date=new Date(time);
		boolean action = ((CheckBox)get("actionnaire")).getCheck().isSelected();
		@SuppressWarnings("deprecation")
		//On fabrique un matricule qui est unique et utilisant la CNI et l'année de la session de reunion
		String matricule = ""+(new Timestamp().getDate().getYear()+1900)+"D"+Math.abs(CNI.hashCode());
		Adherent adherent = new Adherent(matricule, nom, CNI,date, adresse, telephone);
		System.out.println(date);
		int id=-1;
		if(action){
			//S'il est actionnaire, on redirige vers la page qui lui permet de souscrire aux parts
			Volontaire volontaire = new Volontaire(adherent, 0, 0);
			System.out.println("part "+volontaire.getPart());
			new Part(true,matricule,volontaire,getFenetre(),conteneur);					
		}else{
			//Sinon, on l'enregistre dans la base de données et on fait des retro-actions pour monter le déroulement de l'action
			//
			NonVolontaire involontaire = new NonVolontaire(adherent, 0, 0, null, null);
			id = CreationAdherent.EnregistrerAdherent(involontaire);
			if(id!=-1){
				
				getFenetre().getFenetre().getContentPane().removeAll();
				if(conteneur!=null) conteneur.removeAll();
				int pro = JOptionPane.showConfirmDialog(null, "Enregistrement reussi\n"
	    						+ "\tSon matricule est: "+matricule+"\nVoir Profil?",
						"Confirmation", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(pro==JOptionPane.OK_OPTION){
					new ProfilAdherent(getFenetre(),involontaire);
				}else if(pro==JOptionPane.CANCEL_OPTION){
					new Accueil(getFenetre());					
				}
			}else{
				JOptionPane.showMessageDialog(null, "Un problème est survenu lors de l'enregistrement");
				new Enregistrer(getFenetre());
			}
		}		
		
		System.out.println(id);
			
	}
	//recupérer un paramètre dans request
	public Object get(String nom){
		for(int i=0;i<getRequest().size();i++){
			if(getRequest().get(i).getNom().equalsIgnoreCase(nom)){
				return getRequest().get(i).getValue();
			}
		}
		return null;
	}

	public boolean isReussite() {
		return reussite;
	}

	public void setReussite(boolean reussite) {
		this.reussite = reussite;
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
