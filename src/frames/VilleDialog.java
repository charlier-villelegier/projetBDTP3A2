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
 * <b>DeptDialog est la classe graphique affichant le nombre de stages par ville.<b/>
 *
 */
public class VilleDialog extends JDialog{
	
	/**
	 * Constructeur DeptDialog
	 * 
	 * <p>En utilisant ce constructeur on récupère récupère les différentes villes et 
	 * le nombre de stages correspondant et on initialise la JDialog.</p>
	 * 
	 * @param container
	 * 			Le panel qui contient le DeptDialog.
	 *  @see RequeteSQL
	 */
	public VilleDialog(JPanel container){
		
		//Affichage des informations
		this.setLocationRelativeTo(container);
		this.setLayout(new GridLayout(0,2));
		try {
			ResultSet res = RequeteSQL.listeVille.executeQuery();
			while (res.next()){
				this.add(new JLabel(res.getString("colonne")));
				RequeteSQL.nbStageVilleTotal.setString(2, res.getString("colonne"));
				RequeteSQL.nbStageVilleTotal.setInt(3, 50);
				RequeteSQL.nbStageVilleTotal.registerOutParameter(1, java.sql.Types.INTEGER);
				
				//On l'execute
				RequeteSQL.nbStageVilleTotal.execute();
				JLabel nbStage = new JLabel(String.valueOf(RequeteSQL.nbStageVilleTotal.getInt(1)) + " stage(s) au total");
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
