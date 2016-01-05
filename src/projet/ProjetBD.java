package projet;

import outilSQL.RequeteSQL;
import frames.ProjetBDFrame;

public class ProjetBD {

	public static void main(String[] args) {
		RequeteSQL.initialize();
		ProjetBDFrame maFrame = new ProjetBDFrame("Statistiques sur les stages");

	}

}
