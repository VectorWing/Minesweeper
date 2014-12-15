package com.vectorwing.games.minesweeper.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import com.vectorwing.games.minesweeper.reference.Measures;

public class OptionCustomGame extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JLabel		lbl_amount_x;
	private JLabel		lbl_amount_y;
	private JLabel		lbl_amount_mines;
	private JSpinner	set_amount_x;
	private JSpinner	set_amount_y;
	private JSpinner	set_amount_mines;
	
	public OptionCustomGame()
	{
		this.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.insets = new Insets(0, 4, 4, 4);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		
		gbc.gridx = 0;
		
		lbl_amount_x = new JLabel("Largura:");
		gbc.gridy = 0;
		this.add(lbl_amount_x, gbc);
		lbl_amount_y = new JLabel("Altura:");
		gbc.gridy = 1;
		this.add(lbl_amount_y, gbc);
		lbl_amount_mines = new JLabel("Minas:");
		gbc.gridy = 2;
		this.add(lbl_amount_mines, gbc);
		
		gbc.gridx = 1;
		
		SpinnerNumberModel amount_x = new SpinnerNumberModel(5, 5, Measures.MAX_TILE_X, 1);
		set_amount_x = new JSpinner(amount_x);
		gbc.gridy = 0;
		this.add(set_amount_x, gbc);
		SpinnerNumberModel amount_y = new SpinnerNumberModel(5, 5, Measures.MAX_TILE_Y, 1);
		set_amount_y = new JSpinner(amount_y);
		gbc.gridy = 1;
		this.add(set_amount_y, gbc);
		SpinnerNumberModel amount_mines = new SpinnerNumberModel(1, 1, Measures.MAX_TILE_MINES, 1);
		set_amount_mines = new JSpinner(amount_mines);
		gbc.gridy = 2;
		this.add(set_amount_mines, gbc);
	}
	
	public int[] getCustomValues()
	{
		int[] values = {
				(int) set_amount_x.getValue(),
				(int) set_amount_y.getValue(),
				(int) set_amount_mines.getValue()
		};
		return values;
	}

}
