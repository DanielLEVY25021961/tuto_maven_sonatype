package levy.daniel.application.model.metier.meteo;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.model.metier.user.User;

/**
 * class InterrogationMeteoString :<br/>
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
 * @since 25 sept. 2017
 *
 */
public class InterrogationMeteo  
		implements Serializable, Comparable<InterrogationMeteo>, Cloneable {

	// ************************ATTRIBUTS************************************/
	
	
	/**
	 * serialVersionUID : long :<br/>
	 * .<br/>
	 */
	private static final long serialVersionUID = 1L;


	/**
	 * id : Long :<br/>
	 * ID en base.<br/>
	 */
	private Long id;

	
	/**
	 * user : User :<br/>
	 * User de l'application.<br/>
	 */
	private User user;

	
	
	/**
	 * date : LocalDateTime :<br/>
	 * Date d'interrogation du Web Service Meteo de Yahoo.<br/>
	 */
	private LocalDateTime date;
	
		
	/**
	 * pays : String :<br/>
	 * Pays de la Ville du bulletin météo.<br/>
	 */
	private String pays;

	
	/**
	 * ville : String :<br/>
	 * Ville du bulletin météo.<br/>
	 */
	private String ville;

	
	/**
	 * nbreJours : String :<br/>
	 * Nombre de jours de prévision météo demandé 
	 * SOUS FORME D'INTEGER.<br/>
	 */
	private Integer nbreJours;

	
	/**
	 * unites : String :<br/>
	 * Unités (métriques ou impériales) utilisées 
	 * dans le bulletin météo.<br/>
	 */
	private String unites;
	

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG 
		= LogFactory.getLog(InterrogationMeteo.class);


	// *************************METHODES************************************/

	
	
	 /**
	 * method CONSTRUCTEUR InterrogationMeteo() :<br/>
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * <br/>
	 */
	public InterrogationMeteo() {
		
		this(null, null, null, null, null, null, null);
		
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
	
	 /**
	 * method CONSTRUCTEUR InterrogationMeteo() :<br/>
	 * CONSTRUCTEUR COMPLET.<br/>
	 * SANS ID EN BASE.<br/>
	 * <br/>
	 *
	 * @param pUser : User : User de l'application.<br/>
	 * @param pDate : LocalDateTime : Date d'interrogation 
	 * du Web Service Meteo de Yahoo.<br/>
	 * @param pVille : String : Ville du bulletin météo.<br/>
	 * @param pPays : String : Pays de la Ville du bulletin météo.<br/>
	 * @param pNbreJours : Integer : Nombre de jours de prévision 
	 * météo demandé SOUS FORME D'INTEGER.<br/>
	 * @param pUnites : String : Unités (métriques ou impériales) 
	 * utilisées dans le bulletin météo.<br/>
	 */
	public InterrogationMeteo(
			final User pUser
				, final LocalDateTime pDate
					, final String pVille, final String pPays
						, final Integer pNbreJours
							, final String pUnites) {
		
		this(null, pUser, pDate, pVille, pPays, pNbreJours, pUnites);
		
	} // Fin du CONSTRUCTEUR COMPLET.______________________________________
	
	
	
	 /**
	 * method CONSTRUCTEUR InterrogationMeteo() :<br/>
	 * CONSTRUCTEUR COMPLET BASE.<br/>
	 * AVEC ID EN BASE.<br/>
	 * <br/>
	 * 
	 * @param pId : Long : ID en base.<br/>
	 * @param pUser : User : User de l'application.<br/>
	 * @param pDate : LocalDateTime : Date d'interrogation 
	 * du Web Service Meteo de Yahoo.<br/>
	 * @param pVille : String : Ville du bulletin météo.<br/>
	 * @param pPays : String : Pays de la Ville du bulletin météo.<br/>
	 * @param pNbreJours : Integer : Nombre de jours de prévision 
	 * météo demandé SOUS FORME D'INTEGER.<br/>
	 * @param pUnites : String : Unités (métriques ou impériales) 
	 * utilisées dans le bulletin météo.<br/>
	 */
	public InterrogationMeteo(
			final Long pId
				, final User pUser
					, final LocalDateTime pDate
						, final String pVille, final String pPays
							, final Integer pNbreJours
								, final String pUnites) {
		
		super();
		
		this.id = pId;
		this.user = pUser;
		this.date = pDate;
		this.ville = pVille;
		this.pays = pPays;
		this.nbreJours = pNbreJours;
		this.unites = pUnites;
		
	} // Fin de CONSTRUCTEUR COMPLET.______________________________________


		
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int hashCode() {

		final int prime = 31;
		
		int result = 1;
		
		result = prime * result 
				+ ((this.user == null) ? 0 : this.user.hashCode());		
		result = prime * result 
				+ ((this.date == null) ? 0 : this.date.hashCode());
		result = prime * result 
				+ ((this.pays == null) ? 0 : this.pays.hashCode());
		result = prime * result 
				+ ((this.ville == null) ? 0 : this.ville.hashCode());
		result = prime * result 
				+ ((this.nbreJours == null) ? 0 : this.nbreJours.hashCode());
		result = prime * result 
				+ ((this.unites == null) ? 0 : this.unites.hashCode());
		
		return result;
		
	} // Fin de hashCode().________________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean equals(
			final Object pObj) {

		if (this == pObj) {
			return true;
		}
		
		if (pObj == null) {
			return false;
		}
		
		if (!(pObj instanceof InterrogationMeteo)) {
			return false;
		}
		
		final InterrogationMeteo other 
			= (InterrogationMeteo) pObj;
		
		/* user. */
		if (this.user == null) {
			if (other.user != null) {
				return false;
			}
		}
		else if (!this.user.equals(other.user)) {
			return false;
		}
		
		/* date. */
		if (this.date == null) {
			if (other.date != null) {
				return false;
			}
		}
		else if (!this.date.equals(other.date)) {
			return false;
		}

		/* pays. */
		if (this.pays == null) {
			if (other.pays != null) {
				return false;
			}
		}
		else if (!this.pays.equals(other.pays)) {
			return false;
		}

		/* ville. */
		if (this.ville == null) {
			if (other.ville != null) {
				return false;
			}
		}
		else if (!this.ville.equals(other.ville)) {
			return false;
		}

		/* nbreJours. */
		if (this.nbreJours == null) {
			if (other.nbreJours != null) {
				return false;
			}
		}
		else if (!this.nbreJours.equals(other.nbreJours)) {
			return false;
		}
		
		/* unites. */
		if (this.unites == null) {
			if (other.unites != null) {
				return false;
			}
		}
		else if (!this.unites.equals(other.unites)) {
			return false;
		}
		
		return true;
		
	} // Fin de equals(...)._______________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int compareTo(
			final InterrogationMeteo pInterrog) {

		if (this == pInterrog) {
			return 0;
		}

		if (pInterrog == null) {
			return -1;
		}

		int compareUser = 0;
		int compareDate = 0;
		int comparePays = 0;
		int compareVille = 0;
		int compareNbreJours = 0;
		int compareUnites = 0;
		
		/* user. */
		if (this.getUser() == null) {
			if (pInterrog.getUser() != null) {
				return +1;
			}
		} else {
			if (pInterrog.getUser() == null) {
				return -1;
			}
		}
		
		compareUser 
			= this.getUser().compareTo(pInterrog.getUser());
		
		if (compareUser != 0) {
			return compareUser;
		}
		
		/* date. */
		if (this.getDate() == null) {
			if (pInterrog.getUser() != null) {
				return +1;
			}
		} else {
			if (pInterrog.getUser() == null) {
				return -1;
			}
		}
		
		compareDate = this.getDate().compareTo(pInterrog.getDate());
		
		if (compareDate != 0) {
			return compareDate;
		}
		
		/* pays. */
		if (this.getPays() == null) {
			if (pInterrog.getPays() != null) {
				return +1;
			}
		} else {
			if (pInterrog.getPays() == null) {
				return -1;
			}
		}
		
		comparePays 
		= this.getPays().compareToIgnoreCase(pInterrog.getPays());
		
		if (comparePays != 0) {
			return comparePays;
		}
		
		/* ville. */
		if (this.getVille() == null) {
			if (pInterrog.getVille() != null) {
				return +1;
			}
		} else {
			if (pInterrog.getVille() == null) {
				return -1;
			}
		}
		
		compareVille = this.getVille().compareTo(pInterrog.getVille());
		
		if (compareVille != 0) {
			return compareVille;
		}
		
		/* nbreJours. */
		if (this.getNbreJours() == null) {
			if (pInterrog.getNbreJours() != null) {
				return +1;
			}
		} else {
			if (pInterrog.getNbreJours() == null) {
				return -1;
			}
		}
		
		compareNbreJours 
		= this.getNbreJours().compareTo(pInterrog.getNbreJours());
		
		if (compareNbreJours != 0) {
			return compareNbreJours;
		}
		
		/* unites. */
		if (this.getUnites() == null) {
			if (pInterrog.getUnites() != null) {
				return +1;
			}
			
			return 0;
		}
		
		if (pInterrog.getUnites() == null) {
			return -1;
		}
		
		compareUnites = this.getUnites().compareTo(pInterrog.getUnites());
		
		return compareUnites;
				
	} // Fin de compareTo(...).____________________________________________
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final InterrogationMeteo clone() 
			throws CloneNotSupportedException {
		
		final InterrogationMeteo interroClone 
			= (InterrogationMeteo) super.clone();
		
		interroClone.setId(this.getId());
		
		/* Clonage profond du User. */
		User userClone = null;
		if (this.getUser() != null) {
			userClone = this.getUser().clone();
		}
		interroClone.setUser(userClone);
		
		interroClone.setDate(this.getDate());
		interroClone.setPays(this.getPays());
		interroClone.setVille(this.getVille());
		interroClone.setNbreJours(this.getNbreJours());
		interroClone.setUnites(this.getUnites());
		
		return interroClone;
		
	} // Fin de clone().___________________________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String toString() {

		final StringBuilder builder = new StringBuilder();
		
		builder.append("InterrogationMeteo [");
		
		if (this.id != null) {
			builder.append("id=");
			builder.append(this.id);
			builder.append(", ");
		}
		if (this.user != null) {
			builder.append("user=");
			builder.append(this.user.toString());
			builder.append(", ");
		}
		if (this.date != null) {
			builder.append("date=");
			builder.append(this.date);
			builder.append(", ");
		}
		if (this.pays != null) {
			builder.append("pays=");
			builder.append(this.pays);
			builder.append(", ");
		}
		if (this.ville != null) {
			builder.append("ville=");
			builder.append(this.ville);
			builder.append(", ");
		}
		if (this.nbreJours != null) {
			builder.append("nbreJours=");
			builder.append(this.nbreJours);
			builder.append(", ");
		}
		if (this.unites != null) {
			builder.append("unites=");
			builder.append(this.unites);
		}
		
		builder.append(']');
		
		return builder.toString();
		
	} // Fin de toString().________________________________________________



	/**
	 * method getId() :<br/>
	 * Getter de l'ID en base.<br/>
	 * <br/>
	 *
	 * @return id : Long.<br/>
	 */
	public Long getId() {	
		return this.id;
	} // Fin de getId().___________________________________________________



	/**
	* method setId(
	* Long pId) :<br/>
	* Setter de l'ID en base.<br/>
	* <br/>
	*
	* @param pId : Long : valeur à passer à id.<br/>
	*/
	public void setId(
			final Long pId) {	
		this.id = pId;
	} // Fin de setId(...).________________________________________________



	/**
	 * method getUser() :<br/>
	 * Getter du User de l'application.<br/>
	 * <br/>
	 *
	 * @return user : User.<br/>
	 */
	public final User getUser() {	
		return this.user;
	} // Fin de getUser()._________________________________________________



	/**
	* method setUser(
	* User pUser) :<br/>
	* Setter du User de l'application.<br/>
	* <br/>
	*
	* @param pUser : User : valeur à passer à user.<br/>
	*/
	public final void setUser(
			final User pUser) {	
		this.user = pUser;
	} // Fin de setUser(...).______________________________________________


	
	
	/**
	 * method getDate() :<br/>
	 * Getter de la Date d'interrogation 
	 * du Web Service Meteo de Yahoo.<br/>
	 * <br/>
	 *
	 * @return date : LocalDateTime.<br/>
	 */
	public LocalDateTime getDate() {	
		return this.date;
	} // Fin de getDate()._________________________________________________



	
	/**
	* method setDate(
	* LocalDateTime pDate) :<br/>
	* Setter de la Date d'interrogation 
	* du Web Service Meteo de Yahoo.<br/>
	* <br/>
	*
	* @param pDate : LocalDateTime : valeur à passer à date.<br/>
	*/
	public void setDate(
			final LocalDateTime pDate) {	
		this.date = pDate;
	} // Fin de setDate(...).______________________________________________


	
	/**
	 * method getPays() :<br/>
	 * Getter du Pays de la Ville du bulletin météo.<br/>
	 * <br/>
	 *
	 * @return pays : String.<br/>
	 */
	public final String getPays() {	
		return this.pays;
	} // Fin de getPays()._________________________________________________


	
	/**
	* method setPays(
	* String pPays) :<br/>
	* Setter du Pays de la Ville du bulletin météo.<br/>
	* <br/>
	*
	* @param pPays : String : valeur à passer à pays.<br/>
	*/
	public final void setPays(
			final String pPays) {	
		this.pays = pPays;
	} // Fin de setPays(...).______________________________________________


	
	/**
	 * method getVille() :<br/>
	 * Getter de la Ville du bulletin météo.<br/>
	 * <br/>
	 *
	 * @return ville : String.<br/>
	 */
	public final String getVille() {	
		return this.ville;
	} // Fin de getVille().________________________________________________


	
	/**
	* method setVille(
	* String pVille) :<br/>
	* Setter de la Ville du bulletin météo.<br/>
	* <br/>
	*
	* @param pVille : String : valeur à passer à ville.<br/>
	*/
	public final void setVille(
			final String pVille) {	
		this.ville = pVille;
	} // Fin de setVille(...)._____________________________________________


	
	/**
	 * method getNbreJours() :<br/>
	 * Getter du Nombre de jours de prévision météo demandé 
	 * SOUS FORME D'INTEGER.<br/>
	 * <br/>
	 *
	 * @return nbreJours : Integer.<br/>
	 */
	public final Integer getNbreJours() {	
		return this.nbreJours;
	} // Fin de getNbreJours().____________________________________________


	
	/**
	* method setNbreJours(
	* Integer pNbreJours) :<br/>
	* Setter du Nombre de jours de prévision météo demandé 
	* SOUS FORME D'INTEGER.<br/>
	* <br/>
	*
	* @param pNbreJours : Integer : valeur à passer à nbreJours.<br/>
	*/
	public final void setNbreJours(
			final Integer pNbreJours) {	
		this.nbreJours = pNbreJours;
	} // Fin de setNbreJours(...)._________________________________________


	
	/**
	 * method getUnites() :<br/>
	 * Getter des Unités (métriques ou impériales) utilisées 
	 * dans le bulletin météo.<br/>
	 * <br/>
	 *
	 * @return unites : String.<br/>
	 */
	public final String getUnites() {	
		return this.unites;
	} // Fin de getUnites()._______________________________________________


	
	/**
	* method setUnites(
	* String pUnites) :<br/>
	* Setter des Unités (métriques ou impériales) utilisées 
	* dans le bulletin météo.<br/>
	* <br/>
	*
	* @param pUnites : String : valeur à passer à unites.<br/>
	*/
	public final void setUnites(
			final String pUnites) {	
		this.unites = pUnites;
	} // Fin de setUnites(...).____________________________________________


	
	
} // FIN DE LA CLASSE InterrogationMeteoString.------------------------------
