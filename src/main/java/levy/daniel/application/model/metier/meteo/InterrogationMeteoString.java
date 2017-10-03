package levy.daniel.application.model.metier.meteo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.model.metier.user.User;

/**
 * class InterrogationMeteoString :<br/>
 * .<br/>
 * <br/>
 *
 * - Exemple d'utilisation :<br/>
 *<br/>
 * 
 * - Mots-clé :<br/>
 * <br/>
 *
 * - Dépendances :<br/>
 * <br/>
 *
 *
 * @author dan Lévy
 * @version 1.0
 * @since 25 sept. 2017
 *
 */
public class InterrogationMeteoString {

	// ************************ATTRIBUTS************************************/
	
	/**
	 * user : User :<br/>
	 * User de l'application.<br/>
	 */
	private User user;

	
	/**
	 * pays : String :<br/>
	 * Pays de la Ville du bulletin météo.<br/>
	 */
	private String pays;

	
	/**
	 * ville : String :<br/>
	 * Ville du bulletin météo.<br/>
	 */
	private String ville;

	
	/**
	 * nbreJours : String :<br/>
	 * Nombre de jours de prévision météo demandé 
	 * SOUS FORME DE STRING.<br/>
	 */
	private String nbreJours;

	
	/**
	 * unites : String :<br/>
	 * Unités (métriques ou impériales) utilisées 
	 * dans le bulletin météo.<br/>
	 */
	private String unites;
	

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG 
		= LogFactory.getLog(InterrogationMeteoString.class);


	// *************************METHODES************************************/

	
	
	 /**
	 * method CONSTRUCTEUR InterrogationMeteoString() :<br/>
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * <br/>
	 */
	public InterrogationMeteoString() {
		this(null, null, null, null, null);
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
	
	 /**
	 * method CONSTRUCTEUR InterrogationMeteoString() :<br/>
	 * CONSTRUCTEUR COMPLET.<br/>
	 * <br/>
	 *
	 * @param pUser : User : User de l'application.<br/>
	 * @param pPays : String : Pays de la Ville du bulletin météo.<br/>
	 * @param pVille : String : Ville du bulletin météo.<br/>
	 * @param pNbreJours : String : Nombre de jours de prévision 
	 * météo demandé SOUS FORME DE STRING.<br/>
	 * @param pUnites : String : Unités (métriques ou impériales) 
	 * utilisées dans le bulletin météo.<br/>
	 */
	public InterrogationMeteoString(
			final User pUser
				, final String pPays, final String pVille 
					, final String pNbreJours
						, final String pUnites) {
		
		super();
		
		this.user = pUser;
		this.pays = pPays;
		this.ville = pVille;		
		this.nbreJours = pNbreJours;
		this.unites = pUnites;
		
	} // Fin de CONSTRUCTEUR COMPLET.______________________________________


	
	/**
	 * method getUser() :<br/>
	 * Getter du User de l'application.<br/>
	 * <br/>
	 *
	 * @return user : User.<br/>
	 */
	public final User getUser() {	
		return this.user;
	} // Fin de getUser()._________________________________________________



	/**
	* method setUser(
	* User pUser) :<br/>
	* Setter du User de l'application.<br/>
	* <br/>
	*
	* @param pUser : User : valeur à passer à user.<br/>
	*/
	public final void setUser(
			final User pUser) {	
		this.user = pUser;
	} // Fin de setUser(...).______________________________________________


	
	/**
	 * method getPays() :<br/>
	 * Getter du Pays de la Ville du bulletin météo.<br/>
	 * <br/>
	 *
	 * @return pays : String.<br/>
	 */
	public final String getPays() {	
		return this.pays;
	} // Fin de getPays()._________________________________________________


	
	/**
	* method setPays(
	* String pPays) :<br/>
	* Setter du Pays de la Ville du bulletin météo.<br/>
	* <br/>
	*
	* @param pPays : String : valeur à passer à pays.<br/>
	*/
	public final void setPays(
			final String pPays) {	
		this.pays = pPays;
	} // Fin de setPays(...).______________________________________________



	/**
	 * method getVille() :<br/>
	 * Getter de la Ville du bulletin météo.<br/>
	 * <br/>
	 *
	 * @return ville : String.<br/>
	 */
	public final String getVille() {	
		return this.ville;
	} // Fin de getVille().________________________________________________


	
	/**
	* method setVille(
	* String pVille) :<br/>
	* Setter de la Ville du bulletin météo.<br/>
	* <br/>
	*
	* @param pVille : String : valeur à passer à ville.<br/>
	*/
	public final void setVille(
			final String pVille) {	
		this.ville = pVille;
	} // Fin de setVille(...)._____________________________________________


	
	/**
	 * method getNbreJours() :<br/>
	 * Getter du Nombre de jours de prévision météo demandé 
	 * SOUS FORME DE STRING.<br/>
	 * <br/>
	 *
	 * @return nbreJours : String.<br/>
	 */
	public final String getNbreJours() {	
		return this.nbreJours;
	} // Fin de getNbreJours().____________________________________________


	
	/**
	* method setNbreJours(
	* String pNbreJours) :<br/>
	* Setter du Nombre de jours de prévision météo demandé 
	* SOUS FORME DE STRING.<br/>
	* <br/>
	*
	* @param pNbreJours : String : valeur à passer à nbreJours.<br/>
	*/
	public final void setNbreJours(
			final String pNbreJours) {	
		this.nbreJours = pNbreJours;
	} // Fin de setNbreJours(...)._________________________________________


	
	/**
	 * method getUnites() :<br/>
	 * Getter des Unités (métriques ou impériales) utilisées 
	 * dans le bulletin météo.<br/>
	 * <br/>
	 *
	 * @return unites : String.<br/>
	 */
	public final String getUnites() {	
		return this.unites;
	} // Fin de getUnites()._______________________________________________


	
	/**
	* method setUnites(
	* String pUnites) :<br/>
	* Setter des Unités (métriques ou impériales) utilisées 
	* dans le bulletin météo.<br/>
	* <br/>
	*
	* @param pUnites : String : valeur à passer à unites.<br/>
	*/
	public final void setUnites(
			final String pUnites) {	
		this.unites = pUnites;
	} // Fin de setUnites(...).____________________________________________

	
	
} // FIN DE LA CLASSE InterrogationMeteoString.------------------------------
