package vues;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import frames.DeptDialog;
import frames.VilleDialog;
import outilSQL.RequeteSQL;

/**
 * <b>VueGeographique est la classe graphique affichant les diff�rentes statistiques sur les stages 
 * en fonction de la localisation du stage.<b/>
 *
 */
public class VueGeographique extends JPanel{
	
	/**
	 * Constructeur VueGeographique
	 * 
	 * <p>En utilisant ce constructeur on initialise les diff�rentes caract�ristiques
	 * de la vue.</p>
	 */
	public VueGeographique(){
		setupUI();
	}
	
	/**
	 * Permet de sp�cifier les diff�rentes caract�ristiques et contenu de la vue.
	 * 
	 * @see RequeteSQL
	 */
	private void setupUI(){
		
		//Specification des layout et définition des composants hauts.
		
		this.setLayout(new BorderLayout());
		
		SpinnerModel sm = new SpinnerNumberModel(1, 1, 10, 1);
		final JSpinner yearSpinner = new JSpinner(sm);
		JPanel panelTop = new JPanel(new GridLayout(1,4));
		panelTop.add(new JPanel());
		panelTop.add(new JPanel());
		JButton vueDpt = new JButton("Voir le résumé des départements");
		vueDpt.addActionListener(new ActionListener()
	    {
	        @Override
	        public void actionPerformed(ActionEvent e)
	        {
	            DeptDialog dialogDept = new DeptDialog(VueGeographique.this);
	        }
	    });
		JButton vueVille = new JButton("Voir le résumé des villes");
		vueVille.addActionListener(new ActionListener()
	    {
	        @Override
	        public void actionPerformed(ActionEvent e)
	        {
	            VilleDialog dialogVille = new VilleDialog(VueGeographique.this);
	        }
	    });
		panelTop.add(vueVille);
		panelTop.add(vueDpt);
		
		this.add(panelTop, BorderLayout.NORTH);
		
		//Specification des layout et définition des composants centraux.
		
		JPanel topCenter = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		final JComboBox<String> listeVilleDept = new JComboBox<String>();
		final JLabel nbStagesTotal = new JLabel();
		final JLabel nbStagesMoyens = new JLabel();
		
		JLabel textGeog = new JLabel("Nombre de stage dans ");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		topCenter.add(textGeog,c);
		
		
		//JComboBox de choix entre la ville ou les départements
		final JComboBox<String> villeOrDept = new JComboBox<String>();
		villeOrDept.addItem("ville");
		villeOrDept.addItem("département");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 0;
		c.gridwidth = 1;
		villeOrDept.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				try {
					ResultSet res = null;
					if(villeOrDept.getSelectedItem().toString().equals("ville")){
						res=RequeteSQL.listeVille.executeQuery();
						listeVilleDept.removeAllItems();
						
					}
					else if(villeOrDept.getSelectedItem().toString().equals("département")){
						res=RequeteSQL.listeDept.executeQuery();
						listeVilleDept.removeAllItems();
						
					}
					//On met à jour la liste
					while (res.next()){
						listeVilleDept.addItem(res.getString("colonne"));
					}
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
	    });
		topCenter.add(villeOrDept,c);
		
		//JLabel d'affichage
		JLabel textGeog2 = new JLabel(" de/du ");
		c.gridx = 3;
		c.gridy = 0;
		c.gridwidth = 1;
		topCenter.add(textGeog2,c);
		
