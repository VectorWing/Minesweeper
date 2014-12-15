package com.vectorwing.games.minesweeper.enums;

/**
 * Determines default parameters for three difficulties.
 */
public enum GameLevel {
	
	EASY(9, 9, 10),
	MEDIUM(16, 16, 40),
	HARD(30, 16, 99),
	CUSTOM;
	
	public int amount_x;
	public int amount_y;
	public int amount_mines;
	
	GameLevel()
	{
		this.amount_x = 0;
		this.amount_y = 0;
		this.amount_mines = 0;
	}
	
	GameLevel(int amount_x, int amount_y, int amount_mines)
	{
		this.amount_x = amount_x;
		this.amount_y = amount_y;
		this.amount_mines = amount_mines;
	}
	
}
