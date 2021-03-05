
/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.awt.*;

public class Hangman extends ConsoleProgram {

	private String theWord;
	private String progress;
	private int lives;
	private String usedSymbs;

	private HangmanCanvas canv;
	private HangmanLexicon lex;
	private RandomGenerator rgen = RandomGenerator.getInstance();

	public void init() {
		theWord = "";
		lives = 8;
		usedSymbs = "";
		canv = new HangmanCanvas();
		lex = new HangmanLexicon();
		add(canv);
		
	}

	public void run() {

	    setSize(1350,600);
		while (true) {
			resetGame();
			playGame();
			endGame();
		}
	}
	
	/** This part of code is responsible for reseting the game.Each time it 
	 * regenerates player lives, resets usedSymbs (the symbols that were typed),
	 *  it gets new word from lexicon and resets graphic part of code.
	 * */
	private void resetGame() {
		lives = 8;
		usedSymbs = "";
		setWords();
		setCanvas();
		println("\n \n  WELCOME TO HANGMAN!");

	}

	/** This is main part of game. While player has more lives or he did not guessed word yet each time 
	 * 	it updates graphic and console versions of code than asks for next letter until player enters
	 * 	symbol of a-z or A-Z type. After that it checks either secret word includes entered letter or 
	 * 	not.
	 * */
	private void playGame() {
		while (!progress.equals(theWord) && lives > 0) {
			writeNow();
			char symb = getNextTry();
			checkSymbol(symb);
		}
	}

	/** endGame method ends game by checking either player has won or lost. Which after it waits 
	 * for input, and after that game repeats. */
	private void endGame() {
		updateCanvas();
		String endGame;
		if (lives > 0) {
			endGame = "Congratulations! You have won! ";
		} else
			endGame = "Now you are complitely hung. Better luck next time! ";
		println(endGame + "The secret word was: " + theWord);
		pause(2000);
		canv.end(lives);
		int again = readInt("Type 1 to play again");
		while (again!=1) { 
			again = readInt();
		}
	}

	/* This method gets letter and returns capital one*/
	private char toCapital(char y) {
		if (Character.isLowerCase(y))
			y = Character.toUpperCase(y);
		return y;
	}

	/* This part updates game progress each time symbol is entered */
	private void writeNow() {
		updateCanvas();
		println("\nYour word: " + progress);
		if (usedSymbs.length() > 0)
			println("You have tried these symbols: " + usedSymbs);
		if (lives > 1) {
			println("You have " + lives + " lives ^_^");
		} else
			println("You have last try :( Choose wisely");
	}
	
	/* updateCanvas method updates  graphic part of code*/
	private void updateCanvas() {
		canv.noteIncorrectGuess(usedSymbs, lives);
		canv.displayWord(progress, theWord, lives);
	}

	/* This method checks if tried symbol matches any char in secret word. */
	private void checkSymbol(char x) {
		boolean count = false;
		for (int i = 0; i < theWord.length(); i++) {
			if (theWord.charAt(i) == x) {
				progress = progress.substring(0, i) + x + progress.substring(i + 1);
				count = true;
			}
		}
		if (count) {
			println("Nice! There were " + count + " " + x + " in the word!");
		} else {
			println("Welp... There are not such symbols.");
			lives--;
		}
		if (usedSymbs.length() > 0)
			usedSymbs += ", " + x;
		else
			usedSymbs += x;
	}

	/* getNextTry method reads input text while it matches needed type . */
	private char getNextTry() {
		boolean checker = true;
		String symbol = "";
		while (checker) {

			symbol = readLine("Your guess: ");
			checker = checkInput(symbol);
		}
		char z = symbol.charAt(0);
		z = toCapital(z);
		return z;
	}

	/*
	 * This method checks if input text is only one char length, and is A-Z or a-z
	 * type symbol.
	 */
	private boolean checkInput(String a) {
		if (a.length() == 1
				&& ((a.charAt(0) >= 'A' && a.charAt(0) <= 'Z') || (a.charAt(0) >= 'a' && a.charAt(0) <= 'z'))) {

			if (wasAlready(a)) {
				println("You already tried this symbol, try another one");
				return true;
			}
			return false;
		} else {
			println("Please enter only 1 symbol a-z type.");
			return true;
		}
	}

	/* This method checks if input symbol was already entered. */
	private boolean wasAlready(String a) {
		for (int i = 0; i < usedSymbs.length(); i++) {
			if (usedSymbs.charAt(i) == toCapital(a.charAt(0))) {
				return true;

			}
		}

		return false;
	}

	/*
	 * Method generates random word from lexicon and saves it as "theWord". Than
	 * creates as much "_" as the length of theWord is and saves it in "progress"
	 */
	private void setWords() {
		int index = rgen.nextInt(0, lex.getWordCount() - 1);
		theWord = lex.getWord(index);
		progress = "";
		for (int i = 0; i < theWord.length(); i++) {
			progress += "-";
		}
	}

	/* This method resets whole canvas and creates its again for new game. */
	private void setCanvas() {
		canv.reset();
		canv.displayWord(progress, theWord, lives);
	}

}
