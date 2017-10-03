package BD;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

 class Lecture{

	private Scanner clavier,clavier2;
	private static String userName;
	private static String password;
	private static  String port;
	private static  String url;
	private static String error;
	private static String BD;
	private static boolean effectuer=true;
	
	 public Lecture() {
		lecture();		
	}
	
	//La fonction permet de lire dans le fichier config.djo, qui est le fichier de configuration de notre 
	//base de données
	 public void lecture(){
		try {
			String line,mot,value;
			clavier = new Scanner(new FileInputStream("config.djo"));
			
			while(clavier.hasNext()){
				line = clavier.nextLine();
				clavier2 = new Scanner(line);
				mot = clavier2.next();
				value = clavier2.next();
				if(mot.equalsIgnoreCase("user:")){
					setUserName(value);
				}else if(mot.equalsIgnoreCase("password:")){
					setPassword(value);
				}else if(mot.equalsIgnoreCase("port:")){
					setPort(value);
				}else if(mot.equalsIgnoreCase("url:")){
					setUrl(value);
				}else if(mot.equalsIgnoreCase("BD:")){
					setBD(value);
				}
			}
			setEffectuer(true);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			setEffectuer(false);
			setError("fichier introuvable, veuiller à ce qu'il soit là. Merci");
			
		}
		
	}
	
	 public static String getUserName() {
		return userName;
	}

	 public static void setUserName(String userName) {
		Lecture.userName = userName;
	}

	 public static String getPassword() {
		return password;
	}

	 public static  void setPassword(String password) {
		Lecture.password = password;
	}

	 public static String getPort() {
		return port;
	}

	 public static  void setPort(String port) {
		Lecture.port = port;
	}

	 public static  String getUrl() {
		return url;
	}

	 public static  void setUrl(String url) {
		Lecture.url = url;
	}

	 public static String getError() {
		return error;
	}

	 public static void setError(String error) {
		Lecture.error = error;
	}

	 public static boolean isEffectuer() {
		return effectuer;
	}

	 public static void setEffectuer(boolean effectuer) {
		Lecture.effectuer = effectuer;
	}

	public static String getBD() {
		return BD;
	}

	public static void setBD(String bD) {
		BD = bD;
	}

	
	
}
