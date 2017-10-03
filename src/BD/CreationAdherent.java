package BD;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import Model.Adherent;
import Model.NonVolontaire;
import Model.Volontaire;

//Les interactions avec la base de données pour les adhérents 
public class CreationAdherent {
	//Enregistrer un nouvel adhérent, peut importe son type
	public static int EnregistrerAdherent(Adherent adherent){
		int valeur=-1;

		try{
			@SuppressWarnings("deprecation")
			SessionFactory sessionFactory =  new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
			System.out.println("config");
			Session session = sessionFactory.openSession();
			System.out.println("session");
			Transaction tx=null;
			try{
				tx = session.beginTransaction();
				String type = Requêtte.Type(adherent.getMatricule());
				System.out.println("requêtte");
				System.out.println(type);
				if(type!=null &&type.equalsIgnoreCase("Volontaire")){
					Adherent adherentCharge = CreationAdherent.chargerAdherent(adherent.getMatricule());
					System.out.println("Volontaire");
					if (adherentCharge!=null){
						session.delete(adherentCharge);
					}
				}else if(type!=null && type.equalsIgnoreCase("Involontaire")){
					NonVolontaire involontaire = CreationAdherent.chargerAdherentInvolontaire(adherent.getMatricule());
					System.out.println("Involontaire");
					if (involontaire!=null){
						session.delete(involontaire);
					}
				}
				valeur=(Integer)session.save(adherent);
				session.flush();
				tx.commit();
			}catch(Exception e){
				if(tx!=null){
					tx.rollback();
				}
				throw e;
			}finally {
				session.close();
			}
			sessionFactory.close();
		}catch(Throwable ex){
			System.out.println(ex);
		}
		
		return valeur;
	}
	
	//Charger un adhérent actionnaire par son matricule ou le numéro de sa CNI
	public static Volontaire chargerAdherent(String matricule){
		Volontaire adherent=null;
		try {
			@SuppressWarnings("deprecation")
			SessionFactory sessionFactory =  new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
			Session session = sessionFactory.openSession();
			Transaction tx=null;
			try{
				tx=session.beginTransaction();
				adherent =(Volontaire)session.createQuery("FROM Adherent WHERE matricule=? OR CNI=?" )
						.setString(0, matricule).setString(1, matricule).uniqueResult();
				System.out.println("chargement de Adherent reussi");
				tx.commit();
			}finally {
				session.close();
			}
			sessionFactory.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return adherent;
	}
	
	//Charger la liste dans l'ordre croissant des adhérents dont ayant les emprunts en cours suivant le boolean actif
	@SuppressWarnings("unchecked")
	public static ArrayList<NonVolontaire> chargerAdherent(boolean actif){
		ArrayList<NonVolontaire> adherent=null;
		try {
			@SuppressWarnings("deprecation")
			SessionFactory sessionFactory =  new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
			Session session = sessionFactory.openSession();
			Transaction tx=null;
			try{
				tx=session.beginTransaction();
				System.out.println("ici");
				if(actif)adherent =(ArrayList<NonVolontaire>)session.createQuery("FROM Adherent WHERE Etat!=0 ORDER BY nom ASC" ).list();
				else adherent =(ArrayList<NonVolontaire>)session.createQuery("FROM Adherent ORDER BY nom ASC" ).list();
				System.out.println("chargement des Adherents reussi");
				tx.commit();
			}finally {
				session.close();
			}
			sessionFactory.close();
		} catch (Exception e) {
			System.out.println("Chargement échoué");
		}
		return adherent;
	}
	
	//Charger la liste des adhérents défaillant
	@SuppressWarnings("unchecked")
	public static ArrayList<NonVolontaire> chargerAdherentDefaillant(){
		ArrayList<NonVolontaire> adherent=null;
		try {
			@SuppressWarnings("deprecation")
			SessionFactory sessionFactory =  new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
			Session session = sessionFactory.openSession();
			Transaction tx=null;
			try{
				tx=session.beginTransaction();
				System.out.println("ici");
				adherent =(ArrayList<NonVolontaire>)session.createQuery("FROM Adherent WHERE Statut!=? ORDER BY nom ASC" )
						.setString(0, "A").list();
				System.out.println("chargement des Adherents reussi");
				tx.commit();
			}finally {
				session.close();
			}
			sessionFactory.close();
		} catch (Exception e) {
			System.out.println("Chargement échoué");
		}
		return adherent;
	}
	
	//Charger la liste des adhérents actionnaires
	@SuppressWarnings("unchecked")
	public static ArrayList<Volontaire> chargerAdherentVolontaire(){
		ArrayList<Volontaire> adherent=null;
		try {
			@SuppressWarnings("deprecation")
			SessionFactory sessionFactory =  new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
			Session session = sessionFactory.openSession();
			Transaction tx=null;
			try{
				tx=session.beginTransaction();
				System.out.println("ici");
				adherent =(ArrayList<Volontaire>)session.createQuery("FROM Adherent WHERE DTYPE=? ORDER BY nom ASC" )
						.setString(0, "Volontaire").list();
				System.out.println("chargement des Adherents reussi");
				tx.commit();
			}finally {
				session.close();
			}
			sessionFactory.close();
		} catch (Exception e) {
			System.out.println("Chargement échoué");
		}
		return adherent;
	}
	
	
	//Charger un adhérent Non Volontaire par son matricule ou sa CNI
	public static NonVolontaire chargerAdherentInvolontaire(String matricule){
		NonVolontaire adherent=null;
		try {
			@SuppressWarnings("deprecation")
			SessionFactory sessionFactory =  new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
			Session session = sessionFactory.openSession();
			Transaction tx=null;
			try{
				tx=session.beginTransaction();
				adherent =(NonVolontaire)session.createQuery("FROM Adherent WHERE matricule=? OR CNI=?" )
						.setString(0, matricule).setString(1, matricule).uniqueResult();
				System.out.println("chargement de Adherent reussi");
				tx.commit();
			}finally {
				session.close();
			}
			sessionFactory.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return adherent;
	}
}
