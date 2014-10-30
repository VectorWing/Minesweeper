package com.vectorwing.games.minesweeper.widgets;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

import com.vectorwing.games.minesweeper.enums.GameDifficulty;
import com.vectorwing.games.minesweeper.enums.GameState;

public class TileGrid extends JPanel {
	
	private GameState state;
	
	private int tile_width;
	private int tile_height;
	private int amount_x;
	private int amount_y;
	private int amount_mines;
	
	public TileGrid()
	{
		tile_width = 16;
		tile_height = 16;
		newGame(GameDifficulty.EASY);
		
		this.setPreferredSize(new Dimension(256, 256));
		this.setBackground(Color.cyan);
	}
	
	/**
	 * Sets a fully custom game. The amount of X and Y tiles must be passed, as well as mine quantity.
	 * @param amount_x
	 * @param amount_y
	 * @param amount_mines
	 */
	public void newGame(int amount_x, int amount_y, int amount_mines)
	{
		this.amount_x = amount_x;
		this.amount_y = amount_y;
		this.amount_mines = amount_mines;
		this.startGame();
	}
	
	/**
	 * Sets the next game to use default parameters from a difficulty level defined in GameDifficulty.
	 * @param difficulty
	 */
	public void newGame(GameDifficulty difficulty)
	{
		this.amount_x = difficulty.amount_x;
		this.amount_y = difficulty.amount_y;
		this.amount_mines = difficulty.amount_mines;
		this.startGame();
	}
	
	/**
	 * Begins a new game according to newGame() settings. Sets up a board and resets GameState.
	 */
	private void startGame()
	{
		// TODO: Reset all tiles, lay down mines, lay down hints, make sure none overlap.
		this.state = GameState.PRE_GAME;
				
		// TODO: Begin game.
		this.state = GameState.PLAYING;
	}

}
