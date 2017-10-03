package levy.daniel.application.model.services.metier.meteo;

import java.io.InputStream;

import levy.daniel.application.model.metier.meteo.InterrogationMeteoString;
import levy.daniel.application.model.services.metier.IServiceGeneric;

/**
 * class IinterrogationMeteoService :<br/>
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
