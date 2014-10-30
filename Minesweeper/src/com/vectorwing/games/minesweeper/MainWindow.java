package com.vectorwing.games.minesweeper;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.vectorwing.games.minesweeper.widgets.TileGrid;

public class MainWindow extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JButton btn_start_easy;
	private JButton btn_start_medium;
	private JButton btn_start_hard;
	
	private JPanel tile_grid;
	
	public MainWindow()
	{
		this.initProperties();
		this.initLayout();
	}

	private void initProperties() {
		this.setLayout(new GridBagLayout());
	}

	private void initLayout() {
		GridBagConstraints gbc = new GridBagConstraints();
		
		this.btn_start_easy = new JButton("Easy");		
		this.btn_start_medium = new JButton("Medium");		
		this.btn_start_hard = new JButton("Hard");
		this.tile_grid = new TileGrid();
		
		gbc.gridy = 0;
		gbc.insets = new Insets(24, 24, 0, 24);
		this.add(btn_start_easy, gbc);
		this.add(btn_start_medium, gbc);
		this.add(btn_start_hard, gbc);
		gbc.gridy = 1;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.insets = new Insets(24, 24, 24, 24);
		this.add(tile_grid, gbc);
	}

}
