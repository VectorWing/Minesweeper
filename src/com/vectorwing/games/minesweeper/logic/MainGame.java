package com.vectorwing.games.minesweeper.logic;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import com.vectorwing.games.minesweeper.enums.GameLevel;
import com.vectorwing.games.minesweeper.enums.GameState;
import com.vectorwing.games.minesweeper.enums.TileState;
import com.vectorwing.games.minesweeper.gui.MainGUI;
import com.vectorwing.games.minesweeper.gui.Tile;
import com.vectorwing.games.minesweeper.gui.Tile.TileFrame;
import com.vectorwing.games.minesweeper.reference.Measures;

/**
 * Stores the overall logic for a round of Minesweeper.
 */
public class MainGame {
	
	private MainGUI			gui;
	private GameLevel		level;
	private GameState		state;
	
	private int[][]			tile_content;
	
	private int				qt_tile_x;
	private int				qt_tile_y;
	private int				qt_mines;
	private boolean			custom;
	
	private BufferedImage[]	img_tile;
	
	public MainGame(MainGUI gui, GameLevel default_level)
	{
		this.gui = gui;
		this.setNormalGame(default_level);
	}
	
	public void setNormalGame(GameLevel level)
	{
		this.custom = false;
		this.level = level;
		
		this.qt_tile_x = level.amount_x;
		this.qt_tile_y = level.amount_y;
		this.qt_mines = level.amount_mines;
	}
	
	public void setCustomGame(int qt_tile_x, int qt_tile_y, int qt_mines)
	{
		this.custom = true;
		this.level = GameLevel.CUSTOM;
		
		this.qt_tile_x = (qt_tile_x > Measures.MAX_TILE_X) ? Measures.MAX_TILE_X : qt_tile_x;
		this.qt_tile_y = (qt_tile_y > Measures.MAX_TILE_Y) ? Measures.MAX_TILE_Y : qt_tile_y;
		int max_mines = (int) Math.floor(Measures.MAX_TILE_MINES * (qt_tile_x * qt_tile_y));
		this.qt_mines = (qt_mines > max_mines) ? max_mines : qt_mines;
	}
	
	public void begin()
	{
		this.state = GameState.PRE_GAME;
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(0, 0, 0, 0);
		
		for (int iter_y = 0; iter_y < this.qt_tile_y; iter_y++)
		{
			gbc.gridy = iter_y;
			ArrayList<Tile> tilerow = new ArrayList<Tile>();
			for (int iter_x = 0; iter_x < this.qt_tile_x; iter_x++)
			{
				Tile tile = new Tile(iter_x, iter_y, img_tile);
				tile.setPreferredSize(new Dimension(Measures.TILE_WIDTH, Measures.TILE_HEIGHT));
				tile.addMouseListener(new TileMouseAdapter(tile));
				tilerow.add(tile);
				
				gbc.gridx = iter_x;
				gui.getTileGrid().add(tile, gbc);
			}
			gui.getTileGrid().getTileArray().add(tilerow);
		}
		
		// TODO Distribute mines across TileGrid and increment surrounding hints
	}
	
	class TileMouseAdapter extends MouseAdapter
	{
		private Tile tile;
		
		public TileMouseAdapter(Tile tile)
		{
			this.tile = tile;
		}
		
		public void mousePressed(MouseEvent e) {
			if (tile.getState() == TileState.NORMAL &&
			e.getButton() == MouseEvent.BUTTON1 &&
			state != GameState.DEFEAT &&
			state != GameState.VICTORY) {
				tile.setIcon(new ImageIcon(img_tile[TileFrame.TILE_CLICKING.ordinal()]));
			}
		}
	    public void mouseReleased(MouseEvent e) {
	       	if (state != GameState.DEFEAT && state != GameState.VICTORY)
	       	{
	       		if (state == GameState.PRE_GAME) {
	       			//initMines(tile.getPosition());
	       			state = GameState.PLAYING;
	       		}
	       		if (e.getButton() == MouseEvent.BUTTON1) {
	       			//updateLogic(tile);
	       			//info.setParameters(amount_mines, total_triggered, 0);
	       		} else {
	       			tile.toggleFlag();
	       		}
	       	}
	    }
	}
}
