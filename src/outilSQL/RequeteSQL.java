package outilSQL;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RequeteSQL {
	
	//Connexion
	public static Connection myCo;
	
	//Statements
	public static CallableStatement nbStageTotalEntrepriseAnnee;
	public static CallableStatement nbStageMoyenEntrepriseAnnee;
	public static CallableStatement nbStageMoyen;
	
	public static CallableStatement nbStageDepartementTotal;
	public static CallableStatement nbStageDepartementMoyen;
	public static CallableStatement nbStageVilleTotal;
	public static CallableStatement nbStageVilleMoyen;
	
	public static CallableStatement nbSansStageDate;
	
	public static PreparedStatement statisticsTable;
	public static PreparedStatement listeDept;
	public static PreparedStatement listeVille;
	public static PreparedStatement listeEntreprise;
	public static PreparedStatement tableStat;
	public static PreparedStatement EntrepriseContactStage;
	
	
	//Initialisation
	public static void initialize(){

		String url="jdbc:oracle:thin:xvillel/Xavier2303@oracle.iut-orsay.fr:1521:etudom";
		
		//On ouvre la connection
		System.out.println("Connexion en cours...");
		myCo = OutilsJDBC.openConnection(url);
		System.out.println("Connexion réussie");
		
		
		/**Initialisation de tous les PreparedStatement et CallableStatement**/
		
		try {
			listeVille = myCo.prepareStatement ("SELECT DISTINCT s.ENTREPRISE.ADRESSE.Ville as colonne "
					+ "FROM LCHARLI.TABLE_STAGE s");
			
			listeDept = myCo.prepareStatement ("SELECT DISTINCT s.ENTREPRISE.ADRESSE.Departement as colonne "
					+ "FROM LCHARLI.TABLE_STAGE s");
			
			listeEntreprise = myCo.prepareStatement ("SELECT e.nomentreprise as colonne "
					+ "FROM LCHARLI.TABLE_ENTREPRISE e");
			
			tableStat = myCo.prepareStatement ("SELECT * "
					+ "FROM LCHARLI.STAT1");
			
			EntrepriseContactStage = myCo.prepareStatement ("select DISTINCT e.NOMENTREPRISE, e.CONTACT.PERSONNE.nom, e.CONTACT.PERSONNE.prenom "
					+ "from lcharli.TABLE_STAGE st, lcharli.TABLE_entreprise e "
					+ "where e.NumEntreprise=ST.ENTREPRISE.numEntreprise "
					+ "AND ST.ANNEESTAGE >= extract(year FROM (sysdate))-?");
			
			
			nbStageDepartementTotal = myCo.prepareCall("{? = call fonctionDepTotal(?,?)}");
			nbStageDepartementMoyen = myCo.prepareCall("{? = call fonctionDepMoyen(?,?)}");
			
			nbStageVilleTotal = myCo.prepareCall("{? = call fonctionVilleTotal(?,?)}");
			nbStageVilleMoyen = myCo.prepareCall("{? = call fonctionVilleMoyen(?,?)}");
			
			nbStageMoyen = myCo.prepareCall("{? = call fonctionAVGStage(?)}");
			
			nbStageTotalEntrepriseAnnee = myCo.prepareCall("{? = call nbStageTotalEntreprise(?,?)}");
			
			nbStageMoyenEntrepriseAnnee = myCo.prepareCall("{? = call nbStageMoyenEntreprise(?,?)}");
			
			nbSansStageDate = myCo.prepareCall("{? = call nbEtuSansStage(?,?)}");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}
