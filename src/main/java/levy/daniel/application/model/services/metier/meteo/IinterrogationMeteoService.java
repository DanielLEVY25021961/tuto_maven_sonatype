package levy.daniel.application.model.services.metier.meteo;

import java.io.InputStream;

import levy.daniel.application.model.metier.meteo.InterrogationMeteoString;
import levy.daniel.application.model.services.metier.IServiceGeneric;

/**
 * class IinterrogationMeteoService :<br/>
 * <ul>
 * <li>INTERFACE des SERVICES d'interrogation du Web Service météo de Yahoo.</li>
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
 * <img src="../../../../../../../../../../javadoc/images/site_yahoo_meteo.png" 
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
public interface IinterrogationMeteoService 
	extends IServiceGeneric<InterrogationMeteoString> {

	
	
	/**
	 * method interrogerYahooMeteo(
	 * InterrogationMeteoString pInterro) :<br/>
	 * <ul>
	 * <li>Récupère un Bean InterrogationMeteoString provenant 
	 * d'un Controller lui-même alimenté par la vue comportant 
	 * le formulaire d'interrogation du Web Service 
	 * Meteo de Yahoo.</li>
	 * </ul>
	 * retourne null si pInterro == null.<br/>
	 * <br/>
	 *
	 * @param pInterro : InterrogationMeteoString
	 * 
	 * @return InputStream
	 * 
	 * @throws Exception
	 */
	InputStream interrogerYahooMeteo(
			InterrogationMeteoString pInterro) throws Exception;
	
	

} // FIN DE L'INTERFACE IinterrogationMeteoService.------------------------------
