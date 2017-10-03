package Model;



import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import BD.CreationReunion;
import BD.LectureConfigReunion;

@Entity(name="Volontaire")
public class Volontaire extends NonVolontaire{
	
	@Column(name="Revenu")
	private int revenu;
	
	@Column(name="Part")
	private int part=0;

	public Volontaire(){}
	
	//Actionnaire
	public Volontaire(String matricule,String nom,String CNI,Date date,String adresse,String telephone,int revenu,int part){
		super(matricule,nom,CNI,date,adresse,telephone,0,0,null,null);
		this.revenu=revenu;
	}

	public Volontaire(Adherent adherent,int revenu,int part){
		super(adherent.getMatricule(),adherent.getNom(),adherent.getNumeroCNI(),adherent.getDateAdhesion()
				,adherent.getAdresse(),adherent.getTelephone(),0,0,null,null);
		this.revenu=revenu;
	}


	public int getRevenu() {
		return revenu;
	}


	public void setRevenu(int revenu) {
		this.revenu = revenu;
	}


	public int getPart() {
		return part;
	}

	public void setPart(int part) {
		this.part = part;
	}
	
	//Mise à jour de son revenu
	public void AJour(int part,int fond){
		int revenu = (int) (((getPart()*fond)/part)*0.85);
		System.out.println("fond: "+fond+"  part: "+part);
		setRevenu(revenu);
	}
	
	//On fait le présent volontaire souscrire aux parts et on met à jour tout ce qui va avec
	public boolean souscrire(int part){
		setPart(getPart()+part);
		new LectureConfigReunion();
		Reunion reunion = CreationReunion.ChargerReunion(LectureConfigReunion.getUserName());
		reunion.setCapitale(reunion.getCapitale()+part*Reunion.getPart());
		reunion.setNombrePart(reunion.getNombrePart()+part);
		AJour(reunion.getNombrePart(), reunion.getCapitale());
		reunion.setRevenu((int) (reunion.getCapitale()*0.15));
		System.out.println(reunion.getCapitale());
		int id = CreationReunion.EnregistrerReunion(reunion);
		if(id!=-1) return true;
		else return false;
	}
}
