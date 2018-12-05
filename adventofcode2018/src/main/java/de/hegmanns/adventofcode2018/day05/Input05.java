package de.hegmanns.adventofcode2018.day05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import de.hegmanns.adventofcode2018.day03.Input03;

public class Input05 {

	public static String getString() {
		InputStream inputStream = Input03.class.getClassLoader().getResourceAsStream("de/hegmanns/adventofcode2018/day05/input05.dat");
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
