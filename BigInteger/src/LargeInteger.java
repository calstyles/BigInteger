/**
 * An interface that will be used to implement methods for the different list classes
 * 
 * @author Caleb Styles
 * @version November 27, 2021
 */

public interface LargeInteger extends Comparable<Object>{
	
	/**
	 * 
	 * @param addNumbers the second value that will be added to the "this" value
	 * @return the result of adding the two numbers
	 */
	
	public LargeInteger add(LargeInteger addNumbers);
	
	/**
	 * 
	 * @param subNumbers the second value that will be subtracted from this value
	 * @return the result of subtracting the two numbers
	 */
	
	public LargeInteger subtract(LargeInteger subNumbers);
	
	/**
	 * 
	 * @return the negative value of the "this" value
	 */
	
	public LargeInteger negate();
	
	/**
	 * 
	 * @return the absolute value of the "this" value
	 */
	
	public LargeInteger abs();
	
	/**
	 * 
	 * @param maxNumbers the second value that will be compared to the "this" value
	 * @return the maximum value between the two LargeIntegers
	 */
	
	public LargeInteger max(LargeInteger maxNumbers);
	
	/**
	 * 
	 * @param minNumbers the second value that will be compared to the "this" value
	 * @return the minimum value between the two LargeIntegers
	 */
	
	public LargeInteger min(LargeInteger minNumbers);
	
	/**
	 * 
	 * @return the value of the state of the number; If negative then -1, if positive then 1, if 0 then 0.
	 */
	
	public int signum();	
	
}
