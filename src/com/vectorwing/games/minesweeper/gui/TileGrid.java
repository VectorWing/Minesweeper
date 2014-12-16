package com.vectorwing.games.minesweeper.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.vectorwing.games.minesweeper.data.MYSQLScoreDAO;
import com.vectorwing.games.minesweeper.data.Score;
import com.vectorwing.games.minesweeper.data.ScoreDAO;
import com.vectorwing.games.minesweeper.enums.GameLevel;
import com.vectorwing.games.minesweeper.enums.GameState;
import com.vectorwing.games.minesweeper.enums.TileState;
import com.vectorwing.games.minesweeper.gui.Tile.TileFrame;
import com.vectorwing.games.minesweeper.reference.Filepath;
import com.vectorwing.games.minesweeper.reference.Measures;

/**
 * The grid that holds all tiles for the current game.
 **/
public class TileGrid extends JPanel {
	
	private static final long	serialVersionUID	= 1L;
	
	private Timer						timer;
	protected int						score;
	protected GameLevel					level;
	private ArrayList<ArrayList<Tile>>	tile_array;
	private ArrayList<Point>			mine_coords;
	private BufferedImage[]				img_tile;
	private GameState					state;
	private GameInfo					info;
	private int							amount_x;
	private int							amount_y;
	private int							amount_mines;
	private int							total_flagged;
	private int							total_triggered;
	
	public TileGrid(GameInfo info)
	{
		this.tile_array = new ArrayList<ArrayList<Tile>>();
		this.info = info;
		this.initTimer();
		this.resetTimer();
		this.initGraphics();
		this.setLayout(new GridBagLayout());
		this.setBackground(new Color(0.4f, 0.3f, 0.1f));
	}
	
	public ArrayList<ArrayList<Tile>> getTileArray()
	{
		return this.tile_array;
	}
	
	public GameInfo getGameInfo()
	{
		return this.info;
	}
	
	public void initTimer()
	{
		ActionListener action = new ActionListener() {  
			  
            public void actionPerformed(ActionEvent e) {  
                score++; 
                System.out.println(score);
            }  
        };
        this.timer = new Timer(1000, action);
        this.timer.start();
	}
	
	/**
	 * Brings the score back to 0 and resets the timer.
	 */
	public void resetTimer()
	{
        this.score = 0;
		this.timer.restart();
		this.timer.start();
	}
	
	/**
	 * Sets a fully custom game. All settings must be manually passed, and cannot exceed MAX_TILE_X and MAX_TILE_Y.
	 */
	public void newGame(int amount_x, int amount_y, int amount_mines, JFrame frame)
	{
		this.resetGame();
		this.level = null;
		this.amount_mines = amount_mines;		
		this.mine_coords = new ArrayList<Point>();
		
		// Limiting amount of X
		if (amount_x > Measures.MAX_TILE_X)
			this.amount_x = Measures.MAX_TILE_X;
		else
			this.amount_x = amount_x;
		
		// Limiting amount of Y
		if (amount_y > Measures.MAX_TILE_Y)
			this.amount_y = Measures.MAX_TILE_Y;
		else
			this.amount_y = amount_y;
		
		// Limiting mines to 60% of the board's area
		double max_mines = Math.floor(0.60 * (amount_x * amount_y));
		if (this.amount_mines > max_mines)
			this.amount_mines = (int) max_mines;
		
		this.startGame();
		frame.pack();
	}
	
	/**
	 * Sets the next game to use default parameters from a difficulty level defined in GameDifficulty.
	 */
	public void newGame(GameLevel difficulty, JFrame frame)
	{
		this.resetGame();
		this.level = difficulty;	
		this.mine_coords = new ArrayList<Point>();
		
		this.amount_x = difficulty.qt_tiles_x;
		this.amount_y = difficulty.qt_tiles_y;
		this.amount_mines = difficulty.qt_mines;
		
		this.startGame();
		frame.pack();
	}
	
	/**
	 * Resets the present game, if any.
	 */
	public void resetGame()
	{
		this.resetTimer();
		this.removeAll();
		tile_array.clear();
		total_triggered = 0;
	}
	
