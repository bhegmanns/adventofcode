package de.hegmanns.adventofcode2018.day07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import de.hegmanns.adventofcode2018.day03.Input03;

public class Input07 {

	public static String[] getStrings() {
		InputStream inputStream = Input03.class.getClassLoader().getResourceAsStream("de/hegmanns/adventofcode2018/day07/input07.dat");
		if (inputStream != null) {
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
			List<String> input = new ArrayList<>();
			String line = null;
			try {
				while ((line = bufferedReader.readLine())!=null) {
					input.add(line);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Returns " + input.size() + " lines");
			return input.toArray(new String[input.size()]);
		}else {
			throw new RuntimeException("keine Datei gelesen");
		}
	}
}
