package com.vectorwing.games.minesweeper.enums;

/**
 * Determines default parameters for three difficulties.
 */
public enum GameLevel {
	
	EASY(9, 9, 10),
	MEDIUM(16, 16, 40),
	HARD(30, 16, 99),
	CUSTOM;
	
	public int qt_tiles_x;
	public int qt_tiles_y;
	public int qt_mines;
	
	GameLevel()
	{
		this.qt_tiles_x = 0;
		this.qt_tiles_y = 0;
		this.qt_mines = 0;
	}
	
	GameLevel(int amount_x, int amount_y, int amount_mines)
	{
		this.qt_tiles_x = amount_x;
		this.qt_tiles_y = amount_y;
		this.qt_mines = amount_mines;
	}
	
}
