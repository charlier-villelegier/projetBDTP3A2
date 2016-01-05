package vues;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Locale;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JSpinnerDateEditor;


public class VueStat extends JPanel{

	
	public VueStat(){
		setupUI();
	}
	
	
	private void setupUI(){
		this.setLayout(new BorderLayout());

		JPanel topCenter = new JPanel();
		JDateChooser moncalendrier = new JDateChooser();
		moncalendrier.setDateFormatString("dd/MM/yyyy");
		
		
		
		topCenter.add(moncalendrier);
		
		this.add(topCenter, BorderLayout.CENTER);
		
		
		
		
	}
}
