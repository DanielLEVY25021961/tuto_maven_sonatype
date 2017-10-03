package levy.daniel.application.model.services.metier.meteo;

import static org.junit.Assert.assertNull;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.junit.Test;


/**
 * class YahooMeteoRetrieverTest :<br/>
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
public class YahooMeteoRetrieverTest {

	// ************************ATTRIBUTS************************************/

	/**
	 * yahooParser : YahooParser :<br/>
	 * Parseur Yahoo.<br/>
	 */
	public transient YahooParser yahooParser = new YahooParser();
	
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG 
		= LogFactory.getLog(YahooMeteoRetrieverTest.class);


	// *************************METHODES************************************/

	
	 /**
	 * method CONSTRUCTEUR YahooMeteoRetrieverTest() :<br/>
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * <br/>
	 */
	public YahooMeteoRetrieverTest() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
	
	/**
	 * method testRetrieve() :<br/>
	 * Teste la méthode retrieve(int pWoeid).<br/>
	 * <br/>
	 * @throws Exception 
	 */
	@Test
	public void testRetrieve() throws Exception {
		
		final InputStream inputStream 
			= YahooMeteoRetriever.retrieve("Sourdun", "France");
		
		final Document doc = this.yahooParser.parse(inputStream);
		
		/* garantit que afficherRenseignementsElement(inexistant) 
		 * retourne null. */
		final String afficherInexistant 
			= this.yahooParser
				.afficherRenseignementsElement(doc, "/query/toto");		
		assertNull(
				"afficherInexistant doit retourne null : "
					, afficherInexistant);
	
		
		System.out.println("CONTENU RECUPERE : " 
				+ this.yahooParser.lireItemCodeCondition(doc));
		
		System.out.println();
		System.out.println(this.yahooParser
				.afficherRenseignementsElement(
						doc, "/query/results/channel/item/y:condition/@code"));
		System.out.println();
		
		System.out.println(this.yahooParser.format(doc));
				
	} // Fin de testRetrieve().____________________________________________


	
	/**
	 * method testFormaterRequeteYQL() :<br/>
	 * .<br/>
	 * <br/>
	 * @throws UnsupportedEncodingException 
	 */
	@Test
	public void testFormaterRequeteYQL() throws UnsupportedEncodingException {
		
		final String requeteYQL = YahooMeteoRetriever.creerRequeteYQL("Paris", "France", 3, "c");
		System.out.println(requeteYQL);
		
		final String requeteYQLEncodee = YahooMeteoRetriever.encoderRequete(requeteYQL);
		System.out.println(requeteYQLEncodee);
		
		final String urlEncodee = YahooMeteoRetriever.creerUrlEncodee("Sourdun", "France", 3, "c");
		
		System.out.println(urlEncodee);
		
	} // Fin de testFormaterRequeteYQL(...)._______________________________
	
	
	
	
} // FIN DE LA CLASSE YahooMeteoRetrieverTest.-------------------------------
