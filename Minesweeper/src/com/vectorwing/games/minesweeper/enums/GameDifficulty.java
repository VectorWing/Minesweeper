package com.vectorwing.games.minesweeper.enums;

public enum GameDifficulty {
	
	EASY(9, 9, 10),
	MEDIUM(16, 16, 40),
	HARD(16, 30, 99);
	
	public int amount_x;
	public int amount_y;
	public int amount_mines;
	
	GameDifficulty(int amount_x, int amount_y, int amount_mines)
	{
		this.amount_x = amount_x;
		this.amount_y = amount_y;
		this.amount_mines = amount_mines;
	}
	
}
