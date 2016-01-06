package vues;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import outilSQL.RequeteSQL;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JSpinnerDateEditor;

/**
 * <b>VueStat est la classe graphique affichant les statistiques globales sur les stages.<b/>
 *
 */
public class VueStat extends JPanel{

	/**
	 * Constructeur VueStat
	 * 
	 * <p>En utilisant ce constructeur on initialise les diffÈrentes caractÈristiques
	 * de la vue.</p>
	 */
	public VueStat(){
		setupUI();
	}
	
	/**
	 * Permet de spÈcifier les diffÈrentes caractÈristiques et contenu de la vue.
	 * 
	 * @see RequeteSQL
	 */
	private void setupUI(){
		
		//Specification des layout et d√©finition des composants
		
		this.setLayout(new BorderLayout());

		JPanel topCenter = new JPanel(new GridBagLayout());
		final JLabel nbStageMoyenAnnee = new JLabel();
		final JLabel nbSansStage = new JLabel();
		final JLabel anneeCourante = new JLabel();
		final JLabel avecStageAnneeCourante = new JLabel();
		final JLabel sansStageAnneeCourante = new JLabel();
		
		SpinnerModel smnbyear = new SpinnerNumberModel(1, 1, 10, 1);
		SpinnerModel smnbyearEntr = new SpinnerNumberModel(1, 1, 10, 1);
		final JSpinner nbAnnee = new JSpinner(smnbyear);
		final JSpinner nbAnneeEntreprise = new JSpinner(smnbyearEntr);
		
		SpinnerModel sm = new SpinnerNumberModel(2016, 2010, 2016, 1);
		final JSpinner annee = new JSpinner(sm);
		final JDateChooser moncalendrier = new JDateChooser();
		final JComboBox<String> listeEntreprise = new JComboBox<String>();
		GridBagConstraints c = new GridBagConstraints();
		moncalendrier.setDateFormatString("dd/MM/yyyy");
		
		
		//Remplissage des statistiques
		ResultSet res;
		try {
			res = RequeteSQL.tableStat.executeQuery();
			
			while (res.next()){
				anneeCourante.setText(res.getString(1));
				avecStageAnneeCourante.setText(res.getString(3));
				sansStageAnneeCourante.setText(res.getString(2));	
			}
			
		} catch (SQLException e2) {
			
			e2.printStackTrace();
		}
		
		//Formatage de l'interface
		c.insets = new Insets(10, 0, 10, 0);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		topCenter.add(new JLabel("Ann√©e courante : "),c);
		
		
		c.gridx = 1;
		c.gridy = 0;
		c.gridwidth = 1;
		topCenter.add(anneeCourante,c);
		
		
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		topCenter.add(new JLabel("Etudiant avec stage : "),c);
		
	
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 1;
		topCenter.add(avecStageAnneeCourante,c);
		
		
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 1;
		topCenter.add(new JLabel("Etudiant sans stage : "),c);
		
		
		
		c.gridx = 1;
		c.gridy = 2;
		c.gridwidth = 1;
		topCenter.add(sansStageAnneeCourante,c);
		
		
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 6;
		c.insets = new Insets(0, 0, 0, 0);
		JSeparator separator = new JSeparator();
		separator.setBackground(Color.black);
		topCenter.add(separator,c);
		
		c.insets = new Insets(10, 0, 10, 0);
		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 1;
		topCenter.add(new JLabel("Etudiant sans stage en : "),c);
		
		
		c.gridx = 2;
		c.gridy = 4;
		c.gridwidth = 1;
		topCenter.add(annee,c);
		
		
		c.gridx = 3;
		c.gridy = 4;
		c.gridwidth = 1;
		topCenter.add(new JLabel(" a la date du "),c);
		
		
		c.gridx = 4;
		c.gridy = 4;
		c.gridwidth = 1;
		topCenter.add(moncalendrier,c);
		
		JButton calcul = new JButton("Calculer");
		c.gridx = 5;
		c.gridy = 4;
		c.gridwidth = 1;
		calcul.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					RequeteSQL.nbSansStageDate.setInt(2, (int) annee.getValue());
					RequeteSQL.nbSansStageDate.setDate(3, new Date(moncalendrier.getDate().getTime()));
					RequeteSQL.nbSansStageDate.registerOutParameter(1, java.sql.Types.INTEGER);
					
					RequeteSQL.nbSansStageDate.execute();
					
					nbSansStage.setText(String.valueOf(RequeteSQL.nbSansStageDate.getInt(1)) + " etudiants sans stages");
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		topCenter.add(calcul,c);
		
		
		c.gridx = 6;
		c.gridy = 4;
		c.gridwidth = 1;
		topCenter.add(nbSansStage,c);
		
		
		c.gridx = 0;
		c.gridy = 5;
		c.gridwidth = 2;
		topCenter.add(new JLabel("Nombre moyen de stagiaires les "),c);
		nbAnnee.addChangeListener(new ChangeListener() {

	        @Override
	        public void stateChanged(ChangeEvent e) {
	        	try {
					RequeteSQL.nbStageMoyen.setInt(2, (int) nbAnnee.getValue());
					RequeteSQL.nbStageMoyen.registerOutParameter(1, java.sql.Types.FLOAT);
					
					//On l'execute
					RequeteSQL.nbStageMoyen.execute();
					nbStageMoyenAnnee.setText(String.valueOf(RequeteSQL.nbStageMoyen.getFloat(1)) + " stage(s) en moyenne/entreprise");
					
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
				
	        }
	    });
		
		
		c.gridx = 2;
		c.gridy = 5;
		c.gridwidth = 1;
		topCenter.add(nbAnnee,c);
		
		
		c.gridx = 3;
		c.gridy = 5;
		c.gridwidth = 1;
		topCenter.add(new JLabel("derni√®re(s) ann√©e(s) : "),c);
		
		
		c.gridx = 4;
		c.gridy = 5;
		c.gridwidth = 2;
		topCenter.add(nbStageMoyenAnnee,c);
		
		
		c.gridx = 0;
		c.gridy = 6;
		c.gridwidth = 6;
		c.insets = new Insets(0, 0, 0, 0);
		JSeparator separator2 = new JSeparator();
		separator2.setBackground(Color.black);
		topCenter.add(separator2,c);
		
		c.insets = new Insets(10, 0, 10, 0);
		c.gridx = 0;
		c.gridy = 7;
		c.gridwidth = 3;
		topCenter.add(new JLabel("Entreprise et contact ayant eu au moins un stage ces "),c);
		
		c.gridx = 3;
		c.gridy = 7;
		c.gridwidth = 1;
		nbAnneeEntreprise.addChangeListener(new ChangeListener() {

	        @Override
	        public void stateChanged(ChangeEvent e) {
	        	try {
	        		RequeteSQL.EntrepriseContactStage.setInt(1, (int) nbAnneeEntreprise.getValue());
	        		ResultSet res = RequeteSQL.EntrepriseContactStage.executeQuery();
					listeEntreprise.removeAllItems();
	        		
					while (res.next()){
						String entrepriseContact=res.getString(1) + " - Contact : " + res.getString(2) + " " + res.getString(3);	
						listeEntreprise.addItem(entrepriseContact);
					}
					
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
				
	        }
	    });
		topCenter.add(nbAnneeEntreprise,c);
		
		c.gridx = 4;
		c.gridy = 7;
		c.gridwidth = 1;
		topCenter.add(new JLabel("derni√®re(s) ann√©e(s) : "),c);
		
		c.gridx = 5;
		c.gridy = 7;
		c.gridwidth = 2;
		topCenter.add(listeEntreprise,c);
		
		
		this.add(topCenter, BorderLayout.CENTER);
		
		
		//On provoque volontairement la mise √† jour de l'interface
		nbAnnee.getChangeListeners()[0].stateChanged(null);
		nbAnneeEntreprise.getChangeListeners()[0].stateChanged(null);
		
		
	}
}
