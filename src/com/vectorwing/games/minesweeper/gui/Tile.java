package com.vectorwing.games.minesweeper.gui;

import java.awt.Point;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import com.vectorwing.games.minesweeper.enums.TileState;

/**
 * A clickable tile within a TileGrid. May be empty, which displays a hint when triggered, or a mine, which ends the game.
 **/
public class Tile extends JButton {

	private static final long serialVersionUID = 1L;
	
	private Point			pos;
	private int 			adjacent_mines;		// REMOVE
	private boolean 		mine;				// REMOVE
	private TileState 		state;
	private BufferedImage	tile_frames[];
	
	public enum TileFrame {
		SPACE_EMPTY,
		SPACE_1,
		SPACE_2,
		SPACE_3,
		SPACE_4,
		SPACE_MINE,
		SPACE_5,
		SPACE_6,
		SPACE_7,
		SPACE_8,
		TILE_NORMAL,
		TILE_FLAG,
		TILE_QUESTION,
		TILE_CLICKING,
		ERROR
	}
	
	public Tile(int posx, int posy, BufferedImage new_tile_frames[])
	{
		this.pos = new Point(posx, posy);
		this.tile_frames = new_tile_frames;
		this.adjacent_mines = 0;
		this.state = TileState.NORMAL;
		this.setBorderPainted(false);
		this.updateGraphics();
	}
	
	public Tile(int posx, int posy, ImageIcon sprite) {
		this.pos = new Point(posx, posy);
		this.setIcon(sprite);
		this.adjacent_mines = 0;
		this.state = TileState.NORMAL;
		this.setBorderPainted(false);
		this.updateGraphics();
	}

	public Point getPosition()
	{
		return this.pos;
	}
	
	public TileState getState()
	{
		return this.state;
	}
	
	public boolean getMine()
	{
		return mine;
	}
	
	public int getMineCount()
	{
		return adjacent_mines;
	}
	
	public void setMine(boolean has_mine)
	{
		this.mine = has_mine;
		this.updateGraphics();
	}
	
	/** Increments the hint by one. Cannot exceed 8. **/
	public void addAdjacentMine()
	{
		if (this.adjacent_mines < 8)
			this.adjacent_mines++;
	}
	
	/** Triggers the tile. Returns a boolean to indicate if it was a mine or not. **/
	public boolean trigger()
	{
		if (this.state == TileState.NORMAL)
		{
			this.state = TileState.TRIGGERED;
			this.updateGraphics();
			return this.mine;
		}
		return false;
	}
	
	/** Modifies the tile's state between NORMAL, FLAGGED and QUESTION, in this order, unless TRIGGERED. **/
	public int toggleFlag()
	{
		switch (this.state)
		{
		case NORMAL:
			this.state = TileState.FLAGGED;
			this.updateGraphics();
			return 1;
		case FLAGGED:
			this.state = TileState.QUESTION;
			this.updateGraphics();
			return -1;
		case QUESTION:
			this.state = TileState.NORMAL;
			this.updateGraphics();			
			return 0;
		default:
			return 0;
		}
	}
	
	public void updateGraphics() {
		switch (this.state)
		{
		case NORMAL:
			this.setIcon(new ImageIcon(this.tile_frames[TileFrame.TILE_NORMAL.ordinal()]));
			break;
		case FLAGGED:
			this.setIcon(new ImageIcon(this.tile_frames[TileFrame.TILE_FLAG.ordinal()]));
			break;
		case QUESTION:
			this.setIcon(new ImageIcon(this.tile_frames[TileFrame.TILE_QUESTION.ordinal()]));
			break;
		case TRIGGERED:
			if (this.mine)
			{
				this.setIcon(new ImageIcon(this.tile_frames[TileFrame.SPACE_MINE.ordinal()]));
			} else {
				switch (adjacent_mines)
				{
				case 0:
					this.setIcon(new ImageIcon(this.tile_frames[TileFrame.SPACE_EMPTY.ordinal()]));
					break;
				case 1:
					this.setIcon(new ImageIcon(this.tile_frames[TileFrame.SPACE_1.ordinal()]));
					break;
				case 2:
					this.setIcon(new ImageIcon(this.tile_frames[TileFrame.SPACE_2.ordinal()]));
					break;
				case 3:
					this.setIcon(new ImageIcon(this.tile_frames[TileFrame.SPACE_3.ordinal()]));
					break;
				case 4:
					this.setIcon(new ImageIcon(this.tile_frames[TileFrame.SPACE_4.ordinal()]));
					break;
				case 5:
					this.setIcon(new ImageIcon(this.tile_frames[TileFrame.SPACE_5.ordinal()]));
					break;
				case 6:
					this.setIcon(new ImageIcon(this.tile_frames[TileFrame.SPACE_6.ordinal()]));
					break;
				case 7:
					this.setIcon(new ImageIcon(this.tile_frames[TileFrame.SPACE_7.ordinal()]));
					break;
				case 8:
					this.setIcon(new ImageIcon(this.tile_frames[TileFrame.SPACE_8.ordinal()]));
					break;
				}
			}
			break;
		default:
			break;
		}
	}

}
