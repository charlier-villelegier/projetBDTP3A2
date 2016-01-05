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

import outilSQL.RequeteSQL;

public class VueGeographique extends JPanel{

	public VueGeographique(){
		setupUI();
	}
	
	
	private void setupUI(){
		this.setLayout(new BorderLayout());

		JPanel topCenter = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		final JComboBox<String> listeVilleDept = new JComboBox<String>();
		
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
		c.fill = GridBagConstraints.HORIZONTAL;
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
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 5;
		c.gridy = 0;
		c.gridwidth = 2;
		topCenter.add(listeVilleDept,c);
		
		JLabel textYear= new JLabel("durant les/la ");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		topCenter.add(textYear,c);
		
		SpinnerModel sm = new SpinnerNumberModel(1, 1, 10, 1);
		JSpinner yearSpinner = new JSpinner(sm);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 1;
		topCenter.add(yearSpinner,c);
		
		JLabel textYear2 = new JLabel(" dernière(s) année(s)");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 1;
		topCenter.add(textYear2,c);
		
		
		this.add(topCenter, BorderLayout.CENTER);
		
		JPanel south = new JPanel(new GridLayout(2,1));
		JLabel nbStagesTotal = new JLabel("48 stages au total");
		JLabel nbStagesMoyens = new JLabel("3.6/ans en moyenne");
		nbStagesTotal.setFont(new Font("Calibri",Font.BOLD,20));
		nbStagesTotal.setHorizontalAlignment(JLabel.CENTER);
		nbStagesMoyens.setFont(new Font("Calibri",Font.BOLD,20));
		nbStagesMoyens.setHorizontalAlignment(JLabel.CENTER);
		
		
		south.add(nbStagesTotal);
		south.add(nbStagesMoyens);
		
		this.add(south, BorderLayout.SOUTH);
		
		
	}

}
