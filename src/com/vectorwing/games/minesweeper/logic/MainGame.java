package com.vectorwing.games.minesweeper.logic;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.Timer;

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
	
	public static final int MINE = -1;
	
	// Widgets
	private MainGUI							gui;
	private GameLevel						level;
	
	// Arrays
	private ArrayList<ArrayList<Integer>>	list_hints;
	private ArrayList<ArrayList<Boolean>>	list_mines;	
	
	// Game Data
	private GameState						state;
	private int								qt_tile_x;
	private int								qt_tile_y;
	private int								qt_mines;
	private int								count_flags;
	private Timer							count_time;
	private int								count_triggered;
	private boolean							custom;
	
	private BufferedImage[]	img_tile;
	
	public MainGame(MainGUI gui, GameLevel default_level)
	{
		this.gui = gui;
		this.setNormalGame(default_level);
		
		this.list_mines = new ArrayList<ArrayList<Boolean>>();
		this.list_hints = new ArrayList<ArrayList<Integer>>();
	}
	
	/** Sets one of the default game modes. Those have high-scores. **/
	public void setNormalGame(GameLevel level)
	{
		this.custom = false;
		this.level = level;
		
		this.qt_tile_x = this.level.qt_tiles_x;
		this.qt_tile_y = this.level.qt_tiles_y;
		this.qt_mines = this.level.qt_mines;
	}
	
	/** Sets a game with custom-tailored settings. Those do not hold high-scores. **/
	public void setCustomGame(int qt_tile_x, int qt_tile_y, int qt_mines)
	{
		this.custom = true;
		this.level = GameLevel.CUSTOM;
		
		this.qt_tile_x = (qt_tile_x > Measures.MAX_TILE_X) ? Measures.MAX_TILE_X : qt_tile_x;
		this.qt_tile_y = (qt_tile_y > Measures.MAX_TILE_Y) ? Measures.MAX_TILE_Y : qt_tile_y;
		int max_mines = (int) Math.floor(Measures.MAX_TILE_MINES * (qt_tile_x * qt_tile_y));
		this.qt_mines = (qt_mines > max_mines) ? max_mines : qt_mines;
	}
	
	/** Initiates a new game. Resets all counters, then distributes tiles. **/
	public void begin()
	{
		this.state = GameState.PRE_GAME;
		
		this.list_mines.clear();
		this.list_hints.clear();
		this.count_flags = 0;
		this.count_flags = 0;
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(0, 0, 0, 0);
		
		for (int iter_y = 0; iter_y < this.qt_tile_y; iter_y++)
		{
			ArrayList<Boolean> minerow = new ArrayList<Boolean>(Collections.nCopies(qt_tile_x, false));
			this.list_mines.add(minerow);
			
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
	}
	
	/** Ends the current game under the given state. If not VICTORY or DEFEAT, nothing happens. **/
	public void finish(GameState final_state)
	{
		if (this.state != GameState.PLAYING)
			return;
		
		switch (final_state)
		{
		case VICTORY:
			// TODO: Congratulate player and attempt high-score update.
			if (!this.custom)
			{
				System.out.println("NO HISCORS FOR YA");
			} else {
				System.out.println("YU ARE TOP WINNER");
			}
			break;
		case DEFEAT:
			// TODO: Display all mines and declare game over.
			break;
		default:
			return;
		}
	}
	
	/** Called upon the first click. Distributes mines, as well as the hints around them. **/
	private void deployMines(Point first_click)
	{
		if (this.state != GameState.PRE_GAME)
			return;
		
		for (int i = 0; i < qt_mines; i++)
		{
			int row = randomInt(0, qt_tile_y);
			int col = randomInt(0, qt_tile_x);
			if (!this.list_mines.get(row).get(col))
			{
				if (row == first_click.getY() && col == first_click.getX()) {
					i -= 1;
					continue;
				} else {
					this.list_mines.get(row).set(col, true);
				}
			} else {
				i -= 1;
				continue;
			}
			
			if (row > 0) {
				this.list_hints.get(row-1).set(col, this.list_hints.get(row-1).get(col) + 1);
				if (col > 0) 
					this.list_hints.get(row-1).set(col-1, this.list_hints.get(row-1).get(col-1) + 1);
				if (col < qt_tile_x-1)
					this.list_hints.get(row-1).set(col+1, this.list_hints.get(row-1).get(col+1) + 1);
			}
			if (row < qt_tile_y-1) {
				this.list_hints.get(row+1).set(col, this.list_hints.get(row-1).get(col) + 1);
				if (col > 0)
					this.list_hints.get(row+1).set(col-1, this.list_hints.get(row-1).get(col-1) + 1);
				if (col < qt_tile_x-1)
					this.list_hints.get(row+1).set(col+1, this.list_hints.get(row-1).get(col+1) + 1);
			}
			if (col > 0) {
				this.list_hints.get(row).set(col-1, this.list_hints.get(row-1).get(col-1) + 1);
			}
			if (col < qt_tile_x-1) {
				this.list_hints.get(row).set(col+1, this.list_hints.get(row-1).get(col+1) + 1 );
			}
		}
	}
	
	/** Generates a random integer within given range. **/
	public static int randomInt(int min, int max) {
		int randomNum = new Random().nextInt((max - min)) + min;
	    return randomNum;
	}
	
	/** A MouseAdapter designed to trigger behaviors when a tile is clicked. **/
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
	       			deployMines(tile.getPosition());
	       			state = GameState.PLAYING;
	       		}
	       		if (e.getButton() == MouseEvent.BUTTON1) {
	       			//updateLogic(tile);
	       			//info.setParameters(qt_mines, total_triggered, 0);
	       		} else {
	       			tile.toggleFlag();
	       		}
	       	}
	    }
	}
}
