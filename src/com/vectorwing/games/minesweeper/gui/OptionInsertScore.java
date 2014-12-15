package com.vectorwing.games.minesweeper.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class OptionInsertScore extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private JLabel		lbl_congrats;
	private JLabel		lbl_score;
	private JLabel		lbl_name;
	private JTextField	type_name;
	
	public OptionInsertScore(int score)
	{
		this.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		//gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.insets = new Insets(4, 4, 4, 4);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		
		lbl_congrats = new JLabel("Você venceu! Parabéns!");
		gbc.gridy = 0;
		this.add(lbl_congrats, gbc);
		
		lbl_score = new JLabel("Pontuação: " + score);
		gbc.gridy = 1;
		this.add(lbl_score, gbc);
		
		lbl_name = new JLabel("Insira seu nome:");
		gbc.gridy = 2;
		this.add(lbl_name, gbc);
		
		type_name = new JTextField("AAA");
		gbc.gridy = 3;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		this.add(type_name, gbc);
	}
	
	public String getNome()
	{
		return this.type_name.getText();
	}
	
}
