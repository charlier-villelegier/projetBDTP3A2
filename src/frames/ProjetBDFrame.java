package frames;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import vues.VueAnnee;
import vues.VueGeographique;
import vues.VueStat;

/**
 * <b>ProjetBDFrame est la classe graphique repr�sentant la fen�tre principal de l'application.<b/>
 *
 */
public class ProjetBDFrame extends JFrame{
	
	/**
	 * Constructeur ProjetBDFrame
	 * 
	 * <p>En utilisant ce constructeur on initialise les diff�rentes caract�ristiques
	 * de la fen�tre.</p>
	 * 
	 * @param title
	 * 			Le titre de la fen�tre.
	 */
	public ProjetBDFrame(String title){
		super(title);
		this.setupUI();
		this.setSize(950,400);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	/**
	 * Permet de cr�er les diff�rents onglets de l'application.
	 */
	private void setupUI(){
		JTabbedPane tabbedPane = new JTabbedPane();
		
		tabbedPane.addTab("Statistiques globales", null, new VueStat(),
                "Voir des statistiques globales");
		tabbedPane.addTab("Stages par années", null, new VueAnnee(),
                "Voir des statistiques sur les stages par années");
		tabbedPane.addTab("Stages par localisation", null, new VueGeographique(),
                "Voir des statistiques sur les stages par localisation");
		
		this.add(tabbedPane);
	}
}
