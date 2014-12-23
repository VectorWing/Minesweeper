package com.vectorwing.games.minesweeper.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.vectorwing.games.minesweeper.data.Spritesheet;
import com.vectorwing.games.minesweeper.reference.Filepath;
import com.vectorwing.games.minesweeper.reference.Measures;

/**
 * This panel displays an interface for changing the current game`s settings.
 **/
public class GameSettings extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final int BTN_WIDTH = 38;
	private static final int BTN_HEIGHT = 48;
	
	private Spritesheet			img_btn;
	private ArrayList<String>	img_btn_keys;
	
	private JButton				start_easy;
	private JButton				start_medium;
	private JButton				start_hard;
	private JButton				start_custom;
	private JButton				restart_current;
	private JLabel				spacer;
	
	public GameSettings()
	{
		this.initProperties();
		this.initLayout();
	}

	private void initProperties()
	{
		this.setLayout(new GridBagLayout());
		this.setBackground(new Color(0.4f, 0.3f, 0.1f));
	}
	
	@SuppressWarnings("serial")
	private void initGraphics()
	{
		BufferedImage buffer;
		
		try {
			buffer = ImageIO.read(new File(Filepath.IMG_BTN_SETTINGS));
			System.out.println("Resource '" + Filepath.IMG_TILE + "' loaded succesfully.");
		} catch (IOException e) {
			System.out.println("ERROR: Failed to load a resource!");
			buffer = null;
		}
		
		img_btn_keys = new ArrayList<String>()
		{{
			add("btn_easy");
			add("btn_medium");
			add("btn_hard");
		}};
		
		this.img_btn = new Spritesheet();
		this.img_btn.generateSpritesFromImage(buffer, img_btn_keys, BTN_WIDTH, BTN_HEIGHT, 3, 1);
	}

	private void initLayout()
	{
		this.initGraphics();
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.NORTHWEST;
		
		start_easy = new JButton(this.img_btn.getSprite("btn_easy"));
		start_easy.setPreferredSize(new Dimension(BTN_WIDTH, BTN_HEIGHT));
		start_easy.setBorderPainted(false);
		gbc.insets = new Insets(8, 8, 8, 8);
		gbc.gridx = 0;
		this.add(start_easy, gbc);
		
		start_medium = new JButton(this.img_btn.getSprite("btn_medium"));
		start_medium.setPreferredSize(new Dimension(BTN_WIDTH, BTN_HEIGHT));
		start_medium.setBorderPainted(false);
		gbc.insets = new Insets(8, 0, 8, 8);
		gbc.gridx = 1;
		this.add(start_medium, gbc);
		
		start_hard = new JButton(this.img_btn.getSprite("btn_hard"));
		start_hard.setPreferredSize(new Dimension(BTN_WIDTH, BTN_HEIGHT));
		start_hard.setBorderPainted(false);
		gbc.gridx = 2;
		this.add(start_hard, gbc);
		
		spacer = new JLabel();
		gbc.gridx = 3;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		this.add(spacer, gbc);
		
		start_custom = new JButton("Custom");
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
	
	public void restartActionListener(ActionListener e)
	{
		restart_current.addActionListener(e);
	}

}
