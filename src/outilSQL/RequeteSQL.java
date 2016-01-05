package outilSQL;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RequeteSQL {

	public static Connection myCo;
	
	public static CallableStatement nbStageTotalEntrepriseAnnee;
	public static CallableStatement nbStageMoyenEntrepriseAnnee;
	public static PreparedStatement statisticsTable;
	public static PreparedStatement listeDept;
	public static PreparedStatement listeVille;
	public static PreparedStatement listeEntreprise;
	
	
	public static void initialize(){

		String url="jdbc:oracle:thin:xvillel/Xavier2303@oracle.iut-orsay.fr:1521:etudom";
		
		//On ouvre la connection
		System.out.println("Connexion en cours...");
		myCo = OutilsJDBC.openConnection(url);
		System.out.println("Connexion réussie");
		
		try {
			listeVille = myCo.prepareStatement ("SELECT DISTINCT s.ENTREPRISE.ADRESSE.Ville as colonne "
					+ "FROM LCHARLI.TABLE_STAGE s");
			
			listeDept = myCo.prepareStatement ("SELECT DISTINCT s.ENTREPRISE.ADRESSE.Departement as colonne "
					+ "FROM LCHARLI.TABLE_STAGE s");
			
			listeEntreprise = myCo.prepareStatement ("SELECT e.nomentreprise "
					+ "FROM LCHARLI.TABLE_ENTREPRISE e;");
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
