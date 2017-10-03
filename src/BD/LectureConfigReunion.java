package BD;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class LectureConfigReunion {

	private Scanner clavier,clavier2;
	private static String userName;
	private static String password;
	private static String taillaMax;	
	private static boolean effectuer=true;
	private static String error;
	
	 public LectureConfigReunion() {
		lecture();		
	}
	
	//La fonction permet de lire dans le fichier parametres.txt, qui est le fichier de configuration 
	//Pour l'authentification à notre application, pour la gestion d'une reunion précise
	 public void lecture(){
		try {
			String line="",mot="",value="";
			clavier = new Scanner(new FileInputStream("parametres.txt"));
			setUserName("");
			setPassword("");
			setTaillaMax("");
			
			while(clavier.hasNext()){
				line = clavier.nextLine();
				clavier2 = new Scanner(line);
				mot = clavier2.next();
				value = clavier2.next();
				if(mot.equalsIgnoreCase("user:")){
					System.out.println("mot: "+mot+"\nvalue: "+value);
					setUserName(value);
				}else if(mot.equalsIgnoreCase("password:")){
					setPassword(value);
				}else if(mot.equalsIgnoreCase("max:")){
					setTaillaMax(value);
				}
			}
			setEffectuer(true);
			clavier.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			setEffectuer(false);
			JOptionPane.showConfirmDialog(null,"fichier introuvable, veuiller à ce qu'il soit là. Merci");
			
		}
	
	 }
	 
	 public static void main(String[] args){
			new LectureConfigReunion();
			if(LectureConfigReunion.isEffectuer()){
				System.out.println("user:"+LectureConfigReunion.getUserName()+" password:"+LectureConfigReunion.getPassword());
			}
	}
	 //On éfface le fichier pour être obligé de créer une nouvelle reunion
	 public static void SupprimerFichier(){
		 PrintWriter print;
			try {
				print = new PrintWriter(new BufferedWriter(new FileWriter("parametres.txt",false)));
				print.println("");
				print.close();
			} catch (IOException e) {
				System.out.println("Le fichier n'a pas pu être supprimé");
			}
	 }

	 //Les getters et setters
	public static String getUserName() {
		return userName;
	}

	public static void setUserName(String userName) {
		LectureConfigReunion.userName = userName;
	}

	public static String getPassword() {
		return password;
	}

	public static void setPassword(String password) {
		LectureConfigReunion.password = password;
	}

	public static boolean isEffectuer() {
		return effectuer;
	}

	public static void setEffectuer(boolean effectuer) {
		LectureConfigReunion.effectuer = effectuer;
	}

	public static String getError() {
		return error;
	}

	public static void setError(String error) {
		LectureConfigReunion.error = error;
	}

	public static String getTaillaMax() {
		return taillaMax;
	}

	public static void setTaillaMax(String taillaMax) {
		LectureConfigReunion.taillaMax = taillaMax;
	}
	
}