		//JComboBox des département ou des villes
		listeVilleDept.removeAllItems();
		ResultSet res = null;
		try {
			res=RequeteSQL.listeVille.executeQuery();
			while (res.next()){
				listeVilleDept.addItem(res.getString("colonne"));
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		c.gridx = 5;
		c.gridy = 0;
		c.gridwidth = 2;
		listeVilleDept.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				try {
					if(listeVilleDept.getSelectedItem() != null){
						if(villeOrDept.getSelectedItem().toString().equals("ville")){
							//Nombre de stage total
							RequeteSQL.nbStageVilleTotal.setString(2, listeVilleDept.getSelectedItem().toString());
							RequeteSQL.nbStageVilleTotal.setFloat(3, (int) yearSpinner.getValue());
							RequeteSQL.nbStageVilleTotal.registerOutParameter(1, java.sql.Types.FLOAT);
							
							//On l'execute
							RequeteSQL.nbStageVilleTotal.execute();
							nbStagesTotal.setText(String.valueOf(RequeteSQL.nbStageVilleTotal.getInt(1)) + " stage(s) au total");
							
							//Nombre de stage moyen
							RequeteSQL.nbStageVilleMoyen.setString(2, listeVilleDept.getSelectedItem().toString());
							RequeteSQL.nbStageVilleMoyen.setFloat(3, (int) yearSpinner.getValue());
							RequeteSQL.nbStageVilleMoyen.registerOutParameter(1, java.sql.Types.FLOAT);
							
							//On l'execute
							RequeteSQL.nbStageVilleMoyen.execute();
							nbStagesMoyens.setText(String.valueOf(RequeteSQL.nbStageVilleMoyen.getFloat(1)) + " stage(s)/an en moyenne");
						}
						else if(villeOrDept.getSelectedItem().toString().equals("département")){
							
							//Nombre de stage total
							RequeteSQL.nbStageDepartementTotal.setInt(2, Integer.parseInt(listeVilleDept.getSelectedItem().toString()));
							RequeteSQL.nbStageDepartementTotal.setFloat(3, (int) yearSpinner.getValue());
							RequeteSQL.nbStageDepartementTotal.registerOutParameter(1, java.sql.Types.FLOAT);
							
							//On l'execute
							RequeteSQL.nbStageDepartementTotal.execute();
							nbStagesTotal.setText(String.valueOf(RequeteSQL.nbStageDepartementTotal.getInt(1)) + " stage(s) au total");
							
							//Nombre de stage moyen
							RequeteSQL.nbStageDepartementMoyen.setString(2, listeVilleDept.getSelectedItem().toString());
							RequeteSQL.nbStageDepartementMoyen.setFloat(3, (int) yearSpinner.getValue());
							RequeteSQL.nbStageDepartementMoyen.registerOutParameter(1, java.sql.Types.FLOAT);
							
							//On l'execute
							RequeteSQL.nbStageDepartementMoyen.execute();
							nbStagesMoyens.setText(String.valueOf(RequeteSQL.nbStageDepartementMoyen.getFloat(1)) + " stage(s)/an en moyenne");
						}
					}
					
					
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
	    });
		topCenter.add(listeVilleDept,c);
		
		JLabel textYear= new JLabel("durant les/la ");
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		topCenter.add(textYear,c);
		
		//JSpinner
		c.gridx = 1;
		c.gridy = 1;
		yearSpinner.addChangeListener(new ChangeListener() {

	        @Override
	        public void stateChanged(ChangeEvent e) {
	        	try {
	        		if(listeVilleDept.getSelectedItem() != null){
						if(villeOrDept.getSelectedItem().toString().equals("ville")){
							//Nombre de stage total
							RequeteSQL.nbStageVilleTotal.setString(2, listeVilleDept.getSelectedItem().toString());
							RequeteSQL.nbStageVilleTotal.setFloat(3, (int) yearSpinner.getValue());
							RequeteSQL.nbStageVilleTotal.registerOutParameter(1, java.sql.Types.FLOAT);
							
							//On l'execute
							RequeteSQL.nbStageVilleTotal.execute();
							nbStagesTotal.setText(String.valueOf(RequeteSQL.nbStageVilleTotal.getInt(1)) + " stage(s) au total");
							
							//Nombre de stage moyen
							RequeteSQL.nbStageVilleMoyen.setString(2, listeVilleDept.getSelectedItem().toString());
							RequeteSQL.nbStageVilleMoyen.setFloat(3, (int) yearSpinner.getValue());
							RequeteSQL.nbStageVilleMoyen.registerOutParameter(1, java.sql.Types.FLOAT);
							
							//On l'execute
							RequeteSQL.nbStageVilleMoyen.execute();
							nbStagesMoyens.setText(String.valueOf(RequeteSQL.nbStageVilleMoyen.getFloat(1)) + " stage(s)/an en moyenne");
						}
						else if(villeOrDept.getSelectedItem().toString().equals("département")){
							
							//Nombre de stage total
							RequeteSQL.nbStageDepartementTotal.setInt(2, Integer.parseInt(listeVilleDept.getSelectedItem().toString()));
							RequeteSQL.nbStageDepartementTotal.setFloat(3, (int) yearSpinner.getValue());
							RequeteSQL.nbStageDepartementTotal.registerOutParameter(1, java.sql.Types.FLOAT);
							
							//On l'execute
							RequeteSQL.nbStageDepartementTotal.execute();
							nbStagesTotal.setText(String.valueOf(RequeteSQL.nbStageDepartementTotal.getInt(1)) + " stage(s) au total");
							
							//Nombre de stage moyen
							RequeteSQL.nbStageDepartementMoyen.setString(2, listeVilleDept.getSelectedItem().toString());
							RequeteSQL.nbStageDepartementMoyen.setFloat(3, (int) yearSpinner.getValue());
							RequeteSQL.nbStageDepartementMoyen.registerOutParameter(1, java.sql.Types.FLOAT);
							
							//On l'execute
							RequeteSQL.nbStageDepartementMoyen.execute();
							nbStagesMoyens.setText(String.valueOf(RequeteSQL.nbStageDepartementMoyen.getFloat(1)) + " stage(s)/an en moyenne");
						}
					}
					
					
					
	        	} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
				
	        }
	    });
		topCenter.add(yearSpinner,c);
		
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
