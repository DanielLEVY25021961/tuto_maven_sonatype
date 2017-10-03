package levy.daniel.application.model.services.metier.meteo.impl;

import java.io.InputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.model.metier.meteo.InterrogationMeteoString;
import levy.daniel.application.model.services.metier.AbstractServiceGeneric;
import levy.daniel.application.model.services.metier.meteo.IinterrogationMeteoService;
import levy.daniel.application.model.services.valideurs.metier.meteo.InterrogationMeteoValideur;

/**
 * class InterrogationMeteoService :<br/>
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
public class InterrogationMeteoService 
	extends AbstractServiceGeneric<InterrogationMeteoString> 
							implements IinterrogationMeteoService {

	// ************************ATTRIBUTS************************************/

	/**
	 * CLASSE_INTERROGATIONMETEO_SERVICE : String :<br/>
	 * "Classe InterrogationMeteoService".<br/>
	 */
	public static final String CLASSE_INTERROGATIONMETEO_SERVICE 
		= "Classe InterrogationMeteoService";
	
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG 
		= LogFactory.getLog(InterrogationMeteoService.class);

	// *************************METHODES************************************/
	
	
	 /**
	 * method CONSTRUCTEUR InterrogationMeteoService() :<br/>
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * Instancie le valideur du Service.<br/>
	 * <br/>
	 */
	public InterrogationMeteoService() {
		
		super();
		
		/* Instancie le valideur du Service. */
		this.valideur = new InterrogationMeteoValideur();
		
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final InputStream interrogerYahooMeteo(
			final InterrogationMeteoString pInterro) throws Exception {
		
		/* retourne null si pInterro == null. */
		if (pInterro == null) {
			return null;
		}
		
		this.validate(pInterro);
		
		return null;

	} // Fin de interrogerYahooMeteo(...)._________________________________

	
	
	/**
	 * {@inheritDoc}
	 * <br/>
	 * "Classe InterrogationMeteoService".<br/>
	 */
	@Override
	public final String founirNomClasse() {
		return CLASSE_INTERROGATIONMETEO_SERVICE;
	} // Fin de founirNomClasse()._________________________________________
	
	
	
} // FIN DE LA CLASSE InterrogationMeteoService.-----------------------------
