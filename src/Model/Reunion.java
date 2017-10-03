package Model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="Reunion")
public class Reunion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private int id;
	
	@Column(name="Debut")
	private Date dateDebut;

	@Column(name="FinEmprunt")
	private Date dateFinEmprunt;

	@Column(name="FinReunion")
	private Date dateFinReunion;

	@Column(name="FinSouscription")
	private Date dateFinSouscription;

	@Column(name="Capitale")
	private  int capitale;
	
	@Column(name="Part")
	private static int Part=5000;
	
	@Column(name="revenu")
	private int revenu;
	
	@Column(name="gerant")
	private String gerant;
	
	@Column(name="user")
	private String user;
	
	@Column(name="Nombre")
	private  int nombrePart;
		
	public Reunion(){}
	
	public Reunion(Date dateDebut,Date dateFinReunion,Date dateFinSouscription,Date dateFinEmprunt,String gerant,String user){
		this.dateDebut=dateDebut;
		this.dateFinReunion=dateFinReunion;
		this.dateFinSouscription=dateFinSouscription;
		this.gerant=gerant;
		this.dateFinEmprunt = dateFinEmprunt;
		this.user=user;
		this.capitale=0;
	}

	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateFinEmprunt() {
		return dateFinEmprunt;
	}

	public void setDateFinEmprunt(Date dateFinEmprunt) {
		this.dateFinEmprunt = dateFinEmprunt;
	}

	public Date getDateFinReunion() {
		return dateFinReunion;
	}

	public void setDateFinReunion(Date dateFinReunion) {
		this.dateFinReunion = dateFinReunion;
	}

	public Date getDateFinSouscription() {
		return dateFinSouscription;
	}

	public void setDateFinSouscription(Date dateFinSouscription) {
		this.dateFinSouscription = dateFinSouscription;
	}

	public  int getCapitale() {
		return capitale;
	}

	public  void setCapitale(int capitale) {
		this.capitale = capitale;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public static int getPart() {
		return Part;
	}

	public static void setPart(int part) {
		Reunion.Part = part;
	}

	public int getRevenu() {
		return revenu;
	}

	public void setRevenu(int revenu) {
		this.revenu = revenu;
	}

	public String getGerant() {
		return gerant;
	}

	public void setGerant(String gerant) {
		this.gerant = gerant;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public  int getNombrePart() {
		return nombrePart;
	}

	public  void setNombrePart(int nombrePart) {
		this.nombrePart = nombrePart;
	}

	
}
