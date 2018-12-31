package de.hegmanns.adventofcode2018.common.streamprovider;

import java.io.InputStream;

/**
 * Provides an InputStream for reading the input.
 * 
 * @author B. Hegmanns
 *
 */
public interface InputStreamProvider extends StreamProvider{

	/**
	 * Returns an open InputStream.
	 * After reading, the given InputStream can be closed.
	 * 
	 * @return the asked InputStream
	 */
	public InputStream getInputStream();
	
	public static InputStreamProvider convert(InputStream inputStream) {
		return new InputStreamProvider() {

			@Override
			public InputStream getInputStream() {
				return inputStream;
			}};
	}
}
