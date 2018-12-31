package de.hegmanns.adventofcode2018.common.input;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import de.hegmanns.adventofcode2018.common.streamprovider.InputStreamProvider;

/**
 * Implementation of {@link StringArrayInput. Read all lines of a given input (Stream) and returns them.
 * 
 * @author B. Hegmanns
 *
 */
public class DefaultStringArrayInput implements StringArrayInput {

	private InputStreamProvider inputStreamProvider;

	public DefaultStringArrayInput(InputStreamProvider inputStreamProvider) {
		this.inputStreamProvider = inputStreamProvider;
	}

	@Override
	public String[] getInput() {
		InputStream inputStream = this.inputStreamProvider.getInputStream();
		throwIfNull(inputStream);
		
		List<String> input = readLines(inputStream);
		close(inputStream);
		
		return input.toArray(new String[input.size()]);
	}

	private List<String> readLines(InputStream inputStream) {
		List<String> input = new ArrayList<>();
		String currentLine = null;
		try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))){
			while((currentLine = bufferedReader.readLine()) != null) {
				input.add(currentLine);
			}
		} catch (IOException e1) {
			throw new RuntimeException("Exception while reading input stream: " + e1.getMessage());
		}
		return input;
	}
	
	private void close(InputStream inputStream) {
		try {
			inputStream.close();
		}catch(Exception e) {
			// nothing to do
		}
	}

	private void throwIfNull(InputStream inputStream) {
		if (inputStream == null) {
			throw new RuntimeException("keine Datei gelesen");
		}
	}
}