	/**
	 * Begins a new game according to newGame() settings. Sets up a board and resets GameState.
	 */
	private void startGame()
	{
		this.state = GameState.PRE_GAME;
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(0, 0, 0, 0);
		
		for (int iter_y = 0; iter_y < this.amount_y; iter_y++)
		{
			gbc.gridy = iter_y;
			ArrayList<Tile> tilerow = new ArrayList<Tile>();
			for (int iter_x = 0; iter_x < this.amount_x; iter_x++)
			{
				Tile tile = new Tile(iter_x, iter_y, img_tile);
				tile.setPreferredSize(new Dimension(Measures.TILE_WIDTH, Measures.TILE_HEIGHT));
				tile.addMouseListener(new TileMouseAdapter(tile));
				tilerow.add(tile);
				
				gbc.gridx = iter_x;
				this.add(tile, gbc);
			}
			tile_array.add(tilerow);
		}
	}
	
	/**
	 * Activates the logic for the selected tile. Executes cascade clears, mine triggering etc.
	 */
	public void updateLogic(Tile tile)
	{
		int			x = tile.getPosition().x;
		int			y = tile.getPosition().y;
		TileState	tilestate = tile.getState();
		boolean		mine = tile.getMine();
		
		if ((tile.getMineCount() == 0 && !mine) && tilestate != TileState.TRIGGERED)
		{
			tile.trigger();
			this.countTile();
				
			if (y > 0)
			{
				updateLogic(tile_array.get(y-1).get(x));
				if (x > 0)
					updateLogic(tile_array.get(y-1).get(x-1));
				if (x < amount_x-1)
					updateLogic(tile_array.get(y-1).get(x+1));
			}
			
			if (y < amount_y-1)
			{
				updateLogic(tile_array.get(y+1).get(x));
				if (x > 0)
					updateLogic(tile_array.get(y+1).get(x-1));
				if (x < amount_x-1)
					updateLogic(tile_array.get(y+1).get(x+1));
			}
			
			if (x > 0)
				updateLogic(tile_array.get(y).get(x-1));
				
			if (x < amount_x-1)
				updateLogic(tile_array.get(y).get(x+1));
			
			return;
		}
		else if ((tile.getMineCount() > 0 && !mine) && tilestate != TileState.TRIGGERED)
		{
			tile.trigger();
			this.countTile();
			return;
		}
		else if (mine && tilestate == TileState.NORMAL)
		{
			tile.trigger();
			this.state = GameState.DEFEAT;
			this.timer.stop();
			this.info.setDefeat();
			for (Point p : mine_coords)
			{
				this.tile_array.get(p.y).get(p.x).setIcon(new ImageIcon(img_tile[TileFrame.SPACE_MINE.ordinal()]));
			}
		}
		else
		{
			return;
		}		
	}

	/**
	 * Victory is determined by whether all free tiles were cleared. This takes care of it.
	 */
	private void countTile() {
		this.total_triggered++;
		//System.out.println("Total clicked: " +total_triggered+ "/" + ((amount_x * amount_y) - amount_mines));
		this.checkVictory();
	}

	/**
	 * Spreads amount_mines mines across the current board.
	 * Mines may never overlap each other or land on the first tile clicked.
	 * @param first_click
	 */
	private void initMines(Point first_click) {
		for (int i = 0; i < amount_mines; i++)
		{
			int row = randomInt(0, amount_y);
			int col = randomInt(0, amount_x);
			if (!this.tile_array.get(row).get(col).getMine())
			{
				if (row == first_click.getY() && col == first_click.getX()) {
					i -= 1;
					continue;
				} else {
					this.tile_array.get(row).get(col).setMine(true);
					this.mine_coords.add(new Point(col, row));
				}
			} else {
				i -= 1;
				continue;
			}
			
			if (row > 0) {
				this.tile_array.get(row-1).get(col).addAdjacentMine();
				if (col > 0) 
					this.tile_array.get(row-1).get(col-1).addAdjacentMine();
				if (col < amount_x-1)
					this.tile_array.get(row-1).get(col+1).addAdjacentMine();
			}
			if (row < amount_y-1) {
				this.tile_array.get(row+1).get(col).addAdjacentMine();
				if (col > 0)
					this.tile_array.get(row+1).get(col-1).addAdjacentMine();
				if (col < amount_x-1)
					this.tile_array.get(row+1).get(col+1).addAdjacentMine();
			}
			if (col > 0) {
				this.tile_array.get(row).get(col-1).addAdjacentMine();
			}
			if (col < amount_x-1) {
				this.tile_array.get(row).get(col+1).addAdjacentMine();
			}
		}
		// VIEW MINES DEBUG
		/*for (Point p : mine_coords)
		{
			this.tile_array.get(p.y).get(p.x).setIcon(new ImageIcon(img_tile[TileFrame.SPACE_MINE.ordinal()]));
		}*/
	}
	
