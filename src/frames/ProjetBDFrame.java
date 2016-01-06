package frames;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import vues.VueAnnee;
import vues.VueGeographique;
import vues.VueStat;

/**
 * <b>ProjetBDFrame est la classe graphique représentant la fenêtre principal de l'application.<b/>
 *
 */
public class ProjetBDFrame extends JFrame{
	
	/**
	 * Constructeur ProjetBDFrame
	 * 
	 * <p>En utilisant ce constructeur on initialise les différentes caractéristiques
	 * de la fenêtre.</p>
	 * 
	 * @param title
	 * 			Le titre de la fenêtre.
	 */
	public ProjetBDFrame(String title){
		super(title);
		this.setupUI();
		this.setSize(950,400);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}
	
	/**
	 * Permet de créer les différents onglets de l'application.
	 */
	private void setupUI(){
		JTabbedPane tabbedPane = new JTabbedPane();
		
		tabbedPane.addTab("Statistiques globales", null, new VueStat(),
                "Voir des statistiques globales");
		tabbedPane.addTab("Stages par annÃ©es", null, new VueAnnee(),
                "Voir des statistiques sur les stages par annÃ©es");
		tabbedPane.addTab("Stages par localisation", null, new VueGeographique(),
                "Voir des statistiques sur les stages par localisation");
		
		this.add(tabbedPane);
	}
}
