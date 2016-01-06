package vues;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import outilSQL.RequeteSQL;

public class VueAnnee extends JPanel{
	
	public VueAnnee(){
		setupUI();
	}
	
	private void setupUI(){
		
		/**Specification des layout et définition des composants**/
		
		this.setLayout(new BorderLayout());

		JPanel topCenter = new JPanel(new GridBagLayout());
		final JLabel nbStagesTotal = new JLabel();
		final JLabel nbStagesMoyens = new JLabel();
		SpinnerModel sm = new SpinnerNumberModel(1, 1, 10, 1);
		final JSpinner yearSpinner = new JSpinner(sm);
		GridBagConstraints c = new GridBagConstraints();
		
		JLabel textEntreprise = new JLabel("Nombre de stage dans l'entreprise ");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		topCenter.add(textEntreprise,c);
		
		final JComboBox<String> nameEntreprise = new JComboBox<String>();
		nameEntreprise.removeAllItems();
		ResultSet res = null;
		try {
			res=RequeteSQL.listeEntreprise.executeQuery();
			while (res.next()){
				nameEntreprise.addItem(res.getString("colonne"));
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		c.gridx = 2;
		c.gridy = 0;
		c.gridwidth = 2;
		nameEntreprise.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				try {
					/**Nombre total de stage**/
					RequeteSQL.nbStageTotalEntrepriseAnnee.setFloat(3, (int) yearSpinner.getValue());
					RequeteSQL.nbStageTotalEntrepriseAnnee.setString(2, nameEntreprise.getSelectedItem().toString());
					RequeteSQL.nbStageTotalEntrepriseAnnee.registerOutParameter(1, java.sql.Types.INTEGER);
					
					//On l'execute
					RequeteSQL.nbStageTotalEntrepriseAnnee.execute();
					nbStagesTotal.setText(String.valueOf(RequeteSQL.nbStageTotalEntrepriseAnnee.getInt(1)) + " stage(s) au total");
					
					/**Nombre moyen de stage**/
					RequeteSQL.nbStageMoyenEntrepriseAnnee.setFloat(3, (int) yearSpinner.getValue());
					RequeteSQL.nbStageMoyenEntrepriseAnnee.setString(2, nameEntreprise.getSelectedItem().toString());
					RequeteSQL.nbStageMoyenEntrepriseAnnee.registerOutParameter(1, java.sql.Types.INTEGER);
					
					//On l'execute
					RequeteSQL.nbStageMoyenEntrepriseAnnee.execute();
					nbStagesMoyens.setText(String.valueOf(RequeteSQL.nbStageMoyenEntrepriseAnnee.getFloat(1)) + " stage(s)/an en moyenne");
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
	    });
		topCenter.add(nameEntreprise,c);
		
		//Label année
		JLabel textYear= new JLabel("durant les/la ");
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		topCenter.add(textYear,c);
		
		//Spinner
		c.gridx = 1;
		c.gridy = 1;
		yearSpinner.addChangeListener(new ChangeListener() {

	        @Override
	        public void stateChanged(ChangeEvent e) {
	        	try {
	        		/**Nombre total de stage**/
					RequeteSQL.nbStageTotalEntrepriseAnnee.setFloat(3, (int) yearSpinner.getValue());
					RequeteSQL.nbStageTotalEntrepriseAnnee.setString(2, nameEntreprise.getSelectedItem().toString());
					RequeteSQL.nbStageTotalEntrepriseAnnee.registerOutParameter(1, java.sql.Types.FLOAT);
					
					//On l'execute
					RequeteSQL.nbStageTotalEntrepriseAnnee.execute();
					nbStagesTotal.setText(String.valueOf(RequeteSQL.nbStageTotalEntrepriseAnnee.getInt(1)) + " stage(s) au total");
					
					/**Nombre moyen de stage**/
					RequeteSQL.nbStageMoyenEntrepriseAnnee.setFloat(3, (int) yearSpinner.getValue());
					RequeteSQL.nbStageMoyenEntrepriseAnnee.setString(2, nameEntreprise.getSelectedItem().toString());
					RequeteSQL.nbStageMoyenEntrepriseAnnee.registerOutParameter(1, java.sql.Types.FLOAT);
					
					//On l'execute
					RequeteSQL.nbStageMoyenEntrepriseAnnee.execute();
					nbStagesMoyens.setText(String.valueOf(RequeteSQL.nbStageMoyenEntrepriseAnnee.getFloat(1)) + " stage(s)/an en moyenne");
					
					
	        	} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
				
	        }
	    });
		topCenter.add(yearSpinner,c);
		
		//Label année2
		JLabel textYear2 = new JLabel(" dernière(s) année(s)");
		c.gridx = 2;
		c.gridy = 1;
		topCenter.add(textYear2,c);
		
		
		this.add(topCenter, BorderLayout.CENTER);
		
		JPanel south = new JPanel(new GridLayout(2,1));
		
		nbStagesTotal.setFont(new Font("Calibri",Font.BOLD,20));
		nbStagesTotal.setHorizontalAlignment(JLabel.CENTER);
		nbStagesMoyens.setFont(new Font("Calibri",Font.BOLD,20));
		nbStagesMoyens.setHorizontalAlignment(JLabel.CENTER);
		
		
		south.add(nbStagesTotal);
		south.add(nbStagesMoyens);
		
		this.add(south, BorderLayout.SOUTH);
		
		//On provoque volontairement la mise à jour de l'interface
		yearSpinner.getChangeListeners()[0].stateChanged(null);
				
		
	}

}
