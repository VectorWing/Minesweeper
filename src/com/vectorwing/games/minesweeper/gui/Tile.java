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
	private TileState 		state;
	
	public Tile(int posx, int posy, BufferedImage new_tile_frames[])
	{
		this.pos = new Point(posx, posy);
		this.state = TileState.NORMAL;
		this.setBorderPainted(false);
	}
	
	public Tile(int posx, int posy, ImageIcon sprite) {
		this.pos = new Point(posx, posy);
		this.setIcon(sprite);
		this.state = TileState.NORMAL;
		this.setBorderPainted(false);
	}

	public Point getPosition()
	{
		return this.pos;
	}
	
	public TileState getState()
	{
		return this.state;
	}
	
	/** Triggers the tile. Returns a boolean to indicate if it was a mine or not. **/
	public void trigger()
	{
		if (this.state == TileState.NORMAL)
			this.state = TileState.TRIGGERED;
	}
	
	/** Modifies the tile's state between NORMAL, FLAGGED and QUESTION, in this order, unless TRIGGERED. 
	 * @param icons **/
	public int toggleFlag(ImageIcon[] icons)
	{
		switch (this.state)
		{
		case NORMAL:
			this.state = TileState.FLAGGED;
			this.setIcon(icons[1]);
			return 1;
		case FLAGGED:
			this.state = TileState.QUESTION;
			this.setIcon(icons[2]);
			return -1;
		case QUESTION:
			this.state = TileState.NORMAL;
			this.setIcon(icons[0]);			
			return 0;
		default:
			return 0;
		}
	}
	
}
