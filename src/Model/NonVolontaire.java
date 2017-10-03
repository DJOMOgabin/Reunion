package Model;


import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.sun.jmx.snmp.Timestamp;

import BD.CreationReunion;
import BD.LectureConfigReunion;

@Entity(name="Involontaire")
public class NonVolontaire extends Adherent {

	@Column(name="MontantEmprunt")
	private int MontantEmprunt;

	@Column(name="MontantRembourser")
	private int MontantRembourser;

	@Column(name="DateEmprunt")
	private Date dateEmprunt;

	@Column(name="DateRembourser")
	private Date dateRembourser;
	
	//Le statut d'emprunt de l'adh�rent, il est soit Actif = "A", Defaillant = "D", Exclu = "E"
	@Column(name="Statut")
	private String statut="A";
	
	@Column(name="Etat")
	private boolean etatEmprunt=false;
	
	//Permet de savoir si son statut � d�j� �t� v�rifi� ou pas?, c'est pour nous emp�cher de changer son statu de Actif � Exclu
	@Column(name="verifier")
	private boolean verifier = false;
	
	public NonVolontaire(){}
	
	public NonVolontaire(String matricule,String nom,String CNI,Date date,String adresse,
			String telephone,int Emprunt,int Rembourser,Date dateEmprunt,Date dateRembourser){
		super(matricule,nom,CNI,date,adresse,telephone);
		this.MontantEmprunt=Emprunt;
		this.MontantRembourser=Rembourser;
		this.dateEmprunt=dateEmprunt;
		this.dateRembourser=dateRembourser;
	}
	
	public NonVolontaire(Adherent adherent,int Emprunt,int Rembourser,Date dateEmprunt,Date dateRembourser){
		super(adherent.getMatricule(),adherent.getNom(),adherent.getNumeroCNI(),adherent.getDateAdhesion()
				,adherent.getAdresse(),adherent.getTelephone());
		this.MontantEmprunt=Emprunt;
		this.MontantRembourser=Rembourser;
		this.dateEmprunt=dateEmprunt;
		this.dateRembourser=dateRembourser;
	}

	public int getMontantEmprunt() {
		return MontantEmprunt;
	}

	public void setMontantEmprunt(int montantEmprunt) {
		MontantEmprunt = montantEmprunt;
	}

	public int getMontantRembourser() {
		return MontantRembourser;
	}

	public void setMontantRembourser(int montantRembourser) {
		MontantRembourser = montantRembourser;
	}

	public Date getDateEmprunt() {
		return dateEmprunt;
	}

	public void setDateEmprunt(Date dateEmprunt) {
		this.dateEmprunt = dateEmprunt;
	}

	public Date getDateRembourser() {
		return dateRembourser;
	}

	public void setDateRembourser(Date dateRembourser) {
		this.dateRembourser = dateRembourser;
	}

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}

	public boolean isEtat() {
		return etatEmprunt;
	}

	public void setEtat(boolean etat) {
		this.etatEmprunt = etat;
	}
	
	public boolean isVerifier() {
		return verifier;
	}

	public void setVerifier(boolean verifier) {
		this.verifier = verifier;
	}

	@SuppressWarnings("deprecation")
	//Permet de mettre � jour son profil, apr�s un emprunts
	public void AJour(){
		if(getDateRembourser()!=null){
			Date dateDuJour = new Date(new Timestamp().getDateTime());
			//Date o� il est sens� rembourser son argent
			int montant=0;
			Date dateRembourser = getDateRembourser();;
			//Le montant basique � remettre apr�s chaque emprunt
			montant +=getMontantEmprunt()*1.1;
			setMontantRembourser(montant);
			if(dateRembourser.before(dateDuJour)){
				if(getStatut().equals("A")){
					setVerifier(true);
					setStatut("D");
				}
				else if(getStatut().equals("D") && !isVerifier()) {
					setVerifier(true);
					setStatut("E");
				}
				
				
			     System.out.println("date � rembourser: "+dateRembourser);
			     System.out.println("date  du jour: "+dateDuJour);
			     
			     System.out.println();
			     boolean distance=dateDuJour.after(dateRembourser);
			     while(distance){
				     dateRembourser.setMonth(dateRembourser.getMonth()+1);
				     distance=dateDuJour.after(dateRembourser);
				     montant += (int) (getMontantEmprunt()*0.1);
			     }
			     System.out.println("\nMontant g�n�ral:"+montant);
			     setMontantRembourser(montant);
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	//Permet d'emprunt de l'argent
	public String emprunter(int nombre){
		//S'il est d�faillant ou exclu
		if(getStatut().equalsIgnoreCase("E")) return "L'adh�rent est exclu";
		//S'il a un emprunt en cours
		else if(isEtat()) return "L'adh�rent a d�j� un emprunt en cours";
		//S'il est �ligible aux emprunts
		else{
			int somme = nombre*Reunion.getPart();
			new LectureConfigReunion();
			Reunion reunion = CreationReunion.ChargerReunion(LectureConfigReunion.getUserName());
			if(new Date(new Timestamp().getDateTime()).after(reunion.getDateFinEmprunt())
					|| new Date(new Timestamp().getDateTime()).before(reunion.getDateFinSouscription())) 
				return "Vous n'�tes pas dans l'intervalle d'emprunt!\n"+
					"Les emprunts doivent se faire entre "+reunion.getDateFinSouscription()
					+" et le "+reunion.getDateFinEmprunt();
			else if(reunion.getNombrePart()>=nombre){
				setMontantEmprunt(somme);
				setDateEmprunt(new Date(new Timestamp().getDateTime()));
				setDateRembourser(new Date(new Timestamp().getDateTime()));
				getDateRembourser().setMonth(getDateRembourser().getMonth()+3);
				//setMontantRembourser((int) (getMontantEmprunt()*1.1));
				setEtat(true);
				reunion.setCapitale(reunion.getCapitale()-somme);
				CreationReunion.EnregistrerReunion(reunion);
				System.out.println("La reunion a �t� mis � jour");
				AJour();
				
				System.out.println("L'adh�rent a �t� mis � jour");
				return "L'op�ration a reussi avec succes!!!";
			}else{
				return "Le fond de la reunion est insuffisant";
			}
		}	
	}
	
	//rembourser l'argent emprunt� et de faire une mise � jour de son profil
	public boolean rembourser(){
		if(!isEtat()) return false;
		else{
			int somme = getMontantRembourser();
			setDateRembourser(null);
			setDateEmprunt(null);
			setEtat(false);
			new LectureConfigReunion();
			Reunion reunion = CreationReunion.ChargerReunion(LectureConfigReunion.getUserName());
			reunion.setCapitale(reunion.getCapitale()+somme);
			CreationReunion.EnregistrerReunion(reunion);
			setMontantEmprunt(0);
			setMontantRembourser(0);
			return true;
		}
	}
	
	
	
}
