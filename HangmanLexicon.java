
/*
 * File: HangmanLexicon.java
 * -------------------------
 * This file contains a stub implementation of the HangmanLexicon
 * class that you will reimplement for Part III of the assignment.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import acm.util.*;

public class HangmanLexicon {

	private ArrayList<String> ListOfWords = new ArrayList<String>();

	/** Returns the number of words in the lexicon. */
	public int getWordCount() {
		return ListOfWords.size();
	}

	public HangmanLexicon() {
		// adds the individual words in the file to the array list
		try {
			BufferedReader hangmanWords = new BufferedReader(new FileReader("HangmanLexicon.txt"));
			while (true) {
				String line = hangmanWords.readLine();
				if (line == null)
					break;
				ListOfWords.add(line);
			}
			hangmanWords.close();
		} catch (IOException ex) {
			throw new ErrorException(ex);
		}
	}

	/** Returns the word at the specified index. */
	public String getWord(int index) {
		return ListOfWords.get(index);
	}
}