	public static int randomInt(int min, int max) {
		int randomNum = new Random().nextInt((max - min)) + min;
	    return randomNum;
	}
	
	/**
	 * Verifies if the game is won.
	 */
	private void checkVictory()
	{
		if (total_triggered + amount_mines == amount_x * amount_y)
		{
			this.state = GameState.VICTORY;
			this.info.setVictory();
			this.timer.stop();
			
			if (level == null)
				return;
			
			OptionInsertScore highscore = new OptionInsertScore(this.score);
			int opt = JOptionPane.showConfirmDialog(this, highscore, "High Score",
		            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
			
			ScoreDAO send_score = new MYSQLScoreDAO();
			Score score = new Score(highscore.getNome(), this.score);
			
			if (opt == 0)
			{
				OptionDisplayScore show = null;
				
				ScoreDAO view_score;
				
				switch (level)
				{
				case EASY:
					send_score.inserirScoreEasy(score);
					view_score = new MYSQLScoreDAO();
					ResultSet easy = view_score.listarScoreEasy();
					try {
						easy.first();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					try {
						show = new OptionDisplayScore(easy, GameLevel.EASY);
					} catch (SQLException e) {
						show = null;
						e.printStackTrace();
					}
					break;
				case MEDIUM:
					send_score.inserirScoreMedium(score);
					view_score = new MYSQLScoreDAO();
					ResultSet medium = view_score.listarScoreMedium();
					try {
						medium.first();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					try {
						show = new OptionDisplayScore(medium, GameLevel.MEDIUM);
					} catch (SQLException e) {
						show = null;
						e.printStackTrace();
					}
					break;
				case HARD:
					send_score.inserirScoreHard(score);
					view_score = new MYSQLScoreDAO();
					ResultSet hard = view_score.listarScoreHard();
					try {
						hard.first();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					try {
						show = new OptionDisplayScore(hard, GameLevel.HARD);
					} catch (SQLException e) {
						show = null;
						e.printStackTrace();
					}
					break;
				default:
					break;
				}
				
				if (show != null)
				{
					String[] options = {"OK"};
					JOptionPane.showOptionDialog(this, show, "High Score",
			            JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
				}
				
			}
			else
			{
				return;
			}
		}
	}
	
	private void initGraphics() {
		
		BufferedImage buffer = null;
		
		final int sprite_cols = 5;
		final int sprite_rows = 3;
		final int sprite_width = 24;
		final int sprite_height = 24;
		
		img_tile = new BufferedImage[sprite_cols * sprite_rows];
		
		try {
			buffer = ImageIO.read(new File(Filepath.IMG_TILE));
			System.out.println("Resource '" + Filepath.IMG_TILE + "' loaded succesfully.");
		} catch (IOException e) {
			System.out.println("ERROR: Failed to load a resource!");
		}
		
		for (int i = 0; i < sprite_rows; i++)
		{
		    for (int j = 0; j < sprite_cols; j++)
		    {
		    	img_tile[(i * sprite_cols) + j] = buffer.getSubimage(j * sprite_width, i * sprite_height, sprite_width, sprite_height);
		    }
		}
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
	       			initMines(tile.getPosition());
	       			state = GameState.PLAYING;
	       		}
	       		if (e.getButton() == MouseEvent.BUTTON1) {
	       			updateLogic(tile);
	       			info.setParameters(total_flagged, amount_mines, total_triggered, 0);
	       		} else {
	       			total_flagged += tile.toggleFlag();
	       			info.setParameters(total_flagged, amount_mines, total_triggered, 0);
	       		}
	       	}
	    }
	}

}
