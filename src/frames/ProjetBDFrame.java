package frames;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import vues.VueAnnee;
import vues.VueGeographique;
import vues.VueStat;

public class ProjetBDFrame extends JFrame{
	
	public ProjetBDFrame(String title){
		super(title);
		this.setupUI();
		this.pack();
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}
	
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
