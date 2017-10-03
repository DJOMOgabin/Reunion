package BD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class Requêtte {

	public Requêtte(){}
	
	public static String Type(String matricule){
		String resultat=null;
		try {
			//On se connecte à la BD
			if(new Connexion().isEffectuer()){
				String query = "SELECT DTYPE FROM adherent WHERE Matricule=? OR CNI=?";
				PreparedStatement prep = Connexion.getConn().prepareStatement(query);
				prep.setString(1, matricule);
				prep.setString(2, matricule);
				ResultSet result = prep.executeQuery();
				//On recupère le type de l'adhérent
				while(result.next())resultat = result.getString("DTYPE");
				result.close();
				prep.close();
			}else{
				System.out.println("Erreur de connexion");
				return null;
			}
			Connexion.getStmt().close();;
			Connexion.getConn().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultat;
	}
	
	//Supprimer tous les adhérents de la base
	public static boolean Supprimer(){
		boolean resultat=false;
		try {
			if(new Connexion().isEffectuer()){
				String query = "DELETE FROM adherent;";
				PreparedStatement prep = Connexion.getConn().prepareStatement(query);
				prep.executeUpdate();
				prep.close();
				resultat=true;
			}else{
				System.out.println("Erreur de connexion");
				resultat= false;
			}
			Connexion.getStmt().close();;
			Connexion.getConn().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultat;
	}
}
