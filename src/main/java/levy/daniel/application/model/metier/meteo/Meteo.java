package levy.daniel.application.model.metier.meteo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * class Meteo :<br/>
 * .<br/>
 * <br/>
 *
 * - Exemple d'utilisation :<br/>
 * <br/>
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
public class Meteo {

	// ************************ATTRIBUTS************************************/

	/**
	 * city : String :<br/>
	 * Ville.<br/>
	 */
	private String city;


	/**
	 * region : String :<br/>
	 * Région de la ville (Ile de France, ...).<br/>
	 */
	private String region;


	/**
	 * country : String :<br/>
	 * Pays de la ville.<br/>
	 */
	private String country;


	/**
	 * condition : String :<br/>
	 * Conditions méteo du jour pour la ville.<br/>
	 */
	private String condition;


	/**
	 * temp : String :<br/>
	 * Temperature du jour pour la ville.<br/>
	 */
	private String temp;


	/**
	 * chill : String :<br/>
	 * Température ressentie du jour pour la ville.<br/>
	 */
	private String chill;


	/**
	 * humidity : String :<br/>
	 * Humidité du jour pour la ville.<br/>
	 */
	private String humidity;
	
	

	/**
	 * LOG : Log : Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG = LogFactory.getLog(Meteo.class);



	
	// *************************METHODES************************************/
	

	
	 /**
	 * method CONSTRUCTEUR Meteo() :<br/>
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * <br/>
	 */
	public Meteo() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
	
	/**
	 * method getCity() :<br/>
	 * Getter de la Ville.<br/>
	 * <br/>
	 *
	 * @return city : String.<br/>
	 */
	public String getCity() {	
		return this.city;
	} // Fin de getCity()._________________________________________________


	
	/**
	* method setCity(
	* String pCity) :<br/>
	* Setter de la Ville.<br/>
	* <br/>
	*
	* @param pCity : String : valeur à passer à city.<br/>
	*/
	public void setCity(
			final String pCity) {
		this.city = pCity;
	} // Fin de setCity(...).______________________________________________


	
	/**
	 * method getRegion() :<br/>
	 * Getter de la Région de la ville (Ile de France, ...).<br/>
	 * <br/>
	 *
	 * @return region : String.<br/>
	 */
	public String getRegion() {	
		return this.region;
	} // Fin de getRegion()._______________________________________________


	
	/**
	* method setRegion(
	* String pRegion) :<br/>
	* Setter de la Région de la ville (Ile de France, ...).<br/>
	* <br/>
	*
	* @param pRegion : String : valeur à passer à region.<br/>
	*/
	public void setRegion(
			final String pRegion) {	
		this.region = pRegion;
	} // Fin de setRegion(...).____________________________________________


	
	/**
	 * method getCountry() :<br/>
	 * Getter du Pays de la ville.<br/>
	 * <br/>
	 *
	 * @return country : String.<br/>
	 */
	public String getCountry() {	
		return this.country;
	} // Fin de getCountry().______________________________________________


	
	/**
	* method setCountry(
	* String pCountry) :<br/>
	* Setter du Pays de la ville.<br/>
	* <br/>
	*
	* @param pCountry : String : valeur à passer à country.<br/>
	*/
	public void setCountry(
			final String pCountry) {	
		this.country = pCountry;
	} // Fin de setCountry(...).___________________________________________


	
	/**
	 * method getCondition() :<br/>
	 * Getter des Conditions méteo du jour pour la ville.<br/>
	 * <br/>
	 *
	 * @return condition : String.<br/>
	 */
	public String getCondition() {	
		return this.condition;
	} // Fin de getCondition().____________________________________________


	
	/**
	* method setCondition(
	* String pCondition) :<br/>
	* Setter des Conditions méteo du jour pour la ville.<br/>
	* <br/>
	*
	* @param pCondition : String : valeur à passer à condition.<br/>
	*/
	public void setCondition(
			final String pCondition) {	
		this.condition = pCondition;
	} // Fin de setCondition(...)._________________________________________


	
	/**
	 * method getTemp() :<br/>
	 * Getter de la Temperature du jour pour la ville.<br/>
	 * <br/>
	 *
	 * @return temp : String.<br/>
	 */
	public String getTemp() {	
		return this.temp;
	} // Fin de getTemp()._________________________________________________


	
	/**
	* method setTemp(
	* String pTemp) :<br/>
	* Setter de la Temperature du jour pour la ville.<br/>
	* <br/>
	*
	* @param pTemp : String : valeur à passer à temp.<br/>
	*/
	public void setTemp(
			final String pTemp) {	
		this.temp = pTemp;
	} // Fin de setTemp(...).______________________________________________


	
	/**
	 * method getChill() :<br/>
	 * Getter de la Température ressentie du jour pour la ville.<br/>
	 * <br/>
	 *
	 * @return chill : String.<br/>
	 */
	public String getChill() {	
		return this.chill;
	} // Fin de getChill().________________________________________________


	
	/**
	* method setChill(
	* String pChill) :<br/>
	* Setter de la Température ressentie du jour pour la ville.<br/>
	* <br/>
	*
	* @param pChill : String : valeur à passer à chill.<br/>
	*/
	public void setChill(
			final String pChill) {	
		this.chill = pChill;
	} // Fin de setChill(...)._____________________________________________


	
	/**
	 * method getHumidity() :<br/>
	 * Getter de l'Humidité du jour pour la ville.<br/>
	 * <br/>
	 *
	 * @return humidity : String.<br/>
	 */
	public String getHumidity() {	
		return this.humidity;
	}


	
	/**
	* method setHumidity(
	* String pHumidity) :<br/>
	* Setter de l'Humidité du jour pour la ville.<br/>
	* <br/>
	*
	* @param pHumidity : String : valeur à passer à humidity.<br/>
	*/
	public void setHumidity(
			final String pHumidity) {	
		this.humidity = pHumidity;
	} // Fin de setHumidity(...).__________________________________________

	

} // FIN DE LA CLASSE Meteo.-------------------------------------------------
