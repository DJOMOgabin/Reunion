package Model;


import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.sun.jmx.snmp.Timestamp;

@Entity(name="Adherent")
public class Adherent {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private int id;

	@Column(name="Matricule")
	private String matricule;

	@Column(name="Nom")
	private String nom;

	@Column(name="CNI")
	private String NumeroCNI;

	@Column(name="Date")
	//La valeur par défault de la date d'adhésion est la date d'aujourd'hui
	private Date DateAdhesion=new Date(new Timestamp().getDateTime());

	@Column(name="Adresse")
	private String Adresse;

	@Column(name="Telephone")
	private String Telephone;

	@Column(name="Photo")
	private String photo="";
	
	public Adherent(){}
	
	public Adherent(String matricule,String nom,String CNI,Date date,String adresse,String telephone){
		this.matricule=matricule;
		this.nom=nom;
		this.NumeroCNI=CNI;
		this.Adresse=adresse;
		this.Telephone=telephone;
		this.DateAdhesion=date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMatricule() {
		return matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getNumeroCNI() {
		return NumeroCNI;
	}

	public void setNumeroCNI(String numeroCNI) {
		NumeroCNI = numeroCNI;
	}

	public Date getDateAdhesion() {
		return DateAdhesion;
	}

	public void setDateAdhesion(Date dateAdhesion) {
		DateAdhesion = dateAdhesion;
	}

	public String getAdresse() {
		return Adresse;
	}

	public void setAdresse(String adresse) {
		Adresse = adresse;
	}

	public String getTelephone() {
		return Telephone;
	}

	public void setTelephone(String telephone) {
		Telephone = telephone;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

}
