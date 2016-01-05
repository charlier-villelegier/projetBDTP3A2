package vues;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

public class VueAnnee extends JPanel{
	
	public VueAnnee(){
		setupUI();
	}
	
	private void setupUI(){
		this.setLayout(new BorderLayout());

		JPanel topCenter = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		JLabel textEntreprise = new JLabel("Nombre de stage dans l'entreprise ");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		topCenter.add(textEntreprise,c);
		
		JComboBox<String> nameEntreprise = new JComboBox<String>();
		nameEntreprise.addItem("Exemple d'une entreprise");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 0;
		c.gridwidth = 2;
		topCenter.add(nameEntreprise,c);
		
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
