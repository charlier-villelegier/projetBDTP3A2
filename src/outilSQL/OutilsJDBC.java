package outilSQL;
import java.sql.*;
import java.util.Scanner;


/**
 * <b>OutilsJDBC est la classe permettant différentes actions sur la base de données.
 * Cette classe a été fournie lors des précédent TPs.</b>
 */
public class OutilsJDBC {
	
	/**
	 * Permet d'ouvrir une connexion à la base de données.
	 * 
	 * @param url
	 * 			L'URL de la connexion à la base de données.
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
			System.out.println("impossible de se connecter Ã  l'url : "+url);
			System.exit(1);
		}
		return co;
		}
	
	/**
	 * Permet d'exécuter une requête.
	 * 
	 * @param requete
	 * 				La requête.
	 * @param co
	 * 				La connexion.
	 * @param type
	 * 				Le type de retour.
	 * @return
	 * 				Le résultat de la requête.
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
			System.out.println("ProblÃ©me lors de l'exÃ©cution de la requete : "+requete);
		};
		return res;
	}
	
	/**
	 * Permet de fermer une connexion.
	 * 
	 * @param co
	 * 			La connexion que l'on désire fermer.
	 */
	public static void closeConnection(Connection co){
		try {
			co.close();
			System.out.println("Connexion fermÃ©e!");
		}
		catch (SQLException e) {
			System.out.println("Impossible de fermer la connexion");
		}	
	}
}
