package levy.daniel.application.model.services.valideurs.metier.meteo;

import java.net.MalformedURLException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.apptechnic.configurationmanagers.gestionnairesrg.GestionnaireRG;
import levy.daniel.application.apptechnic.configurationmanagers.gestionnairesrg.LigneRG;
import levy.daniel.application.model.metier.meteo.InterrogationMeteoString;
import levy.daniel.application.model.services.valideurs.AbstractValideurGeneric;
import levy.daniel.application.model.services.valideurs.LigneRapportValidation;


/**
 * class InterrogationMeteoValideur :<br/>
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
public class InterrogationMeteoValideur 
	extends AbstractValideurGeneric<InterrogationMeteoString> {

	// ************************ATTRIBUTS************************************/

	/**
	 * PAYS : String :<br/>
	 * attribut du BEAN "pays".<br/>
	 */
	public static final String PAYS = "pays";

	
	/**
	 * VILLE : String :<br/>
	 * attribut du BEAN "ville".<br/>
	 */
	public static final String VILLE = "ville";

	
	/**
	 * NBREJOURS : String :<br/>
	 * attribut du BEAN "nbreJours".<br/>
	 */
	public static final String NBREJOURS = "nbreJours";
	
	
	/**
	 * SANS_OBJET : String :<br/>
	 * "Sans Objet (contrôle non effectué)".<br/>
	 */
	public static final String SANS_OBJET 
		= "Sans Objet (contrôle non effectué)";
	
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG 
		= LogFactory.getLog(InterrogationMeteoValideur.class);


	// *************************METHODES************************************/
	

	
	 /**
	 * method CONSTRUCTEUR InterrogationMeteoValideur() :<br/>
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * <br/>
	 */
	public InterrogationMeteoValideur() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Map<String, Map<String, String>> validate(
			final InterrogationMeteoString pObject) 
							throws MalformedURLException {

		/* pays. */
		this.validerPays(pObject);
		
		/* ville. */
		this.validerVille(pObject);
		
		/* nbreJours. */
		this.validerNbreJours(pObject);
		
		return this.erreurs;
		
	} // Fin de validate(...)._____________________________________________


	
	/**
	 * method validerPays(
	 * InterrogationMeteoString pObject) :<br/>
	 * <ul>
	 * <li>Applique l'ensemble des contrôles activés sur toutes 
	 * les règles de gestion appliquées à l'attribut voulu 
	 * de l'objet metier.</li>
	 * <li>Alimente la map 'this.erreurs'.</li>
	 * <li>Alimente la Map 'this.controles'.</li>
	 * </ul>
	 * <br/>
	 * retourne true si pObject == null.<br/>
	 * <br/>
	 *
	 * @param pObject : InterrogationMeteoString.<br/>
	 * 
	 * @return boolean : false si l'attribut 
	 * à vérifier n'est pas valide.<br/>
	 * 
	 * @throws MalformedURLException 
	 */
	public boolean validerPays(
			final InterrogationMeteoString pObject) 
						throws MalformedURLException {
			
		final Map<String, String> mapErreursPays 
		= new ConcurrentHashMap<String, String>();
		
		final Map<String, LigneRapportValidation> mapControlesPays 
		= new ConcurrentHashMap<String, LigneRapportValidation>();
		
		/* Vérification de la RG_INTERRO_PAYS_01. */
		final boolean isValideRGInterroPays01 
			= this.validerRGInterroPays01(
					pObject, mapErreursPays, mapControlesPays);
		
		/* Vérification de la RG_INTERRO_PAYS_02. */
		final boolean isValideRGInterroPays02 
			= this.validerRGInterroPays02(
					pObject, mapErreursPays, mapControlesPays);
		
		/* Alimente la map 'this.erreurs'. */
		this.erreurs.put(PAYS, mapErreursPays);
		
		/* Alimente la Map 'this.controles'. */
		this.controles.put(PAYS, mapControlesPays);
		
		return isValideRGInterroPays01 && isValideRGInterroPays02;
		
	} // Fin de validerPays(...).__________________________________________
	

	
	/**
	 * method validerRGInterroPays01(
	 * InterrogationMeteoString pObject
	 * , Map&lt;String, String&gt; pMapErreurs
	 * , Map&lt;String, LigneRapportValidation&gt; pMapControles) :<br/>
	 * <ul>
	 * <li>Contrôle si la RG_INTERRO_PAYS_01 est bien appliquée.</li>
	 * <li>"RG_INTERRO_PAYS_01 : le pays doit être renseigné".</li>
	 * <li>alimente this.listeRGImplementees.</li>
	 * <li>Remplit la map contenant les erreurs associées à l'
	 * attribut 'pays' en cas de violation de la règle.</li>
	 * <li>Remplit la map contenant les contrôles associés 
	 * à l'attribut 'pays'.</li>
	 * <li>remplit la liste des contrôles 'this.controlesList'.</li>
	 * </ul>
	 * retourne true si pObject == null.<br/>
	 * <br/>
	 *
	 * @param pObject : InterrogationMeteoString.<br/>
	 * @param pMapErreurs : Map&lt;String, String&gt; : 
	 * Map contenant le nom de la règle violée et son message d'erreur.<br/>
	 * @param pMapControles : Map&lt;String, LigneRapportValidation&gt; 
	 * : Map contenant le nom de la règle et le rapport de contrôle.<br/>
	 * 
	 * @return : boolean : false si la règle est violée.<br/>
	 * 
	 * @throws MalformedURLException 
	 */
	public boolean validerRGInterroPays01(
			final InterrogationMeteoString pObject
			, final Map<String, String> pMapErreurs
			, final Map<String, LigneRapportValidation> pMapControles) 
							throws MalformedURLException {
		
		/* retourne true si pObject == null. */
		if (pObject == null) {
			return true;
		}
		
		boolean resultat = true;
		
		/* Extraction de l'attribut du bean 'pays'. */
		final String pays = pObject.getPays();
				
		/* RG_INTERRO_PAYS_01. */
		final LigneRG ligneRGInterroPays01 
			= GestionnaireRG.getLigneRG(GestionnaireRG.RG_INTERRO_PAYS_01);
		
		/* alimente this.listeRGImplementees. */
		this.alimenterListeRGImplementees(ligneRGInterroPays01);

		/* Récupération de l'activité de la règle. */
		final Boolean actif = ligneRGInterroPays01.getActif();
		
		if (actif) {
			
			// CAS DE VIOLATION DE LA REGLE.
			/* RG_INTERRO_PAYS_01 : le pays doit être renseigné. */
			if (StringUtils.isBlank(pays)) {
				
				/* Remplit la map contenant les erreurs associées 
				 * à l'attribut 'pays' en cas de violation de la règle. */
				pMapErreurs.put(
						GestionnaireRG.RG_INTERRO_PAYS_01
						, GestionnaireRG.RG_INTERRO_PAYS_01_MESSAGE);
				
				/* Remplit la map contenant les contrôles 
				 * associés à l'attribut 'pays'. */
				final LigneRapportValidation ligneRapportValidation 
				= new LigneRapportValidation(
						"KO"
						, "Le champ pays est null ou vide (que des espaces)"
						, ligneRGInterroPays01);
				
				pMapControles.put(
						GestionnaireRG.RG_INTERRO_PAYS_01
						, ligneRapportValidation);
				
				/* remplit la liste des contrôles 'this.controlesList'. */
				this.controlesList.add(ligneRapportValidation);
								
				resultat = false;
			
			// PAS DE VIOLATION DE LA REGLE.
			} else {
				
				/* Remplissage de la map contenant les contrôles 
				 * associés à l'attribut 'nom'. */
				final LigneRapportValidation ligneRapportValidation 
				= new LigneRapportValidation(
						"OK"
						, "Le champ pays est renseigné"
						, ligneRGInterroPays01);
				
				pMapControles.put(
						GestionnaireRG.RG_INTERRO_PAYS_01
						, ligneRapportValidation);
				
				/* remplissage de la liste des contrôles. */
				this.controlesList.add(ligneRapportValidation);
				
				resultat = true;
			}
		
		// CONTROLE INACTIF.
		} else {
			
			/* Remplissage de la map contenant les contrôles 
			 * associés à l'attribut 'nom'. */
			final LigneRapportValidation ligneRapportValidation 
			= new LigneRapportValidation(
					SANS_OBJET
					, "La nullité du champ pays n'est pas contrôlée"
					, ligneRGInterroPays01);
			
			pMapControles.put(
					GestionnaireRG.RG_INTERRO_PAYS_01
					, ligneRapportValidation);
			
			/* remplissage de la liste des contrôles. */
			this.controlesList.add(ligneRapportValidation);
			
			resultat = true;
			
		}
				
		return resultat;
		
	} // Fin de validerRGInterroPays01(...)._______________________________


	
	/**
	 * method validerRGInterroPays02(
	 * InterrogationMeteoString pObject
	 * , Map&lt;String, String&gt; pMapErreurs
	 * , Map&lt;String, LigneRapportValidation&gt; pMapControles) :<br/>
	 * <ul>
	 * <li>Contrôle si la RG_INTERRO_PAYS_02 est bien appliquée.</li>
	 * <li>"RG_INTERRO_PAYS_02 : le pays ne doit contenir que des lettres".</li>
	 * <li>alimente this.listeRGImplementees.</li>
	 * <li>Remplit la map contenant les erreurs associées à l'
	 * attribut 'pays' en cas de violation de la règle.</li>
	 * <li>Remplit la map contenant les contrôles associés 
	 * à l'attribut 'pays'.</li>
	 * <li>remplit la liste des contrôles 'this.controlesList'.</li>
	 * </ul>
	 * retourne true si pObject == null.<br/>
	 * retourne true si pays == null.<br/>
	 * <br/>
	 *
	 * @param pObject : InterrogationMeteoString.<br/>
	 * @param pMapErreurs : Map&lt;String, String&gt; : 
	 * Map contenant le nom de la règle violée et son message d'erreur.<br/>
	 * @param pMapControles : Map&lt;String, LigneRapportValidation&gt; 
	 * : Map contenant le nom de la règle et le rapport de contrôle.<br/>
	 * 
	 * @return : boolean : false si la règle est violée.<br/>
	 * 
	 * @throws MalformedURLException 
	 */
	public boolean validerRGInterroPays02(
			final InterrogationMeteoString pObject
			, final Map<String, String> pMapErreurs
			, final Map<String, LigneRapportValidation> pMapControles) 
							throws MalformedURLException {
		
		/* retourne true si pObject == null. */
		if (pObject == null) {
			return true;
		}
		
		boolean resultat = true;
		
		/* Extraction de l'attribut du bean 'pays'. */
		final String pays = pObject.getPays();
				
		/* RG_INTERRO_PAYS_02. */
		final LigneRG ligneRGInterroPays02 
			= GestionnaireRG.getLigneRG(GestionnaireRG.RG_INTERRO_PAYS_02);
		
		/* alimente this.listeRGImplementees. */
		this.alimenterListeRGImplementees(ligneRGInterroPays02);
		
		/* retourne true si pays == null. */
		if (pays == null) {
			
			/* Remplissage de la map contenant les contrôles 
			 * associés à l'attribut 'pays'. */
			final LigneRapportValidation ligneRapportValidation 
			= new LigneRapportValidation(
					"OK"
					, "Le champ pays est null. Donc pas de chiffres !"
					, ligneRGInterroPays02);
			
			pMapControles.put(
					GestionnaireRG.RG_INTERRO_PAYS_02
					, ligneRapportValidation);
			
			/* remplissage de la liste des contrôles. */
			this.controlesList.add(ligneRapportValidation);
			
			return true;
			
		}

		/* Récupération de l'activité de la règle. */
		final Boolean actif = ligneRGInterroPays02.getActif();
		
		if (actif) {
			
			/* Pattern "que des lettres". 
			 * '+' signifie au moins une lettre. */
			/* '*' signifie 0 à n lettres. */
			final Pattern pattern = Pattern.compile("\\D*");
			
			/* Moteur de recherche Matcher. */
			final Matcher matcher = pattern.matcher(pays);
			
			/* boolean qui stipule si le pattern est respecté. */
			final boolean match = matcher.matches();
			
			// CAS DE VIOLATION DE LA REGLE.
			/* RG_INTERRO_PAYS_02 : le pays ne doit contenir que des lettres. */
			if (!match) {
				
				/* Remplit la map contenant les erreurs associées 
				 * à l'attribut 'pays' en cas de violation de la règle. */
				pMapErreurs.put(
						GestionnaireRG.RG_INTERRO_PAYS_02
						, GestionnaireRG.RG_INTERRO_PAYS_02_MESSAGE);
				
				/* Remplit la map contenant les contrôles 
				 * associées à l'attribut 'pays'. */
				final LigneRapportValidation ligneRapportValidation 
				= new LigneRapportValidation(
						"KO"
						, "Le champ pays ne contient pas que des lettres : " + pays
						, ligneRGInterroPays02);
				
				pMapControles.put(
						GestionnaireRG.RG_INTERRO_PAYS_02
						, ligneRapportValidation);
				
				/* remplit la liste des contrôles 'this.controlesList'. */
				this.controlesList.add(ligneRapportValidation);
								
				resultat = false;
			
			// PAS DE VIOLATION DE LA REGLE.
			} else {
				
				if (!StringUtils.isBlank(pays)) {
					
					/* Remplissage de la map contenant les contrôles 
					 * associés à l'attribut 'pays'. */
					final LigneRapportValidation ligneRapportValidation 
					= new LigneRapportValidation(
							"OK"
							, "Le champ pays ne contient que des lettres : " + pays
							, ligneRGInterroPays02);
					
					pMapControles.put(
							GestionnaireRG.RG_INTERRO_PAYS_02
							, ligneRapportValidation);
					
					/* remplissage de la liste des contrôles. */
					this.controlesList.add(ligneRapportValidation);
					
				} else {
					
					/* Remplissage de la map contenant les contrôles 
					 * associés à l'attribut 'pays'. */
					final LigneRapportValidation ligneRapportValidation 
					= new LigneRapportValidation(
							"OK"
							, "Le champ pays est blank (espaces). Donc pas de chiffres !"
							, ligneRGInterroPays02);
					
					pMapControles.put(
							GestionnaireRG.RG_INTERRO_PAYS_02
							, ligneRapportValidation);
					
					/* remplissage de la liste des contrôles. */
					this.controlesList.add(ligneRapportValidation);
										
				}
				
				resultat = true;
			}
		
		// CONTROLE INACTIF.
		} else {
			
			/* Remplissage de la map contenant les contrôles 
			 * associés à l'attribut 'pays'. */
			final LigneRapportValidation ligneRapportValidation 
			= new LigneRapportValidation(
					SANS_OBJET
					, "Le format du champ pays n'est pas contrôlé"
					, ligneRGInterroPays02);
			
			pMapControles.put(
					GestionnaireRG.RG_INTERRO_PAYS_02
					, ligneRapportValidation);
			
			/* remplissage de la liste des contrôles. */
			this.controlesList.add(ligneRapportValidation);
			
			resultat = true;
			
		}
				
		return resultat;
		
	} // Fin de validerRGInterroPays02(...)._______________________________


	
	/**
	 * method validerVille(
	 * InterrogationMeteoString pObject) :<br/>
	 * <ul>
	 * <li>Applique l'ensemble des contrôles activés sur toutes 
	 * les règles de gestion appliquées à l'attribut voulu 
	 * de l'objet metier.</li>
	 * <li>Alimente la map 'this.erreurs'.</li>
	 * <li>Alimente la Map 'this.controles'.</li>
	 * </ul>
	 * <br/>
	 * retourne true si pObject == null.<br/>
	 * <br/>
	 *
	 * @param pObject : InterrogationMeteoString.<br/>
	 * 
	 * @return boolean : false si l'attribut 
	 * à vérifier n'est pas valide.<br/>
	 * 
	 * @throws MalformedURLException 
	 */
	public boolean validerVille(
			final InterrogationMeteoString pObject) 
						throws MalformedURLException {
			
		final Map<String, String> mapErreursVille 
		= new ConcurrentHashMap<String, String>();
		
		final Map<String, LigneRapportValidation> mapControlesVille 
		= new ConcurrentHashMap<String, LigneRapportValidation>();
		
		/* Vérification de la RG_INTERRO_VILLE_03. */
		final boolean isValideRGInterroVille03 
			= this.validerRGInterroVille03(
					pObject, mapErreursVille, mapControlesVille);
		
		/* Vérification de la RG_INTERRO_VILLE_04. */
		final boolean isValideRGInterroVille04 
			= this.validerRGInterroVille04(
					pObject, mapErreursVille, mapControlesVille);
		
		/* Alimente la map 'this.erreurs'. */
		this.erreurs.put(VILLE, mapErreursVille);
		
		/* Alimente la Map 'this.controles'. */
		this.controles.put(VILLE, mapControlesVille);
		
		return isValideRGInterroVille03 && isValideRGInterroVille04;
		
	} // Fin de validerVille(...)._________________________________________
	

	
	/**
	 * method validerRGInterroVille03(
	 * InterrogationMeteoString pObject
	 * , Map&lt;String, String&gt; pMapErreurs
	 * , Map&lt;String, LigneRapportValidation&gt; pMapControles) :<br/>
	 * <ul>
	 * <li>Contrôle si la RG_INTERRO_VILLE_03 est bien appliquée.</li>
	 * <li>"RG_INTERRO_VILLE_03 : la ville doit être renseignée".</li>
	 * <li>alimente this.listeRGImplementees.</li>
	 * <li>Remplit la map contenant les erreurs associées à l'
	 * attribut 'ville' en cas de violation de la règle.</li>
	 * <li>Remplit la map contenant les contrôles associés 
	 * à l'attribut 'ville'.</li>
	 * <li>remplit la liste des contrôles 'this.controlesList'.</li>
	 * </ul>
	 * retourne true si pObject == null.<br/>
	 * <br/>
	 *
	 * @param pObject : InterrogationMeteoString.<br/>
	 * @param pMapErreurs : Map&lt;String, String&gt; : 
	 * Map contenant le nom de la règle violée et son message d'erreur.<br/>
	 * @param pMapControles : Map&lt;String, LigneRapportValidation&gt; 
	 * : Map contenant le nom de la règle et le rapport de contrôle.<br/>
	 * 
	 * @return : boolean : false si la règle est violée.<br/>
	 * 
	 * @throws MalformedURLException 
	 */
	public boolean validerRGInterroVille03(
			final InterrogationMeteoString pObject
			, final Map<String, String> pMapErreurs
			, final Map<String, LigneRapportValidation> pMapControles) 
							throws MalformedURLException {
		
		/* retourne true si pObject == null. */
		if (pObject == null) {
			return true;
		}
		
		boolean resultat = true;
		
		/* Extraction de l'attribut du bean 'ville'. */
		final String ville = pObject.getVille();
				
		/* RG_INTERRO_VILLE_03. */
		final LigneRG ligneRGInterroVille03 
			= GestionnaireRG.getLigneRG(
					GestionnaireRG.RG_INTERRO_VILLE_03);
		
		/* alimente this.listeRGImplementees. */
		this.alimenterListeRGImplementees(ligneRGInterroVille03);

		/* Récupération de l'activité de la règle. */
		final Boolean actif = ligneRGInterroVille03.getActif();
		
		if (actif) {
			
			// CAS DE VIOLATION DE LA REGLE.
			/* RG_INTERRO_VILLE_03 : la ville doit être renseignée. */
			if (StringUtils.isBlank(ville)) {
				
				/* Remplit la map contenant les erreurs associées 
				 * à l'attribut 'ville' en cas de violation de la règle. */
				pMapErreurs.put(
						GestionnaireRG.RG_INTERRO_VILLE_03
						, GestionnaireRG.RG_INTERRO_VILLE_03_MESSAGE);
				
				/* Remplit la map contenant les contrôles 
				 * associés à l'attribut 'ville'. */
				final LigneRapportValidation ligneRapportValidation 
				= new LigneRapportValidation(
						"KO"
						, "Le champ ville est null ou vide (que des espaces)"
						, ligneRGInterroVille03);
				
				pMapControles.put(
						GestionnaireRG.RG_INTERRO_VILLE_03
						, ligneRapportValidation);
				
				/* remplit la liste des contrôles 'this.controlesList'. */
				this.controlesList.add(ligneRapportValidation);
								
				resultat = false;
			
			// PAS DE VIOLATION DE LA REGLE.
			} else {
				
				/* Remplissage de la map contenant les contrôles 
				 * associés à l'attribut 'ville'. */
				final LigneRapportValidation ligneRapportValidation 
				= new LigneRapportValidation(
						"OK"
						, "Le champ ville est renseigné"
						, ligneRGInterroVille03);
				
				pMapControles.put(
						GestionnaireRG.RG_INTERRO_VILLE_03
						, ligneRapportValidation);
				
				/* remplissage de la liste des contrôles. */
				this.controlesList.add(ligneRapportValidation);
				
				resultat = true;
			}
		
		// CONTROLE INACTIF.
		} else {
			
			/* Remplissage de la map contenant les contrôles 
			 * associés à l'attribut 'ville'. */
			final LigneRapportValidation ligneRapportValidation 
			= new LigneRapportValidation(
					SANS_OBJET
					, "La nullité du champ ville n'est pas contrôlée"
					, ligneRGInterroVille03);
			
			pMapControles.put(
					GestionnaireRG.RG_INTERRO_VILLE_03
					, ligneRapportValidation);
			
			/* remplissage de la liste des contrôles. */
			this.controlesList.add(ligneRapportValidation);
			
			resultat = true;
			
		}
				
		return resultat;
		
	} // Fin de validerRGInterroVille03(...).______________________________


	
	/**
	 * method validerRGInterroVille04(
	 * InterrogationMeteoString pObject
	 * , Map&lt;String, String&gt; pMapErreurs
	 * , Map&lt;String, LigneRapportValidation&gt; pMapControles) :<br/>
	 * <ul>
	 * <li>Contrôle si la RG_INTERRO_VILLE_04 est bien appliquée.</li>
	 * <li>"RG_INTERRO_VILLE_04 : le ville ne doit contenir que des lettres".</li>
	 * <li>alimente this.listeRGImplementees.</li>
	 * <li>Remplit la map contenant les erreurs associées à l'
	 * attribut 'ville' en cas de violation de la règle.</li>
	 * <li>Remplit la map contenant les contrôles associés 
	 * à l'attribut 'ville'.</li>
	 * <li>remplit la liste des contrôles 'this.controlesList'.</li>
	 * </ul>
	 * retourne true si pObject == null.<br/>
	 * retourne true si ville == null.<br/>
	 * <br/>
	 *
	 * @param pObject : InterrogationMeteoString.<br/>
	 * @param pMapErreurs : Map&lt;String, String&gt; : 
	 * Map contenant le nom de la règle violée et son message d'erreur.<br/>
	 * @param pMapControles : Map&lt;String, LigneRapportValidation&gt; 
	 * : Map contenant le nom de la règle et le rapport de contrôle.<br/>
	 * 
	 * @return : boolean : false si la règle est violée.<br/>
	 * 
	 * @throws MalformedURLException 
	 */
	public boolean validerRGInterroVille04(
			final InterrogationMeteoString pObject
			, final Map<String, String> pMapErreurs
			, final Map<String, LigneRapportValidation> pMapControles) 
							throws MalformedURLException {
		
		/* retourne true si pObject == null. */
		if (pObject == null) {
			return true;
		}
		
		boolean resultat = true;
		
		/* Extraction de l'attribut du bean 'ville'. */
		final String ville = pObject.getVille();
				
		/* RG_INTERRO_VILLE_04. */
		final LigneRG ligneRGInterroVille04 
			= GestionnaireRG.getLigneRG(GestionnaireRG.RG_INTERRO_VILLE_04);
		
		/* alimente this.listeRGImplementees. */
		this.alimenterListeRGImplementees(ligneRGInterroVille04);
		
		/* retourne true si ville == null. */
		if (ville == null) {
			
			/* Remplissage de la map contenant les contrôles 
			 * associés à l'attribut 'ville'. */
			final LigneRapportValidation ligneRapportValidation 
			= new LigneRapportValidation(
					"OK"
					, "Le champ ville est null. Donc pas de chiffres !"
					, ligneRGInterroVille04);
			
			pMapControles.put(
					GestionnaireRG.RG_INTERRO_VILLE_04
					, ligneRapportValidation);
			
			/* remplissage de la liste des contrôles. */
			this.controlesList.add(ligneRapportValidation);
			
			return true;
			
		}

		/* Récupération de l'activité de la règle. */
		final Boolean actif = ligneRGInterroVille04.getActif();
		
		if (actif) {
			
			/* Pattern "que des lettres". 
			 * '+' signifie au moins une lettre. */
			/* '*' signifie 0 à n lettres. */
			final Pattern pattern = Pattern.compile("\\D*");
			
			/* Moteur de recherche Matcher. */
			final Matcher matcher = pattern.matcher(ville);
			
			/* boolean qui stipule si le pattern est respecté. */
			final boolean match = matcher.matches();
			
			// CAS DE VIOLATION DE LA REGLE.
			/* RG_INTERRO_VILLE_04 : le ville ne doit contenir que des lettres. */
			if (!match) {
				
				/* Remplit la map contenant les erreurs associées 
				 * à l'attribut 'ville' en cas de violation de la règle. */
				pMapErreurs.put(
						GestionnaireRG.RG_INTERRO_VILLE_04
						, GestionnaireRG.RG_INTERRO_VILLE_04_MESSAGE);
				
				/* Remplit la map contenant les contrôles 
				 * associées à l'attribut 'ville'. */
				final LigneRapportValidation ligneRapportValidation 
				= new LigneRapportValidation(
						"KO"
						, "Le champ ville ne contient pas que des lettres : " + ville
						, ligneRGInterroVille04);
				
				pMapControles.put(
						GestionnaireRG.RG_INTERRO_VILLE_04
						, ligneRapportValidation);
				
				/* remplit la liste des contrôles 'this.controlesList'. */
				this.controlesList.add(ligneRapportValidation);
								
				resultat = false;
			
			// PAS DE VIOLATION DE LA REGLE.
			} else {
				
				if (!StringUtils.isBlank(ville)) {
					
					/* Remplissage de la map contenant les contrôles 
					 * associés à l'attribut 'ville'. */
					final LigneRapportValidation ligneRapportValidation 
					= new LigneRapportValidation(
							"OK"
							, "Le champ ville ne contient que des lettres : " + ville
							, ligneRGInterroVille04);
					
					pMapControles.put(
							GestionnaireRG.RG_INTERRO_VILLE_04
							, ligneRapportValidation);
					
					/* remplissage de la liste des contrôles. */
					this.controlesList.add(ligneRapportValidation);
					
				} else {
					
					/* Remplissage de la map contenant les contrôles 
					 * associés à l'attribut 'ville'. */
					final LigneRapportValidation ligneRapportValidation 
					= new LigneRapportValidation(
							"OK"
							, "Le champ ville est blank (espaces). Donc pas de chiffres !"
							, ligneRGInterroVille04);
					
					pMapControles.put(
							GestionnaireRG.RG_INTERRO_VILLE_04
							, ligneRapportValidation);
					
					/* remplissage de la liste des contrôles. */
					this.controlesList.add(ligneRapportValidation);
										
				}
				
				resultat = true;
			}
		
		// CONTROLE INACTIF.
		} else {
			
			/* Remplissage de la map contenant les contrôles 
			 * associés à l'attribut 'ville'. */
			final LigneRapportValidation ligneRapportValidation 
			= new LigneRapportValidation(
					SANS_OBJET
					, "Le format du champ ville n'est pas contrôlé"
					, ligneRGInterroVille04);
			
			pMapControles.put(
					GestionnaireRG.RG_INTERRO_VILLE_04
					, ligneRapportValidation);
			
			/* remplissage de la liste des contrôles. */
			this.controlesList.add(ligneRapportValidation);
			
			resultat = true;
			
		}
				
		return resultat;
		
	} // Fin de validerRGInterroVille04(...).______________________________


	
	/**
	 * method validerNbreJours(
	 * InterrogationMeteoString pObject) :<br/>
	 * <ul>
	 * <li>Applique l'ensemble des contrôles activés sur toutes 
	 * les règles de gestion appliquées à l'attribut voulu 
	 * de l'objet metier.</li>
	 * <li>Alimente la map 'this.erreurs'.</li>
	 * <li>Alimente la Map 'this.controles'.</li>
	 * </ul>
	 * <br/>
	 * retourne true si pObject == null.<br/>
	 * <br/>
	 *
	 * @param pObject : InterrogationMeteoString.<br/>
	 * 
	 * @return boolean : false si l'attribut 
	 * à vérifier n'est pas valide.<br/>
	 * 
	 * @throws MalformedURLException 
	 */
	public boolean validerNbreJours(
			final InterrogationMeteoString pObject) 
						throws MalformedURLException {
			
		final Map<String, String> mapErreursNbreJours 
		= new ConcurrentHashMap<String, String>();
		
		final Map<String, LigneRapportValidation> mapControlesNbreJours 
		= new ConcurrentHashMap<String, LigneRapportValidation>();
		
		/* Vérification de la RG_INTERRO_NBREJOURS_05. */
		final boolean isValideRGInterroNbreJours05 
			= this.validerRGInterroNbreJours05(
					pObject, mapErreursNbreJours, mapControlesNbreJours);
		
		/* Vérification de la RG_INTERRO_NBREJOURS_06. */
		final boolean isValideRGInterroNbreJours06 
			= this.validerRGInterroNbreJours06(
					pObject, mapErreursNbreJours, mapControlesNbreJours);
		
		/* Alimente la map 'this.erreurs'. */
		this.erreurs.put(NBREJOURS, mapErreursNbreJours);
		
		/* Alimente la Map 'this.controles'. */
		this.controles.put(NBREJOURS, mapControlesNbreJours);
		
		return isValideRGInterroNbreJours05 
				&& isValideRGInterroNbreJours06;
		
	} // Fin de validerNbreJours(...)._____________________________________
	


	
	/**
	 * method validerRGInterroNbreJours05(
	 * InterrogationMeteoString pObject
	 * , Map&lt;String, String&gt; pMapErreurs
	 * , Map&lt;String, LigneRapportValidation&gt; pMapControles) :<br/>
	 * <ul>
	 * <li>Contrôle si la RG_INTERRO_NBREJOURS_05 est bien appliquée.</li>
	 * <li>"RG_INTERRO_NBREJOURS_05 : le nombre de jours doit être homogène à un entier numérique".</li>
	 * <li>alimente this.listeRGImplementees.</li>
	 * <li>Remplit la map contenant les erreurs associées à l'
	 * attribut 'nbreJours' en cas de violation de la règle.</li>
	 * <li>Remplit la map contenant les contrôles associés 
	 * à l'attribut 'nbreJours'.</li>
	 * <li>remplit la liste des contrôles 'this.controlesList'.</li>
	 * </ul>
	 * retourne true si pObject == null.<br/>
	 * retourne true si nbreJours == null.<br/>
	 * <br/>
	 *
	 * @param pObject : InterrogationMeteoString.<br/>
	 * @param pMapErreurs : Map&lt;String, String&gt; : 
	 * Map contenant le nom de la règle violée et son message d'erreur.<br/>
	 * @param pMapControles : Map&lt;String, LigneRapportValidation&gt; 
	 * : Map contenant le nom de la règle et le rapport de contrôle.<br/>
	 * 
	 * @return : boolean : false si la règle est violée.<br/>
	 * 
	 * @throws MalformedURLException 
	 */
	public boolean validerRGInterroNbreJours05(
			final InterrogationMeteoString pObject
			, final Map<String, String> pMapErreurs
			, final Map<String, LigneRapportValidation> pMapControles) 
							throws MalformedURLException {
		
		/* retourne true si pObject == null. */
		if (pObject == null) {
			return true;
		}
		
		boolean resultat = true;
		
		/* Extraction de l'attribut du bean 'nbreJours'. */
		final String nbreJours = pObject.getNbreJours();
				
		/* RG_INTERRO_NBREJOURS_05. */
		final LigneRG ligneRGInterroNbreJours05 
			= GestionnaireRG.getLigneRG(GestionnaireRG.RG_INTERRO_NBREJOURS_05);
		
		/* alimente this.listeRGImplementees. */
		this.alimenterListeRGImplementees(ligneRGInterroNbreJours05);
		
		/* retourne true si nbreJours == null. */
		if (nbreJours == null) {
			
			/* Remplissage de la map contenant les contrôles 
			 * associés à l'attribut 'nbreJours'. */
			final LigneRapportValidation ligneRapportValidation 
			= new LigneRapportValidation(
					"OK"
					, "Le champ nbreJours est null. Donc pas de lettres !"
					, ligneRGInterroNbreJours05);
			
			pMapControles.put(
					GestionnaireRG.RG_INTERRO_NBREJOURS_05
					, ligneRapportValidation);
			
			/* remplissage de la liste des contrôles. */
			this.controlesList.add(ligneRapportValidation);
			
			return true;
			
		}

		/* Récupération de l'activité de la règle. */
		final Boolean actif = ligneRGInterroNbreJours05.getActif();
		
		if (actif) {
			
			/* Pattern "que des chiffres". 
			 * '+' signifie au moins une lettre. */
			/* '*' signifie 0 à n lettres. */
			final Pattern pattern = Pattern.compile("\\d*");
			
			/* Moteur de recherche Matcher. */
			final Matcher matcher = pattern.matcher(nbreJours);
			
			/* boolean qui stipule si le pattern est respecté. */
			final boolean match = matcher.matches();
			
			// CAS DE VIOLATION DE LA REGLE.
			/* RG_INTERRO_NBREJOURS_05 : le nombre de jours 
			 * doit être homogène à un entier numérique. */
			if (!match) {
				
				/* Remplit la map contenant les erreurs associées 
				 * à l'attribut 'nbreJours' en cas de violation de la règle. */
				pMapErreurs.put(
						GestionnaireRG.RG_INTERRO_NBREJOURS_05
						, GestionnaireRG.RG_INTERRO_NBREJOURS_05_MESSAGE);
				
				/* Remplit la map contenant les contrôles 
				 * associées à l'attribut 'nbreJours'. */
				final LigneRapportValidation ligneRapportValidation 
				= new LigneRapportValidation(
						"KO"
						, "Le champ nbreJours ne contient pas que des chiffres : " + nbreJours
						, ligneRGInterroNbreJours05);
				
				pMapControles.put(
						GestionnaireRG.RG_INTERRO_NBREJOURS_05
						, ligneRapportValidation);
				
				/* remplit la liste des contrôles 'this.controlesList'. */
				this.controlesList.add(ligneRapportValidation);
								
				resultat = false;
			
			// PAS DE VIOLATION DE LA REGLE.
			} else {
				
				if (!StringUtils.isBlank(nbreJours)) {
					
					/* Remplissage de la map contenant les contrôles 
					 * associés à l'attribut 'nbreJours'. */
					final LigneRapportValidation ligneRapportValidation 
					= new LigneRapportValidation(
							"OK"
							, "Le champ nbreJours ne contient que des chiffres : " + nbreJours
							, ligneRGInterroNbreJours05);
					
					pMapControles.put(
							GestionnaireRG.RG_INTERRO_NBREJOURS_05
							, ligneRapportValidation);
					
					/* remplissage de la liste des contrôles. */
					this.controlesList.add(ligneRapportValidation);
					
				} else {
					
					/* Remplissage de la map contenant les contrôles 
					 * associés à l'attribut 'nbreJours'. */
					final LigneRapportValidation ligneRapportValidation 
					= new LigneRapportValidation(
							"OK"
							, "Le champ nbreJours est blank (espaces). Donc pas de lettres !"
							, ligneRGInterroNbreJours05);
					
					pMapControles.put(
							GestionnaireRG.RG_INTERRO_NBREJOURS_05
							, ligneRapportValidation);
					
					/* remplissage de la liste des contrôles. */
					this.controlesList.add(ligneRapportValidation);
										
				}
				
				resultat = true;
			}
		
		// CONTROLE INACTIF.
		} else {
			
			/* Remplissage de la map contenant les contrôles 
			 * associés à l'attribut 'nbreJours'. */
			final LigneRapportValidation ligneRapportValidation 
			= new LigneRapportValidation(
					SANS_OBJET
					, "Le format du champ nbreJours n'est pas contrôlé"
					, ligneRGInterroNbreJours05);
			
			pMapControles.put(
					GestionnaireRG.RG_INTERRO_NBREJOURS_05
					, ligneRapportValidation);
			
			/* remplissage de la liste des contrôles. */
			this.controlesList.add(ligneRapportValidation);
			
			resultat = true;
			
		}
				
		return resultat;
		
	} // Fin de validerRGInterroNbreJours05(...).__________________________


	
	
	/**
	 * method validerRGInterroNbreJours06(
	 * InterrogationMeteoString pObject
	 * , Map&lt;String, String&gt; pMapErreurs
	 * , Map&lt;String, LigneRapportValidation&gt; pMapControles) :<br/>
	 * <ul>
	 * <li>Contrôle si la RG_INTERRO_NBREJOURS_06 est bien appliquée.</li>
	 * <li>"RG_INTERRO_NBREJOURS_06 : le nombre de jours doit être inférieur ou égal à 10".</li>
	 * <li>alimente this.listeRGImplementees.</li>
	 * <li>Remplit la map contenant les erreurs associées à l'
	 * attribut 'nbreJours' en cas de violation de la règle.</li>
	 * <li>Remplit la map contenant les contrôles associés 
	 * à l'attribut 'nbreJours'.</li>
	 * <li>remplit la liste des contrôles 'this.controlesList'.</li>
	 * </ul>
	 * retourne true si pObject == null.<br/>
	 * retourne true si nbreJours == null.<br/>
	 * <br/>
	 *
	 * @param pObject : InterrogationMeteoString.<br/>
	 * @param pMapErreurs : Map&lt;String, String&gt; : 
	 * Map contenant le nom de la règle violée et son message d'erreur.<br/>
	 * @param pMapControles : Map&lt;String, LigneRapportValidation&gt; 
	 * : Map contenant le nom de la règle et le rapport de contrôle.<br/>
	 * 
	 * @return : boolean : false si la règle est violée.<br/>
	 * 
	 * @throws MalformedURLException 
	 */
	public boolean validerRGInterroNbreJours06(
			final InterrogationMeteoString pObject
			, final Map<String, String> pMapErreurs
			, final Map<String, LigneRapportValidation> pMapControles) 
							throws MalformedURLException {
		
		/* retourne true si pObject == null. */
		if (pObject == null) {
			return true;
		}
		
		boolean resultat = true;
		
		/* Extraction de l'attribut du bean 'nbreJours'. */
		final String nbreJours = pObject.getNbreJours();
				
		/* RG_INTERRO_NBREJOURS_06. */
		final LigneRG ligneRGInterroNbreJours06 
			= GestionnaireRG.getLigneRG(GestionnaireRG
					.RG_INTERRO_NBREJOURS_06);
		
		/* alimente this.listeRGImplementees. */
		this.alimenterListeRGImplementees(ligneRGInterroNbreJours06);
		
		/* retourne true si nbreJours == null. */
		if (nbreJours == null) {
			
			/* Remplissage de la map contenant les contrôles 
			 * associés à l'attribut 'nbreJours'. */
			final LigneRapportValidation ligneRapportValidation 
			= new LigneRapportValidation(
					"OK"
					, "Le champ nbreJours est null. Donc < 10 !"
					, ligneRGInterroNbreJours06);
			
			pMapControles.put(
					GestionnaireRG.RG_INTERRO_NBREJOURS_05
					, ligneRapportValidation);
			
			/* remplissage de la liste des contrôles. */
			this.controlesList.add(ligneRapportValidation);
			
			return true;
			
		}
		
		/* Récupération de l'activité de la règle. */
		final Boolean actif = ligneRGInterroNbreJours06.getActif();
		
		if (actif) {
			
			Integer nJoursInt = null;
			
			try {
				
				nJoursInt = Integer.valueOf(nbreJours);
				
				// CAS DE VIOLATION DE LA REGLE.
				/* RG_INTERRO_NBREJOURS_06 : 
				 * le nombre de jours doit être inférieur ou égal à 10. */
				if (nJoursInt > 10) {
					
					/* Remplit la map contenant les erreurs associées 
					 * à l'attribut 'nbreJours' en cas de violation de la règle. */
					pMapErreurs.put(
							GestionnaireRG.RG_INTERRO_NBREJOURS_06
							, GestionnaireRG.RG_INTERRO_NBREJOURS_06_MESSAGE);
					
					/* Remplit la map contenant les contrôles 
					 * associées à l'attribut 'nbreJours'. */
					final LigneRapportValidation ligneRapportValidation 
					= new LigneRapportValidation(
							"KO"
							, "Le champ nbreJours contient une valeur > 10 : " + nbreJours
							, ligneRGInterroNbreJours06);
					
					pMapControles.put(
							GestionnaireRG.RG_INTERRO_NBREJOURS_06
							, ligneRapportValidation);
					
					/* remplit la liste des contrôles 'this.controlesList'. */
					this.controlesList.add(ligneRapportValidation);
									
					resultat = false;
				
				// PAS DE VIOLATION DE LA REGLE.
				} else {
					
					if (!StringUtils.isBlank(nbreJours)) {
						
						/* Remplissage de la map contenant les contrôles 
						 * associés à l'attribut 'nbreJours'. */
						final LigneRapportValidation ligneRapportValidation 
						= new LigneRapportValidation(
								"OK"
								, "Le champ nbreJours =< 10 : " + nbreJours
								, ligneRGInterroNbreJours06);
						
						pMapControles.put(
								GestionnaireRG.RG_INTERRO_NBREJOURS_06
								, ligneRapportValidation);
						
						/* remplissage de la liste des contrôles. */
						this.controlesList.add(ligneRapportValidation);
						
					} else {
						
						/* Remplissage de la map contenant les contrôles 
						 * associés à l'attribut 'nbreJours'. */
						final LigneRapportValidation ligneRapportValidation 
						= new LigneRapportValidation(
								"OK"
								, "Le champ nbreJours est blank (espaces). Donc =< 10 !"
								, ligneRGInterroNbreJours06);
						
						pMapControles.put(
								GestionnaireRG.RG_INTERRO_NBREJOURS_05
								, ligneRapportValidation);
						
						/* remplissage de la liste des contrôles. */
						this.controlesList.add(ligneRapportValidation);
											
					}
					
					resultat = true;
				}

				
			} catch (Exception e) {
				
				/* Remplissage de la map contenant les contrôles 
				 * associés à l'attribut 'nbreJours'. */
				final LigneRapportValidation ligneRapportValidation 
				= new LigneRapportValidation(
						SANS_OBJET
						, "La valeur du champ nbreJours n'est pas contrôlable (champ non entier)"
						, ligneRGInterroNbreJours06);
				
				pMapControles.put(
						GestionnaireRG.RG_INTERRO_NBREJOURS_06
						, ligneRapportValidation);
				
				/* remplissage de la liste des contrôles. */
				this.controlesList.add(ligneRapportValidation);
				
				resultat = true;
				
			}
			
		// CONTROLE INACTIF.
		} else {
			
			/* Remplissage de la map contenant les contrôles 
			 * associés à l'attribut 'nbreJours'. */
			final LigneRapportValidation ligneRapportValidation 
			= new LigneRapportValidation(
					SANS_OBJET
					, "La valeur du champ nbreJours n'est pas contrôlée"
					, ligneRGInterroNbreJours06);
			
			pMapControles.put(
					GestionnaireRG.RG_INTERRO_NBREJOURS_06
					, ligneRapportValidation);
			
			/* remplissage de la liste des contrôles. */
			this.controlesList.add(ligneRapportValidation);
			
			resultat = true;
			
		}
				
		return resultat;
		

	} // Fin de validerRGInterroNbreJours06(...).__________________________
	
	
	
} // FIN DE LA CLASSE InterrogationMeteoValideur.----------------------------
