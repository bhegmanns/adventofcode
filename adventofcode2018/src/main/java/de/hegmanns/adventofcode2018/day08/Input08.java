package de.hegmanns.adventofcode2018.day08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import de.hegmanns.adventofcode2018.day03.Input03;

public class Input08 {

	public static String getString() {
		InputStream inputStream = Input08.class.getClassLoader().getResourceAsStream("de/hegmanns/adventofcode2018/day08/input08.dat");
		if (inputStream != null) {
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
			String line = null;
			try {
				line = bufferedReader.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return line;
		}else {
			throw new RuntimeException("keine Datei gelesen");
		}
	}
}
