package com.vectorwing.games.minesweeper.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.vectorwing.games.minesweeper.enums.GameLevel;

public class GameInfo extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private static final String MSG_MINES = " Minas";
	private static final String MSG_TILES = " Espaços";
	private static final String MSG_VICTORY = "Sucesso!";
	private static final String MSG_DEFEAT = "Fim de Jogo.";
	
	private JLabel show_mines;
	private JLabel show_tiles;
	private JLabel end_game;
	
	private int amount_mines;
	private int amount_tiles;
	private int total_tiles;
	
	private Font bold;
	
	public GameInfo()
	{
		this.setLayout(new GridBagLayout());
		this.setBackground(new Color(0.4f, 0.3f, 0.1f));
		
		bold = new Font("Arial", Font.BOLD, 12);
		
		this.amount_mines = 0;
		this.amount_tiles = 0;
		this.total_tiles = 0;
		
		this.initLayout();
	}

	private void initLayout() {

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1;
		gbc.insets = new Insets(8, 8, 8, 8);

		show_mines = new JLabel(amount_mines + MSG_MINES);
		show_mines.setFont(bold);
		show_mines.setForeground(Color.WHITE);
		gbc.gridx = 0;
		gbc.anchor = GridBagConstraints.WEST;
		this.add(show_mines, gbc);
		
		end_game = new JLabel();
		end_game.setFont(bold);
		gbc.gridx = 1;
		gbc.weightx = 1;
		gbc.anchor = GridBagConstraints.CENTER;
		this.add(end_game, gbc);
		
		show_tiles = new JLabel(amount_tiles +"/"+ total_tiles + MSG_TILES);
		show_tiles.setFont(bold);
		show_tiles.setForeground(Color.WHITE);
		gbc.gridx = 2;
		gbc.weightx = 0;
		gbc.anchor = GridBagConstraints.EAST;
		this.add(show_tiles, gbc);
		
	}

	public void setParameters(int flags, int mines, int tiles, int total) {
		this.amount_mines = mines;
		this.amount_tiles = tiles;
		if (total != 0)
			this.total_tiles = total;
		
		this.show_mines.setText(amount_mines - flags + MSG_MINES);
		this.show_tiles.setText(amount_tiles +"/"+ total_tiles + MSG_TILES);
	}
	
	public void setParameters(GameLevel diff) {
		this.amount_mines = diff.qt_mines;
		this.amount_tiles = 0;
		this.total_tiles = diff.qt_tiles_x*diff.qt_tiles_y-diff.qt_mines;
		
		this.show_mines.setText(amount_mines + MSG_MINES);
		this.show_tiles.setText(amount_tiles +"/"+ total_tiles + MSG_TILES);
	}
	
	public void setVictory()
	{
		this.end_game.setForeground(Color.GREEN);
		this.end_game.setText(MSG_VICTORY);
	}
	
	public void setDefeat()
	{
		this.end_game.setForeground(new Color(1f, 0.3f, 0.3f));
		this.end_game.setText(MSG_DEFEAT);
	}
	
	public void setClear()
	{
		this.end_game.setText("");
	}

}
