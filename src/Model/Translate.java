package Model;

public class Translate {

	private String nom;
	private Object value;
	//La classe qui nous permet de stocker tous type d'objet et de pouvoir le transporter dans une autre classe
	public Translate(String nom,Object value){
		this.nom=nom;
		this.value=value;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	
	
}
