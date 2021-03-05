
/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import java.awt.Color;
import java.awt.Font;

import acm.graphics.*;

public class HangmanCanvas extends GCanvas {
	private GLabel incorrect;
	private GImage img;
	private GLabel lab = null;
	private static final int WORD_OFFSET = 15;

	/** Resets the display so that only the scaffold appears */
	public void reset() {
		removeAll();
		setBackground();
		setGuesses();

	}

	/**
	 * Updates the word on the screen to correspond to the current state of the
	 * game. The argument string shows what letters have been guessed so far;
	 * unguessed letters are indicated by hyphens.
	 */
	public void displayWord(String word, String end, int a) {
		if (lab != null) {
			remove(lab);
		}
		if (a!=0)lab = new GLabel(word);
		else lab = new GLabel (end);
		lab.setColor(Color.blue);
		lab.setFont(new Font("Ariel-40", Font.BOLD, 40));
		lab.setLocation((getWidth() - lab.getWidth()) / 2, 50);
		lab.setColor(Color.blue);
		lab.setFont(new Font("Ariel-40", Font.BOLD, 40));
		add(lab);
	}

	/**
	 * Updates the display to correspond to an incorrect guess by the user. Calling
	 * this method causes the next body part to appear on the scaffold and adds the
	 * letter to the list of incorrect guesses that appears at the bottom of the
	 * window.
	 */
	
	public void noteIncorrectGuess(String text, int lives) {
		if (incorrect != null)
			remove(incorrect);
		incorrect = new GLabel(text);
		incorrect.setLocation(getWidth() * 3 / 4 - incorrect.getWidth() / 2 - 20, getHeight() - WORD_OFFSET);
		incorrect.setColor(Color.green);
		incorrect.setFont(new Font("Ariel-20", Font.BOLD, 14));
		add(incorrect);

		updateHangingMan(lives);
	}

	/** One of the main parts in graphics part of the game. 
	 * 	This block is responsible for ending game. It adds 
	 * 	specified background which depends if player has won
	 * 	or lost.*/
	public void end(int a) {
		if (a == 0) {

			img = new GImage("lose.png");
			img.setBounds(0, 0, getWidth(), getHeight());
			add(img, 0, 0);

			GImage im = new GImage("jocker.png");
			im.setBounds(0, 0, 170, 290);
			add(im, 50, 180);
			dialog(false);
		} else {

			img = new GImage("win.png");
			img.setBounds(0, 0, getWidth(), getHeight());
			add(img, 0, 0);
			GImage im = new GImage("batman.png");
			im.setBounds(0, 0, 170, 270);
			add(im, 200, 280);
			dialog(true);
		}
	}

	/*This mini-method sets background for the game. */
	private void setBackground() {

		img = new GImage("lego.jpg");
		img.setBounds(0, 0, getWidth(), getHeight());
		add(img, 0, 0);

	}

	/* Another mini-method which stands for appearing new label on the screen. */
	private void setGuesses() {

		GLabel text = new GLabel("Your guesses : ");
		text.setLocation(getWidth() * 3 / 4 - text.getWidth() / 2 - 20,
				getHeight() - WORD_OFFSET - 2 * text.getAscent());
		text.setColor(Color.cyan);
		text.setFont(new Font("Ariel-40", Font.BOLD, 15));
		add(text);
	}
	
	/** This method updates hanging man. It gets 
	 * 	integer value of lives left and adds certain 
	 * 	hanging man image.*/
	private void updateHangingMan(int a) {
		if (a < 7)
			remove(img);
		if (a == 7) {
			img = new GImage("1.png");

		} else if (a == 6)
			img = new GImage("2.png");
		else if (a == 5)
			img = new GImage("3.png");
		else if (a == 4)
			img = new GImage("4.png");
		else if (a == 3)
			img = new GImage("5.png");
		else if (a == 2)
			img = new GImage("6.png");
		else if (a == 1)
			img = new GImage("7.png");
		else if (a == 0) {
			img = new GImage("8.png");

		}

		if (a != 8) {
			img.setBounds(0, 0, 350, 400);
			add(img, getWidth() / 2 - 175, 60);
		}
	}

	/* One of mini-methods which is responsible for adding 
	 * labels in the end of the game.*/
	private void dialog(boolean a) {
		GLabel dial;
		if (a) {
			dial = new GLabel("You saved ma' life...", 370, 370);
		}
		else {

			dial = new GLabel("Why so serious?", 160, 370);
		}

		dial.setColor(Color.white);
		dial.setFont(new Font("Ariel-40", Font.BOLD, 30));
		add(dial);
	}


}
