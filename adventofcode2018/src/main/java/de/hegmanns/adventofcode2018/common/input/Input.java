package de.hegmanns.adventofcode2018.common.input;

/**
 * Common input for a special task. Typically the type of the read datas. Should be a String or Integer, or a set of them.
 * 
 * @author B. Hegmanns
 *
 * @param <T> the type for the special task
 */
public interface Input<T> {

	/**
	 * 
	 * @return
	 */
	public T getInput();
}
