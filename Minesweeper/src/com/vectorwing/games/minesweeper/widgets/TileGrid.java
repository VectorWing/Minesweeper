package com.vectorwing.games.minesweeper.widgets;

import com.vectorwing.games.minesweeper.enums.GameDifficulty;
import com.vectorwing.games.minesweeper.enums.GameState;

public class TileGrid {
	
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
		startGame(GameDifficulty.EASY);
	}
	
	/**
	 * Starts a fully custom game. The amount of X and Y tiles must be passed, as well as mine quantity.
	 * @param amount_x
	 * @param amount_y
	 * @param amount_mines
	 */
	public void startGame(int amount_x, int amount_y, int amount_mines)
	{
		this.amount_x = amount_x;
		this.amount_y = amount_y;
		this.amount_mines = amount_mines;
		
		// TODO: GameState PREGAME - Reset all tiles, lay down mines, lay down hints, make sure none overlap.
		this.state = GameState.PRE_GAME;
	}
	
	/**
	 * Starts a game using default parameters from a difficulty level defined in EnumGameDifficulty.
	 * @param difficulty
	 */
	public void startGame(GameDifficulty difficulty)
	{
		// TODO: Initialize an array of Tile objects, using args from given Enum.
		this.amount_x = difficulty.amount_x;
		this.amount_y = difficulty.amount_y;
		this.amount_mines = difficulty.amount_mines;
	}

}
