package de.hegmanns.adventofcode2018.day10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class Input10 {
	
	public static List<Star> getStars(){
		List<Star> stars = new ArrayList<>();
		InputStream inputStream = Input10.class.getClassLoader().getResourceAsStream("de/hegmanns/adventofcode2018/day10/input10.dat");
		if (inputStream != null) {
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
			String line = null;
			try {
				while ((line = bufferedReader.readLine())!=null) {
					stars.add(convertToStar(line));
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return stars;
		}else {
			throw new RuntimeException("keine Datei gelesen");
		}
	}
	
	private static Star convertToStar(String line) {
		int startIndex = -1;
		int finalIndex = -1;
		startIndex = line.indexOf("<");
		finalIndex = line.indexOf(">");
		String positionString = line.substring(startIndex+1, finalIndex);
		startIndex = line.indexOf("<", finalIndex);
		finalIndex = line.indexOf(">", startIndex);
		String velocityString = line.substring(startIndex + 1, finalIndex);
		
		StringTokenizer positionTokenizer = new StringTokenizer(positionString, ", ");
		StringTokenizer velocityTokenizer = new StringTokenizer(velocityString, ", ");
		return new Star(Integer.parseInt(positionTokenizer.nextToken()), Integer.parseInt(positionTokenizer.nextToken()), Integer.parseInt(velocityTokenizer.nextToken()), Integer.parseInt(velocityTokenizer.nextToken()));
	}
}
