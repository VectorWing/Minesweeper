package com.vectorwing.games.minesweeper.gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * A This panel holds forms that allow the player to create new preset or custom games.
 **/
public class GameSettings extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton		start_easy;
	private JButton		start_medium;
	private JButton		start_hard;
	private JButton		start_custom;
	
	private JLabel		spacer;
	
	public GameSettings()
	{
		this.setLayout(new GridBagLayout());
		this.setBackground(new Color(0.4f, 0.3f, 0.1f));
		
		this.initLayout();
	}

	private void initLayout() {

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.NORTHWEST;
		
		start_easy = new JButton("Fácil");
		gbc.insets = new Insets(4, 4, 4, 4);
		gbc.gridx = 0;
		this.add(start_easy, gbc);
		
		start_medium = new JButton("Médio");
		//gbc.insets = new Insets(0, 4, 0, 4);
		gbc.gridx = 1;
		this.add(start_medium, gbc);
		
		start_hard = new JButton("Difícil");
		gbc.gridx = 2;
		this.add(start_hard, gbc);
		
		spacer = new JLabel();
		gbc.gridx = 3;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		this.add(spacer, gbc);
		
		start_custom = new JButton("Personalizado");
		gbc.gridx = 4;
		gbc.weightx = 0;
		this.add(start_custom, gbc);
		
	}
	
	public void easyActionListener(ActionListener e)
	{
		start_easy.addActionListener(e);
	}
	
	public void mediumActionListener(ActionListener e)
	{
		start_medium.addActionListener(e);
	}
	
	public void hardActionListener(ActionListener e)
	{
		start_hard.addActionListener(e);
	}
	
	public void customActionListener(ActionListener e)
	{
		start_custom.addActionListener(e);
	}

}
