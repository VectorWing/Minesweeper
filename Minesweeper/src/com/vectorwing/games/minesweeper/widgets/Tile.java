package com.vectorwing.games.minesweeper.widgets;

import com.vectorwing.games.minesweeper.enums.TileState;

/**
 * @author VectorWing
 * A clickable tile within a TileGrid. May be empty, which displays a hint when triggered, or a mine, which ends the game.
 */
public class Tile {

	private int 		posx;
	private int 		posy;
	private int 		adjacent_mines;
	private boolean 	mine;
	private TileState 	state;
	
	public Tile(int posx, int posy)
	{
		this.posx = posx;
		this.posy = posy;
	}
	
	public Tile trigger()
	{
		this.state = TileState.TRIGGERED;
		return this;
	}
	
	public void toggle_flag()
	{
		if (this.state != TileState.TRIGGERED)
		{
			switch (this.state)
			{
			case NORMAL:
				this.state = TileState.FLAGGED;
				break;
			case FLAGGED:
				this.state = TileState.QUESTION;
				break;
			case QUESTION:
				this.state = TileState.NORMAL;
				break;
			default:
				break;
			}
		}
	}

}
