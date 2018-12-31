package de.hegmanns.adventofcode2018.common;

/**
 * A class which presents the solution of a task.
 * 
 * @author B. Hegmanns
 *
 * @param <T>
 */
public interface Solution<T> {

	/**
	 * The solve-method solve the task and present the solution.
	 * 
	 * @return
	 */
	public T solve();
}
