package levy.daniel.application.model.services.metier.meteo;

import java.io.InputStream;
import java.io.StringWriter;
import java.io.Writer;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.dom4j.Namespace;
import org.dom4j.Node;
import org.dom4j.QName;
import org.dom4j.io.DocumentSource;
import org.dom4j.io.SAXReader;

/**
 * class YahooParser :<br/>
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
public class YahooParser {

	// ************************ATTRIBUTS************************************/

	/**
	 * SAUT_LIGNE : char :<br/>
	 * '\n'.<br/>
	 */
	public static final char SAUT_LIGNE = '\n';

	
	/**
	 * PATH_CHANNEL : String :<br/>
	 * XPath englobant la racine vers la balise (Element) 'channel'.<br/>
	 * "/query/results/channel/".<br/>
	 */
	public static final String PATH_CHANNEL 
		= "/query/results/channel/";

	
	/**
	 * ROOT : String :<br/>
	 * "ROOT : ".<br/>
	 */
	public static final String ROOT = "ROOT : ";

	
	/**
	 * NODE : String :<br/>
	 * "NOEUD (balise ou attribut) : ".<br/>
	 */
	public static final String NODE 
		= "NOEUD (balise ou attribut) : ";
	
	
	/**
	 * ELEMENT : String :<br/>
	 * "ELEMENT (Balise) : ".<br/>
	 */
	public static final String ELEMENT 
		= "ELEMENT (Balise) : ";
	
	
	/**
	 * ATTRIBUT : String :<br/>
	 * "ATTRIBUT".<br/>
	 */
	public static final String ATTRIBUT 
		= "ATTRIBUT";
	
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG = LogFactory.getLog(YahooParser.class);

	// *************************METHODES************************************/
	
	
	 /**
	 * method CONSTRUCTEUR YahooParser() :<br/>
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * <br/>
	 */
	public YahooParser() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	

	
	/**
	 * method parse(
	 * InputStream pInputStream) :<br/>
	 * <ul>
	 * <li>Parse un flux XML pInputStream au moyen 
	 * d'un SAXReader (xmlReader).</li>
	 * <li>Retourne un org.dom4j.Document.</li>
	 * </ul>
	 * retourne null si pInputStream == null.<br/>
	 * <br/>
	 *
	 * @param pInputStream : InputStream.<br/>
	 *  
	 * @return : org.dom4j.Document.<br/>
	 * 
	 * @throws Exception 
	 */
	public final Document parse(
			final InputStream pInputStream) throws Exception {
		
		/* retourne null si pInputStream == null. */
		if (pInputStream == null) {
			return null;
		}
		
		/* Instancie un XMLReader pour lire un flux RSS 2.0 
		 * provenant du Web Service Meteo de Yahoo.*/
		final SAXReader xmlReader = this.createXmlReader();
		
		final Document xmlDoc = xmlReader.read(pInputStream);
		
		return xmlDoc;
		
	} // Fin de parse(...).________________________________________________


	
	/**
	 * method format(
	 * Document pDoc) :<br/>
	 * Retourne une String pour l'affichage formaté d'un Document XML.<br/>
	 * <br/>
	 *
	 * @param pDoc : org.dom4j.Document.<br/>
	 * 
	 * @return : String.<br/>
	 * 
	 * @throws TransformerFactoryConfigurationError 
	 * @throws TransformerException 
	 */
	public String format(
			final Document pDoc) 
					throws TransformerFactoryConfigurationError
									, TransformerException {
		
		/* Instanciation d'une TransformerFactory. */
		final TransformerFactory transformerFactory 
			= TransformerFactory.newInstance();
		
		/* Fixe le nombre de caractères d'indentation. */
		transformerFactory.setAttribute("indent-number", 5);
		
		/* Instanciation d'une Transformer. */
		final Transformer transformer 
			= transformerFactory.newTransformer();
		
		/* Encode la sortie en UTF-8. */
		transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		
		/* Indentation de la sortie. */
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");

		/* Instancie un Writer pour écrire dans la console. */
		final Writer out = new StringWriter();

		final Source source = new DocumentSource(pDoc);
		final StreamResult resultat = new StreamResult(out);
		
		transformer.transform(source, resultat);
		
		final String xmlString = resultat.getWriter().toString();
		
		return xmlString;
		
	} // Fin de format(...)._______________________________________________
	
	
	
	/**
	 * method createXmlReader() :<br/>
	 * Crée un SAXReader (XMLReader) capable de lire un flux RSS 2.0 
	 * provenant du Web Service Meteo de Yahoo.<br/>
	 * <br/>
	 *
	 * @return : SAXReader : XMLReader pour lire un flux RSS 2.0 
	 * provenant du Web Service Meteo de Yahoo.<br/>
	 */
	private SAXReader createXmlReader() {

		final Map<String, String> uris 
			= new ConcurrentHashMap<String, String>();
		uris.put("y", "http://xml.weather.yahoo.com/ns/rss/1.0");
		uris.put("geo", "http://www.w3.org/2003/01/geo/wgs84_pos#");

		final DocumentFactory factory = new DocumentFactory();
		factory.setXPathNamespaceURIs(uris);

		final SAXReader xmlReader = new SAXReader();
		xmlReader.setDocumentFactory(factory);
		
		return xmlReader;
		
	} // Fin de createXmlReader()._________________________________________
	
	
	
	/**
	 * method lireUnitesDistance(
	 * Document pXmlDoc) :<br/>
	 * <ul>
	 * <li>Lit (et retourne sous forme de String) 
	 * dans le org.dom4j.Document 
	 * retourné par le Web Service Meteo de Yahoo 
	 * l'unité de distance (Km ou Mile).</li>
	 * <li>"/query/results/channel/y:units/@distance".</li>
	 * </ul>
	 *
	 * @param pXmlDoc : org.dom4j.Document.<br/>
	 * 
	 * @return : String : unité de distance (Km ou Mile).<br/>
	 */
	private String lireUnitesDistance(
			final Document pXmlDoc) {
		
		return pXmlDoc.valueOf(PATH_CHANNEL + "y:units/@distance");
		
	} // Fin de lireUnitesDistance(...).___________________________________
	

	
	/**
	 * method lireUnitesPression(
	 * Document pXmlDoc) :<br/>
	 * <ul>
	 * <li>Lit (et retourne sous forme de String) 
	 * dans le org.dom4j.Document 
	 * retourné par le Web Service Meteo de Yahoo 
	 * l'unité de pression (MilliBars ou psi).</li>
	 * <li>"/query/results/channel/y:units/@pressure".</li>
	 * </ul>
	 *
	 * @param pXmlDoc : org.dom4j.Document.<br/>
	 * 
	 * @return : String : unité de Pression (MilliBars ou psi).<br/>
	 */
	private String lireUnitesPression(
			final Document pXmlDoc) {
		
		return pXmlDoc.valueOf(PATH_CHANNEL + "y:units/@pressure");
		
	} // Fin de lireUnitesPression(...).___________________________________
	
	

	/**
	 * method lireUnitesVitesse(
	 * Document pXmlDoc) :<br/>
	 * <ul>
	 * <li>Lit (et retourne sous forme de String) dans le org.dom4j.Document 
	 * retourné par le Web Service Meteo de Yahoo 
	 * l'unité de vitesse (km/h ou mph).</li>
	 * <li>"/query/results/channel/y:units/@speed".</li>
	 * </ul>
	 *
	 * @param pXmlDoc : org.dom4j.Document.<br/>
	 * 
	 * @return : String : unité de Vitesse (km/h ou mph).<br/>
	 */
	private String lireUnitesVitesse(
			final Document pXmlDoc) {
		
		return pXmlDoc.valueOf(PATH_CHANNEL + "y:units/@speed");
		
	} // Fin de lireUnitesVitesse(...).____________________________________
	
	
	
	/**
	 * method lireUnitesTemperature(
	 * Document pXmlDoc) :<br/>
	 * <ul>
	 * <li>Lit (et retourne sous forme de String) 
	 * dans le org.dom4j.Document 
	 * retourné par le Web Service Meteo de Yahoo 
	 * l'unité de température (F (Farenheit) ou C (Celsius)).</li>
	 * <li>"/query/results/channel/y:units/@temperature".</li>
	 * </ul>
	 *
	 * @param pXmlDoc : org.dom4j.Document.<br/>
	 * 
	 * @return : String : unité de Température (F ou mph).<br/>
	 */
	private String lireUnitesTemperature(
			final Document pXmlDoc) {
		
		return pXmlDoc.valueOf(PATH_CHANNEL + "y:units/@temperature");
		
	} // Fin de lireUnitesTemperature(...).________________________________
	

	
	/**
	 * method lireTitle(
	 * Document pXmlDoc) :<br/>
	 * <ul>
	 * <li>Lit (et retourne sous forme de String) 
	 * dans le org.dom4j.Document 
	 * retourné par le Web Service Meteo de Yahoo 
	 * le Title.</li>
	 * <li>"/query/results/channel/title".</li>
	 * </ul>
	 *
	 * @param pXmlDoc : org.dom4j.Document.<br/>
	 * 
	 * @return : String : Title.<br/>
	 */
	private String lireTitle(
			final Document pXmlDoc) {
		
		return pXmlDoc.valueOf(PATH_CHANNEL + "title");
		
	} // Fin de lireTitle(...).____________________________________________
	
	
	
	/**
	 * method lireLienURL(
	 * Document pXmlDoc) :<br/>
	 * <ul>
	 * <li>Lit (et retourne sous forme de String) 
	 * dans le org.dom4j.Document 
	 * retourné par le Web Service Meteo de Yahoo 
	 * le Lien (URL) vers la Page Web de Yahoo Meteo.</li>
	 * <li>"/query/results/channel/link".</li>
	 * </ul>
	 *
	 * @param pXmlDoc : org.dom4j.Document.<br/>
	 * 
	 * @return : String : Lien (URL) vers la Page Web de Yahoo Meteo.<br/>
	 */
	private String lireLienURL(
			final Document pXmlDoc) {
		
		final String link = pXmlDoc.valueOf(PATH_CHANNEL + "link");
		final String[] tableau 
			= StringUtils.splitByWholeSeparator(link, "https:");
		
		final int tailleTableau = tableau.length;
		
		final String resultat = "https:" + tableau[tailleTableau - 1];
		
		return resultat;
		
	} // Fin de lireLienURL(...).__________________________________________

	
	
	/**
	 * method lireLocationVille(
	 * Document pXmlDoc) :<br/>
	 * <ul>
	 * <li>Lit (et retourne sous forme de String) 
	 * dans le org.dom4j.Document 
	 * retourné par le Web Service Meteo de Yahoo 
	 * la Ville.</li>
	 * <li>"/query/results/channel/y:location/@city".</li>
	 * </ul>
	 *
	 * @param pXmlDoc : org.dom4j.Document.<br/>
	 * 
	 * @return : String : Ville.<br/>
	 */
	private String lireLocationVille(
			final Document pXmlDoc) {
		
		return pXmlDoc.valueOf(PATH_CHANNEL + "y:location/@city");
		
	} // Fin de lireLocationVille(...).____________________________________
	

	
	/**
	 * method lireLocationPays(
	 * Document pXmlDoc) :<br/>
	 * <ul>
	 * <li>Lit (et retourne sous forme de String) 
	 * dans le org.dom4j.Document 
	 * retourné par le Web Service Meteo de Yahoo 
	 * le Pays.</li>
	 * <li>"/query/results/channel/y:location/@country".</li>
	 * </ul>
	 *
	 * @param pXmlDoc : org.dom4j.Document.<br/>
	 * 
	 * @return : String : Pays.<br/>
	 */
	private String lireLocationPays(
			final Document pXmlDoc) {
		
		return pXmlDoc.valueOf(PATH_CHANNEL + "y:location/@country");
		
	} // Fin de lireLocationPays(...)._____________________________________
	

	
	/**
	 * method lireLocationRegion(
	 * Document pXmlDoc) :<br/>
	 * <ul>
	 * <li>Lit (et retourne sous forme de String) 
	 * dans le org.dom4j.Document 
	 * retourné par le Web Service Meteo de Yahoo 
	 * la Région.</li>
	 * <li>"/query/results/channel/y:location/@region".</li>
	 * </ul>
	 *
	 * @param pXmlDoc : org.dom4j.Document.<br/>
	 * 
	 * @return : String : Pays.<br/>
	 */
	private String lireLocationRegion(
			final Document pXmlDoc) {
		
		return pXmlDoc.valueOf(PATH_CHANNEL + "y:location/@region");
		
	} // Fin de lireLocationRegion(...).___________________________________
	

	
	/**
	 * method lireVentTemperatureApparente(
	 * Document pXmlDoc) :<br/>
	 * <ul>
	 * <li>Lit (et retourne sous forme de String) 
	 * dans le org.dom4j.Document 
	 * retourné par le Web Service Meteo de Yahoo 
	 * la température apparente (chill) convertie en degrés Celsius.</li>
	 * <li>"/query/results/channel/y:wind/@chill".</li>
	 * </ul>
	 *
	 * @param pXmlDoc : org.dom4j.Document.<br/>
	 * 
	 * @return : String : température apparente.<br/>
	 */
	private String lireVentTemperatureApparente(
			final Document pXmlDoc) {
		
		final String tempApparenteFarenheit 
			= pXmlDoc.valueOf(PATH_CHANNEL + "y:wind/@chill");
		
		final String tempApparenteCelsius 
		= this.convertirFarenheitEnCelsius(tempApparenteFarenheit);
		
		return tempApparenteCelsius;
		
	} // Fin de lireVentTemperatureApparente(...)._________________________

	
	
	/**
	 * method lireVentDirection(
	 * Document pXmlDoc) :<br/>
	 * <ul>
	 * <li>Lit (et retourne sous forme de String) 
	 * dans le org.dom4j.Document 
	 * retourné par le Web Service Meteo de Yahoo 
	 * la direction du vent en degrés.</li>
	 * <li>"/query/results/channel/y:wind/@direction".</li>
	 * </ul>
	 *
	 * @param pXmlDoc : org.dom4j.Document.<br/>
	 * 
	 * @return : String : direction du vent en degrés.<br/>
	 */
	private String lireVentDirection(
			final Document pXmlDoc) {
		
		return pXmlDoc.valueOf(PATH_CHANNEL + "y:wind/@direction");
		
	} // Fin de lireVentDirection(...).____________________________________
	
	
	
	/**
	 * method lireVentVitesse(
	 * Document pXmlDoc) :<br/>
	 * <ul>
	 * <li>Lit (et retourne sous forme de String) 
	 * dans le org.dom4j.Document 
	 * retourné par le Web Service Meteo de Yahoo 
	 * la vitesse du vent en km/h.</li>
	 * <li>"/query/results/channel/y:wind/@speed".</li>
	 * </ul>
	 *
	 * @param pXmlDoc : org.dom4j.Document.<br/>
	 * 
	 * @return : String : vitesse du vent en km/h.<br/>
	 */
	private String lireVentVitesse(
			final Document pXmlDoc) {
		
		return pXmlDoc.valueOf(PATH_CHANNEL + "y:wind/@speed");
		
	} // Fin de lireVentVitesse(...).______________________________________
	
	
	
	/**
	 * method lireAtmosphereHumidite(
	 * Document pXmlDoc) :<br/>
	 * <ul>
	 * <li>Lit (et retourne sous forme de String) 
	 * dans le org.dom4j.Document 
	 * retourné par le Web Service Meteo de Yahoo 
	 * le taux d'hygrométrie en %.</li>
	 * <li>"/query/results/channel/y:atmosphere/@humidity".</li>
	 * </ul>
	 *
	 * @param pXmlDoc : org.dom4j.Document.<br/>
	 * 
	 * @return : String : taux d'hygrométrie en %.<br/>
	 */
	private String lireAtmosphereHumidite(
			final Document pXmlDoc) {
		
		return pXmlDoc.valueOf(PATH_CHANNEL + "y:atmosphere/@humidity");
		
	} // Fin de lireAtmosphereHumidite(...)._______________________________
	

	
	/**
	 * method lireAtmospherePression(
	 * Document pXmlDoc) :<br/>
	 * <ul>
	 * <li>Lit (et retourne sous forme de String) 
	 * dans le org.dom4j.Document 
	 * retourné par le Web Service Meteo de Yahoo 
	 * la pression en millibars.</li>
	 * <li>"/query/results/channel/y:atmosphere/@pressure".</li>
	 * </ul>
	 *
	 * @param pXmlDoc : org.dom4j.Document.<br/>
	 * 
	 * @return : String : pression en millibars.<br/>
	 */
	private String lireAtmospherePression(
			final Document pXmlDoc) {
		
		return pXmlDoc.valueOf(PATH_CHANNEL + "y:atmosphere/@pressure");
		
	} // Fin de lireAtmospherePression(...)._______________________________

	
	
	/**
	 * method lireAtmosphereRising(
	 * Document pXmlDoc) :<br/>
	 * <ul>
	 * <li>Lit (et retourne sous forme de String) 
	 * dans le org.dom4j.Document 
	 * retourné par le Web Service Meteo de Yahoo 
	 * la tendance de la pression.</li>
	 * <li>steady (0), rising (1), or falling (2).</li>
	 * <li>"/query/results/channel/y:atmosphere/@rising".</li>
	 * </ul>
	 *
	 * @param pXmlDoc : org.dom4j.Document.<br/>
	 * 
	 * @return : String : tendance de la pression.<br/>
	 */
	private String lireAtmosphereRising(
			final Document pXmlDoc) {
		
		return pXmlDoc.valueOf(PATH_CHANNEL + "y:atmosphere/@rising");
		
	} // Fin de lireAtmosphereRising(...)._________________________________

	
	
	/**
	 * method lireAtmosphereVisibillite(
	 * Document pXmlDoc) :<br/>
	 * <ul>
	 * <li>Lit (et retourne sous forme de String) 
	 * dans le org.dom4j.Document 
	 * retourné par le Web Service Meteo de Yahoo 
	 * la visibilité en Km.</li>
	 * <li>"/query/results/channel/y:atmosphere/@visibility".</li>
	 * </ul>
	 *
	 * @param pXmlDoc : org.dom4j.Document.<br/>
	 * 
	 * @return : String : visibilité en Km.<br/>
	 */
	private String lireAtmosphereVisibillite(
			final Document pXmlDoc) {
		
		return pXmlDoc.valueOf(PATH_CHANNEL + "y:atmosphere/@visibility");
		
	} // Fin de lireAtmosphereVisibillite(...).____________________________


		
	/**
	 * method lireAstronomyHeureLever(
	 * Document pXmlDoc) :<br/>
	 * <ul>
	 * <li>Lit (et retourne sous forme de String) 
	 * dans le org.dom4j.Document 
	 * retourné par le Web Service Meteo de Yahoo 
	 * l'heure de lever du soleil au format 24h.</li>
	 * <li>"/query/results/channel/y:astronomy/@sunrise".</li>
	 * </ul>
	 *
	 * @param pXmlDoc : org.dom4j.Document.<br/>
	 * 
	 * @return : String : heure de lever du soleil au format 24h.<br/>
	 */
	private String lireAstronomyHeureLever(
			final Document pXmlDoc) {
		
		final String heureLeverUS 
		= pXmlDoc.valueOf(PATH_CHANNEL + "y:astronomy/@sunrise");
		
		final String heureLever 
			= this.convertirHeureAmEn24Heures(heureLeverUS);
		
		return heureLever;
		
	} // Fin de lireAstronomyHeureLever(...).______________________________
	
	
	
	/**
	 * method lireAstronomyHeureCoucher(
	 * Document pXmlDoc) :<br/>
	 * <ul>
	 * <li>Lit (et retourne sous forme de String) 
	 * dans le org.dom4j.Document 
	 * retourné par le Web Service Meteo de Yahoo 
	 * l'heure de coucher du soleil au format 24h.</li>
	 * <li>"/query/results/channel/y:astronomy/@sunset".</li>
	 * </ul>
	 *
	 * @param pXmlDoc : org.dom4j.Document.<br/>
	 * 
	 * @return : String : heure de coucher du soleil au format 24h.<br/>
	 */
	private String lireAstronomyHeureCoucher(
			final Document pXmlDoc) {
		
		final String heureCoucherUS 
			= pXmlDoc.valueOf(PATH_CHANNEL + "y:astronomy/@sunset");
		
		final String heureCoucher 
			= this.convertirHeureAmEn24Heures(heureCoucherUS); 
		
		return heureCoucher;
		
	} // Fin de lireAstronomyHeureCoucher(...).____________________________


	
	/**
	 * method lireItemConditionsTitle(
	 * Document pXmlDoc) :<br/>
	 * <ul>
	 * <li>Lit (et retourne sous forme de String) 
	 * dans le org.dom4j.Document 
	 * retourné par le Web Service Meteo de Yahoo 
	 * le titre du message météo (conditions).</li>
	 * <li>"/query/results/channel/item/title".</li>
	 * </ul>
	 *
	 * @param pXmlDoc : org.dom4j.Document.<br/>
	 * 
	 * @return : String : titre du message météo (conditions).<br/>
	 */
	private String lireItemConditionsTitle(
			final Document pXmlDoc) {
		
		return pXmlDoc.valueOf(PATH_CHANNEL + "item/title");
		
	} // Fin de lireItemConditionsTitle(...).______________________________
	

	
	/**
	 * method lireItemConditionsGeoLatitude(
	 * Document pXmlDoc) :<br/>
	 * <ul>
	 * <li>Lit (et retourne sous forme de String) 
	 * dans le org.dom4j.Document 
	 * retourné par le Web Service Meteo de Yahoo 
	 * la latitude de la ville.</li>
	 * <li>"/query/results/channel/item/geo:lat".</li>
	 * </ul>
	 *
	 * @param pXmlDoc : org.dom4j.Document.<br/>
	 * 
	 * @return : String : latitude de la ville.<br/>
	 */
	private String lireItemConditionsGeoLatitude(
			final Document pXmlDoc) {
		
		return pXmlDoc.valueOf(PATH_CHANNEL + "item/geo:lat");
		
	} // Fin de lireItemConditionsGeoLatitude(...).________________________
	
	
	
	/**
	 * method lireItemConditionsGeoLongitude(
	 * Document pXmlDoc) :<br/>
	 * <ul>
	 * <li>Lit (et retourne sous forme de String) 
	 * dans le org.dom4j.Document 
	 * retourné par le Web Service Meteo de Yahoo 
	 * la longitude de la ville.</li>
	 * <li>"/query/results/channel/item/geo:long".</li>
	 * </ul>
	 *
	 * @param pXmlDoc : org.dom4j.Document.<br/>
	 * 
	 * @return : String : longitude de la ville.<br/>
	 */
	private String lireItemConditionsGeoLongitude(
			final Document pXmlDoc) {
		
		return pXmlDoc.valueOf(PATH_CHANNEL + "item/geo:long");
		
	} // Fin de lireItemConditionsGeoLongitude(...)._______________________
	

	
	/**
	 * method lireItemDatePublication(
	 * Document pXmlDoc) :<br/>
	 * <ul>
	 * <li>Lit (et retourne sous forme de String) 
	 * dans le org.dom4j.Document 
	 * retourné par le Web Service Meteo de Yahoo 
	 * la date de publication du message météo.</li>
	 * <li>Convertit la date-heure Yahoo comme 
	 * "Sun, 24 Sep 2017 11:07 PM CEST" en 
	 * date-heure européenne comme 
	 * "dimanche 24 septembre 2017 23:07".</li>
	 * <li>"/query/results/channel/item/pubDate".</li>
	 * </ul>
	 *
	 * @param pXmlDoc : org.dom4j.Document.<br/>
	 * 
	 * @return : String : date de publication du message météo.<br/>
	 */
	private String lireItemDatePublication(
			final Document pXmlDoc) {
		
		final String dateYahoo 
			= pXmlDoc.valueOf(PATH_CHANNEL + "item/pubDate");
		
		final String dateConvertie 
			= this.convertirDateHeureYahooEn24Heures(dateYahoo);
		
		return dateConvertie;
		
	} // Fin de lireItemDatePublication(...).______________________________
	

		
	/**
	 * method lireItemConditionDateCondition(
	 * Document pXmlDoc) :<br/>
	 * <ul>
	 * <li>Lit (et retourne sous forme de String) 
	 * dans le org.dom4j.Document 
	 * retourné par le Web Service Meteo de Yahoo 
	 * la date des conditions du message météo.</li>
	 * <li>Convertit la date-heure Yahoo comme 
	 * "Sun, 24 Sep 2017 11:07 PM CEST" en 
	 * date-heure européenne comme 
	 * "dimanche 24 septembre 2017 23:07".</li>
	 * <li>"/query/results/channel/item/y:condition/@date".</li>
	 * </ul>
	 *
	 * @param pXmlDoc : org.dom4j.Document.<br/>
	 * 
	 * @return : String : date des conditions du message météo.<br/>
	 */
	private String lireItemConditionDateCondition(
			final Document pXmlDoc) {
		
		final String dateYahoo 
		= pXmlDoc.valueOf(PATH_CHANNEL + "item/y:condition/@date");
	
		final String dateConvertie 
			= this.convertirDateHeureYahooEn24Heures(dateYahoo);
		
		return dateConvertie;
	
	} // Fin de lireItemConditionDateCondition(...)._______________________
	

	
	/**
	 * method lireItemCodeCondition(
	 * Document pXmlDoc) :<br/>
	 * <ul>
	 * <li>Lit (et retourne sous forme de String) 
	 * dans le org.dom4j.Document 
	 * retourné par le Web Service Meteo de Yahoo 
	 * le code des conditions du message météo.</li>
	 * <li>Convertit le code des conditions en texte comme 
	 * "pluies éparses".</li>
	 * <li>"/query/results/channel/item/y:condition/@code".</li>
	 * </ul>
	 *
	 * @param pXmlDoc : org.dom4j.Document.<br/>
	 * 
	 * @return : String : date des conditions du message météo.<br/>
	 */
	public String lireItemCodeCondition(
			final Document pXmlDoc) {
		
		final String codeCondition 
		= pXmlDoc.valueOf(PATH_CHANNEL + "item/y:condition/@code");
		
		return codeCondition;
		
	} // Fin de lireItemCodeCondition(...).________________________________
	
	
	
	/**
	 * method afficherRenseignementsRacine(
	 * Document pXmlDoc) :<br/>
	 * <ul>
	 * <li>Crée et retourne une String pour l'affichage à la console 
	 * des informations concernant la racine d'un document XML.</li>
	 * <li>affiche le nom de la racine.</li>
	 * <li>affiche le nom qualifié de la racine.</li>
	 * <li>affiche le nom de l'espace de noms.</li>
	 * <li>affiche le préfixe utilisé pour l'espace de noms.</li>
	 * <li>affiche le contenu textuel de la racine.</li>
	 * </ul>
	 * retourne null si pXmlDoc == null.<br/>
	 * <br/>
	 *
	 * @param pXmlDoc : org.dom4j.Document.<br/>
	 * 
	 * @return : String.<br/>
	 */
	private String afficherRenseignementsRacine(
			final Document pXmlDoc) {
		
		/* retourne null si pXmlDoc == null. */
		if (pXmlDoc == null) {
			return null;
		}
		
		final StringBuilder stb = new StringBuilder();
		
		/* Récupération de la racine. */
		final Element racine = pXmlDoc.getRootElement();		
		stb.append(ROOT);
		stb.append(racine.getName());
		
		/* Récupération du nom qualifié de la racine. */
		final QName qName = racine.getQName();
		stb.append(SAUT_LIGNE);
		stb.append("NOM QUALIFIE DE ROOT : ");
		stb.append(qName.getName());
		
		 // lecture du nom de l'espace de noms
		final String nomEspaceDeNoms = qName.getNamespaceURI() ;
		stb.append(SAUT_LIGNE);
		stb.append("ESPACE DE NOMS : ");
		stb.append(nomEspaceDeNoms);
				
		 // lecture du préfixe utilisé pour cet espace de nom 
		final String nomPrefix = qName.getNamespacePrefix() ;
		stb.append(SAUT_LIGNE);
		stb.append("NOM DU PREFIXE : ");
		stb.append(nomPrefix);
		
		/* Lit le contenu de la balise racine. */
		final String contenuRacine = racine.getTextTrim();
		stb.append(SAUT_LIGNE);
		stb.append("TEXTE CONTENU AU NIVEAU ROOT : ");
		stb.append(contenuRacine);
		
		return stb.toString();
		
	} // Fin de afficherRenseignementsRacine(...)._________________________

	
	
	/**
	 * method afficherRenseignementsNamespaceRacine(
	 * Document pXmlDoc) :<br/>
	 * <ul>
	 * <li>Crée et retourne une String pour l'affichage à la console 
	 * des informations concernant le namespace 
	 * la racine d'un document XML.</li>
	 * <li>affiche le nom de la racine.</li>
	 * <li>affiche l'URI du namespace de la racine.</li>
	 * <li>affiche le préfixe utilisé pour référencer cet espace de nom 
	 * de la racine dans le document.</li>
	 * </ul>
	 * retourne null si pXmlDoc == null.<br/>
	 * <br/>
	 *
	 * @param pXmlDoc : org.dom4j.Document.<br/>
	 * 
	 * @return : String.<br/>
	 */
	private String afficherRenseignementsNamespaceRacine(
			final Document pXmlDoc) {
		
		/* retourne null si pXmlDoc == null. */
		if (pXmlDoc == null) {
			return null;
		}
		
		final StringBuilder stb = new StringBuilder();
		
		/* Récupération de la racine. */
		final Element racine = pXmlDoc.getRootElement();		
		stb.append(ROOT);
		stb.append(racine.getName());
		
		/* Récupération du namespace de la racine. */
		final Namespace nameSpace = racine.getNamespace();
		
		/* Récupération de l'URI du namespace de la racine. */
		final String uriNameSpace = nameSpace.getURI();
		stb.append(SAUT_LIGNE);
		stb.append("URI DU NAMESPACE DE LA RACINE : ");
		stb.append(uriNameSpace);
		
		/* Récupération du préfixe utilisé pour référencer 
		 * cet espace de nom de la racine dans le document. */ 
		final String prefixNameSpace = nameSpace.getPrefix() ; 
		stb.append(SAUT_LIGNE);
		stb.append("PREFIXE DU NAMESPACE DE LA RACINE : ");
		stb.append(prefixNameSpace);
		
		return stb.toString();
		
	} // Fin de afficherRenseignementsNamespaceRacine(...).________________
	
	
	
	/**
	 * method listeAttributsRacineString(
	 * Document pXmlDoc) :<br/>
	 * <ul>
	 * <li>Crée et retourne une String pour l'affichage à la console 
	 * des informations concernant les attributs de
	 * la racine d'un document XML.</li>
	 * <li>affiche le nom de la racine.</li>
	 * <li>affiche la liste des attributs de la racine et 
	 * pour chaque attribut de la racine : </li>
	 * <ul>
	 * <li>nom de l'attribut.</li>
	 * <li>nom qualifié de l'attribut.</li>
	 * <li>valeur de l'attribut.</li>
	 * <li>URI du namespace de l'attribut.</li>
	 * <li>préfixe du namespace de l'attribut.</li>
	 * </ul>
	 * </ul>
	 * retourne null si pXmlDoc == null.<br/>
	 * <br/>
	 *
	 * @param pXmlDoc : org.dom4j.Document.<br/>
	 * 
	 * @return : String.<br/>
	 */
	private String listeAttributsRacineString(
			final Document pXmlDoc) {
		
		/* retourne null si pXmlDoc == null. */
		if (pXmlDoc == null) {
			return null;
		}
		
		final StringBuilder stb = new StringBuilder();
		
		/* Récupération de la racine. */
		final Element racine = pXmlDoc.getRootElement();		
		stb.append(ROOT);
		stb.append(racine.getName());
		
		String nomAttribut = null;
		String nomQualifieAttribut = null;
		String path = null;
		String valeurAttribut = null;
		String nomURINamespaceAttribut = null;
		String prefixNamespaceAttribut = null;
		
		/* Récupération de la liste des attributs de la racine. */
		final List<Attribute> listeAttributs = racine.attributes();
		
		for (final Attribute attribut : listeAttributs) {
			
			/* récupération du nom de l'attribut. */
			nomAttribut = attribut.getName();
			
			/* récupération du nom qualifié de l'attribut. */
			nomQualifieAttribut = attribut.getQualifiedName();

			/* récupération du path de l'attribut. */
			path = attribut.getPath();

			/* récupération de la valeur de l'attribut. */
			valeurAttribut = attribut.getValue();
						
			/* Récupération de l'URI du namespace de l'attribut. */
			nomURINamespaceAttribut 
				= attribut.getNamespaceURI();
			
			/* Récupération du préfixe du namespace de l'attribut. */
			prefixNamespaceAttribut = attribut.getNamespacePrefix();
			
			stb.append(SAUT_LIGNE);
			final String ligneAttribut 
			= String.format("NOM DE L'ATTRIBUT DE LA RACINE : %-20s "
					+ "NOM QUALIFIE DE L'ATTRIBUT DE LA RACINE : %-20s "
					+ "PATH DE L'ATTRIBUT : %-30s"
					+ "VALEUR DE L'ATTRIBUT : %-30s "
					+ "URI DU NAMESPACE DE L'ATTRIBUT DE LA RACINE : %-60s"
					+ "PREFIXE DU NAMESPACE DE L'ATTRIBUT DE LA RACINE : %-20s"
					, nomAttribut, nomQualifieAttribut, path, valeurAttribut
					, nomURINamespaceAttribut, prefixNamespaceAttribut);
			
			stb.append(ligneAttribut);
			
		}
			
		return stb.toString();
		
	} // Fin de listeAttributsRacineString(...).___________________________
	

	
	/**
	 * method listeElementsRacineString(
	 * Document pXmlDoc) :<br/>
	 * <ul>
	 * <li>Crée et retourne une String pour l'affichage à la console 
	 * des informations concernant les Elements (balises) de
	 * la racine d'un document XML.</li>
	 * <li>affiche le nom de la racine.</li>
	 * <li>affiche la liste des Elements de la racine et 
	 * pour chaque element de la racine : </li>
	 * <ul>
	 * <li>nom de l'element.</li>
	 * <li>nom qualifié de l'element.</li>
	 * <li>valeur (contenu) de l'element.</li>
	 * <li>URI du namespace de l'element.</li>
	 * <li>préfixe du namespace de l'element.</li>
	 * </ul>
	 * </ul>
	 * retourne null si pXmlDoc == null.<br/>
	 * <br/>
	 *
	 * @param pXmlDoc : org.dom4j.Document.<br/>
	 * 
	 * @return : String.<br/>
	 */
	private String listeElementsRacineString(
			final Document pXmlDoc) {
		
		/* retourne null si pXmlDoc == null. */
		if (pXmlDoc == null) {
			return null;
		}
		
		final StringBuilder stb = new StringBuilder();
		
		/* Récupération de la racine. */
		final Element racine = pXmlDoc.getRootElement();		
		stb.append(ROOT);
		stb.append(racine.getName());

		String nomElement = null;
		String nomQualifieElement = null;
		String path = null;
		String valeurElement = null;
		String nomURINamespacEelement = null;
		String prefixNamespaceElement = null;
		
		/* Récupération de la liste des balises (Elements) de la racine. */
		final List<Element> listeElements = racine.elements();
				
		for (final Element element : listeElements) {
			
			/* récupération du nom de l'element. */
			nomElement = element.getName();
			
			/* récupération du nom qualifié de l'element. */
			nomQualifieElement = element.getQualifiedName();

			/* récupération du path de l'element. */
			path = element.getPath();

			/* récupération de la valeur de l'element. */
			valeurElement = element.getTextTrim();
						
			/* Récupération de l'URI du namespace de l'element. */
			nomURINamespacEelement 
				= element.getNamespaceURI();
			
			/* Récupération du préfixe du namespace de l'element. */
			prefixNamespaceElement = element.getNamespacePrefix();
			
			stb.append(SAUT_LIGNE);
			final String ligneElement 
			= String.format("NOM DE L'ELEMENT DE LA RACINE : %-20s "
					+ "NOM QUALIFIE DE L'ELEMENT DE LA RACINE : %-20s "
					+ "PATH DE L'ELEMENT : %-30s"
					+ "VALEUR DE L'ELEMENT : %-30s "
					+ "URI DU NAMESPACE DE L'ELEMENT DE LA RACINE : %-60s"
					+ "PREFIXE DU NAMESPACE DE L'ELEMENT DE LA RACINE : %-20s"
					, nomElement, nomQualifieElement, path, valeurElement
					, nomURINamespacEelement, prefixNamespaceElement);
			
			stb.append(ligneElement);
			
		}
		
		return stb.toString();
		
	} // Fin de listeElementsRacineString(...).____________________________
	
	
	
	/**
	 * method afficherRenseignementsElement(
	 * Document pXmlDoc
	 * , String pPathElement) :<br/>
	 * <ul>
	 * <li>Crée et retourne une String pour l'affichage à la console 
	 * des informations concernant le Node 
	 * (Element (balise) ou Attribut) situé au XPath pPathElement 
	 * d'un document XML pXmlDoc .</li>
	 * <li>affiche le nom du node (balise ou attribut).</li>
	 * <li>affiche le nom qualifié du node (balise ou attribut).</li>
	 * <li>affiche le nom de l'espace de noms du node 
	 * (balise ou attribut).</li>
	 * <li>affiche le préfixe utilisé pour l'espace de noms du node 
	 * (balise ou attribut).</li>
	 * <li>affiche le path du Node (balise ou attribut).</li>
	 * <li>affiche le contenu textuel du node (balise ou attribut).</li>
	 * </ul>
	 * retourne null si pXmlDoc == null.<br/>
	 * Affiche les renseignements pour la racine (ROOT) 
	 * si pPathElement est blank.<br/>
	 * Retourne null si il n'y a pas de Node à XPath.<br/>
	 * <br/>
	 *
	 * @param pXmlDoc : org.dom4j.Document.<br/>
	 * @param pPathElement : String : XPath du Node 
	 * (balise ou attribut).<br/>
	 * 
	 * @return : String.<br/>
	 */
	public String afficherRenseignementsElement(
			final Document pXmlDoc
				, final String pPathElement) {
		
		/* retourne null si pXmlDoc == null. */
		if (pXmlDoc == null) {
			return null;
		}
		
		Element element = null;
		Attribute attribut = null;
		
		/* Element == Root si pPath est blank. */
		if (StringUtils.isBlank(pPathElement)) {
			element = pXmlDoc.getRootElement();
		}
		
		
		final StringBuilder stb = new StringBuilder();
		
		/* Récupération du noeud. */
		final Node node = pXmlDoc.selectSingleNode(pPathElement);
		
		/* Retourne null si il n'y a pas de Node à XPath. */
		if (node == null) {
			return null;
		}
		
		/* Récupération de l'Element (Balise). */
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			
			element = (Element) node;
			
			stb.append(ELEMENT);
			stb.append(element.getName());
			
			/* Récupération du nom qualifié de la balise. */
			final QName qName = element.getQName();
			stb.append(SAUT_LIGNE);
			stb.append("NOM QUALIFIE DE LA BALISE : ");
			stb.append(qName.getName());
			
			// lecture du nom de l'espace de noms
			final String nomEspaceDeNoms = qName.getNamespaceURI() ;
			stb.append(SAUT_LIGNE);
			stb.append("ESPACE DE NOMS DE LA BALISE : ");
			stb.append(nomEspaceDeNoms);
					
			// lecture du préfixe utilisé pour cet espace de nom 
			final String nomPrefix = qName.getNamespacePrefix() ;
			stb.append(SAUT_LIGNE);
			stb.append("NOM DU PREFIXE : ");
			stb.append(nomPrefix);
			
			/* Lit le path de la balise. */
			final String path = element.getUniquePath();
			stb.append(SAUT_LIGNE);
			stb.append("XPATH DE LA BALISE : ");
			stb.append(path);
			
			/* Lit le contenu de la balise. */
			final String contenuRacine = element.getTextTrim();
			stb.append(SAUT_LIGNE);
			stb.append("TEXTE CONTENU AU NIVEAU DE LA BALISE : ");
			stb.append(contenuRacine);
			
		}
		/* Récupération de l'Attribut. */
		else if (node.getNodeType() == Node.ATTRIBUTE_NODE) {
			
			attribut = (Attribute) node;
			
			/* Récupération du nom qualifié de l'attribut. */
			final QName qName = attribut.getQName();
			stb.append(SAUT_LIGNE);
			stb.append("NOM QUALIFIE DE L'ATTRIBUT : ");
			stb.append(qName.getName());
			
			// lecture du nom de l'espace de noms
			final String nomEspaceDeNoms = qName.getNamespaceURI() ;
			stb.append(SAUT_LIGNE);
			stb.append("ESPACE DE NOMS DE L'ATTRIBUT : ");
			stb.append(nomEspaceDeNoms);
					
			// lecture du préfixe utilisé pour cet espace de nom 
			final String nomPrefix = qName.getNamespacePrefix() ;
			stb.append(SAUT_LIGNE);
			stb.append("NOM DU PREFIXE : ");
			stb.append(nomPrefix);
			
			/* Lit le path de l'attribut. */
			final String path = attribut.getUniquePath();
			stb.append(SAUT_LIGNE);
			stb.append("XPATH DE L'ATTRIBUT : ");
			stb.append(path);
			
			/* Lit le contenu de l'attribut. */
			final String contenuRacine = attribut.getValue();
			stb.append(SAUT_LIGNE);
			stb.append("TEXTE CONTENU AU NIVEAU DE L'ATTRIBUT : ");
			stb.append(contenuRacine);			
		}
		else {
			return null;
		}
		
		
		return stb.toString();
		
	} // Fin de afficherRenseignementsElement(...).________________________
	

		
	/**
	 * method convertirFarenheitEnCelsius(
	 * String pTempFarenheit) :<br/>
	 * <ul>
	 * <li>Convertit une température exprimée en 
	 * °Farenheit en °Celsius.</li>
	 * <li>Retourne la température en °Celsius 
	 * sous forme de String.</li>
	 * </ul>
	 * return null if StringUtils.isBlank(pTempFarenheit).<br/>
	 * retourne null si pTempFarenheit n'est pas 
	 * homogène à un Double.<br/>
	 * <br/>
	 *
	 * @param pTempFarenheit
	 * @return : String : Temperature convertie en degrés Celsius.<br/>
	 */
	private String convertirFarenheitEnCelsius(
			final String pTempFarenheit) {
		
		/* return null if StringUtils.isBlank(pTempFarenheit). */
		if (StringUtils.isBlank(pTempFarenheit)) {
			return null;
		}
		
		String resultat = null;
		Double tempF = null;
		Double tempC = null;
		
		/* Arrondi du double. */
		final NumberFormat numberFormat = NumberFormat.getInstance();
		numberFormat.setMaximumFractionDigits(1);
		
		try {
			
			tempF = Double.valueOf(pTempFarenheit);
			
			/* Conversion. */
			tempC = (tempF - 32) / 1.8D;
			
			/* Arrondi et parsing en texte. */
			resultat = numberFormat.format(tempC);
			
		}
		catch (NumberFormatException e) {
			
			/* retourne null si pTempFarenheit 
			 * n'est pas homogène à un Double. */
			return null;
		}
		
		return resultat;
				
	} // Fin de convertirFarenheitEnCelsius(...).__________________________
	

		
	/**
	 * method convertirHeureAmEn24Heures(
	 * String pHeureAmericaine) :<br/>
	 * <ul>
	 * <li>Convertit une heure américaine comme "7:39 PM" en 
	 * heure européenne comme "19:39".</li>
	 * <li>Met en majuscule AM ou PM 
	 * (fonctionne avec 'a.m.', 'am', 'p.m.' et 'pm').</li>
	 * </ul>
	 * retourne null si pHeureAmericaine est blank.<br/>
	 * retourne null si pHeureAmericaine n'est pas 
	 * homogène à une heure.<br/>
	 * <br/>
	 *
	 * @param pHeureAmericaine : String : heure au 
	 * format américain comme "7:39 PM".<br/>
	 * 
	 * @return : String : Heure sur 24h.<br/>
	 */
	private String convertirHeureAmEn24Heures(
			final String pHeureAmericaine) {
		
		/* retourne null si pHeureAmericaine est blank. */
		if (StringUtils.isBlank(pHeureAmericaine)) {
			return null;
		}
		
		String resultat = null;
		
		final Locale locale = Locale.getDefault();
		
		try {
			
			String heureUS = null;
			
			/* Mise en majuscule de AM ou PM. */
			if (pHeureAmericaine.contains("a.m.")) {
				heureUS = StringUtils.replace(
						pHeureAmericaine, "a.m.", "AM");
			} else if (pHeureAmericaine.contains("am")) {
				heureUS = StringUtils.replace(
						pHeureAmericaine, "am", "AM");
			} else if (pHeureAmericaine.contains("p.m.")) {
				heureUS = StringUtils.replace(
						pHeureAmericaine, "p.m.", "PM");
			} else if (pHeureAmericaine.contains("pm")) {
				heureUS = StringUtils.replace(
						pHeureAmericaine, "pm", "PM");
			} else {
				heureUS = pHeureAmericaine;
			}
						
			/* Formatter américain. */
			final DateTimeFormatter dtfAmericain 
				= DateTimeFormatter.ofPattern("h:mm a", locale);
			
			/* Instanciation d'une LocalTime à l'aide 
			 * du Formatteur Americain. */
			final LocalTime heureAm 
				= LocalTime.parse(heureUS
						, dtfAmericain);
			
			/* Formatter européen. */
			final DateTimeFormatter dateTimeFormatterEurope 
				= DateTimeFormatter.ofPattern("HH:mm", locale);
						
			if (heureAm != null) {
				resultat = heureAm.format(dateTimeFormatterEurope);
			}
						
			return resultat;
		}
		
		catch (Exception e) {
			
			/* retourne null si pHeureAmericaine n'est pas 
			 * homogène à une heure. */
			return null;
		}
		
	} // Fin de convertirHeureAmEn24Heures(...).___________________________


	
	/**
	 * method convertirDateHeureYahooEn24Heures(
	 * String pDateHeureYahoo) :<br/>
	 * <ul>
	 * <li>Convertit une date-heure Yahoo comme 
	 * "Sun, 24 Sep 2017 11:07 PM CEST" en 
	 * date-heure européenne comme 
	 * "dimanche 24 septembre 2017 23:07".</li>
	 * </ul>
	 * retourne null si pDateHeureYahoo est blank.<br/>
	 * retourne null si pDateHeureYahoo n'est pas 
	 * homogène à une date-heure Yahoo.<br/>
	 * <br/>
	 *
	 * @param pDateHeureYahoo : String : date-heure au 
	 * format Yahoo comme "Sun, 24 Sep 2017 11:07 PM CEST".<br/>
	 * 
	 * @return : String : Date-Heure sur 24h comme 
	 * "dimanche 24 septembre 2017 23:07".<br/>
	 */
	private String convertirDateHeureYahooEn24Heures(
			final String pDateHeureYahoo) {
		
		/* retourne null si pDateHeureYahoo est blank. */
		if (StringUtils.isBlank(pDateHeureYahoo)) {
			return null;
		}
		
		String resultat = null;
		
		try {
			
			final DateTimeFormatter dtfYahoo 
				= DateTimeFormatter.ofPattern(
						"E, d MMM yyyy hh:mm a z", Locale.ROOT);
			
			final LocalDateTime date 
				= LocalDateTime.parse(pDateHeureYahoo, dtfYahoo);
			
			final DateTimeFormatter dtfEurope
			= DateTimeFormatter.ofPattern(
					"EEEE dd MMMM yyyy HH:mm", Locale.getDefault());
			
			resultat = date.format(dtfEurope);
			
			return resultat;
			
		}
		catch (Exception e) {
			
			/* retourne null si pDateHeureYahoo n'est pas homogène 
			 * à une date-heure Yahoo. */
			return null;
			
		}
		
	} // Fin de convertirDateHeureYahooEn24Heures(...).____________________
	
	
	
} // FIN DE LA CLASSE YahooParser.-------------------------------------------
