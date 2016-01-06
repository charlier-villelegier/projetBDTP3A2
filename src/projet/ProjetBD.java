package projet;

import outilSQL.RequeteSQL;
import frames.ProjetBDFrame;

/**
 * <b>ProjetBD est la classe contenant uniquement le main et permet donc de lancer l'application.</b>
 */
public class ProjetBD {

	/**
	 * Met � jour le message d'erreur.
	 * 
	 * @param args
	 * 		Les arguments pour lancer le programme (inutile ici).
	 * 
	 * @see outilSQL.RequeteSQL
	 * @see RequeteSQL.initialize
	 * @see frames.ProjetBDFrame
	 */
	public static void main(String[] args) {
		//On initialise les requêtes afin de les préparer dans le serveur SQL.
		//Permet d'alléger le traitement côté JDBC
		RequeteSQL.initialize();
		
		//On affiche la frame
		ProjetBDFrame maFrame = new ProjetBDFrame("Statistiques sur les stages");

	}

}
