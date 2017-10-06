package levy.daniel.application.model.services.metier.meteo;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * class YahooMeteoRetriever :<br/>
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
 * @since 22 sept. 2017
 *
 */
public final class YahooMeteoRetriever {

	// ************************ATTRIBUTS************************************/
	
	/**
	 * URL_QUERY_METEO_YAHOO : String :<br/>
	 * <ul>
	 * <li>URL de base du Web Service Météo de Yahoo.</li>
	 * <li>"https://query.yahooapis.com/v1/public/yql?q=".</li>
	 * <li>La valeur du paramètre q est la requête YQL précisant 
	 * le lieu pour lequel ont veut obtenir la météo ainsi que 
	 * divers attributs.</li>
	 * </ul>
	 */
	public static final String URL_QUERY_METEO_YAHOO 
		= "https://query.yahooapis.com/v1/public/yql?q=";
	
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG = LogFactory.getLog(YahooMeteoRetriever.class);

	// *************************METHODES************************************/
	
	
	 /**
	 * method CONSTRUCTEUR YahooMeteoRetriever() :<br/>
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * <br/>
	 */
	private YahooMeteoRetriever() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	

	
	/**
	 * method formaterRequeteYQL(
	 * String pVille
	 * , String pCountry
	 * , int pLimit
	 * , String pUnites) :<br/>
	 * <ul>
	 * <li>Crée une requête YQL (Yahoo SQL) pour interroger 
	 * le Web Service Meteo de Yahoo.</li>
	 * <li>Exemple : select title, location, wind, atmosphere
	 * , astronomy, item.forecast from weather.forecast 
	 * where woeid in (select woeid from geo.places(1) 
	 * where text='Sourdun, France') and u='c' 
	 * limit 5 |sort(field='item.forecast.date', descending='false');</li>
	 * </ul>
	 * Lien vers le site Yahoo-Meteo : 
	 * https://fr.news.yahoo.com/meteo/ puis 
	 * sélectionner un autre lieu.<br/>
	 * Lien vers le site pour Sourdun : 
	 * https://fr.news.yahoo.com/meteo/france/%C3%AEle-de-france
	 * /sourdun-12727383.<br/>
	 * <br/>
	 *
	 * @param pVille : String : Ville pour la prévision météo.<br/>
	 * @param pCountry : Country : Pays de la Ville pour la prévision.<br/>
	 * @param pLimit : int : nombre de jours de prévision (< 10)
	 * @param pUnites : String : 'f' pour Farenheit et 
	 * 'c' pour Celsius.<br/>
	 * 
	 * @return : String : Requête YQL non encodée.<br/>
	 */
	public static String creerRequeteYQL(
			final String pVille
				, final String pCountry
					, final int pLimit
						, final String pUnites) {
		
		final String stringFormatee 
			= String.format("select * from weather.forecast "
					+ "where woeid in (select woeid from geo.places(1) "
					+ "where text=\"%s, %s\") and u='%s' limit " 
					+ pLimit 
					+ " |sort(field='item.forecast.date', descending='false')"
					, pVille, pCountry, pUnites);
		
//		final String stringFormatee 
//		= String.format("select units, location, wind, atmosphere"
//				+ ", astronomy, condition, item.title, item.geo:lat"
//				+ ", item.geo:long, item.condition, item.forecast "
//				+ "from weather.forecast "
//				+ "where woeid in (select woeid from geo.places(1) "
//				+ "where text=\"%s, %s\") and u='%s' limit " 
//				+ pLimit 
//				+ " |sort(field='item.forecast.date', descending='false')"
//				, pVille, pCountry, pUnites);
		
		return stringFormatee;
		
	} // Fin de creerRequeteYQL(...).______________________________________
	

	
	/**
	 * method encoderRequete(
	 * String pYQL) :<br/>
	 * .<br/>
	 * <br/>
	 *
	 * @param pYQL
	 * @return :  :  .<br/>
	 * 
	 * @throws UnsupportedEncodingException 
	 */
	public static String encoderRequete(
			final String pYQL) throws UnsupportedEncodingException {
		
		final String uriEncodee = URLEncoder.encode(pYQL, "UTF-8");
		
		return  uriEncodee;
		
	} // Fin de encoderRequete(...).______________________________________
	

	
	/**
	 * method creerUrlEncodee(String pVille, String pCountry, int pLimit) :<br/>
	 * .<br/>
	 * <br/>
	 *
	 * @param pVille : String : Ville pour la prévision météo.<br/>
	 * @param pCountry : Country : Pays de la Ville pour la prévision.<br/>
	 * @param pLimit : int : nombre de jours de prévision (< 10)
	 * @param pUnites : String : 'f' pour Farenheit et 
	 * 'c' pour Celsius.<br/>
	 * 
	 * @return :  :  .<br/>
	 * 
	 * @throws UnsupportedEncodingException 
	 */
	public static String creerUrlEncodee(
			final String pVille
				, final String pCountry
					, final int pLimit
						, final String pUnites) 
								throws UnsupportedEncodingException {
		
		final String yql = creerRequeteYQL(pVille, pCountry, pLimit, pUnites);
		
		final String yqlEncodee = encoderRequete(yql);
		
		final String urlEncodee = URL_QUERY_METEO_YAHOO + yqlEncodee + "&format=xml";
		
		return urlEncodee;
		
	} // Fin de creerUrlEncodee(...).______________________________________
	
	
	
