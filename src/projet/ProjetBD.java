package projet;

import outilSQL.RequeteSQL;
import frames.ProjetBDFrame;

public class ProjetBD {

	public static void main(String[] args) {
		//On initialise les requêtes afin de les préparer dans le serveur SQL.
		//Permet d'alléger le traitement côté JDBC
		RequeteSQL.initialize();
		
		//On affiche la frame
		ProjetBDFrame maFrame = new ProjetBDFrame("Statistiques sur les stages");

	}

}
