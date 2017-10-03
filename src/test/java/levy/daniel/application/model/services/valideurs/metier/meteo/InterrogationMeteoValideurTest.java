package levy.daniel.application.model.services.valideurs.metier.meteo;

import java.net.MalformedURLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import static org.junit.Assert.*;

import levy.daniel.application.model.metier.meteo.InterrogationMeteoString;
import levy.daniel.application.model.metier.user.User;
import levy.daniel.application.model.services.valideurs.IValideurGeneric;


/**
 * class InterrogationMeteoValideurTest :<br/>
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
 * @since 27 sept. 2017
 *
 */
public class InterrogationMeteoValideurTest {

	// ************************ATTRIBUTS************************************/
	
	/**
	 * VALIDEUR : IValideurGeneric<InterrogationMeteoString> :<br/>
	 * Valideur.<br/>
	 */
	public static final IValideurGeneric<InterrogationMeteoString> VALIDEUR 
		= new InterrogationMeteoValideur();
	
	
	/**
	 * ADMIN : User :<br/>
	 * User Administrateur.<br/>
	 */
	public static final User ADMIN 
		= new User("admin", "admin", "administrateur");

	
	/**
	 * NOW : LocalDateTime :<br/>
	 * Date système.<br/>
	 */
	public static final LocalDateTime NOW = LocalDateTime.now();
	
	
	/**
	 * DTF_EUROPE : DateTimeFormatter :<br/>
	 * DATETIMEFORMATTER "mercredi 27 septembre 2017 - 00:51:19".<br/>
	 */
	public static final DateTimeFormatter DTF_EUROPE
	= DateTimeFormatter.ofPattern(
			"EEEE dd MMMM yyyy - HH:mm:ss", Locale.getDefault());
	
	
	/**
	 * NOW_STRING : String :<br/>
	 * date système sous forme de String.<br/>
	 * "mercredi 27 septembre 2017 - 00:51:19".<br/>
	 */
	public static final String NOW_STRING = NOW.format(DTF_EUROPE);
	
	
	/**
	 * AFFICHAGE_GENERAL : Boolean :<br/>
	 * Boolean qui commande l'affichage pour tous les tests.<br/>
	 */
	public static final Boolean AFFICHAGE_GENERAL = true;



	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG 
		= LogFactory.getLog(InterrogationMeteoValideurTest.class);


	// *************************METHODES************************************/

	
	 /**
	 * method CONSTRUCTEUR InterrogationMeteoValideurTest() :<br/>
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * <br/>
	 */
	public InterrogationMeteoValideurTest() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
	
	
	/**
	 * method testFournirStringListeCsvRGImplementeesAvecEntete() :<br/>
	 * teste la méthode fournirStringListeCsvRGImplementeesAvecEntete()
	 * qui fournit la liste des RG vérifiées dans un Valideur.
	 * <ul>
	 * </ul>
	 * @throws MalformedURLException 
	 */
	@Test
	public void testFournirStringListeCsvRGImplementeesAvecEntete() 
			throws MalformedURLException {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = true;
		// **********************************
		
		final InterrogationMeteoString interro 
			= new InterrogationMeteoString(
					ADMIN
					, "Chambéry"
					, "Paris"
					, "3"
					, "C");
		
		VALIDEUR.validate(interro);

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			
			System.out.println("LISTE DES RG IMPLEMENTEES DANS LE VALIDEUR : ");
			System.out.println(VALIDEUR.fournirStringListeCsvRGImplementeesAvecEntete());
			System.out.println();
			
			System.out.println("LISTE DES ERREURS DETECTEES PAR LE VALIDEUR : ");
			System.out.println(VALIDEUR.afficherErreurs());
			System.out.println();
			
			System.out.println("LISTE DES CONTROLES EFFECTUES PAR LE VALIDEUR : ");
			System.out.println(VALIDEUR.afficherControles());
			System.out.println();
			
			System.out.println("VALIDITE : ");
			System.out.println("VALIDE ? : " + VALIDEUR.getValide());
			System.out.println();
		}
		
		
		assertNotNull("Le rapport de contrôle ne doit pas être null : "
				, VALIDEUR.getControles());
		
	} // Fin de testFournirStringListeCsvRGImplementeesAvecEntete()._______
	



} // FIN DE LA CLASSE InterrogationMeteoValideurTest.------------------------