	/**
	 * method retrieve(
	 * String pVille
	 * , String pCountry) :<br/>
	 * <ul>
	 * <li>Appelle le Web Service Meteo de Yahoo et lui demande 
	 * le bulletin météo sur 5 jours pour la ville désignée 
	 * par pVille et pCountry.</li>
	 * <li>Fournit la réponse en degrés Celsius et unités métriques.</li>
	 * <li>Fournit la réponse sous forme de flux XML conforme 
	 * au RSS 2.0.</li>
	 * </ul>
	 *
	 * @param pVille : String : Ville pour la prévision météo.<br/>
	 * @param pCountry : Country : Pays de la Ville pour la prévision.<br/>
	 * 
	 * @return : InputStream : Flux XML RSS 2.0 en 
	 * réponse du Web Service.<br/>
	 * 
	 * @throws Exception
	 */
	public static InputStream retrieve(
			final String pVille
				, final String pCountry) throws Exception {
		
		return retrieve(pVille, pCountry, 5, "C");
		
	} // Fin de retrieve(...)._____________________________________________
	
	
	
	/**
	 * method retrieve() :<br/>
	 * <ul>
	 * <li>Appelle le Web Service Meteo de Yahoo et lui demande 
	 * le bulletin météo sur pLimit jours pour la ville désignée 
	 * par pVille et pCountry.</li>
	 * <li>Fournit la réponse en degrés Farenheit 
	 * si le paramètre pUnites vaut f, 
	 * en degrés Celsius si pUnites vaut c.</li>
	 * <li>Fournit la réponse sous forme de flux XML conforme 
	 * au RSS 2.0.</li>
	 * </ul>
	 *
	 * @param pVille : String : Ville pour la prévision météo.<br/>
	 * @param pCountry : Country : Pays de la Ville pour la prévision.<br/>
	 * @param pLimit : int : nombre de jours de prévision (=< 10)
	 * @param pUnites : String : 'f' pour Farenheit et 
	 * 'c' pour Celsius.<br/>
	 * 
	 * @return : InputStream : Flux XML RSS 2.0 en 
	 * réponse du Web Service.<br/>
	 * 
	 * @throws Exception :  :  .<br/>
	 */
	public static InputStream retrieve(
			final String pVille
				, final String pCountry
					, final int pLimit
						, final String pUnites) 
								throws Exception {
		
		/* prépare l'URL encodée pour le Web Service. */
		final String url 
			= creerUrlEncodee(pVille, pCountry, pLimit, pUnites);
		
		/* Connexion au Web Service. */
		final URLConnection connexion = new URL(url).openConnection();
		
		/* retourne la réponse du Web Service. */
		return connexion.getInputStream();
		
	} // Fin de retrieve(...).___________________________________________

	
	
} // FIN DE LA CLASSE YahooMeteoRetriever.-----------------------------------
