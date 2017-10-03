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
	 * Région.<br/>
	 */
	private String region;


	/**
	 * country : String :<br/>
	 * Pays.<br/>
	 */
	private String country;


	/**
	 * condition : String :<br/>
	 * Conditions méteo.<br/>
	 */
	private String condition;


	/**
	 * temp : String :<br/>
	 * Temperature.<br/>
	 */
	private String temp;


	/**
	 * chill : String :<br/>
	 * Température ressentie.<br/>
	 */
	private String chill;


	/**
	 * humidity : String :<br/>
	 * Humidité.<br/>
	 */
	private String humidity;
	
	

	/**
	 * LOG : Log : Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG = LogFactory.getLog(Meteo.class);



	
	// *************************METHODES************************************/
	

	
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
	 * Getter .<br/>
	 * <br/>
	 *
	 * @return region : String.<br/>
	 */
	public String getRegion() {
	
		return this.region;
	}


	
	/**
	* method setRegion(
	* String pRegion) :<br/>
	* .<br/>
	* <br/>
	*
	* @param pRegion : String : valeur à passer à region.<br/>
	*/
	public void setRegion(String pRegion) {
	
		this.region = pRegion;
	}


	
	/**
	 * method getCountry() :<br/>
	 * Getter .<br/>
	 * <br/>
	 *
	 * @return country : String.<br/>
	 */
	public String getCountry() {
	
		return this.country;
	}


	
	/**
	* method setCountry(
	* String pCountry) :<br/>
	* .<br/>
	* <br/>
	*
	* @param pCountry : String : valeur à passer à country.<br/>
	*/
	public void setCountry(String pCountry) {
	
		this.country = pCountry;
	}


	
	/**
	 * method getCondition() :<br/>
	 * Getter .<br/>
	 * <br/>
	 *
	 * @return condition : String.<br/>
	 */
	public String getCondition() {
	
		return this.condition;
	}


	
	/**
	* method setCondition(
	* String pCondition) :<br/>
	* .<br/>
	* <br/>
	*
	* @param pCondition : String : valeur à passer à condition.<br/>
	*/
	public void setCondition(String pCondition) {
	
		this.condition = pCondition;
	}


	
	/**
	 * method getTemp() :<br/>
	 * Getter .<br/>
	 * <br/>
	 *
	 * @return temp : String.<br/>
	 */
	public String getTemp() {
	
		return this.temp;
	}


	
	/**
	* method setTemp(
	* String pTemp) :<br/>
	* .<br/>
	* <br/>
	*
	* @param pTemp : String : valeur à passer à temp.<br/>
	*/
	public void setTemp(String pTemp) {
	
		this.temp = pTemp;
	}


	
	/**
	 * method getChill() :<br/>
	 * Getter .<br/>
	 * <br/>
	 *
	 * @return chill : String.<br/>
	 */
	public String getChill() {
	
		return this.chill;
	}


	
	/**
	* method setChill(
	* String pChill) :<br/>
	* .<br/>
	* <br/>
	*
	* @param pChill : String : valeur à passer à chill.<br/>
	*/
	public void setChill(String pChill) {
	
		this.chill = pChill;
	}


	
	/**
	 * method getHumidity() :<br/>
	 * Getter .<br/>
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
	* .<br/>
	* <br/>
	*
	* @param pHumidity : String : valeur à passer à humidity.<br/>
	*/
	public void setHumidity(String pHumidity) {
	
		this.humidity = pHumidity;
	}


	
	

} // FIN DE LA CLASSE Meteo.-------------------------------------------------
