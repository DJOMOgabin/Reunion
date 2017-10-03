package BD;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import Model.Reunion;

//Les interactions de la reunion avec la base de données pour la manipulation de la reunion
public class CreationReunion {
	//Suppression d'une reunion, on supprime une session
	public static boolean SupprimerReunion(Reunion reunion){
		boolean valeur=false;

		try{
			@SuppressWarnings("deprecation")
			SessionFactory sessionFactory =  new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
			System.out.println("config");
			Session session = sessionFactory.openSession();
			System.out.println("session");
			Transaction tx=null;
			try{
				tx = session.beginTransaction();
				Reunion second = (Reunion)session.createQuery("FROM Reunion WHERE user=?").setString(0, reunion.getUser()).uniqueResult();
				if(second!=null) {
					session.delete(second);
				}
				session.flush();
				tx.commit();
				Requêtte.Supprimer();
				valeur=true;
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
			valeur=false;
		}
		
		return valeur;
	}
	
	//Creation d'une nouvelle reunion et qui retourne son id dans la base, s'il est -1, alors l'opération a échouée
	public static int EnregistrerReunion(Reunion reunion){
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
				Reunion second = (Reunion)session.createQuery("FROM Reunion WHERE user=?").setString(0, reunion.getUser()).uniqueResult();
				if(second!=null) {
					session.delete(second);
				}
				valeur=(Integer)session.save(reunion);		
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
	
	//Recupérer une reunion dans la base de donnéeset est null si la dite reunion n'existe pas
	public static Reunion ChargerReunion(String user){
		Reunion second=null;
		try{
			@SuppressWarnings("deprecation")
			SessionFactory sessionFactory =  new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
			System.out.println("config");
			Session session = sessionFactory.openSession();
			System.out.println("session");
			Transaction tx=null;
			try{
				tx = session.beginTransaction();
				second = (Reunion)session.createQuery("FROM Reunion WHERE user=?").setString(0,user).uniqueResult();				
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
		
		return second;
	}
}
