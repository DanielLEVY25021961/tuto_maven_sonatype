package levy.daniel.application.apptechnic.exceptions.technical.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.apptechnic.exceptions.technical.AbstractTechnicalException;

/**
 * class BundleManquantException :<br/>
 * Hérite de AbstractTechnicalException.<br/>
 * Typée pour signaler que le Bundle n'a pu être instancié
 * car le properties est manquant.<br/>
 * Utilise la liste static listeExceptions de
 * AbstractTechnicalException pour y logger les
 * messages d'Exception.<br/>
 * <br/>
 *
 * - Exemple d'utilisation :<br/>
 *<br/>
 *
 * - Mots-clé :<br/>
 * Bundle, ResourceBundle, .properties
 *<br/>
 *
 * - Dépendances :<br/>
 *<br/>
 *
 * @author Levy Lévy
 * @version 1.0
 * @since 23 mai 2011
 *
 */
public class BundleManquantException extends AbstractTechnicalException {

	// ************************ATTRIBUTS************************************/

	/**
	 * serialVersionUID : long :<br/>
	 * .<br/>
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG = LogFactory
			.getLog(BundleManquantException.class);

	// *************************METHODES************************************/
	

	/**
	 * method CONSTRUCTEUR BundleManquantException() :<br/>
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * Permet de construire une BundleManquantException
	 * sans message ni propagation d'exception cause.<br/>
	 */
	public BundleManquantException() {
		super();
	} // Fin de BundleManquantException()._________________________________
	


	/**
	 * method CONSTRUCTEUR BundleManquantException(String pMessage) :<br/>
	 * Constructeur d'arité 1.<br/>
	 * Permet de construire une BundleManquantException
	 * qui propage un message.<br/>
	 * <br/>
	 *
	 * @param pMessage : String : le message à propager.<br/>
	 */
	public BundleManquantException(final String pMessage) {
		super(pMessage);
	} // Fin de CONSTRUCTEUR BundleManquantException(
	// String pMessage).___________________________________________________
	
	
	
	/**
	 * method CONSTRUCTEUR BundleManquantException(Throwable pCause) :<br/>
	 * Constructeur d'arité 1.<br/>
	 * Permet de construire une BundleManquantException
	 * qui propage un Throwable qui a causé la présente
	 * exception.<br/>
	 * <br/>
	 *
	 * @param pCause : Throwable : l'exception qui a
	 * causé la présente et que l'on veut propager.<br/>
	 */
	public BundleManquantException(final Throwable pCause) {
		super(pCause);
	} // Fin de CONSTRUCTEUR BundleManquantException(
	// Throwable pCause).__________________________________________________
	
	
	
	/**
	 * method CONSTRUCTEUR BundleManquantException(
	 * String pMessage
	 * , Throwable pCause) :<br/>
	 * Constructeur d'arité 2.<br/>
	 * Permet de construire une BundleManquantException
	 * qui propage :<br/>
	 * - un message,<br/>
	 * - un Throwable qui a causé la présente
	 * exception.<br/>
	 * <br/>
	 *
	 * @param pMessage : String : le message à propager.<br/>
	 * @param pCause : Throwable : l'exception qui a
	 * causé la présente et que l'on veut propager.<br/>
	 */
	public BundleManquantException(
			final String pMessage, final Throwable pCause) {
		super(pMessage, pCause);
	} // Fin de CONSTRUCTEUR BundleManquantException(
	 // String pMessage, Throwable pCause).________________________________

	
	
	/**
	 * method CONSTRUCTEUR BundleManquantException(
	 * String pMessage
	 * , Throwable pCause
	 * , List<String> pListeExceptions) :<br/>
	 * Constructeur d'arité 3.<br/>
	 * Permet de construire une BundleManquantException
	 * qui propage :<br/>
	 * - un message,<br/>
	 * - un Throwable qui a causé la présente
	 * exception.<br/>
	 * - Une liste de String susceptible d'encapsuler
	 * le message de la présente Exception.<br/>
	 * <br/>
	 *
	 * @param pMessage : String : le message à propager.<br/>
	 * @param pCause : Throwable : l'exception qui a
	 * causé la présente et que l'on veut propager.<br/>
	 * @param pListeExceptions : Liste dans laquelle on veut
	 * insérer le message de la présente Exception.<br/>
	 */
	public BundleManquantException(
			final String pMessage
				, final Throwable pCause
					, final List<String> pListeExceptions) {
		
		super(pMessage, pCause, pListeExceptions);
		
	} // Fin de CONSTRUCTEUR BundleManquantException(
	 // String pMessage
	 // , Throwable pCause
	 // , List<String> pListeExceptions).__________________________________
	

	
} // FIn DE LA CLASSE BundleManquantException.-------------------------------
