package de.hegmanns.adventofcode2018.day05;

public class SolutionAppDay05Task01 {

	public static void main(String[] args) {
		String s = Input05.getString();
		System.out.println("Laenge : " + s.length());
		StringBuffer text = new StringBuffer(s);
		boolean reacted = true;
		while (reacted) {
			reacted = false;
			for (int i = 0; i < text.length() - 1; i++) {
				char a = text.charAt(i);
				char b = text.charAt(i + 1);
				if (isCapitalization(a, b)) {
					text.deleteCharAt(i);
					text.deleteCharAt(i);
					reacted = true;
					break;
				}
				
			}
		}
		
		System.out.println("rest string: " + text);
		System.out.println("rest length : " + text.length());
		
	}

	private static boolean isCapitalization(char a, char b) {
		boolean isLowerCaseA = Character.isLowerCase(a);
		if (isLowerCaseA) {
			return Character.toUpperCase(a) == b;
		}else {
			return Character.toLowerCase(a) == b;
		}
	}

}
