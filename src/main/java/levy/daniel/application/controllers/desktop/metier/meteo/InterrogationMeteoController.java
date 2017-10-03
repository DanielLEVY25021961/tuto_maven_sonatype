package levy.daniel.application.controllers.desktop.metier.meteo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.model.metier.meteo.InterrogationMeteoString;
import levy.daniel.application.model.metier.user.User;
import levy.daniel.application.model.services.metier.meteo.IinterrogationMeteoService;
import levy.daniel.application.model.services.metier.meteo.impl.InterrogationMeteoService;

/**
 * class InterrogationMeteoController :<br/>
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
 * @since 26 sept. 2017
 *
 */
public class InterrogationMeteoController {

	// ************************ATTRIBUTS************************************/

	/**
	 * service : IinterrogationMeteoService :<br/>
	 * Service d'interrogation du Web Service Meteo de Yahoo.<br/>
	 */
	private final transient IinterrogationMeteoService service 
		= new InterrogationMeteoService();
	
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG 
		= LogFactory.getLog(InterrogationMeteoController.class);
	

	// *************************METHODES************************************/
	
	
	 /**
	 * method CONSTRUCTEUR InterrogationMeteoController() :<br/>
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * <br/>
	 */
	public InterrogationMeteoController() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
	/**
	 * method interrogerYahooMeteo(
	 * User pUser
	 * , String pPays
	 * , String pVille
	 * , String pNbreJours
	 * , String pUnites) :<br/>
	 * <ul>
	 * <li>INTERCEPTE LA VUE comportant le formulaire d'interrogation 
	 * du Web Service Meteo de Yahoo.</li>
	 * <li>INSTANCIE un BEAN InterrogationMeteoString à partir 
	 * des paramètres renseignés dans la vue.</li>
	 * <li>DELEGUE au SERVICE IinterrogationMeteoService 
	 * l'interrogation du Web Service Meteo de Yahoo.</li>
	 * <li>DISPATCH vers </li>
	 * </ul>
	 *
	 * @param pUser : User : User de l'application.<br/>
	 * @param pVille : String : Ville du bulletin météo.<br/>
	 * @param pPays : String : Pays de la Ville du bulletin météo.<br/>
	 * @param pNbreJours : String : Nombre de jours de prévision 
	 * météo demandé SOUS FORME DE STRING.<br/>
	 * @param pUnites : String : Unités (métriques ou impériales) 
	 * utilisées dans le bulletin météo.<br/>
	 * 
	 * @return String : 
	 * 
	 * @throws Exception :  :  .<br/>
	 */
	public String interrogerYahooMeteo(
			final User pUser
			, final String pPays, final String pVille
				, final String pNbreJours
					, final String pUnites) throws Exception {
		
		/* Crée un Bean InterrogationMeteoString 
		 * à partir des paramètres rrenseignés dans la vue. */
		final InterrogationMeteoString interro  
			= this.creerBean(pUser, pPays, pVille, pNbreJours, pUnites);
		
		/* délègue au SERVICE IinterrogationMeteoService l'interrogation 
		 * du Web Service Meteo de Yahoo. */
		this.service.interrogerYahooMeteo(interro);
		
		
		return null;
		
	} // Fin de interrogerYahooMeteo(...)._________________________________
	
	
	
	/**
	 * method creerBean(
	 * User pUser
	 * , String pPays
	 * , String pVille
	 * , String pNbreJours
	 * , String pUnites) :<br/>
	 * <ul>
	 * <li>Instancie un Bean InterrogationMeteoString 
	 * à partir du contenu de la vue Formulaire d'interrogation 
	 * du Web Service Meteo de Yahoo.</li>
	 * <li>Retourne le Bean InterrogationMeteoString.</li>
	 * </ul>
	 *
	 * @param pUser : User : User de l'application.<br/>
	 * @param pVille : String : Ville du bulletin météo.<br/>
	 * @param pPays : String : Pays de la Ville du bulletin météo.<br/>
	 * @param pNbreJours : String : Nombre de jours de prévision 
	 * météo demandé SOUS FORME DE STRING.<br/>
	 * @param pUnites : String : Unités (métriques ou impériales) 
	 * utilisées dans le bulletin météo.<br/>
	 * 
	 * @return : InterrogationMeteoString.<br/>
	 */
	private InterrogationMeteoString creerBean(
			final User pUser
				, final String pPays, final String pVille
					, final String pNbreJours
						, final String pUnites) {
		
		final InterrogationMeteoString interro 
		= new InterrogationMeteoString(pUser
				, pVille, pPays, pNbreJours, pUnites);
		
		return interro;
		
	} // Fin de creerBean(...).____________________________________________
	
	
	
} // FIN DE LA CLASSE InterrogationMeteoController.--------------------------
