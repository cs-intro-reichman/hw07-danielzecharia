
public class SpellChecker {


	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);
	}

	public static String tail(String str) {
		// Your code goes here
		return str.substring(1,str.length());
	}

	public static int levenshtein(String word1, String word2) {
		// Your code goes here
		word1 = word1.toLowerCase();
		word2 = word2.toLowerCase();

		if (word1.equals("")) return word2.length();
		if (word2.equals("")) return word1.length();
		if (word1.charAt(0)==word2.charAt(0)) return levenshtein(tail(word1), tail(word2));

		int min = Math.min(levenshtein(tail(word1),word2), levenshtein(word1, tail(word2)));
		min = Math.min(min, levenshtein(tail(word1), tail(word2)));
		return (min+1);
	}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];
		In in = new In(fileName);
		for (int i=0; i<3000;i++)
			dictionary[i]= in.readLine();

		return dictionary;
	}

	public static String spellChecker(String word, int threshold, String[] dictionary) {
		// Your code goes here
		String newWord = word;
		int min = threshold + 1;
		for(int i=0 ; i<dictionary.length ; i++)
			if(levenshtein(word, dictionary[i])<min) {
				min = levenshtein(word, dictionary[i]);
				newWord = dictionary[i];
			}
		return newWord;
	}

}
