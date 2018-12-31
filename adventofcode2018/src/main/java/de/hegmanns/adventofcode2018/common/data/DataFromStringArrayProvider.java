package de.hegmanns.adventofcode2018.common.data;

import de.hegmanns.adventofcode2018.common.input.StringArrayInput;

/**
 * Special provider for data {@link DataProvider} which converts input string-array in the needed data structure.
 * 
 * @author B. Hegmanns
 *
 * @param <T>
 */
public abstract class DataFromStringArrayProvider<T> implements DataProvider<T>{

	private StringArrayInput stringArrayInput;
	private String[] input = null;
	
	/**
	 * @see DataProvider#getData()
	 */
	public abstract T getData();
	
	public DataFromStringArrayProvider(StringArrayInput stringArrayInput) {
		this.stringArrayInput = stringArrayInput;
		
	}

	protected final String[] getInput() {
		synchronized (stringArrayInput) {
			if (input == null) {
				input = stringArrayInput.getInput();
			}
		}
		
		return input;
	}
	
}
