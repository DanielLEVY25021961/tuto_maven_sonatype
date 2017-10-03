package levy.daniel.application.model.services.metier.meteo.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import static org.junit.Assert.*;

import levy.daniel.application.model.metier.meteo.InterrogationMeteoString;
import levy.daniel.application.model.metier.user.User;
import levy.daniel.application.model.services.metier.meteo.IinterrogationMeteoService;


/**
 * class InterrogationMeteoServiceTest :<br/>
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
public class InterrogationMeteoServiceTest {

	// ************************ATTRIBUTS************************************/

	/**
	 * SERVICE : IinterrogationMeteoService :<br/>
	 * InterrogationMeteoService().<br/>
	 */
	public static final IinterrogationMeteoService SERVICE 
		= new InterrogationMeteoService();
	
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
		= LogFactory.getLog(InterrogationMeteoServiceTest.class);
	

	// *************************METHODES************************************/
	
	
	/**
	 * method CONSTRUCTEUR InterrogationMeteoValideur() :<br/>
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * <br/>
	 */
	public InterrogationMeteoServiceTest() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
	
	/**
	 * method testInterrogerYahooMeteo() :<br/>
	 * teste la méthode interrogerYahooMeteo(
	 * InterrogationMeteoString pInterro).<br/>
	 * <ul>
	 * </ul>
	 * @throws Exception 
	 */
	@Test
	public void testInterrogerYahooMeteo() 
			throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = true;
		// **********************************
		
		final InterrogationMeteoString interro 
			= new InterrogationMeteoString(
					ADMIN
					, "France 21"
					, "Chambéry 73"
					, "13"
					, "C");
		
		SERVICE.interrogerYahooMeteo(interro);

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			
			System.out.println("EN-TETE D'UNE LIGNE RG : ");
			System.out.println(SERVICE.fournirEnTeteCsvRGImplementees());
			System.out.println();
			
			System.out.println("LISTE DES RG IMPLEMENTEES DANS LE VALIDEUR : ");
			System.out.println(SERVICE.fournirStringListeCsvRGImplementeesAvecEntete());
			System.out.println();
			
			final String attribut = "nbreJours";
			System.out.println("ERREURS SUR L'ATTRIBUT " + attribut);
			System.out.println(SERVICE.fournirStringErreursAttribut(attribut, "\n"));
			System.out.println();
			
			final String attribut2 = "nbreJours";
			System.out.println("CONTROLES EFFECTUES SUR L'ATTRIBUT " + attribut2);
			System.out.println(SERVICE.fournirStringControlesAttribut(attribut2, "\n"));
			System.out.println();
			
			System.out.println("LISTE DES ERREURS (CONTROLES KO) DETECTEES PAR LE VALIDEUR : ");
			System.out.println(SERVICE.afficherErreurs());
			System.out.println();
			
			System.out.println("LISTE DES CONTROLES EFFECTUES PAR LE VALIDEUR : ");
			System.out.println(SERVICE.afficherControles());
			System.out.println();
			
			System.out.println("VALIDITE : ");
			System.out.println("VALIDE ? : " + SERVICE.getValide());
			System.out.println();
		}
		

		final String enteteLigneRG 
			= "id;Actif;activité des contrôles sur l'attribut;activité de la RG;"
					+ "RG implémentée;clé du type de contrôle;type de contrôle;"
					+ "Message d'erreur;Objet Métier concerné;Attribut concerné;"
					+ "Classe implémentant la RG;Méthode implémentant la RG;"
					+ "properties;clé;";
		
		assertEquals("En-tête  : "
				, enteteLigneRG
				, SERVICE.fournirEnTeteCsvRGImplementees());
		
		assertNotNull("Le valideur doit être instancié : "
				, SERVICE.getValideur());
		
		assertNotNull("La liste des RG imlémentées ne doit pas être null : "
				, SERVICE.getListeRGImplementees());
		
		assertNotNull("La Map des erreurs ne doit pas être null : "
				, SERVICE.getErreurs());
		
		assertNotNull("La Map des contrôles ne doit pas être null : "
				, SERVICE.getControles());
		
		assertNotNull("La liste des contrôles ne doit pas être null : "
				, SERVICE.getControlesList());
		
	} // Fin de testFournirStringListeCsvRGImplementeesAvecEntete()._______
	


} // FIN DE LA CLASSE InterrogationMeteoServiceTest.-------------------------
