package de.hegmanns.adventofcode2018.common.data;

/**
 * Returns a special Data, typically an aggregate created for the special task which contains all needed data.
 * 
 * Typically the provider converts the input string or string-array in the needed data-structure.
 * 
 * @author B. Hegmanns
 *
 * @param <T>
 */
public interface DataProvider<T> {

	/**
	 * Returns the needed Data.
	 * 
	 * @return the needed data
	 */
	public T getData();
}
