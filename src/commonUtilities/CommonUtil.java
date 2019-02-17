package commonUtilities;

/**
 * The Class CommonUtil holds methods that might be useful throughout the project
 */
public class CommonUtil {

	/**
     * is Integer checks if string param represents and integer.
     *
     * @param string the string
     * @return the boolean isValidInteger
     */
	public static boolean isInteger(String s) {
      boolean isValidInteger = false;
      try {
         Integer.parseInt(s);	 
         // s is a valid integer	 
         isValidInteger = true;
      }
      catch (NumberFormatException ex) {
         // s is not an integer
	      }	 
	  return isValidInteger;
	   }
	

	
}
