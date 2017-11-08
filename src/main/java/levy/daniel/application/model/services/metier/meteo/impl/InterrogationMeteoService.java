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
 * <ul>
 * <li>SERVICE CONCRET d'interrogation du Web Service météo de Yahoo.</li>
 * <li>Un SERVICE d'interrogation du Web Service météo de Yahoo fait la même 
 * chose que ce que l'on fait à la main en se connectant au 
 * site web Meteo de Yahoo : <b>https://fr.news.yahoo.com/meteo/</b> 
 * puis que l'on sélectionne un lieu.</li>
 * </ul>
 * <p>
 * Lien vers le site Yahoo-Meteo : 
 * <b>https://fr.news.yahoo.com/meteo/</b> puis 
 * <b>sélectionner un autre lieu</b>.
 * </p>
 * <p>
 * <img src="../../../../../../../../../../../javadoc/images/site_yahoo_meteo.png" 
 * alt="Site Web de Yahoo Meteo" border="1" align="center"/>
 * </p>
 * <p>
 * Lien direct vers le site pour Sourdun : 
 * <b>https://fr.news.yahoo.com/meteo/france/%C3%AEle-de-france
 * /sourdun-12727383</b>.<br/>
 * </p>
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
