package com.vectorwing.games.minesweeper.gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.vectorwing.games.minesweeper.enums.GameLevel;

/**
 * The main container for Minesweeper's frame.
 */
public class MainGUI extends JPanel {

	private static final long serialVersionUID = 1L;
	
	protected JFrame		parent;
	protected TileGrid		tile_grid;
	protected GameSettings	game_settings;
	protected GameInfo		game_info;
	
	public MainGUI()
	{
		this.initProperties();
		this.initLayout();
	}

	private void initProperties() {
		this.setLayout(new GridBagLayout());
		this.setBackground(new Color(0.5f, 0.4f, 0.2f));
	}

	private void initLayout() {
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(24, 24, 0, 24);
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		
		this.game_info = new GameInfo();
		
		this.tile_grid = new TileGrid(game_info);
		gbc.insets = new Insets(24, 24, 24, 24);
		
		this.game_settings = new GameSettings();
		game_settings.easyActionListener((e)-> {
			/*this.parent = (JFrame) SwingUtilities.getWindowAncestor(this);
			this.tile_grid.newGame(GameLevel.EASY, parent);
			this.game_info.setParameters(GameLevel.EASY);
			this.game_info.setClear();*/
		});
		game_settings.mediumActionListener((e)-> {
			/*this.parent = (JFrame) SwingUtilities.getWindowAncestor(this);
			this.tile_grid.newGame(GameLevel.MEDIUM, parent);
			this.game_info.setParameters(GameLevel.MEDIUM);
			this.game_info.setClear();*/
		});
		game_settings.hardActionListener((e)-> {
			/*this.parent = (JFrame) SwingUtilities.getWindowAncestor(this);
			this.tile_grid.newGame(GameLevel.HARD, parent);
			this.game_info.setParameters(GameLevel.HARD);
			this.game_info.setClear();*/
		});
		game_settings.customActionListener((e)-> {
			this.parent = (JFrame) SwingUtilities.getWindowAncestor(this);
			
			OptionCustomGame custom_game = new OptionCustomGame();
			int result = JOptionPane.showConfirmDialog(this, custom_game, "Jogo Personalizado",
		            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
			
			if (result == 0)
			{
				/*int[] values = custom_game.getCustomValues();
				this.tile_grid.newGame(values[0], values[1], values[2], parent);
				this.game_info.setParameters(0, values[2], 0, (values[0]*values[1])-values[2]);
				this.game_info.setClear();*/
			}
		});		
		
		gbc.gridy = 0;
		gbc.insets = new Insets(24, 24, 0, 24);
		this.add(game_settings, gbc);
		gbc.gridy = 1;
		gbc.insets = new Insets(24, 24, 24, 24);
		this.add(tile_grid, gbc);
		gbc.gridy = 2;
		gbc.insets = new Insets(0, 24, 24, 24);
		this.add(game_info, gbc);
		
		this.parent = (JFrame) SwingUtilities.getWindowAncestor(this);
		/*this.tile_grid.newGame(GameDifficulty.EASY, parent);
		this.game_info.setParameters(GameDifficulty.EASY);
		this.game_info.setClear();*/
	}
	
	public TileGrid getTileGrid()
	{
		return this.tile_grid;
	}
	
	public GameInfo getGameInfo()
	{
		return this.game_info;
	}

}
