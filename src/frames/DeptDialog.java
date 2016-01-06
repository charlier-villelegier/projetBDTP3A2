package frames;

import java.awt.GridLayout;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import outilSQL.RequeteSQL;

/**
 * <b>DeptDialog est la classe graphique affichant le nombre de stages par département.<b/>
 *
 */
public class DeptDialog extends JDialog{
	
	/**
	 * Constructeur DeptDialog
	 * 
	 * <p>En utilisant ce constructeur on récupère récupère les différents départements et 
	 * le nombre de stages correspondant et on initialise la JDialog.</p>
	 * 
	 * @param container
	 * 			Le panel qui contient le DeptDialog.
	 * @see RequeteSQL
	 */
	public DeptDialog(JPanel container){
		
		//Affichage des informations
		this.setLocationRelativeTo(container);
		this.setLayout(new GridLayout(0,2));
		try {
			ResultSet res = RequeteSQL.listeDept.executeQuery();
			while (res.next()){
				this.add(new JLabel(res.getString("colonne")));
				RequeteSQL.nbStageDepartementTotal.setString(2, res.getString("colonne"));
				RequeteSQL.nbStageDepartementTotal.setInt(3, 50);
				RequeteSQL.nbStageDepartementTotal.registerOutParameter(1, java.sql.Types.INTEGER);
				
				//On l'execute
				RequeteSQL.nbStageDepartementTotal.execute();
				JLabel nbStage = new JLabel(String.valueOf(RequeteSQL.nbStageDepartementTotal.getInt(1)) + " stage(s) au total");
				nbStage.setBorder(BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
				this.add(nbStage);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		this.pack();
		this.setVisible(true);
		
	}

}
