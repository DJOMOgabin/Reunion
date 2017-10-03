package BD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


//Cette classe permet juste d'établir la connexion avec la bd, sans toute fois faire la moindre requêtte
public class Connexion extends com.mysql.jdbc.Driver {
	
	private static Connection conn;
	private static Statement stmt;
	private boolean effectuer=true;
	private static String error;
	
	public Connexion() throws SQLException{
		super();
		Etablish();		
	}
	
	//la fonction nous permet de faire la connection avec la base de donnée
	public void Etablish(){
		new Lecture();
		setConn(null);
		String url="jdbc:mysql://"+Lecture.getUrl()+":"+Lecture.getPort()+"/"+Lecture.getBD()+"?useUnicode=true&;characterEncoding=utf8";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			setConn(DriverManager.getConnection(url, Lecture.getUserName(), Lecture.getPassword()));
			setStmt(getConn().createStatement());
			setEffectuer(true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			setEffectuer(false);
			setError("Une erreur est survenu, verifiez si tous les paramètres sont bien definis");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			setEffectuer(false);
			setError("Une erreur est survenu, classe du pilot pour la connexion à la bd est introuvable");
		}
	}

	public static Connection getConn() {
		return conn;
	}

	public static void setConn(Connection conn) {
		Connexion.conn = conn;
	}

	public static Statement getStmt() {
		return stmt;
	}

	public static void setStmt(Statement stmt) {
		Connexion.stmt = stmt;
	}


	public boolean isEffectuer() {
		return effectuer;
	}

	public void setEffectuer(boolean effectuer) {
		this.effectuer = effectuer;
	}

	public static String getError() {
		return error;
	}

	public static void setError(String error) {
		Connexion.error = error;
	}
	
	
}