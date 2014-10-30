package com.vectorwing.games.minesweeper.widgets;

import com.vectorwing.games.minesweeper.enums.EnumGameDifficulty;
import com.vectorwing.games.minesweeper.enums.EnumGameState;

public class TileGrid {
	
	private EnumGameState state;
	
	private int tile_width;
	private int tile_height;
	private int amount_x;
	private int amount_y;
	
	public TileGrid()
	{
		
	}
	
	/**
	 * Starts a fully custom game. The amount of X and Y tiles must be passed, as well as mine quantity.
	 * @param amount_x
	 * @param amount_y
	 * @param amount_mines
	 */
	public void startGame(int amount_x, int amount_y, int amount_mines)
	{
		// TODO: Initialize an array of Tile objects, using args as X and Y amounts.
	}
	
	public void startGame(EnumGameDifficulty difficulty)
	{
		
	}

}
