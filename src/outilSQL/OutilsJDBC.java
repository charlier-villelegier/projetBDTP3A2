package outilSQL;
import java.sql.*;
import java.util.Scanner;


/**
 * <b>OutilsJDBC est la classe permettant diff�rentes actions sur la base de donn�es.
 * Cette classe a �t� fournie lors des pr�c�dent TPs.</b>
 */
public class OutilsJDBC {
	
	/**
	 * Permet d'ouvrir une connexion � la base de donn�es.
	 * 
	 * @param url
	 * 			L'URL de la connexion � la base de donn�es.
	 * @return
	 * 			La connexion.
	 */
	public static Connection openConnection (String url) {
		Connection co=null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			co= DriverManager.getConnection(url);
		}
		catch (ClassNotFoundException e){
			System.out.println("il manque le driver oracle");
			System.exit(1);
		}
		catch (SQLException e) {
			System.out.println("impossible de se connecter à l'url : "+url);
			System.exit(1);
		}
		return co;
		}
	
	/**
	 * Permet d'ex�cuter une requ�te.
	 * 
	 * @param requete
	 * 				La requ�te.
	 * @param co
	 * 				La connexion.
	 * @param type
	 * 				Le type de retour.
	 * @return
	 * 				Le r�sultat de la requ�te.
	 */
	public static ResultSet exec1Requete (String requete, Connection co, int type){
		ResultSet res=null;
		try {
			Statement st;
			if (type==0){
				st=co.createStatement();}
			else {
				st=co.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 
					       	ResultSet.CONCUR_READ_ONLY);
				};
			res= st.executeQuery(requete);
		}
		catch (SQLException e){
			System.out.println("Probléme lors de l'exécution de la requete : "+requete);
		};
		return res;
	}
	
	/**
	 * Permet de fermer une connexion.
	 * 
	 * @param co
	 * 			La connexion que l'on d�sire fermer.
	 */
	public static void closeConnection(Connection co){
		try {
			co.close();
			System.out.println("Connexion fermée!");
		}
		catch (SQLException e) {
			System.out.println("Impossible de fermer la connexion");
		}	
	}
}
