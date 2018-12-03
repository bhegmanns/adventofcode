package de.hegmanns.adventofcode2018.day02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SolutionAppDay02Task02 {

	public static void main(String[] args) {
		List<String> strings = new ArrayList<>(Arrays.asList(Input02.STRINGS));
		boolean found = false;
		String code = null;
		do {
			String s = strings.remove(0);
			for (String string : strings) {
				code = findEqualCode(s, string);
				if (!code.isEmpty() && code.length() == string.length()-1) {
					found = true;
					break;
				}
			}
		}while(!strings.isEmpty() && !found);
		if (found) {
			System.out.println("FOUND: " + code);
		}else {
			System.out.println("NOT FOUND ...");
		}
	}

	private static String findEqualCode(String s, String string) {
		String code = "";
		
		char[] sToChars = s.toCharArray();
		char[] stringToChars = string.toCharArray();
		
		for (int i = 0 ; i < sToChars.length ; i++) {
			if (sToChars[i]==stringToChars[i]) {
				code += sToChars[i];
			}
		}
		return code;
	}

	
	
}
